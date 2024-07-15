package com.halmisae.dto.store;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClosingFoodDTO {
    private int storeNumber;
    private int quantity;
    private LocalDateTime registTime;
}
