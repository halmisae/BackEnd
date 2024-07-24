package com.halmisae.service.user;

import com.halmisae.dto.user.StoreReadMainDTO;
import com.halmisae.dto.user.*;
import com.halmisae.entity.Enum.Status;
import com.halmisae.entity.User.*;
import com.halmisae.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService/*, UserDetailsService */{
    private final UserRepository userRepository;

    @Override
    public UserCreateResponseDTO createUser(UserCreateRequestDTO uc) {
        List<Favorite> favorite = new ArrayList<>();
        List<Rating> rating = new ArrayList<>();
        List<ClosingOrder> closingOrder = new ArrayList<>();
        List<Reservation> reservation = new ArrayList<>();
        User user = new User(uc.getEmail(), uc.getId(), uc.getPassword(), uc.getUserName(), uc.getNickname(), uc.getPhone(), uc.getAddress(), 0, Status.AVAILABLE, 0, LocalDateTime.now(), favorite, rating, closingOrder, reservation);
        User s = userRepository.save(user);
        boolean result = !(s.getEmail().isEmpty());
        return new UserCreateResponseDTO(result, s.getUserName(), s.getNickname(), s.getPhone(), s.getAddress());
    }

    @Override
    public List<StoreReadMainDTO> readFavorite(String email) {
        return null;
    }

    @Override
    public List<Object> readHistory(String email) {
        return null;
    }

    @Override
    public RatingClosingOrderDTO createClosingOrderRating(RatingClosingOrderDTO rco) {
        return null;
    }

    @Override
    public Boolean passwordCheck(UserPasswordCheckDTO upc) {
        return null;
    }

    @Override
    public UserUpdateDTO readUser(UserUpdateDTO uu) {
        return null;
    }

//    // 누가 볼 화면인지 정의가 필요하다.
//    @Override
//    public UserReadDTO readUserByEmail(String email) {
//        User user = userRepository.findById(email).get();
//        return new UserReadDTO(user.getEmail(), user.getUserName(), user.getNickname(), user.getPhone(), user.getPenaltyPoint(), user.getStatus(), user.getPenaltyNumber(), user.getRegistDate());
//    }
//
//    @Override
//    public List<UserReadDTO> readUserList() {
//        return userRepository.findALLUserReadDTO();
//    }

    @Override
    public UserUpdateDTO updateUser(UserUpdateDTO uu) {
        User user = userRepository.findById(uu.getEmail()).get();
        user.setPassword(uu.getPassword());
        user.setPhone(uu.getPhone());
        userRepository.save(user);
        return new UserUpdateDTO();
    }

    @Override
    public boolean deleteUser(String email) {
        userRepository.deleteById(email);
        return userRepository.findById(email).isEmpty();
    }

    @Override
    // 사용자 이름(email)으로 사용자의 정보를 가져오는 메서드
    public User loadUserByUsername(String email) {
        return userRepository.findById(email)
                .orElseThrow(() -> new IllegalArgumentException((email)));
    }
}
