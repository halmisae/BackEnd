package com.halmisae.entity.User;

import com.halmisae.entity.Store.Menu;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReserveMenu {
    private int quantity;
    @EmbeddedId
    private ReserveMenuID id;
    @MapsId("reserveNumber")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserve_number")
    private Reservation reservation;
    @MapsId("menuNumber")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_number")
    private Menu menu;
}
