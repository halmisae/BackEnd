package com.halmisae.service.user;

import com.halmisae.dto.user.StoreReadMainDTO;
import com.halmisae.dto.user.*;
import com.halmisae.entity.User.*;
import java.util.List;

public interface UserService {
    // POST 회원가입
    UserCreateResponseDTO createUser(UserCreateRequestDTO uc);
    // GET 찜 목록 보기
    List<StoreReadMainDTO> readFavorite(String email);
    // GET 사용내역 및 별점 (Sales, Rating)
    List<Object> readHistory(String email);
    // POST 마감할인상품 주문 별점 주기
    RatingClosingOrderDTO createClosingOrderRating(RatingClosingOrderDTO rco);
    // POST 회원정보수정 - 비밀번호 인증 (나중에 인증, 인가로 바꾸기)
    Boolean passwordCheck(UserPasswordCheckDTO upc);
    // GET 회원정보수정
    UserUpdateDTO readUser(UserUpdateDTO uu);
    // PATCH 회원정보수정
    UserUpdateDTO updateUser(UserUpdateDTO uu);
    // DELETE 회원탈퇴
    boolean deleteUser(String email);
}
