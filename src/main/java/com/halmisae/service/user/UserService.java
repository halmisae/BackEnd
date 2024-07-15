package com.halmisae.service.user;

import com.halmisae.dto.user.UserCreateDTO;
import com.halmisae.dto.user.UserReadDTO;
import com.halmisae.dto.user.UserUpdateDTO;
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
public class UserService {
    private final UserRepository userRepository;

    public User createUser(UserCreateDTO uc) {
        List<Favorite> favorite = new ArrayList<>();
        List<Rating> rating = new ArrayList<>();
        List<ClosingOrder> closingOrder = new ArrayList<>();
        List<Reservation> reservation = new ArrayList<>();
        User user = new User(uc.getEmail(), uc.getId(), uc.getPassword(), uc.getUserName(), uc.getNickname(), uc.getPhone(), 0, Status.AVAILABLE, 0, LocalDateTime.now(), favorite, rating, closingOrder, reservation);
        return userRepository.save(user);
    }

    // 누가 볼 화면인지 정의가 필요하다.
    public UserReadDTO readUserByEmail(String email) {
        User user = userRepository.findById(email).get();
        return new UserReadDTO(user.getEmail(), user.getUserName(), user.getNickname(), user.getPhone(), user.getPenaltyPoint(), user.getStatus(), user.getPenaltyNumber(), user.getRegistDate());
    }

    public List<UserReadDTO> readUserList() {
        return userRepository.findALLUserReadDTO();
    }

    public User updateUser(UserUpdateDTO uu) {
        User user = userRepository.findById(uu.getEmail()).get();
        user.setPassword(uu.getPassword());
        user.setPhone(uu.getPhone());
        return userRepository.save(user);
    }

    public void deleteUser(String email) {
        userRepository.deleteById(email);
    }
}
