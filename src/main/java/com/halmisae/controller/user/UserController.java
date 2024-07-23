package com.halmisae.controller.user;

import com.halmisae.dto.user.*;
import com.halmisae.service.user.MainService;
import com.halmisae.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/user")
@RequiredArgsConstructor
@Tag(name = "User - Login, MyPage", description = "고객의 CRUD 컨트롤러")
public class UserController {
    private final UserService userService;
    private final MainService mainService;

    // UserService : 회원가입
    @PostMapping()
    @Operation(summary = "고객 회원가입", description = "고객 회원가입")
    public UserCreateResponseDTO readDailySchedule(@RequestBody UserCreateRequestDTO userCreateRequestDTO) {
        return userService.createUser(userCreateRequestDTO);
    }

    // MainService : 메인페이지 및 가게 상세 페이지 및 주문 등 메인 기능
    @GetMapping("/main")
    @Operation(summary = "메인페이지(전체)", description = "고객 회원가입")
    public List<StoreReadMainDTO> readAllStore() {
        return mainService.readAllStore();
    }
    @GetMapping("/main/closingOrder")
    @Operation(summary = "메인페이지(마감할인)", description = "고객 회원가입")
    public List<StoreReadMainDTO> readClosingDiscountStore() {
        return mainService.readClosingDiscountStore();
    }
    @GetMapping("/main/search")
    @Operation(summary = "검색 결과 페이지", description = "고객 회원가입")
    public List<StoreReadMainDTO> searchStore(@RequestParam String keyword) {
        return mainService.searchStore(keyword);
    }
    @GetMapping("/main/detail")
    @Operation(summary = "가게 상세 정보 보기", description = "고객 회원가입")
    public StoreReadDetailDTO readStoreDetail(@RequestParam int storeNumber) {
        return mainService.readStoreDetail(storeNumber);
    }
    @PostMapping("/main/detail/closingOrder")
    @Operation(summary = "마감할인상품 주문", description = "고객 회원가입")
    public ClosingOrderDTO createClosingOrder(@RequestBody ClosingOrderRequestDTO cor) {
        return mainService.createClosingOrder(cor);
    }
}
