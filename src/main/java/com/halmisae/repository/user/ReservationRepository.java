package com.halmisae.repository.user;

import com.halmisae.entity.Enum.DoneType;
import com.halmisae.entity.User.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
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

    @Query("SELECT COUNT(r) " +
            "FROM Reservation r " +
            "WHERE r.store.storeNumber = :storeNumber " +
            "AND r.visitTime BETWEEN :startDate AND :endDate")
    Long countByVisitTimeAndStoreNumber(@Param("storeNumber") int storeNumber,
                                        @Param("startDate") LocalDateTime startDate,
                                        @Param("endDate") LocalDateTime endDate);

    @Query("SELECT r " +
            "FROM Reservation r " +
            "WHERE r.store.storeNumber = :storeNumber " +
            "AND r.visitTime BETWEEN :startDate AND :endDate")
    List<Reservation> findByVisitTimeAndStoreNumber(@Param("storeNumber") int storeNumber,
                                                       @Param("startDate") LocalDateTime startDate,
                                                       @Param("endDate") LocalDateTime endDate);

    @Query("SELECT r " +
            "FROM Reservation r " +
            "WHERE r.store.storeNumber = :storeNumber " +
            "AND r.visitTime BETWEEN :startDate AND :endDate " +
            "AND r.sales.doneType IN (:doneTypes)")
    List<Reservation> findByVisitTimeAndStoreNumberAndDoneType(@Param("storeNumber") int storeNumber,
                                                    @Param("startDate") LocalDateTime startDate,
                                                    @Param("endDate") LocalDateTime endDate,
                                                               @Param("doneTypes") List<DoneType> doneTypes);

    @Query("SELECT r " +
            "FROM Reservation r " +
            "WHERE r.store.storeNumber = :storeNumber " +
            "AND r.reserveTime BETWEEN :startDate AND :endDate")
    List<Reservation> findByReserveTimeAndStoreNumber(@Param("storeNumber") int storeNumber,
                                                    @Param("startDate") LocalDateTime startDate,
                                                    @Param("endDate") LocalDateTime endDate);
}
