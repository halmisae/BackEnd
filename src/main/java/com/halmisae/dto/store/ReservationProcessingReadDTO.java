package com.halmisae.dto.store;

import com.halmisae.dto.user.ReserveMenuCreateDTO;
import com.halmisae.entity.Enum.DoneType;
import com.halmisae.entity.Enum.OrderType;
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
    private DoneType doneType;
    private OrderType orderType;
    private RequestStatus requestStatus;
    private String email;
    private int storeNumber;
    private List<ProcessingMenuResponseDTO> menuDTO;
}
