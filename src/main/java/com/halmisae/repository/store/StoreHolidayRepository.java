package com.halmisae.repository.store;

import com.halmisae.entity.Enum.Weekday;
import com.halmisae.entity.Store.StoreHoliday;
import com.halmisae.entity.Store.StoreHolidayID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreHolidayRepository extends JpaRepository<StoreHoliday, StoreHolidayID> {
    @Query("SELECT sh " +
            "FROM StoreHoliday sh " +
            "WHERE sh.id.storeNumber = :storeNumber")
    List<StoreHoliday> findAllByStoreNumber(@Param("storeNumber") int storeNumber);
}
