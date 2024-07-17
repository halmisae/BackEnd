package com.halmisae.service.user;

import com.halmisae.dto.user.ReservationDTO;
import com.halmisae.dto.user.ReservationReadDTO;
import com.halmisae.dto.user.ReservationUpdateDTO;

import java.util.List;

public interface MyReservationService {
    // GET 나의 예약
    List<ReservationReadDTO> readMyReservation(String email);
    // GET 가게 예약 변경 보기
    ReservationDTO readReservation(int reserveNumber);
    // PATCH 가게 예약 변경
    ReservationDTO updateReservation(ReservationUpdateDTO ru);
    // DELETE 가게 예약 취소
    boolean deleteReservation(int reserveNumber);
    // DELETE 마감할인상품 주문 취소
    boolean deleteClosingOrder(int orderNumber);
}
