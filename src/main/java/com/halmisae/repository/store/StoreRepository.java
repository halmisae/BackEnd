package com.halmisae.repository.store;

import com.halmisae.dto.store.StoreReadUserMainDTO;
import com.halmisae.entity.Store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    @Query("SELECT new com.halmisae.dto.store.StoreReadUserMainDTO(s.storeName, s.image, s.rating, s.closingPrice, s.isFavorite, , u.nickname, u.phone, u.penaltyPoint, u.status, u.penaltyNumber, u.registDate) FROM Store s")
    List<StoreReadUserMainDTO> findStoreMain();
}
