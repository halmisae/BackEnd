package com.halmisae.repository.user;

import com.halmisae.dto.store.ReservationProcessingReadDTO;
import com.halmisae.entity.User.ClosingOrder;
import com.halmisae.entity.User.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("SELECT r " +
            "FROM Reservation r " +
            "WHERE r.user.email = :email")
    List<Reservation> findAllByEmail(@Param("email") String email);
    @Query("SELECT r " +
            "FROM Reservation r " +
            "WHERE r.store.storeNumber = :storeNumber")
    List<Reservation> findAllByStoreNumber(@Param("storeNumber") int storeNumber);
}
