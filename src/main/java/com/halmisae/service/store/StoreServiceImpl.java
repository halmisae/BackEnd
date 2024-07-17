package com.halmisae.service.store;

import com.halmisae.dto.store.*;
import com.halmisae.entity.Enum.Status;
import com.halmisae.entity.Store.*;
import com.halmisae.repository.store.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService{
    private final StoreRepository storeRepository;
    private final SalesRepository salesRepository;
    private final NoShowFoodRepository noShowFoodRepository;
    private final MenuRepository menuRepository;
    private final StoreHolidayRepository storeHolidayRepository;
    private final ReservationDiscountRepository reservationDiscountRepository;
    private final ClosingDiscountRepository closingDiscountRepository;
    private final ClosingFoodRepository closingFoodRepository;

    List<Store> all = storeRepository.findAll();

    public Store createStore(StoreCreateDTO sc) {
        ReservationDiscount reservationDiscount = new ReservationDiscount();
        ClosingDiscount closingDiscount = new ClosingDiscount();
        ClosingFood closingFood = new ClosingFood();
        Store store = new Store(0, sc.getId(), sc.getPassword(), sc.getName(), sc.getPhone(), sc.getBusinessNumber(), sc.getEmail(), sc.getStoreName(), sc.getAddress(), sc.getStorePhone(), sc.getWeekdayOpen(), sc.getWeekdayClose(), sc.getWeekendOpen(), sc.getWeekendClose(), sc.getBreakStart(), sc.getBreakEnd(), LocalDateTime.now(), Status.AVAILABLE, 0, new ArrayList<>(), new ArrayList<>(), reservationDiscount, new ArrayList<>(), closingDiscount, closingFood, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        reservationDiscountRepository.save(reservationDiscount);
        closingDiscountRepository.save(closingDiscount);
        closingFoodRepository.save(closingFood);
        return storeRepository.save(store);
    }

    @Override
    public Boolean passwordCheck(StorePasswordCheckDTO spc) {
        return null;
    }

    @Override
    public StoreReadOwnerDTO readStoreOwner(int storeNumber) {
        return null;
    }

    @Override
    public StoreUpdateOwnerDTO updateStoreOwner(StoreUpdateOwnerDTO uo) {
        return null;
    }

    @Override
    public StoreDTO readStore(int storeNumber) {
        return null;
    }

    @Override
    public StoreDTO updateStore(StoreDTO us) {
        return null;
    }

    @Override
    public Boolean deleteStore(int storeNumber) {
        return null;
    }
}
