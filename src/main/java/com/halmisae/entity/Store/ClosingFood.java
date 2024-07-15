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
public class ClosingFood {
    @Id
    private int storeNumber;
    private int quantity;
    private LocalDateTime registTime;
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_number")
    private Store store;
}
