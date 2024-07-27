package com.halmisae.service.user;

import com.halmisae.dto.store.MenuDTO;
import com.halmisae.dto.user.*;
import com.halmisae.entity.Enum.RequestStatus;
import com.halmisae.entity.Enum.Weekday;
import com.halmisae.entity.Store.Menu;
import com.halmisae.entity.Store.ReservationDiscount;
import com.halmisae.entity.Store.Store;
import com.halmisae.entity.Store.StoreHoliday;
import com.halmisae.entity.User.Reservation;
import com.halmisae.entity.User.ReserveMenu;
import com.halmisae.entity.User.ReserveMenuID;
import com.halmisae.entity.User.User;
import com.halmisae.repository.store.*;
import com.halmisae.repository.user.RatingRepository;
import com.halmisae.repository.user.ReservationRepository;
import com.halmisae.repository.user.ReserveMenuRepository;
import com.halmisae.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final ReserveMenuRepository reserveMenuRepository;
    private final MenuRepository menuRepository;
    private final StoreHolidayRepository storeHolidayRepository;

    @Override
    // GET 가게 예약 보기 화면, 날짜 선택 보기
    public ReservationReadDetailDTO readReservationDetail(int storeNumber) {
        Store store = storeRepository.findById(storeNumber).get();
        List<StoreHoliday> shList = storeHolidayRepository.findAllByStoreNumber(storeNumber);
        List<Weekday> shl = new ArrayList<>();
        for (StoreHoliday sh : shList) shl.add(sh.getId().getDayOfWeek());
        List<MenuDTO> ml = menuRepository.findAllByStoreNumber(storeNumber);
        ReservationDiscount reservationDiscount = store.getReservationDiscount();
        int usageTime = reservationDiscount.getUsageTime();
        int unitTime = reservationDiscount.getUnitTime();
        int discount = reservationDiscount.getDiscount();
        int preorderDiscount = reservationDiscount.getPreorderDiscount();
        return new ReservationReadDetailDTO(storeNumber, shl, usageTime, unitTime, discount, preorderDiscount, ml);
    }

    @Override
    // POST 예약완료
    public ReservationCreateResponseDTO createReservation(ReservationCreateRequestDTO rr) {
        Store store = storeRepository.findById(rr.getStoreNumber()).get();
        Reservation reservation = new Reservation(0, LocalDateTime.now(), rr.getVisitTime(), rr.getUseTime(), rr.getPeople(), rr.getTotalPrice(), rr.getOrderType(), RequestStatus.NOT_YET, null, store, null, null, null, new ArrayList<>());
        // 유저가 있을 경우의 예약
//        User user = userRepository.findById(rr.getEmail()).get();
//        Reservation reservation = new Reservation(0, LocalDateTime.now(), rr.getVisitTime(), rr.getUseTime(), rr.getPeople(), rr.getTotalPrice(), rr.getOrderType(), RequestStatus.NOT_YET, user, store, null, null, null, new ArrayList<>());
        Reservation savedReservation = reservationRepository.save(reservation);
        List<ReserveMenu> rmList = new ArrayList<>();
        for (ReserveMenuCreateDTO rmc : rr.getReserveMenu()) {
            Menu menu = menuRepository.findById(rmc.getMenuNumber()).get();
            ReserveMenu rm = new ReserveMenu(rmc.getQuantity(), new ReserveMenuID(savedReservation.getReserveNumber(), rmc.getMenuNumber()), savedReservation, menu);
            reserveMenuRepository.save(rm);
            rmList.add(rm);
        }
        savedReservation.setReserveMenu(rmList);
        Reservation r = reservationRepository.save(savedReservation);
        List<ReserveMenuResponseDTO> rms = new ArrayList<>();
        for (ReserveMenu rm : r.getReserveMenu()) {
            ReserveMenuResponseDTO rmr = new ReserveMenuResponseDTO(rm.getReservation().getReserveNumber(), rm.getMenu().getMenuNumber(), rm.getQuantity());
            rms.add(rmr);
        }
        boolean result = r.getReserveNumber() > -1;
        return new ReservationCreateResponseDTO(result, null, r.getStore().getStoreNumber(), r.getVisitTime(), r.getUseTime(), r.getPeople(), r.getTotalPrice(), r.getOrderType(), r.getRequestStatus(), rms);
    }
}
