package com.halmisae.dto.user;

import com.halmisae.entity.User.ReserveMenu;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReservationUpdateDTO {
    private String email;
    private int StoreNumber;
    private int reserveNumber;
    private LocalDateTime visitTime;
    private int people;
    private List<ReserveMenu> reserveMenu;
}