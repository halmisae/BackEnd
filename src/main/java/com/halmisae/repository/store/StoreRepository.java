package com.halmisae.repository.store;

import com.halmisae.dto.user.StoreReadDetailDTO;
import com.halmisae.dto.user.StoreReadMainDTO;
import com.halmisae.dto.user.StoreReadMainRequestDTO;
import com.halmisae.entity.Store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    @Query("SELECT new com.halmisae.dto.user.StoreReadMainDTO(s.storeNumber, s.storeName, s.image, s.closingDiscount.closingPrice, s.closingFood.quantity) " +
            "FROM Store s")
    List<StoreReadMainDTO> findStoreMain();

    @Query("SELECT new com.halmisae.dto.user.StoreReadMainDTO(s.storeNumber, s.storeName, s.image, s.closingDiscount.closingPrice, s.closingFood.quantity) " +
            "FROM Store s " +
            "WHERE s.closingFood.quantity > 0")
    List<StoreReadMainDTO> findClosingOrderStore();

    List<Store> findAllByStoreNameContaining(String keyword);

    @Query("SELECT new com.halmisae.dto.user.StoreReadDetailDTO(s.storeNumber, s.storeName, s.image, s.closingDiscount.closingPrice, s.weekdayOpen, s.weekdayClose, s.breakStart, s.breakEnd, s.closingDiscount.pickupTime, s.address, s.closingFood.quantity) " +
            "FROM Store s " +
            "WHERE s.storeNumber = :storeNumber")
    StoreReadDetailDTO findStoreWeekdayDetail(@Param("storeNumber") int storeNumber);

    @Query("SELECT new com.halmisae.dto.user.StoreReadDetailDTO(s.storeNumber, s.storeName, s.image, s.closingDiscount.closingPrice, s.weekendOpen, s.weekendClose, s.breakStart, s.breakEnd, s.closingDiscount.pickupTime, s.address, s.closingFood.quantity) " +
            "FROM Store s " +
            "WHERE s.storeNumber = :storeNumber")
    StoreReadDetailDTO findStoreWeekendDetail(@Param("storeNumber") int storeNumber);
}
