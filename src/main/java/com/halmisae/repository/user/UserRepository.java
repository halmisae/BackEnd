package com.halmisae.repository.user;

import com.halmisae.dto.user.UserReadDTO;
import com.halmisae.entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT new com.halmisae.dto.user.UserReadDTO(u.email, u.userName, u.nickname, u.phone, u.penaltyPoint, u.status, u.penaltyNumber, u.registDate) FROM User u")
    List<UserReadDTO> findALLUserReadDTO();
}
