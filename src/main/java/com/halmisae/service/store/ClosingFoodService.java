package com.halmisae.service.store;

import com.halmisae.dto.store.ClosingFoodDTO;
import com.halmisae.entity.Store.ClosingFood;
import com.halmisae.entity.Store.Store;

import java.time.LocalDateTime;

public interface ClosingFoodService {
    ClosingFoodDTO createClosingFood(ClosingFoodDTO cf);

    ClosingFoodDTO readClosingFood(int storeNumber);

    ClosingFood updateClosingFood(ClosingFoodDTO cf);

    void deleteClosingFood(int storeNumber);
}
