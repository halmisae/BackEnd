package com.halmisae.dto.user;

import com.halmisae.dto.store.MenuDTO;
import com.halmisae.entity.Enum.OrderType;
import com.halmisae.entity.Enum.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private LocalDateTime reserveTime;
    private LocalDateTime visitTime;
    private int useTime;
    private int people;
    private int totalPrice;
    private OrderType orderType;
    private RequestStatus requestStatus;
    private String message;
    private String email;
    private int storeNumber;
    private List<ReserveMenuResponseDTO> reserveMenu;
    private List<MenuDTO> menu;
}
