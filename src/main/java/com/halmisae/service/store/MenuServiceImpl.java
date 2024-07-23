package com.halmisae.service.store;

import com.halmisae.dto.store.MenuCreateRequestDTO;
import com.halmisae.dto.store.MenuDTO;
import com.halmisae.entity.Store.Menu;
import com.halmisae.entity.Store.Store;
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
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    @Override
    public List<MenuDTO> readMenuList(int storeNumber) {
        return menuRepository.findAllByStoreNumber(storeNumber);
    }

    @Override
    public MenuDTO createMenu(MenuCreateRequestDTO mcr) {
        Store store = storeRepository.findById(mcr.getStoreNumber()).get();
        Menu menu = new Menu(0, mcr.getMenuName(), mcr.getPrice(), mcr.getIntroduction(), mcr.getImage(), 0 ,LocalDateTime.now(), store, new ArrayList<>());
        Menu m = menuRepository.save(menu);
        return new MenuDTO(m.getMenuNumber(), m.getMenuName(), m.getPrice(), m.getIntroduction(), m.getImage(), m.getStore().getStoreNumber());
    }

    @Override
    public MenuDTO updateMenu(MenuDTO m) {
        Menu menu = menuRepository.findById(m.getMenuNumber()).get();
        menu.setMenuName(m.getMenuName());
        menu.setPrice(m.getPrice());
        menu.setIntroduction(m.getIntroduction());
        menu.setImage(m.getImage());
        Menu sm = menuRepository.save(menu);
        return new MenuDTO(sm.getMenuNumber(), sm.getMenuName(), sm.getPrice(), sm.getIntroduction(), sm.getImage(), sm.getStore().getStoreNumber());
    }

    @Override
    public boolean deleteMenu(int menuNumber) {
        Menu menu = menuRepository.findById(menuNumber).get();
        menuRepository.delete(menu);
        return !(menuRepository.existsById(menuNumber));
    }
}
