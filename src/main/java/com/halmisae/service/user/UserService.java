package com.halmisae.service.user;

import com.halmisae.dto.user.UserCreateDTO;
import com.halmisae.dto.user.UserReadDTO;
import com.halmisae.dto.user.UserUpdateDTO;
import com.halmisae.entity.User.*;
import java.util.List;

public interface UserService {
    User createUser(UserCreateDTO uc);
    // 누가 볼 화면인지 정의가 필요하다.
    UserReadDTO readUserByEmail(String email);

    List<UserReadDTO> readUserList();

    User updateUser(UserUpdateDTO uu);

    void deleteUser(String email);
}
