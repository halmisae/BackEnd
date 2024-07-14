package com.halmisae.entity.User;

import com.halmisae.entity.Store.Store;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Favorite {
    private LocalDateTime registDate;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private User user;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_number")
    private Store store;
}
