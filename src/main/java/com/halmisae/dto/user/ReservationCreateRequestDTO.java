package com.halmisae.dto.user;

import com.halmisae.entity.Enum.OrderType;
import com.halmisae.entity.User.ReserveMenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationCreateRequestDTO {
    private String email;
    private int storeNumber;
    private LocalDateTime visitTime;
    private int useTime;
    private int people;
    private int totalPrice;
    private OrderType orderType;
    private List<ReserveMenuCreateDTO> reserveMenu;
}