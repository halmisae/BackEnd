package com.halmisae.service.store;

import com.halmisae.dto.store.MenuDTO;
import com.halmisae.entity.Store.Menu;
import com.halmisae.entity.Store.Store;
import com.halmisae.entity.User.ReserveMenu;
import com.halmisae.repository.store.MenuRepository;
import com.halmisae.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    public Menu createMenu(MenuDTO m) {
        Store store = new Store();
        List<ReserveMenu> reserveMenu = new ArrayList<>();
        Menu menu = new Menu(0, m.getMenuName(), m.getPrice(), m.getIntroduction(), m.getImage(), LocalDateTime.now(), store, reserveMenu);
        storeRepository.save(store);
        return menuRepository.save(menu);
    }

    public List<MenuDTO> readMenuListByStoreNumber() {
        return null;
    }

    public MenuDTO updateMenu() {
        return null;
    }

    public void deleteMenu() {
    }
}
