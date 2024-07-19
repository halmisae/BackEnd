package com.halmisae.dto.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuCreateRequestDTO {
    private int storeNumber;
    private String menuName;
    private int price;
    private String introduction;
    private String image;
}
