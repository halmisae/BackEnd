package com.halmisae.dto.store;

import com.halmisae.dto.user.ReserveMenuResponseDTO;
import com.halmisae.entity.Enum.DoneType;
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
public class ReservationDoneResponseDTO {
    private LocalDateTime reserveTime;
    private LocalDateTime visitTime;
    private int useTime;
    private int people;
    private int totalPrice;
    private OrderType orderType;
    private RequestStatus requestStatus;
    private DoneType doneType;
    private String email;
    private int storeNumber;
    private List<ReserveMenuResponseDTO> reserveMenu;
    private List<MenuDTO> menu;
}
