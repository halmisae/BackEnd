package com.halmisae.repository.user;

import com.halmisae.entity.User.ClosingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClosingOrderRepository extends JpaRepository<ClosingOrder, Integer> {
}
