package com.halmisae.entity.Store;

import jakarta.persistence.*;

@Entity
public class StoreHoliday {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_number")
    private Store store;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_number")
    private DayOfWeek dayOfWeek;
}
