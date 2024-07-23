package com.halmisae.controller;

import com.halmisae.dto.user.*;
import com.halmisae.service.user.MainService;
import com.halmisae.service.user.ReservationService;
import com.halmisae.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/api/user")
@RequiredArgsConstructor
@Tag(name = "User - Login, MyPage", description = "고객의 CRUD 컨트롤러")
public class UserController {
    private final UserService userService;
    private final MainService mainService;
    private final ReservationService reservationService;

    // UserService : 회원가입
    @PostMapping()
    @Operation(summary = "고객 회원가입", description = "고객 회원가입")
    public UserCreateResponseDTO readDailySchedule(@RequestBody UserCreateRequestDTO userCreateRequestDTO) {
        return userService.createUser(userCreateRequestDTO);
    }

    // MainService : 메인페이지 및 가게 상세 페이지 및 주문 등 메인 기능
    @GetMapping("/main")
    @Operation(summary = "메인페이지(전체)", description = "모든 가게를 보여준다.")
    public List<StoreReadMainDTO> readAllStore() {
        return mainService.readAllStore();
    }
    @GetMapping("/main/closingOrder")
    @Operation(summary = "메인페이지(마감할인)", description = "마감할인을 진행중인 가게만을 보여준다.")
    public List<StoreReadMainDTO> readClosingDiscountStore() {
        return mainService.readClosingDiscountStore();
    }
    @GetMapping("/main/search")
    @Operation(summary = "검색 결과 페이지", description = "가게를 검색한다.")
    public List<StoreReadMainDTO> searchStore(@RequestParam String keyword) {
        return mainService.searchStore(keyword);
    }
    @GetMapping("/main/detail")
    @Operation(summary = "가게 상세 정보 보기", description = "해당 가게의 영업시간, 주소 등 상세 정보를 본다.")
    public StoreReadDetailDTO readStoreDetail(@RequestParam int storeNumber) {
        return mainService.readStoreDetail(storeNumber);
    }
    @PostMapping("/main/detail/closingOrder")
    @Operation(summary = "마감할인상품 주문", description = "해당 가게의 마감할인상품을 주문한다.")
    public ClosingOrderDTO createClosingOrder(@RequestBody ClosingOrderRequestDTO cor) {
        return mainService.createClosingOrder(cor);
    }

    // ReservationService : 가게 정보 얻기, 가게 예약하기
    @GetMapping("/main/detail/reservation")
    @Operation(summary = "가게 예약 보기 화면, 날짜 선택 보기", description = "해당 가게의 휴무일과 메뉴 목록을 본다.")
    public ReservationReadDetailDTO readReservationDetail(@RequestParam int storeNumber) {
        return reservationService.readReservationDetail(storeNumber);
    }
    @PostMapping("/main/detail/reservation")
    @Operation(summary = "가게 예약하기", description = "해당 가게를 예약한다")
    public ReservationCreateResponseDTO createReservation(@RequestBody ReservationCreateRequestDTO reservationCreateRequestDTO) {
        return reservationService.createReservation(reservationCreateRequestDTO);
    }
}
