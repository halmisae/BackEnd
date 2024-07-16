package com.halmisae.dto.store;

import com.halmisae.entity.Enum.DoneType;
import com.halmisae.entity.Enum.OrderType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SalesReservationDTO {
    private int paymentPrice;
    private LocalDateTime doneDate;
    private OrderType orderType;
    private DoneType doneType;
    private int reserveNumber;
    private int storeNumber;
}
