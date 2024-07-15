package com.halmisae.entity.Store;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreHoliday {
    @EmbeddedId
    private StoreHolidayID id;
    @MapsId("storeNumber")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_number")
    private Store store;
    @MapsId("dayNumber")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_number")
    private DayOfWeek dayOfWeek;
}
