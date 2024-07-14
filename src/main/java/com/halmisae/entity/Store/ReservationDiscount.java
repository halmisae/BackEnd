package com.halmisae.entity.Store;

import jakarta.persistence.*;

@Entity
public class ReservationDiscount {
    private int discount;
    private int unitTime;
    private int preorderDiscount;
    private int overFee;
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_number")
    private Store store;
}
