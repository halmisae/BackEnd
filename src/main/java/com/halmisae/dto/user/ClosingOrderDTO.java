package com.halmisae.dto.user;

import com.halmisae.entity.Enum.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClosingOrderDTO {
    private int orderNumber;
    private int quantity;
    private int totalPrice;
    private LocalDateTime orderDate;
    private RequestStatus requestStatus;
    private String rejectMessage;
    private String email;
    private int storeNumber;
}
