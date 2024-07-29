package com.halmisae.dto.user;

import com.halmisae.dto.store.MenuDTO;
import com.halmisae.entity.Enum.Weekday;
import com.halmisae.entity.Store.Menu;
import com.halmisae.entity.Store.StoreHoliday;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationReadDetailDTO {
    private int storeNumber;
    private List<Weekday> storeHoliday;
    private int usageTime;
    private int unitTime;
    private int discount;
    private int preorderDiscount;
    private List<MenuDTO> menu;
    private String openTime;
    private String closeTime;
    private String breakStart;
    private String breakEnd;
}
