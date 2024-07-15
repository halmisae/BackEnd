package com.halmisae.dto.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserCreateDTO {
    private String email;
    private String id;
    private String password;
    private String userName;
    private String nickname;
    private String phone;
}
