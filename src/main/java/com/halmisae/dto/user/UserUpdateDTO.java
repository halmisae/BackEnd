package com.halmisae.dto.user;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String email;
    private String password;
    private String phone;
    private String address;
}
