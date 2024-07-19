package com.halmisae.repository.store;

import com.halmisae.dto.store.MenuDTO;
import com.halmisae.entity.Store.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
    @Query("SELECT new com.halmisae.dto.store.MenuDTO(m.menuNumber, m.menuName, m.price, m.introduction, m.image, m.store.storeNumber) " +
            "FROM Menu m " +
            "WHERE m.store.storeNumber = :storeNumber ")
    List<MenuDTO> findAllByStoreNumber(@Param("storeNumber") int storeNumber);
}
