package com.halmisae.dto.store;

import lombok.Data;

@Data
public class StoreReadUserMainDTO {
    private String storeName;
    private String image;
    private float rating;
    private String closingPrice;
    private Boolean isFavorite;
    private int closingFoodCount;
}
