package com.halmisae.dto.store;

import lombok.Data;

@Data
public class StroeReadUserDetailDTO {
    private String storeName;
    private String image;
    private float rating;
    private String closingPrice;
    private String pickupTime;
    private String openTime;
    private String closeTime;
    private String address;
    private Boolean isFavorite;
    private int closingFoodCount;
}
