package com.halmisae.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreReadMainDTO {
    private int storeNumber;
    private String storeName;
    private String image;
    private float averageRating;
    private int closingPrice;
    private Boolean isFavorite;
    private int closingFoodCount;
}
