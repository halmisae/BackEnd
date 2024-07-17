package com.halmisae.service.store;

import com.halmisae.dto.user.ClosingOrderDTO;
import com.halmisae.dto.user.ReservationDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationScheduleService {
    // GET 날짜별 예약 보기
    List<ReservationDTO> readMonthlySchedule(LocalDateTime month);
    // GET 해당 날짜의 예약 보기
    List<ReservationDTO> readDailySchedule(LocalDateTime day);
    // PUT 예약 취소하기
    ReservationDTO deleteReservation(int reserveNumber);
    // PUT 마감할인상품 주문 취소하기
    ClosingOrderDTO deleteClosingOrder(int orderNumber);
}
