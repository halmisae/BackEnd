package com.halmisae.dto.store;

import com.halmisae.entity.Enum.Weekday;
import lombok.Data;

import java.util.List;

@Data
public class StoreDTO {
    private int storeNumber;
    private String storeName;
    private String address;
    private String storePhone;
    private String weekdayOpen;
    private String weekdayClose;
    private String weekendOpen;
    private String weekendClose;
    private String breakStart;
    private String breakEnd;
    private List<Weekday> storeHoliday;
}
