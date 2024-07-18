package com.halmisae.controller.store;

import com.halmisae.dto.store.StoreCreateRequestDTO;
import com.halmisae.dto.store.StoreCreateResponseDTO;
import com.halmisae.service.store.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/store")
@RequiredArgsConstructor
@Tag(name = "Store - Login, MyPage", description = "가게의 CRUD 컨트롤러")
public class StoreController {
    private final StoreService storeService;

    @PostMapping()
    @Operation(summary = "가게 회원가입", description = "가게 회원가입")
    public StoreCreateResponseDTO readDailySchedule(@RequestBody StoreCreateRequestDTO storeCreateRequestDTO) {
        return storeService.createStore(storeCreateRequestDTO);
    }
}
