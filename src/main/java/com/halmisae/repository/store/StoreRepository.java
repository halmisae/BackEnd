package com.halmisae.repository.store;

import com.halmisae.entity.Store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
