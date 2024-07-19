package com.halmisae.service.store;

import com.halmisae.dto.store.ClosingDcAndFoodDTO;
import com.halmisae.dto.store.ReservationDcPreorderDTO;
import com.halmisae.dto.store.ReservationDcUnitTimeDTO;
import com.halmisae.entity.Store.ClosingDiscount;
import com.halmisae.entity.Store.ClosingFood;
import com.halmisae.entity.Store.ReservationDiscount;
import com.halmisae.entity.Store.Store;
import com.halmisae.repository.store.ClosingDiscountRepository;
import com.halmisae.repository.store.ClosingFoodRepository;
import com.halmisae.repository.store.ReservationDiscountRepository;
import com.halmisae.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final ReservationDiscountRepository reservationDiscountRepository;
    private final ClosingDiscountRepository closingDiscountRepository;
    private final ClosingFoodRepository closingFoodRepository;
    private final StoreRepository storeRepository;
    // 할인 및 위약금 관리

    @Override
    // GET 예약 할인 설정 보기
    public ReservationDcPreorderDTO readReservationPreOrder(int storeNumber) {
        ReservationDiscount rd = reservationDiscountRepository.findById(storeNumber).get();
        return new ReservationDcPreorderDTO(rd.getStoreNumber(), rd.getPreorderDiscount());
    }

    @Override
    // POST 예약 할인 설정 (CREATE or UPDATE)
    public ReservationDcPreorderDTO setReservationPreOrder(ReservationDcPreorderDTO rdp) {
        ReservationDiscount rd;
        if (reservationDiscountRepository.existsById(rdp.getStoreNumber())) {
            rd = reservationDiscountRepository.findById(rdp.getStoreNumber()).get();
            rd.setPreorderDiscount(rdp.getPreorderDiscount());
        }
        else {
            Store store = storeRepository.findById(rdp.getStoreNumber()).get();
            rd = new ReservationDiscount(store.getStoreNumber(), 0, 0, rdp.getPreorderDiscount(), 0, store);
        }
        ReservationDiscount srd = reservationDiscountRepository.save(rd);
        return new ReservationDcPreorderDTO(srd.getStoreNumber(), srd.getPreorderDiscount());
    }

    @Override
    // GET 이용시간 할인 설정 보기
    public ReservationDcUnitTimeDTO readReservationUnit(int storeNumber) {
        ReservationDiscount rd = reservationDiscountRepository.findById(storeNumber).get();
        return new ReservationDcUnitTimeDTO(rd.getStoreNumber(), rd.getUsageTime(), rd.getUnitTime(), rd.getDiscount());
    }

    @Override
    // POST 이용시간 할인 설정 (CREATE or UPDATE)
    public ReservationDcUnitTimeDTO setReservationUnit(ReservationDcUnitTimeDTO rdu) {
        ReservationDiscount rd;
        if (reservationDiscountRepository.existsById(rdu.getStoreNumber())) {
            rd = reservationDiscountRepository.findById(rdu.getStoreNumber()).get();
            rd.setUsageTime(rdu.getUsageTime());
            rd.setUnitTime(rdu.getUnitTime());
            rd.setDiscount(rdu.getDiscount());
        }
        else {
            Store store = storeRepository.findById(rdu.getStoreNumber()).get();
            rd = new ReservationDiscount(store.getStoreNumber(), rdu.getDiscount(), rdu.getUnitTime(), 0, rdu.getUsageTime(), store);
        }
        ReservationDiscount srd = reservationDiscountRepository.save(rd);
        return new ReservationDcUnitTimeDTO(srd.getStoreNumber(), srd.getUsageTime(), srd.getUnitTime(), srd.getDiscount());
    }

    @Override
    // GET 마감할인상품 가격 설정 보기
    public ClosingDcAndFoodDTO readClosingDiscountAndFood(int storeNumber) {
        ClosingDiscount cd = closingDiscountRepository.findById(storeNumber).get();
        ClosingFood cf = closingFoodRepository.findById(storeNumber).get();
        return new ClosingDcAndFoodDTO(cd.getStoreNumber(), cd.getClosingPrice(), cf.getQuantity(), cd.getPickupTime());
    }

    @Override
    // POST 마감할인상품 가격 설정 (CREATE or UPDATE)
    public ClosingDcAndFoodDTO setClosingDiscountAndFood(ClosingDcAndFoodDTO cdf) {
        ClosingDiscount cd;
        ClosingFood cf;
        if (closingDiscountRepository.existsById(cdf.getStoreNumber())) {
            cd = closingDiscountRepository.findById(cdf.getStoreNumber()).get();
            cd.setClosingPrice(cdf.getClosingPrice());
            cd.setPickupTime(cdf.getPickupTime());
        } else {
            Store store = storeRepository.findById(cdf.getStoreNumber()).get();
            cd = new ClosingDiscount(store.getStoreNumber(), cdf.getClosingPrice(), cdf.getPickupTime(), store);
        }
        if (closingFoodRepository.existsById(cdf.getStoreNumber())) {
            cf = closingFoodRepository.findById(cdf.getStoreNumber()).get();
            cf.setQuantity(cdf.getQuantity());
            cf.setRegistTime(LocalDateTime.now());
        } else {
            Store store = storeRepository.findById(cdf.getStoreNumber()).get();
            cf = new ClosingFood(store.getStoreNumber(), cdf.getQuantity(), LocalDateTime.now(), store);
        }
        ClosingDiscount scd = closingDiscountRepository.save(cd);
        ClosingFood scf = closingFoodRepository.save(cf);
        return new ClosingDcAndFoodDTO(scd.getStoreNumber(), scd.getClosingPrice(), scf.getQuantity(), scd.getPickupTime());
    }
}
