package com.halmisae.dto.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FavoriteDTO {
    private String email;
    private int storeNumber;
}
