package com.halmisae.service.store;

import com.halmisae.dto.store.ClosingFoodDTO;
import com.halmisae.entity.Store.ClosingFood;
import com.halmisae.entity.Store.Store;
import com.halmisae.repository.store.ClosingFoodRepository;
import com.halmisae.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class ClosingFoodServiceImpl {
    private ClosingFoodRepository closingFoodRepository;
    private StoreRepository storeRepository;

    public ClosingFoodDTO createClosingFood(ClosingFoodDTO cf) {
        Store store = storeRepository.findById(cf.getStoreNumber()).get();
        ClosingFood closingFood = new ClosingFood(cf.getStoreNumber(), cf.getQuantity(), LocalDateTime.now(), store);
        closingFoodRepository.save(closingFood);
        return closingFoodRepository.findDTOById(closingFood.getStoreNumber());
    }

    public ClosingFoodDTO readClosingFood(int storeNumber) {
        ClosingFood cf = closingFoodRepository.findById(storeNumber).get();
        return new ClosingFoodDTO(cf.getStoreNumber(), cf.getQuantity(), cf.getRegistTime());
    }

    public ClosingFood updateClosingFood(ClosingFoodDTO cf) {
        return null;
    }

    public void deleteClosingFood(int storeNumber) {

    }
}
