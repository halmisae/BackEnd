package com.halmisae.dto.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessingMenuResponseDTO {
    private int menuNumber;
    private String menuName;
    private int price;
    private int quantity;
    private String introduction;
    private String image;
    private int storeNumber;
}
