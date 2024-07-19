package com.halmisae.controller.user;

import com.halmisae.dto.user.ReservationCreateRequestDTO;
import com.halmisae.dto.user.ReservationCreateResponseDTO;
import com.halmisae.service.user.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/user/reservaion")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping()
    @Operation(summary = "가게 예약하기", description = "가게 예약 생성")
    public ReservationCreateResponseDTO createReservation(@RequestBody ReservationCreateRequestDTO reservationCreateRequestDTO) {
        return reservationService.createReservation(reservationCreateRequestDTO);
    }
}
