package com.halmisae.repository.user;

import com.halmisae.dto.user.ReserveMenuCreateDTO;
import com.halmisae.entity.User.ReserveMenu;
import com.halmisae.entity.User.ReserveMenuID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReserveMenuRepository extends JpaRepository<ReserveMenu, ReserveMenuID> {
    @Query("SELECT new com.halmisae.dto.user.ReserveMenuCreateDTO(rm.menu.menuNumber, rm.quantity) FROM ReserveMenu rm WHERE rm.id.reserveNumber = :reserveNumber")
    List<ReserveMenuCreateDTO> findAllByReserveNumber(@Param("reserveNumber") int reserveNumber);
}
