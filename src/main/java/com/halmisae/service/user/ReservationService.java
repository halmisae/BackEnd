package com.halmisae.service.user;

import com.halmisae.dto.user.*;

public interface ReservationService {
    // GET 가게 예약 보기 화면, 날짜 선택, 노쇼 상품 보기
    ReservationReadDetailDTO readReservationDetail(int storeNumber);
    // POST 예약완료
    ReservationCreateResponseDTO createReservation(ReservationCreateRequestDTO rr);
}
