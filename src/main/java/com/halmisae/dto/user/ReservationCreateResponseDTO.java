package com.halmisae.dto.user;

import com.halmisae.entity.Enum.OrderType;
import com.halmisae.entity.Enum.RequestStatus;
import com.halmisae.entity.User.ReserveMenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.coyote.Request;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationCreateResponseDTO {
    private boolean result;
    private String email;
    private int storeNumber;
    private LocalDateTime visitTime;
    private int useTime;
    private int people;
    private int totalPrice;
    private OrderType orderType;
    private RequestStatus requestStatus;
    private List<ReserveMenuResponseDTO> reserveMenu;
}