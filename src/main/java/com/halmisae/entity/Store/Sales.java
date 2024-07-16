package com.halmisae.entity.Store;

import com.halmisae.entity.Enum.DoneType;
import com.halmisae.entity.Enum.OrderType;
import com.halmisae.entity.User.ClosingOrder;
import com.halmisae.entity.User.Reservation;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salesNumber;
    private int paymentPrice;
    private LocalDateTime doneDate;
    private OrderType orderType;
    private DoneType doneType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_number")
    private Store store;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_number")
    private ClosingOrder closingOrder;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserve_number")
    private Reservation reservation;
}
