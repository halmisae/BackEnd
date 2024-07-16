package com.halmisae.dto.store;

import com.halmisae.entity.Enum.Status;
import com.halmisae.entity.Store.Store;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class StoreReadDTO {
    private String name;
    private String phone;
    private String businessNumber;
    private String email;
    private String storeName;
    private String address;
    private String storePhone;
    private String weekdayOpen;
    private String weekdayClose;
    private String weekendOpen;
    private String weekendClose;
    private String breakStart;
    private String breakEnd;
    private LocalDateTime registDate;
    private Status status;
    private int penaltyNumber;
    private List<Integer> storeHoliday;
}
