//package com.halmisae.config;
//
//import com.halmisae.entity.User.User;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Header;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.time.Duration;
//import java.util.Collections;
//import java.util.Date;
//import java.util.Set;
//
//@Service
//@RequiredArgsConstructor
//public class TokenProvider {
//    private final JwtProperties jwtProperties;
//
//    public String generateToken(UserDetails userDetails, Duration expiredAt) {
//        Date now = new Date();
//        User user = (User) userDetails; // 캐스팅
//        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
//    }
//
//    // 1. JWT 토큰 생성 메서드
//    private String makeToken(Date expiry, UserDetails userDetails) {
//        Date now = new Date();
//        User user = (User) userDetails; // 캐스팅
//
//        return Jwts.builder()
//                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)   // 헤더 typ : JWT
//                .setIssuer(jwtProperties.getIssuer())
//                .setIssuedAt(now)
//                .setExpiration(expiry)
//                .setSubject(user.getEmail())
//                .claim("email", user.getEmail())
//                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
//                .compact();
//    }
//
//    // 2. JWT 토큰 유효성 검증 메서드
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(jwtProperties.getSecretKey())   // 비밀값으로 복호화
//                    .parseClaimsJws(token);
//
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    // 3. 토큰 기반으로 인증 정보를 가져오는 메서드
//    public Authentication getAuthentication(String token) {
//        Claims claims = getClaims(token);
//        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
//
//        User user = new User(claims.getSubject(),
//                "", // 비밀번호는 사용되지 않으므로 빈 문자열로 설정
//                authorities.toString());
//        user.setEmail(claims.get("email", String.class));
//
//        return new UsernamePasswordAuthenticationToken(user, token, authorities);
//    }
//
//    private Claims getClaims(String token) {
//        return Jwts.parser()    // 클레임 조회
//                .setSigningKey(jwtProperties.getSecretKey())
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    // 4. 토큰 기반으로 유저 ID를 가져오는 메서드
//    public String getUserId(String token) {
//        Claims claims = getClaims(token);
//        return claims.get("email", String.class);
//    }
//}
