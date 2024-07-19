package com.halmisae.service.store;

import com.halmisae.dto.store.ClosingDcAndFoodDTO;
import com.halmisae.dto.store.ReservationDcPreorderDTO;
import com.halmisae.dto.store.ReservationDcUnitTimeDTO;

public interface DiscountService {
    // 할인 및 위약금 관리
    // GET 예약 할인 설정 보기
    ReservationDcPreorderDTO readReservationPreOrder(ReservationDcPreorderDTO rdu);
    // PATCH 예약 할인 설정
    ReservationDcPreorderDTO updateReservationPreOrder(ReservationDcPreorderDTO rdu);
    // GET 이용시간 할인 설정
    ReservationDcUnitTimeDTO readReservationUnit(ReservationDcUnitTimeDTO rdu);
    // PATCH 이용시간 할인 설정
    ReservationDcUnitTimeDTO updateReservationUnit(ReservationDcUnitTimeDTO rdu);
    // GET 마감할인상품 가격 설정 보기
    ClosingDcAndFoodDTO readClosingDiscountAndFood(ClosingDcAndFoodDTO cdf);
    // PATCH 마감할인상품 가격 설정
    ClosingDcAndFoodDTO updateClosingDiscountAndFood (ClosingDcAndFoodDTO cdf);
}
