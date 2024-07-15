package com.halmisae.entity.Store;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationDiscount {
    @Id
    private int storeNumber;
    private int discount;
    private int unitTime;
    private int preorderDiscount;
    private int overFee;
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_number")
    private Store store;
}
