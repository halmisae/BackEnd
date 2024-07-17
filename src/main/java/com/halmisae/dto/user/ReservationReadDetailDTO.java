package com.halmisae.dto.user;

import com.halmisae.entity.Store.Menu;
import com.halmisae.entity.Store.StoreHoliday;
import lombok.Data;

import java.util.List;

@Data
public class ReservationReadDetailDTO {
    private int storeNumber;
    private List<StoreHoliday> storeHoliday;
    private List<String> timeTable;
    private int usageTime;
    private List<Menu> menu;
}
