package com.halmisae.entity.User;

import com.halmisae.entity.Store.Menu;
import jakarta.persistence.*;

@Entity
public class ReserveMenu {
    private int quantity;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserve_number")
    private Reservation reservation;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_number")
    private Menu menu;
}
