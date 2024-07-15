package com.halmisae.entity.Store;

import com.halmisae.entity.User.ReserveMenu;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuNumber;
    private String menuName;
    private int price;
    private String introduction;
    private String image;
    private LocalDateTime registDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_number")
    private Store store;
    @OneToMany(mappedBy = "menu")
    private List<ReserveMenu> reserveMenu;
}
