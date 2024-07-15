package com.halmisae.service;

import com.halmisae.dto.user.UserCreateDTO;
import com.halmisae.dto.user.UserReadDTO;
import com.halmisae.dto.user.UserUpdateDTO;
import com.halmisae.entity.User.User;
import com.halmisae.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserCreateDTO createUser() {
        return new UserCreateDTO();
    }

    public UserReadDTO readUserByEmail() {
        return new UserReadDTO();
    }

    public List<UserReadDTO> readUserList() {

        return null;
    }

    public UserUpdateDTO updateUser() {
        return new UserUpdateDTO();
    }

    public void deleteUser() {

    }
}
