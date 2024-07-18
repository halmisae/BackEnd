package com.halmisae.repository.store;

import com.halmisae.dto.user.StoreReadMainDTO;
import com.halmisae.dto.user.StoreReadMainRequestDTO;
import com.halmisae.entity.Store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    @Query("SELECT new com.halmisae.dto.user.StoreReadMainDTO(s.storeNumber, s.storeName, s.image, s.averageRating, s.closingDiscount.closingPrice, " +
            "CASE WHEN (SELECT COUNT(f) FROM Favorite f WHERE f.store.storeNumber = :storeNumber AND f.user.email = :email) > 0 THEN true ELSE false END, " +
            "s.closingFood.quantity) " +
            "FROM Store s " +
            "WHERE s.storeNumber = :storeNumber")
    List<StoreReadMainDTO> findStoreMain(@Param("storeNumber") Integer storeNumber, @Param("email") String email);
}
