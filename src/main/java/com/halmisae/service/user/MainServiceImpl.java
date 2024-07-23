package com.halmisae.service.user;

import com.halmisae.dto.user.*;
import com.halmisae.entity.Enum.DoneType;
import com.halmisae.entity.Enum.RequestStatus;
import com.halmisae.entity.Store.ClosingFood;
import com.halmisae.entity.Store.Store;
import com.halmisae.entity.User.ClosingOrder;
import com.halmisae.repository.store.ClosingFoodRepository;
import com.halmisae.repository.store.StoreRepository;
import com.halmisae.repository.user.ClosingOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MainServiceImpl implements MainService{
    private final StoreRepository storeRepository;
    private final ClosingOrderRepository closingOrderRepository;
    private final ClosingFoodRepository closingFoodRepository;


    @Override
    // GET 메인페이지(전체)
    public List<StoreReadMainDTO> readAllStore() {
        return storeRepository.findStoreMain();
    }

    @Override
    // GET 메인페이지(마감할인)
    public List<StoreReadMainDTO> readClosingDiscountStore() {
        return storeRepository.findClosingOrderStore();
    }

    @Override
    // GET 검색 결과 페이지
    public List<StoreReadMainDTO> searchStore(String keyword) {
        List<Store> searched = storeRepository.findAllByStoreNameContaining(keyword);
        List<StoreReadMainDTO> srmList = new ArrayList<>();
        for (Store s : searched) {
            StoreReadMainDTO srm = new StoreReadMainDTO(s.getStoreNumber(), s.getStoreName(), s.getImage(), s.getClosingDiscount().getClosingPrice(), s.getClosingFood().getQuantity());
            srmList.add(srm);
        }
        return srmList;
    }

    @Override
    // GET 가게 상세 정보 보기
    public StoreReadDetailDTO readStoreDetail(int storeNumber) {
        int today = LocalDate.now().getDayOfWeek().getValue();
        if (today < 6) return storeRepository.findStoreWeekdayDetail(storeNumber);
        else return storeRepository.findStoreWeekendDetail(storeNumber);
    }

    @Override
    // POST 마감할인상품 주문
    public ClosingOrderDTO createClosingOrder(ClosingOrderRequestDTO cor) {
        Store store = storeRepository.findById(cor.getStoreNumber()).get();
        // 수량 초과 주문 거절
        if (cor.getQuantity() > store.getClosingFood().getQuantity())
            return new ClosingOrderDTO(0, cor.getQuantity(), cor.getTotalPrice(), LocalDateTime.now(), RequestStatus.REJECT, null, "수량을 초과하였습니다.", null, cor.getStoreNumber());
        ClosingFood closingFood = closingFoodRepository.findById(cor.getStoreNumber()).get();
        closingFood.setQuantity(closingFood.getQuantity() - cor.getQuantity());
        closingFoodRepository.save(closingFood);
        ClosingOrder co = new ClosingOrder(0, cor.getQuantity(), cor.getTotalPrice(), LocalDateTime.now(), RequestStatus.NOT_YET, null, store, null, null);
        ClosingOrder sco = closingOrderRepository.save(co);
        return new ClosingOrderDTO(sco.getOrderNumber(), sco.getQuantity(), sco.getTotalPrice(), sco.getOrderDate(), sco.getRequestStatus(), DoneType.NOT_YET, null, null, sco.getStore().getStoreNumber());
    }
}
