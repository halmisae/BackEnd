package com.halmisae.service.user;

import com.halmisae.dto.user.ReservationDTO;
import com.halmisae.dto.user.ReservationReadDetailDTO;
import com.halmisae.dto.user.ReservationReadDetailRequestDTO;
import com.halmisae.dto.user.ReservationRequestDTO;

public interface ReservationService {
    // GET 가게 예약 보기 화면, 날짜 선택, 노쇼 상품 보기
    ReservationReadDetailDTO readReservationDetail(ReservationReadDetailRequestDTO rrdr);
    // POST 결제완료
    ReservationDTO createReservation(ReservationRequestDTO rr);
}
