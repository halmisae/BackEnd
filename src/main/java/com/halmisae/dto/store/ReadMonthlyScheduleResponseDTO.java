package com.halmisae.dto.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadMonthlyScheduleResponseDTO {
    private String stringDate;
    private LocalDate date;
    private int reserveCount;
}
