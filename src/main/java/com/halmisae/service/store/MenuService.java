package com.halmisae.service.store;

import com.halmisae.dto.store.MenuDTO;
import com.halmisae.entity.Store.Menu;
import java.util.List;

public interface MenuService {
    Menu createMenu(MenuDTO m);
    List<MenuDTO> readMenuListByStoreNumber();
    MenuDTO updateMenu();
    void deleteMenu();
}
