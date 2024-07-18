package com.halmisae.dto.store;

import com.halmisae.entity.Enum.Category;
import com.halmisae.entity.Enum.Status;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class StoreCreateDTO {
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
    private String storePhone;
    private String weekdayOpen;
    private String weekdayClose;
    private String weekendOpen;
    private String weekendClose;
    private String breakStart;
    private String breakEnd;
    private List<Integer> storeHoliday;
}
