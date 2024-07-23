package com.halmisae.service;

import com.halmisae.config.TokenProvider;
import com.halmisae.entity.User.User;
import com.halmisae.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken) {
        // 토큰 유효성 검사에 실패하면 예외 발생
        if(!tokenProvider.validateToken(refreshToken))
            throw new IllegalArgumentException("Unexpected token");
        String userEmail = refreshTokenService.findByRefreshToken(refreshToken).getEmail();
        User user = userService.loadUserByUsername(userEmail);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
