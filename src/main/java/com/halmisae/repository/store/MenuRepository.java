package com.halmisae.repository.store;

import com.halmisae.entity.Store.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
