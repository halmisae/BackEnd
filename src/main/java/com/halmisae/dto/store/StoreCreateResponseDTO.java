package com.halmisae.dto.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreCreateResponseDTO {
    private boolean result;
    private String name;
    private String phone;
    private String storeName;
    private String address;
    private String storePhone;
}
