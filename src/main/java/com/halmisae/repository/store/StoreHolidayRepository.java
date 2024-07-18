package com.halmisae.repository.store;

import com.halmisae.entity.Enum.Weekday;
import com.halmisae.entity.Store.StoreHoliday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreHolidayRepository extends JpaRepository<StoreHoliday, Integer> {
}
