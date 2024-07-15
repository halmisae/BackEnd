package com.halmisae.dto.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RatingClosingOrderDTO {
    private int rating;
    private String email;
    private int storeNumber;
    private int orderNumber;
}
