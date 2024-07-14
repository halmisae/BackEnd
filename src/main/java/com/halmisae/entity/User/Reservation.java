package com.halmisae.entity.User;

import com.halmisae.entity.Store.NoShowFood;
import com.halmisae.entity.Store.Sales;
import com.halmisae.entity.Store.Store;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reserveNumber;
    private LocalDateTime reserveTime;
    private LocalDateTime visitTime;
    private int useTime;
    private int people;
    private int totalPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_number")
    private Store store;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "reservation")
    private NoShowFood noShowFood;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "reservation")
    private Sales sales;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "reservation")
    private Rating rating;
}
