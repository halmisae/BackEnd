package com.halmisae.entity.Store;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ClosingFood {
    private int quantity;
    private LocalDateTime registTime;
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_number")
    private Store store;
}
