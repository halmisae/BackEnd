package com.halmisae.repository.store;

import com.halmisae.entity.Store.StoreHoliday;
import com.halmisae.entity.Store.StoreHolidayID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreHolidayRepository extends JpaRepository<StoreHoliday, StoreHolidayID> {
}
