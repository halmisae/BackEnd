package com.halmisae.entity.Store;

import com.halmisae.entity.Enum.Status;
import com.halmisae.entity.User.ClosingOrder;
import com.halmisae.entity.User.Favorite;
import com.halmisae.entity.User.Rating;
import com.halmisae.entity.User.Reservation;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storeNumber;
    private String id;
    private String password;
    private String name;
    private String phone;
    private String businessNumber;
    private String email;
    private String storeName;
    private String image;
    private float averageRating;
    private String address;
    private String storePhone;
    private String weekdayOpen;
    private String weekdayClose;
    private String weekendOpen;
    private String weekendClose;
    private String breakStart;
    private String breakEnd;
    private LocalDateTime registDate;
    private Status status;
    private int penaltyNumber;
    @OneToMany(mappedBy = "store")
    private List<Sales> sales;
    @OneToMany(mappedBy = "store")
    private List<StoreHoliday> storeHoliday;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "store")
    private ReservationDiscount reservationDiscount;
    @OneToMany(mappedBy = "store")
    private List<NoShowFood> noShowFood;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "store")
    private ClosingDiscount closingDiscount;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "store")
    private ClosingFood closingFood;
    @OneToMany(mappedBy = "store")
    private List<Menu> menu;
    @OneToMany(mappedBy = "store")
    private List<Favorite> favorite;
    @OneToMany(mappedBy = "store")
    private List<Rating> rating;
    @OneToMany(mappedBy = "store")
    private List<ClosingOrder> closingOrder;
    @OneToMany(mappedBy = "store")
    private List<Reservation> reservation;
}
