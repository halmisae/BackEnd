package com.halmisae.repository.store;

import com.halmisae.entity.Store.Sales;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SalesRepository extends JpaRepository<Sales, Integer> {
    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE Sales AUTO_INCREMENT = :newai", nativeQuery = true)
    void resetAutoIncrement(@Param("newai") int autoIncrementValue);

    @Query(value = "SELECT COALESCE(MAX(s.salesNumber), 0) FROM Sales s")
    int findMaxSalesNumber();
}