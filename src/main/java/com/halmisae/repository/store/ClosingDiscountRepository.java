package com.halmisae.repository.store;

import com.halmisae.entity.Store.ClosingDiscount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClosingDiscountRepository extends JpaRepository<ClosingDiscount, Integer> {
}
