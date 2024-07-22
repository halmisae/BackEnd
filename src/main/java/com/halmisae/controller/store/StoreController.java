package com.halmisae.controller.store;

import com.halmisae.dto.store.*;
import com.halmisae.dto.user.ClosingOrderDTO;
import com.halmisae.dto.user.ReservationDTO;
import com.halmisae.repository.store.ReservationDiscountRepository;
import com.halmisae.repository.user.ReservationRepository;
import com.halmisae.service.store.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/store")
@RequiredArgsConstructor
@Tag(name = "Store Controller", description = "가게의 모든 CRUD 컨트롤러")
public class StoreController {
    private final StoreService storeService;
    private final ProcessingService processingService;
    private final MenuService menuService;
    private final DiscountService discountService;
    private final ReservationScheduleService reservationScheduleService;
    private final SalesService salesService;

    // StoreService : 가게 회원가입
    @PostMapping()
    @Operation(summary = "가게 회원가입", description = "가게 회원가입")
    public StoreCreateResponseDTO readDailySchedule(@RequestBody StoreCreateRequestDTO storeCreateRequestDTO) {
        return storeService.createStore(storeCreateRequestDTO);
    }

    // ProcessingService : 가게 예약 처리 페이지
    @GetMapping("/processing")
    @Operation(summary = "처리중인 예약 보기", description = "가게의 처리중 화면에서 처리중인 예약과 신규 예약 정보를 보내준다.")
    public List<Object> readDailySchedule(@RequestParam("storeNumber") int storeNumber) {
        return processingService.readDailySchedule(storeNumber);
    }
    @PostMapping("/processing/new/closingOrder/accept")
    @Operation(summary = "신규 마감할인상품 수락", description = "가게의 처리중 화면에서 신규 마감할인상품을 수락한다.")
    public ClosingOrderDTO closingOrderAccept(@RequestParam("orderNumber") int orderNumber) {
        return processingService.closingOrderAccept(orderNumber);
    }
    @PostMapping("/processing/new/closingOrder/reject")
    @Operation(summary = "신규 마감할인상품 거절", description = "가게의 처리중 화면에서 신규 마감할인상품을 거절한다.")
    public ClosingOrderDTO closingOrderReject(@RequestBody ClosingOrderRejectRequestDTO corr) {
        return processingService.closingOrderReject(corr);
    }
    @PostMapping("/processing/new/reservation/accept")
    @Operation(summary = "신규 예약 수락", description = "가게의 처리중 화면에서 신규 가게 예약을 수락한다.")
    public ReservationDTO reservationAccept(@RequestParam("reserveNumber") int reserveNumber) {
        return processingService.reservationAccept(reserveNumber);
    }
    @PostMapping("/processing/new/reservation/reject")
    @Operation(summary = "신규 예약 거절", description = "가게의 처리중 화면에서 신규 가게 예약을 거절한다.")
    public ReservationDTO reservationReject(@RequestBody ReservationRejectRequestDTO rrr) {
        return processingService.reservationReject(rrr);
    }
    @PostMapping("/processing/reservation/complete")
    @Operation(summary = "진행중 예약 완료", description = "가게의 처리중 화면에서 진행중인 예약을 완료한다.")
    public ReservationDoneResponseDTO reservationDone(@RequestParam("reserveNumber") int reserveNumber) {
        return processingService.reservationDone(reserveNumber);
    }
    @PostMapping("/processing/reservation/noShow")
    @Operation(summary = "진행중 예약 노쇼", description = "가게의 처리중 화면에서 진행중인 예약을 노쇼 등록한다.")
    public ReservationNoShowResponseDTO reservationNoShow(@RequestParam("reserveNumber") int reserveNumber) {
        return processingService.reservationNoShow(reserveNumber);
    }
    @PostMapping("/processing/closingOrder/complete")
    @Operation(summary = "마감할인상품 완료", description = "가게의 처리중 화면에서 진행중인 마감할인상품을 완료한다.")
    public ClosingOrderDTO closingOrderDone(@RequestParam("orderNumber") int orderNumber) {
        return processingService.closingOrderDone(orderNumber);
    }
    @PostMapping("/processing/closingOrder/noShow")
    @Operation(summary = "마감할인상품 노쇼", description = "가게의 처리중 화면에서 진행중인 마감할인상품을 노쇼 등록한다.")
    public ClosingOrderDTO closingOrderNoShow(@RequestParam("orderNumber") int orderNumber) {
        return processingService.closingOrderNoShow(orderNumber);
    }

    // ReservationScheduleService : 가게 예약 현황 페이지
    @GetMapping("/schedule")
    @Operation(summary = "날짜별 예약 보기", description = "현재 월의 날짜별 예약 건수를 달력에 표시하여 보여준다.")
    public List<ReadMonthlyScheduleResponseDTO> readMonthlySchedule(@RequestBody ReadScheduleRequestDTO today) {
        return reservationScheduleService.readMonthlySchedule(today);
    }
    @GetMapping("/schedule/daily")
    @Operation(summary = "해당 날짜의 예약 보기", description = "달력에서 날짜를 선택하여 해당 날짜의 예약 목록을 보여준다.")
    public List<ReservationDTO> readDailySchedule(@RequestBody ReadScheduleRequestDTO day) {
        return reservationScheduleService.readDailySchedule(day);
    }
    @PutMapping("/schedule/daily/reservation/cancel")
    @Operation(summary = "예약 취소하기", description = "가게의 해당 예약을 취소한다.")
    public ReservationDTO deleteReservation(@RequestParam("reserveNumber") int reserveNumber) {
        return reservationScheduleService.deleteReservation(reserveNumber);
    }
    @PutMapping("/schedule/daily/closingOrder/cancel")
    @Operation(summary = "마감할인상품 주문 취소하기", description = "가게의 해당 마감할인 주문 취소한다.")
    public ClosingOrderDTO deleteClosingOrder(@RequestParam("orderNumber") int orderNumber) {
        return reservationScheduleService.deleteClosingOrder(orderNumber);
    }

    // SalesService : 매출 조회 페이지
    @GetMapping("/sales")
    @Operation(summary = "월별 매출 데이터 보기", description = "가게의 월별 매출 데이터를 그래프와 내역으로 보여준다.")
    public List<Object> readSales(@RequestBody SalesReadRequestDTO sr) {
        return salesService.readSales(sr);
    }

    // MenuService : 가게 설정 페이지 - my 매장 관리 - 메뉴 관리
    @GetMapping("/menuList")
    @Operation(summary = "메뉴 목록 보기", description = "내 가게의 메뉴 목록을 보여준다.")
    public List<MenuDTO> readMenuList(@RequestParam("storeNumber") int storeNumber) {
        return menuService.readMenuList(storeNumber);
    }
    @PostMapping("/menuList/menu")
    @Operation(summary = "메뉴 등록", description = "내 가게에 메뉴를 등록한다.")
    public MenuDTO createMenu(@RequestBody MenuCreateRequestDTO mcr) {
        return menuService.createMenu(mcr);
    }
    @PatchMapping("/menuList/menu")
    @Operation(summary = "메뉴 수정", description = "내 가게의 메뉴를 수정한다.")
    public MenuDTO updateMenu(@RequestBody MenuDTO m) {
        return menuService.updateMenu(m);
    }
    @DeleteMapping("/menuList/menu")
    @Operation(summary = "메뉴 삭제", description = "내 가게의 메뉴를 삭제한다.")
    public boolean deleteMenu(@RequestParam("menuNumber") int menuNumber) {
        return menuService.deleteMenu(menuNumber);
    }


    // DiscountService : 가게 설정 페이지 - 할인 및 위약금 관리
    @GetMapping("/preorderDiscount")
    @Operation(summary = "예약 할인 설정 보기", description = "내 가게의 예약 할인 설정을 조회한다.")
    public ReservationDcPreorderDTO readReservationPreOrder(@RequestParam("storeNumber") int storeNumber) {
        return discountService.readReservationPreOrder(storeNumber);
    }
    @PostMapping("/preorderDiscount")
    @Operation(summary = "예약 할인 설정", description = "내 가게의 예약 할인 설정을 등록 또는 수정한다.")
    public ReservationDcPreorderDTO setReservationPreOrder(@RequestBody ReservationDcPreorderDTO rdp) {
        return discountService.setReservationPreOrder(rdp);
    }
    @GetMapping("/unitTimeDiscount")
    @Operation(summary = "이용시간 할인 설정 보기", description = "내 가게의 이용시간 할인 설정을 조회한다.")
    public ReservationDcUnitTimeDTO readReservationUnit(@RequestParam("storeNumber") int storeNumber) {
        return discountService.readReservationUnit(storeNumber);
    }
    @PostMapping("/unitTimeDiscount")
    @Operation(summary = "이용시간 할인 설정", description = "내 가게의 이용시간 할인 설정을 등록 또는 수정한다.")
    public ReservationDcUnitTimeDTO readReservationPreOrder(@RequestBody ReservationDcUnitTimeDTO rdu) {
        return discountService.setReservationUnit(rdu);
    }
    @GetMapping("/closingDiscount")
    @Operation(summary = "마감할인상품 가격 설정 보기", description = "내 가게의 마감할인상품 가격 설정을 조회한다.")
    public ClosingDcAndFoodDTO readClosingDiscountAndFood(@RequestParam("storeNumber") int storeNumber) {
        return discountService.readClosingDiscountAndFood(storeNumber);
    }
    @PostMapping("/closingDiscount")
    @Operation(summary = "마감할인상품 가격 설정", description = "내 가게의 마감할인상품 가격 설정을 등록 또는 수정한다.")
    public ClosingDcAndFoodDTO setClosingDiscountAndFood(@RequestBody ClosingDcAndFoodDTO cdf) {
        return discountService.setClosingDiscountAndFood(cdf);
    }
}
