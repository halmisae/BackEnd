package com.halmisae.repository.user;

import com.halmisae.dto.store.ClosingOrderProcessingReadDTO;
import com.halmisae.entity.Enum.DoneType;
import com.halmisae.entity.User.ClosingOrder;
import com.halmisae.entity.User.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ClosingOrderRepository extends JpaRepository<ClosingOrder, Integer> {
    @Query("SELECT co " +
            "FROM ClosingOrder co " +
            "WHERE co.user.email = :email")
    List<ClosingOrder> findAllByEmail(@Param("email") String email);
    @Query("SELECT co " +
            "FROM ClosingOrder co " +
            "WHERE co.store.storeNumber = :storeNumber")
    List<ClosingOrder> findAllByStoreNumber(@Param("storeNumber") int storeNumber);

    @Query("SELECT co " +
            "FROM ClosingOrder co " +
            "WHERE co.store.storeNumber = :storeNumber " +
            "AND co.orderDate BETWEEN :startDate AND :endDate")
    List<ClosingOrder> findByVisitTimeAndStoreNumber(@Param("storeNumber") int storeNumber,
                                                    @Param("startDate") LocalDateTime startDate,
                                                    @Param("endDate") LocalDateTime endDate);

    @Query("SELECT co " +
            "FROM ClosingOrder co " +
            "WHERE co.store.storeNumber = :storeNumber " +
            "AND co.orderDate BETWEEN :startDate AND :endDate " +
            "AND co.sales.doneType IN (:doneTypes)")
    List<ClosingOrder> findByVisitTimeAndStoreNumberAndDoneType(@Param("storeNumber") int storeNumber,
                                                     @Param("startDate") LocalDateTime startDate,
                                                     @Param("endDate") LocalDateTime endDate,
                                                     @Param("doneTypes") List<DoneType> doneTypes);
}
