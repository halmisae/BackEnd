package com.halmisae.dto.store;

import com.halmisae.dto.user.ReserveMenuDTO;
import com.halmisae.entity.Enum.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationProcessingReadDTO {
    private int reserveNumber;
    private LocalDateTime reserveTime;
    private LocalDateTime visitTime;
    private int useTime;
    private int people;
    private int totalPrice;
    private RequestStatus requestStatus;
    private String email;
    private int storeNumber;
    private List<ReserveMenuDTO> reserveMenuDTOs;
}
