package com.halmisae.repository.store;

import com.halmisae.entity.Store.NoShowFood;
import com.halmisae.entity.Store.NoShowFoodID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoShowFoodRepository extends JpaRepository<NoShowFood, NoShowFoodID> {
}
