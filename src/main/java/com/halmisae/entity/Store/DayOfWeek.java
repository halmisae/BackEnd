package com.halmisae.entity.Store;

import com.halmisae.entity.Enum.Weekday;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DayOfWeek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dayNumber;
    private Weekday dayOfWeek;
    @OneToMany(mappedBy = "dayOfWeek")
    private List<StoreHoliday> storeHoliday;
}
