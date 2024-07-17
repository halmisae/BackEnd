package com.halmisae.dto.user;

import com.halmisae.entity.Enum.RequestStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClosingOrderRequestDTO {
    private int quantity;
    private int totalPrice;
    private String email;
    private int storeNumber;
}
