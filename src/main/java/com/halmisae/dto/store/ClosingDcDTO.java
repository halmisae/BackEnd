package com.halmisae.dto.store;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClosingDcDTO {
    private int storeNumber;
    private int closingPrice;
    private LocalDateTime pickupTime;
}
