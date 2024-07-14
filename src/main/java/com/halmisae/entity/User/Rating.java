package com.halmisae.entity.User;

import com.halmisae.entity.Store.Store;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ratingNumber;
    private int rating;
    private LocalDateTime registDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_number")
    private Store store;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "rating")
    @JoinColumn(name = "order_number")
    private ClosingOrder closingOrder;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "rating")
    @JoinColumn(name = "reserve_number")
    private Reservation reservation;
}
