package com.halmisae.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequestDTO {
    private String email;
    private String id;
    private String password;
    private String userName;
    private String nickname;
    private String phone;
    private String address;
}
