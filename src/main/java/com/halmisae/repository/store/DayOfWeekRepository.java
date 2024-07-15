package com.halmisae.repository.store;

import com.halmisae.entity.Store.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayOfWeekRepository extends JpaRepository<DayOfWeek, Integer> {
}
