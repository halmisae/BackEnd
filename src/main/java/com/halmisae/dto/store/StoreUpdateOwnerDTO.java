package com.halmisae.dto.store;

import lombok.Data;

@Data
public class StoreUpdateOwnerDTO {
    private int storeNumber;
    private String id;
    private String password;
    private String phone;
}
