package com.halmisae.repository.user;

import com.halmisae.entity.User.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
