package com.halmisae.service.store;

import com.halmisae.dto.user.ReservationDTO;
import com.halmisae.entity.User.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationScheduleService {
    List<ReservationDTO> readMonthlySchedule(LocalDateTime month);
    List<ReservationDTO> readDailySchedule(LocalDateTime day);
}
