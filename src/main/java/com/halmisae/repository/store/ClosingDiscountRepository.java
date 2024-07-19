package com.halmisae.repository.store;

import com.halmisae.dto.store.ClosingDcAndFoodDTO;
import com.halmisae.entity.Store.ClosingDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClosingDiscountRepository extends JpaRepository<ClosingDiscount, Integer> {
    @Query("SELECT new com.halmisae.dto.store.ClosingDcDTO(cd.storeNumber, cd.closingPrice, cd.pickupTime) FROM ClosingDiscount cd WHERE cd.storeNumber = :ocd")
    ClosingDcAndFoodDTO findDTOById(@Param("ocd") int ocd);
}
