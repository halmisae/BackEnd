package com.halmisae.repository.user;

import com.halmisae.dto.store.ClosingOrderProcessingReadDTO;
import com.halmisae.entity.User.ClosingOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClosingOrderRepository extends JpaRepository<ClosingOrder, Integer> {
    @Query("SELECT new com.halmisae.dto.store.ClosingOrderProcessingReadDTO(co.orderNumber, co.quantity, co.orderDate, co.requestStatus, co.user.email, co.store.storeNumber) " +
            "FROM ClosingOrder co " +
            "WHERE co.user.email = :email")
    List<ClosingOrderProcessingReadDTO> findAllByEmail(@Param("email") String email);
    @Query("SELECT new com.halmisae.dto.store.ClosingOrderProcessingReadDTO(co.orderNumber, co.quantity, co.orderDate, co.requestStatus, co.user.email, co.store.storeNumber)" +
            "FROM ClosingOrder co " +
            "WHERE co.store.storeNumber = :storeNumber")
    List<ClosingOrderProcessingReadDTO> findAllByStoreNumber(@Param("storeNumber") int storeNumber);
}
