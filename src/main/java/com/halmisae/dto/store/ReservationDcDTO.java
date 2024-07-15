package com.halmisae.dto.store;

import lombok.Data;

@Data
public class ReservationDcDTO {
    private int storeNumber;
    private int discount;
    private int unitTime;
    private int preorderDiscount;
    private int overFee;
}