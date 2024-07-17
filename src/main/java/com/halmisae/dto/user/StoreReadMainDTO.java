package com.halmisae.dto.user;

import lombok.Data;

@Data
public class StoreReadMainDTO {
    private int storeNumber;
    private String storeName;
    private String image;
    private float rating;
    private String closingPrice;
    private Boolean isFavorite;
    private int closingFoodCount;
}
