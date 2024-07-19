package com.halmisae.controller.store;

import com.halmisae.dto.store.*;
import com.halmisae.repository.store.ReservationDiscountRepository;
import com.halmisae.service.store.DiscountService;
import com.halmisae.service.store.MenuService;
import com.halmisae.service.store.ProcessingService;
import com.halmisae.service.store.StoreService;
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
    public boolean deleteMenu(@RequestParam("storeNumber") int menuNumber) {
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
