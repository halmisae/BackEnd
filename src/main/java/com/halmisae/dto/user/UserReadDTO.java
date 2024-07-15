package com.halmisae.dto.user;

import com.halmisae.entity.Enum.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserReadDTO {
    private String email;
    private String userName;
    private String nickname;
    private String phone;
    private int penaltyPoint;
    private Status status;
    private int penaltyNumber;
    private LocalDateTime registDate;
}
