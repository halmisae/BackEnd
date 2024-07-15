package com.halmisae.service;

import com.halmisae.dto.store.StoreCreateDTO;
import com.halmisae.dto.store.StoreReadDTO;
import com.halmisae.dto.store.StoreUpdateDTO;
import com.halmisae.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreCreateDTO createStore() {
        return new StoreCreateDTO();
    }

    public StoreReadDTO readStoreById() {
        return new StoreReadDTO();
    }

    public List<StoreReadDTO> readStoreList() {
        return null;
    }

    public StoreUpdateDTO updateStore() {
        return new StoreUpdateDTO();
    }

    public void deleteStore() {

    }
}
