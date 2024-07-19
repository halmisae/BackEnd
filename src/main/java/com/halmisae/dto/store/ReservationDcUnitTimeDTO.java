package com.halmisae.dto.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDcUnitTimeDTO {
    private int storeNumber;
    private int usageTime;
    private int unitTime;
    private int discount;
}
