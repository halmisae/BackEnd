package com.halmisae.service.store;

import com.halmisae.dto.store.*;
import com.halmisae.dto.user.ClosingOrderDTO;
import com.halmisae.dto.user.ReservationDTO;

import java.util.List;

public interface ProcessingService {
    // GET 오늘의 예약, 마감 주문 전체 보기 (ClosingOrder, Reservation)
    List<Object> readDailySchedule(int storeNumber);
    // POST 신규 마감할인상품 수락
    ClosingOrderDTO closingOrderAccept(int orderNumber);
    // POST 신규 마감할인상품 거절
    ClosingOrderDTO closingOrderReject(ClosingOrderRejectRequestDTO corr);
    // POST 신규 예약 수락;
    ReservationDTO reservationAccept(int reserveNumber);
    // POST 신규 예약 거절;
    ReservationDTO reservationReject(ReservationRejectRequestDTO rrr);
    // POST 진행중 예약 완료;
    ReservationDoneResponseDTO reservationDone(int reserveNumber);
    // POST 진행중 예약 노쇼;
    ReservationNoShowResponseDTO reservationNoShow(int reserveNumber);
    // POST 마감할인상품 완료;
    ClosingOrderDTO closingOrderDone(int orderNumber);
    // POST 마감할인상품 노쇼;
    ClosingOrderDTO closingOrderNoShow(int orderNumber);
}