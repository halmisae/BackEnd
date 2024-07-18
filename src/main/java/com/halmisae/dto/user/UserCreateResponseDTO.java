package com.halmisae.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateResponseDTO {
    private boolean result;
    private String userName;
    private String nickname;
    private String phone;
    private String address;
}
