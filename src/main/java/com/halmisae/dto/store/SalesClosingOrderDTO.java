package com.halmisae.dto.store;

import com.halmisae.entity.Enum.OrderType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SalesClosingOrderDTO {
    private int paymentPrice;
    private LocalDateTime doneDate;
    private OrderType orderType;
    private int orderNumber;
    private int storeNumber;
}
