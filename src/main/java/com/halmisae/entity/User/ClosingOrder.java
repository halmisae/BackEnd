package com.halmisae.entity.User;

import com.halmisae.entity.Store.Sales;
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
public class ClosingOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int OrderNumber;
    private int quantity;
    private LocalDateTime orderDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_number")
    private Store store;
    @OneToOne(fetch = FetchType.LAZY)
    private Sales sales;
    @OneToOne(fetch = FetchType.LAZY)
    private Rating rating;
}
