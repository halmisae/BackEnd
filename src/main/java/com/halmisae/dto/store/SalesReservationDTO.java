package com.halmisae.dto.store;

import com.halmisae.entity.Enum.OrderType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SalesReservationDTO {
    private int paymentPrice;
    private LocalDateTime doneDate;
    private OrderType orderType;
    private int reserveNumber;
    private int storeNumber;
}
