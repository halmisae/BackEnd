package com.halmisae.entity.Store;

import com.halmisae.entity.User.Reservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoShowFood {
    private LocalDateTime registTime;
    @EmbeddedId
    private NoShowFoodID id;
    @MapsId("storeNumber")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_number")
    private Store store;
    @MapsId("reserveNumber")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserve_number")
    private Reservation reservation;
}
