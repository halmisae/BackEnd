package com.halmisae.entity.Store;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClosingDiscount {
    @Id
    private int storeNumber;
    private int closingPrice;
    private LocalDateTime pickupTime;
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_number")
    private Store store;
}
