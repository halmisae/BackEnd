package com.halmisae.dto.store;

import com.halmisae.entity.Store.Menu;
import lombok.Data;

import java.util.List;

@Data
public class StoreReadUserReserveDTO {
    private List<String> reservationOkayTime;
    private List<Integer> storeHoliday;
    private List<Menu> menu;
}
