package com.halmisae.entity.User;

import com.halmisae.entity.Enum.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class User /*implements UserDetails*/ {
    @Id
    private String email;
    private String id;
    private String password;
    private String userName;
    private String nickname;
    private String phone;
    private String address;
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

//    @Builder
//    public User(String email, String password, String auth) {
//        this.email = email;
//        this.password = password;
//    }
//
//    @Override
//    // 권한 반환
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority("user"));
//    }
//
//    @Override
//    // 사용자의 id를 반환(고유한 값)
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    // 사용자의 패스워드 반환
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    // 계정 만료 여부 반환
//    public boolean isAccountNonExpired() {
//        // 만료되었는지 확인하는 로직
//        return true;    // true -> 만료되지 않았음
//    }
//
//    @Override
//    // 계정 잠금 여부 반환
//    public boolean isAccountNonLocked() {
//        // 계정 잠금되었는지 확인하는 로직
//        return true;    // true -> 잠금되지 않았음
//    }
//
//    @Override
//    // 패스워드의 만료 여부 반환
//    public boolean isCredentialsNonExpired() {
//        // 패스워드가 만료되었는지 확인하는 로직
//        return true;    // true -> 만료되지 않았음
//    }
//
//    @Override
//    // 계정 사용 가능 여부 반환
//    public boolean isEnabled() {
//        // 계정이 사용 가능한지 확인하는 로직
//        return true;   // true -> 사용 가능
//    }
}
