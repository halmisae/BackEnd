package com.halmisae.dto.user;

import com.halmisae.entity.Enum.RequestStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationDTO {
    private LocalDateTime reserveTime;
    private LocalDateTime visitTime;
    private int useTime;
    private int people;
    private int totalPrice;
    private RequestStatus requestStatus;
    private String email;
    private int storeNumber;
}
