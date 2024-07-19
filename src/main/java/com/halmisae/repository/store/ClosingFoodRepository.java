package com.halmisae.repository.store;

import com.halmisae.dto.store.ClosingFoodDTO;
import com.halmisae.entity.Store.ClosingFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClosingFoodRepository extends JpaRepository<ClosingFood, Integer> {
//    @Query("SELECT new com.halmisae.dto.store.ClosingFoodDTO(cf.storeNumber, cf.quantity, cf.registTime) FROM ClosingFood cf WHERE cf.storeNumber = :ocf")
//    ClosingFoodDTO findDTOById(@Param("ocf") int ocf);
}
