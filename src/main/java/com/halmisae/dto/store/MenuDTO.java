package com.halmisae.dto.store;

import lombok.Data;

@Data
public class MenuDTO {
    private String menuName;
    private int price;
    private String introduction;
    private String image;
    private int storeNumber;
}
