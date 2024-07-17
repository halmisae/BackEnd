package com.halmisae.dto.user;

import com.halmisae.entity.User.ReserveMenu;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationRequestDTO {
    private String email;
    private int storeNumber;
    private LocalDateTime visitTime;
    private int useTime;
    private int people;
    private int totalPrice;
    private List<ReserveMenu> reserveMenu;
}
