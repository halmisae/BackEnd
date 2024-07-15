package com.halmisae.repository.user;

import com.halmisae.entity.User.ReserveMenu;
import com.halmisae.entity.User.ReserveMenuID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveMenuRepository extends JpaRepository<ReserveMenu, ReserveMenuID> {
}
