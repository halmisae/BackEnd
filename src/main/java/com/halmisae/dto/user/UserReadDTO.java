package com.halmisae.dto.user;

import com.halmisae.entity.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
