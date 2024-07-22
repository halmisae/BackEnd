package com.halmisae.service.store;

import com.halmisae.dto.store.ReadMonthlyScheduleResponseDTO;
import com.halmisae.dto.store.ReadScheduleRequestDTO;
import com.halmisae.dto.user.ClosingOrderDTO;
import com.halmisae.dto.user.ReservationDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationScheduleService {
    // GET 날짜별 예약 보기
    List<ReadMonthlyScheduleResponseDTO> readMonthlySchedule(ReadScheduleRequestDTO today);
    // GET 해당 날짜의 예약 보기
    List<ReservationDTO> readDailySchedule(ReadScheduleRequestDTO today);
    // PUT 예약 취소하기
    ReservationDTO deleteReservation(int reserveNumber);
    // PUT 마감할인상품 주문 취소하기
    ClosingOrderDTO deleteClosingOrder(int orderNumber);
}
