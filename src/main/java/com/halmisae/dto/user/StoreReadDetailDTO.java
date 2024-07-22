package com.halmisae.dto.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoreReadDetailDTO {
    private int storeNumber;
    private String storeName;
    private String image;
    private float rating;
    private String closingPrice;
    private String openTime;
    private String closeTime;
    private String breakStart;
    private String breakEnd;
    private LocalDateTime pickupTime;
    private String address;
    private Boolean isFavorite;
    private int closingFoodCount;
}
