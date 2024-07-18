package com.halmisae.dto.store;

import com.halmisae.entity.Enum.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClosingOrderProcessingReadDTO {
    private int orderNumber;
    private int quantity;
    private LocalDateTime orderDate;
    private RequestStatus requestStatus;
    private String email;
    private int storeNumber;
}
