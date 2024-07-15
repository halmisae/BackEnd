package com.halmisae.entity.User;

import com.halmisae.entity.Enum.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    private String email;
    private String id;
    private String password;
    private String userName;
    private String nickname;
    private String phone;
    private int penaltyPoint;
    private Status status;
    private int penaltyNumber;
    private LocalDateTime registDate;
    @OneToMany(mappedBy = "user")
    private List<Favorite> favorite;
    @OneToMany(mappedBy = "user")
    private List<Rating> rating;
    @OneToMany(mappedBy = "user")
    private List<ClosingOrder> closingOrder;
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservation;
}
