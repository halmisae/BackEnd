package com.halmisae.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveMenuDTO {
    private int reserveNumber;
    private int menuNumber;
    private int quantity;
    private int menuPrice;
}
