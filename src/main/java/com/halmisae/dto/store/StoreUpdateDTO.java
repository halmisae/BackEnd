package com.halmisae.dto.store;

import com.halmisae.entity.Enum.Weekday;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreUpdateDTO {
    private int storeNumber;
    private String storeName;
    private String address;
    private String storePhone;
    private MultipartFile image;
    private String weekdayOpen;
    private String weekdayClose;
    private String weekendOpen;
    private String weekendClose;
    private String breakStart;
    private String breakEnd;
    private List<Weekday> storeHoliday;
}
