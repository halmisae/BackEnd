package com.halmisae.entity.Store;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ClosingDiscount {
    private int closingPrice;
    private LocalDateTime pickupTime;
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_number")
    private Store store;
}
