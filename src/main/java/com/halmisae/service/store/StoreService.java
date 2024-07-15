package com.halmisae.service.store;

import com.halmisae.dto.store.StoreCreateDTO;
import com.halmisae.dto.store.StoreReadDTO;
import com.halmisae.dto.store.StoreUpdateDTO;
import com.halmisae.entity.Enum.Status;
import com.halmisae.entity.Store.*;
import com.halmisae.entity.User.ClosingOrder;
import com.halmisae.entity.User.Favorite;
import com.halmisae.entity.User.Rating;
import com.halmisae.entity.User.Reservation;
import com.halmisae.repository.store.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final SalesRepository salesRepository;
    private final NoShowFoodRepository noShowFoodRepository;
    private final MenuRepository menuRepository;
    private final StoreHolidayRepository storeHolidayRepository;
    private final ReservationDiscountRepository reservationDiscountRepository;
    private final ClosingDiscountRepository closingDiscountRepository;
    private final ClosingFoodRepository closingFoodRepository;


    public Store createStore(StoreCreateDTO sc) {
        List<Sales> sales = new ArrayList<>();
        List<StoreHoliday> storeHoliday = new ArrayList<>();
        ReservationDiscount reservationDiscount = new ReservationDiscount();
        List<NoShowFood> noShowFood = new ArrayList<>();
        ClosingDiscount closingDiscount = new ClosingDiscount();
        ClosingFood closingFood = new ClosingFood();
        List<Menu> menu = new ArrayList<>();
        List<Favorite> favorite = new ArrayList<>();
        List<Rating> rating = new ArrayList<>();
        List<ClosingOrder> closingOrder = new ArrayList<>();
        List<Reservation> reservation = new ArrayList<>();
        Store store = new Store(0, sc.getId(), sc.getPassword(), sc.getName(), sc.getPhone(), sc.getBusinessNumber(), sc.getEmail(), sc.getStoreName(), sc.getAddress(), sc.getStorePhone(), sc.getWeekdayOpen(), sc.getWeekdayClose(), sc.getWeekendOpen(), sc.getWeekendClose(), sc.getBreakStart(), sc.getBreakEnd(), LocalDateTime.now(), Status.AVAILABLE, 0, sales, storeHoliday, reservationDiscount, noShowFood, closingDiscount, closingFood, menu, favorite, rating, closingOrder, reservation);
        reservationDiscountRepository.save(reservationDiscount);
        closingDiscountRepository.save(closingDiscount);
        closingFoodRepository.save(closingFood);
        return storeRepository.save(store);
    }

    public StoreReadDTO readStoreById() {
        return new StoreReadDTO();
    }

    public List<StoreReadDTO> readStoreList() {
        return null;
    }

    public StoreUpdateDTO updateStore() {
        return new StoreUpdateDTO();
    }

    public void deleteStore() {

    }
}
