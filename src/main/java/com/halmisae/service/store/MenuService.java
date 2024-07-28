package com.halmisae.service.store;

import com.halmisae.dto.store.MenuCreateRequestDTO;
import com.halmisae.dto.store.MenuDTO;
import com.halmisae.dto.store.MenuDeleteRequestDTO;
import com.halmisae.dto.store.MenuUpdateDTO;

import java.io.IOException;
import java.util.List;

public interface MenuService {
    // 메뉴관리
    // GET 메뉴 목록 보기
    List<MenuDTO> readMenuList(int storeNumber);
    // POST 메뉴 추가하기
    MenuDTO createMenu(MenuCreateRequestDTO mcr) throws IOException;
    // PATCH 메뉴 수정하기
    MenuDTO updateMenu(MenuUpdateDTO m) throws IOException;
    // DELETE 메뉴 삭제하기
    boolean deleteMenu(int menuNumber);
}
