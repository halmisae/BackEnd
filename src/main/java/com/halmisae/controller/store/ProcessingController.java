package com.halmisae.controller.store;

import com.halmisae.service.store.ProcessingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/store")
@RequiredArgsConstructor
@Tag(name = "Store - Processing", description = "가게 프로그램의 처리중 화면 컨트롤러")
public class ProcessingController {
    private final ProcessingService processingService;

    @GetMapping("/{storeNumber}/processing")
    @Operation(summary = "처리중인 예약 보기", description = "가게의 처리중 화면에서 처리중인 예약과 신규 예약 정보를 보내준다.")
    public List<Object> readDailySchedule(@PathVariable("storeNumber") int storeNumber) {
        return processingService.readDailySchedule(storeNumber);
    }
}
