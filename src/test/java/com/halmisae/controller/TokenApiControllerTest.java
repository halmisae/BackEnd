//package com.halmisae.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.halmisae.config.JwtFactory;
//import com.halmisae.config.JwtProperties;
//import com.halmisae.dto.CreateAccessTokenRequest;
//import com.halmisae.entity.RefreshToken;
//import com.halmisae.entity.User.User;
//import com.halmisae.repository.RefreshTokenRepository;
//import com.halmisae.repository.user.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MockMvcBuilder;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class TokenApiControllerTest {
//    @Autowired
//    protected MockMvc mockMvc;
//    @Autowired
//    protected ObjectMapper objectMapper;
//    @Autowired
//    private WebApplicationContext context;
//    @Autowired
//    JwtProperties jwtProperties;
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    RefreshTokenRepository refreshTokenRepository;
//
//    @BeforeEach
//    public void mockMvcSetUp() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
//                .build();
//        userRepository.deleteAll();
//    }
//
//    @DisplayName("createNewAccessToken: 새로운 액세스 토큰을 발급한다.")
//    @Test
//    void createNewAccessToken() throws Exception {
//        // given
//        final String url = "/api/token";
//        User testUser = userRepository.save(User.builder()
//                .email("user@gamil.com")
//                .password("test1234")
//                .build());
//
//        String refreshToken = JwtFactory.builder()
//                .claims(Map.of("email", testUser.getEmail()))
//                .build()
//                .createToken(jwtProperties);
//        refreshTokenRepository.save(new RefreshToken(testUser.getEmail(), refreshToken));
//
//        CreateAccessTokenRequest request = new CreateAccessTokenRequest();
//        request.setRefreshToken(refreshToken);
//        final String requestBody = objectMapper.writeValueAsString(request);
//
//        // when
//        ResultActions resultActions = mockMvc.perform(post(url)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(requestBody));
//        // then
//        resultActions
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.accessToken").isNotEmpty());
//    }
//}