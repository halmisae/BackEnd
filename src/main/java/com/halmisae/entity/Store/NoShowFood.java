package com.halmisae.entity.Store;

import com.halmisae.entity.User.Reservation;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class NoShowFood {
    private LocalDateTime registTime;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_number")
    private Store store;
    @Id
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "noShowFood")
    @JoinColumn(name = "reserve_number")
    private Reservation reservation;
}
