package com.halmisae.dto.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClosingOrderDTO {
    private int quantity;
    private LocalDateTime orderDate;
    private String email;
    private int storeNumber;
}
