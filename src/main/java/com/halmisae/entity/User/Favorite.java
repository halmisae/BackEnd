package com.halmisae.entity.User;

import com.halmisae.entity.Store.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Favorite {
    private LocalDateTime registDate;
    @EmbeddedId
    private FavoriteID id;
    @MapsId("email")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private User user;
    @MapsId("storeNumber")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_number")
    private Store store;
}
