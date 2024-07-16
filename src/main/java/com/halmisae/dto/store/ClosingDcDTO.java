package com.halmisae.dto.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClosingDcDTO {
    private int storeNumber;
    private int closingPrice;
    private LocalDateTime pickupTime;
}
