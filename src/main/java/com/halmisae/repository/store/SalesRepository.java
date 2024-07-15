package com.halmisae.repository.store;

import com.halmisae.entity.Store.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales, Integer> {
}
