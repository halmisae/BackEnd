package com.halmisae.dto.user;

import com.halmisae.entity.Enum.RequestStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClosingOrderDTO {
    private int quantity;
    private LocalDateTime orderDate;
    private RequestStatus requestStatus;
    private String email;
    private int storeNumber;
}
