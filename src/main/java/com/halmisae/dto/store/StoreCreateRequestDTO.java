package com.halmisae.dto.store;

import com.halmisae.entity.Enum.Weekday;
import lombok.Data;

import java.util.List;

@Data
public class StoreCreateRequestDTO {
    private String id;
    private String password;
    private String passwordCheck;
    private String name;
    private String phone;
    private String businessNumber;
    private String email;
    private String storeName;
    private String image;
    private float averageRating;
    private String address;
    private String addressDetail;
    private String storePhone;
    private String weekdayOpen;
    private String weekdayClose;
    private String weekendOpen;
    private String weekendClose;
    private String breakStart;
    private String breakEnd;
    private List<Weekday> storeHoliday;
}
