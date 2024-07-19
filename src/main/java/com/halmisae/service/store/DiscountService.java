package com.halmisae.service.store;

import com.halmisae.dto.store.ClosingDcAndFoodDTO;
import com.halmisae.dto.store.ReservationDcPreorderDTO;
import com.halmisae.dto.store.ReservationDcUnitTimeDTO;

public interface DiscountService {
    // 할인 및 위약금 관리
    // GET 예약 할인 설정 보기
    ReservationDcPreorderDTO readReservationPreOrder(int storeNumber);
    // POST 예약 할인 설정 (CREATE or UPDATE)
    ReservationDcPreorderDTO setReservationPreOrder(ReservationDcPreorderDTO rdu);
    // GET 이용시간 할인 설정
    ReservationDcUnitTimeDTO readReservationUnit(int storeNumber);
    // POST 이용시간 할인 설정 (CREATE or UPDATE)
    ReservationDcUnitTimeDTO setReservationUnit(ReservationDcUnitTimeDTO rdu);
    // GET 마감할인상품 가격 설정 보기
    ClosingDcAndFoodDTO readClosingDiscountAndFood(int storeNumber);
    // POST 마감할인상품 가격 설정 (CREATE or UPDATE)
    ClosingDcAndFoodDTO setClosingDiscountAndFood (ClosingDcAndFoodDTO cdf);
}
