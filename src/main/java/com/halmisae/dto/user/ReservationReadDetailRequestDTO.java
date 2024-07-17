package com.halmisae.dto.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationReadDetailRequestDTO {
    private int storeNumber;
    private LocalDateTime day;
}
