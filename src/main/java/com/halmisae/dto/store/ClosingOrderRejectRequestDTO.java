package com.halmisae.dto.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClosingOrderRejectRequestDTO {
    private int orderNumber;
    private String rejectMessage;
}
