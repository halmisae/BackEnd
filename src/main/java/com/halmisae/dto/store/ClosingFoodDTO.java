package com.halmisae.dto.store;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ClosingFoodDTO {
    private int storeNumber;
    private int quantity;
    private LocalDateTime registTime;
}
