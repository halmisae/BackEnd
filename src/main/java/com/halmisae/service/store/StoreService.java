package com.halmisae.service.store;

import com.halmisae.dto.store.StoreCreateDTO;
import com.halmisae.dto.store.StoreReadDTO;
import com.halmisae.dto.store.StoreReadUserMainDTO;
import com.halmisae.dto.store.StoreUpdateDTO;
import com.halmisae.entity.Store.Store;
import java.util.List;

public interface StoreService {
    Store createStore(StoreCreateDTO sc);
    StoreReadUserMainDTO readStore(String userAddress);
    List<StoreReadDTO> readStoreList();
    StoreUpdateDTO updateStore();
    void deleteStore();
}
