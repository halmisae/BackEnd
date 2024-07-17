package com.halmisae.service.store;

import com.halmisae.dto.store.ReservationDcDTO;
import com.halmisae.entity.Store.ReservationDiscount;
import com.halmisae.entity.Store.Store;
import com.halmisae.repository.store.ReservationDiscountRepository;
import com.halmisae.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationDiscountServiceImpl {
    private final ReservationDiscountRepository reservationDiscountRepository;
    private final StoreRepository storeRepository;

    public ReservationDiscount createReservationDc(ReservationDcDTO rd) {
        Store store = storeRepository.findById(rd.getStoreNumber()).get();
        ReservationDiscount reservationDiscount = new ReservationDiscount(rd.getStoreNumber(), rd.getDiscount(), rd.getUnitTime(), rd.getPreorderDiscount(), rd.getUsageTime(), store);
        return reservationDiscountRepository.save(reservationDiscount);
    }

    public ReservationDcDTO readReservationDcByStoreNumber() {
        return null;
    }

    public List<ReservationDcDTO> readReservationDcList() {
        return null;
    }

    public ReservationDcDTO updateReservationDc() {
        return null;
    }

    public void deleteReservationDc() {
    }
}
