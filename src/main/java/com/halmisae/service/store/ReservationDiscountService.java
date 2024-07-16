package com.halmisae.service.store;

import com.halmisae.dto.store.ReservationDcDTO;
import com.halmisae.entity.Store.ReservationDiscount;
import com.halmisae.entity.Store.Store;

import java.util.List;

public interface ReservationDiscountService {
    ReservationDiscount createReservationDc(ReservationDcDTO rd);
    ReservationDcDTO readReservationDcByStoreNumber();
    List<ReservationDcDTO> readReservationDcList();
    ReservationDcDTO updateReservationDc();
    void deleteReservationDc();
}
