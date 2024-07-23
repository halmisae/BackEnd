package com.halmisae.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreReadDetailDTO {
    private int storeNumber;
    private String storeName;
    private String image;
//    private float rating;
    private int closingPrice;
    private String openTime;
    private String closeTime;
    private String breakStart;
    private String breakEnd;
    private LocalDateTime pickupTime;
    private String address;
//    private Boolean isFavorite;
    private int closingFoodCount;
}
