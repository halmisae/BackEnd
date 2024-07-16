package com.halmisae.dto.store;

import lombok.Data;

import java.util.List;

@Data
public class StoreUpdateDTO {
    private int storeNumber;
    private String password;
    private String passwordCheck;
    private String phone;
    private String storeName;
    //    private Category category;
    private String address;
    private String storePhone;
    private String weekdayOpen;
    private String weekdayClose;
    private String weekendOpen;
    private String weekendClose;
    private String breakStart;
    private String breakEnd;
    private List<Integer> storeHoliday;
}
