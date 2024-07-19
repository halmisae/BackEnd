package com.halmisae.dto.user;

import com.halmisae.entity.Enum.OrderType;
import com.halmisae.entity.Enum.RequestStatus;
import com.halmisae.entity.Store.Menu;
import com.halmisae.entity.User.ReserveMenu;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReservationDTO {
    private LocalDateTime reserveTime;
    private LocalDateTime visitTime;
    private int useTime;
    private int people;
    private int totalPrice;
    private OrderType orderType;
    private RequestStatus requestStatus;
    private String email;
    private int storeNumber;
    private List<ReserveMenu> reserveMenu;
    private List<Menu> menu;
}
