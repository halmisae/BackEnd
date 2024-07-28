package com.halmisae.service.store;

import com.halmisae.dto.store.MenuCreateRequestDTO;
import com.halmisae.dto.store.MenuDTO;
import com.halmisae.dto.store.MenuUpdateDTO;
import com.halmisae.entity.Store.Menu;
import com.halmisae.entity.Store.Store;
import com.halmisae.repository.store.MenuRepository;
import com.halmisae.repository.store.StoreRepository;
import com.halmisae.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;
    private final S3Uploader s3Uploader;

    @Override
    // GET 메뉴 목록 보기
    public List<MenuDTO> readMenuList(int storeNumber) {
        return menuRepository.findAllByStoreNumber(storeNumber);
    }

    @Override
    // POST 메뉴 추가하기
    public MenuDTO createMenu(MenuCreateRequestDTO mcr) throws IOException {
        Store store = storeRepository.findById(mcr.getStoreNumber()).get();
        String image = s3Uploader.upload(mcr.getImage(), "menuImage");
        Menu menu = new Menu(0, mcr.getMenuName(), mcr.getPrice(), mcr.getIntroduction(), image, 0 ,LocalDateTime.now(), store, new ArrayList<>());
        Menu m = menuRepository.save(menu);
        return new MenuDTO(m.getMenuNumber(), m.getMenuName(), m.getPrice(), m.getIntroduction(), m.getImage(), m.getStore().getStoreNumber());
    }

    @Override
    // PATCH 메뉴 수정하기
    public MenuDTO updateMenu(MenuUpdateDTO m) throws IOException{
        Menu menu = menuRepository.findById(m.getMenuNumber()).get();
        menu.setMenuName(m.getMenuName());
        menu.setPrice(m.getPrice());
        menu.setIntroduction(m.getIntroduction());
        String updatedImage = s3Uploader.updateFile(m.getImage(), menu.getImage(), "menuImage");
        menu.setImage(updatedImage);
        Menu sm = menuRepository.save(menu);
        return new MenuDTO(sm.getMenuNumber(), sm.getMenuName(), sm.getPrice(), sm.getIntroduction(), sm.getImage(), sm.getStore().getStoreNumber());
    }

    @Override
    // DELETE 메뉴 삭제하기
    public boolean deleteMenu(int menuNumber) {
        Menu menu = menuRepository.findById(menuNumber).get();
        s3Uploader.deleteFile(menu.getImage());
        menuRepository.delete(menu);
        return !(menuRepository.existsById(menuNumber));
    }
}
