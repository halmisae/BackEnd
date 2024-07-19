package com.halmisae.dto.user;

import com.halmisae.entity.Enum.OrderType;
import com.halmisae.entity.Enum.RequestStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationReadDTO {
    private int storeNumber;
    private String email;
    private OrderType orderType;
    private String storeName;
    private LocalDateTime visitTime;
    private int useTime;
    private int people;
    private int totalPrice;
    private RequestStatus requestStatus;
    private LocalDateTime pickupTime;
    private int orderNumber;
    private int reserveNumber;
}
