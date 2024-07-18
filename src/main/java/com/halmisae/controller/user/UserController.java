package com.halmisae.controller.user;

import com.halmisae.dto.user.UserCreateRequestDTO;
import com.halmisae.dto.user.UserCreateResponseDTO;
import com.halmisae.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/user")
@RequiredArgsConstructor
@Tag(name = "User - Login, MyPage", description = "고객의 CRUD 컨트롤러")
public class UserController {
    private final UserService userService;

    @PostMapping()
    @Operation(summary = "고객 회원가입", description = "고객 회원가입")
    public UserCreateResponseDTO readDailySchedule(@RequestBody UserCreateRequestDTO userCreateRequestDTO) {
        return userService.createUser(userCreateRequestDTO);
    }
}
