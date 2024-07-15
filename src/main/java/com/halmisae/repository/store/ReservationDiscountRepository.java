package com.halmisae.repository.store;

import com.halmisae.entity.Store.ReservationDiscount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationDiscountRepository extends JpaRepository<ReservationDiscount, Integer> {
}
