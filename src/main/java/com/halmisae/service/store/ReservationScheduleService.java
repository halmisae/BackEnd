package com.halmisae.service.store;

import com.halmisae.dto.store.ReadMonthlyScheduleResponseDTO;
import com.halmisae.dto.store.ReservationDailyScheduleDTO;
import com.halmisae.dto.user.ClosingOrderDTO;
import com.halmisae.dto.user.ReservationDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationScheduleService {
    // GET 날짜별 예약 보기
    List<ReadMonthlyScheduleResponseDTO> readMonthlySchedule(int storeNumber, LocalDateTime today);
    // GET 해당 날짜의 예약 보기
    List<ReservationDailyScheduleDTO> readDailySchedule(int storeNumber, String date);
    // PUT 예약 취소하기
    ReservationDTO deleteReservation(int reserveNumber);
}
