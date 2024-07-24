package com.halmisae.service.store;

import com.halmisae.dto.store.MenuDTO;
import com.halmisae.dto.store.ReadMonthlyScheduleResponseDTO;
import com.halmisae.dto.user.*;
import com.halmisae.entity.Enum.RequestStatus;
import com.halmisae.entity.Store.ClosingFood;
import com.halmisae.entity.Store.Menu;
import com.halmisae.entity.User.ClosingOrder;
import com.halmisae.entity.User.Reservation;
import com.halmisae.entity.User.ReserveMenu;
import com.halmisae.repository.store.ClosingFoodRepository;
import com.halmisae.repository.user.ClosingOrderRepository;
import com.halmisae.repository.user.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationScheduleServiceImpl implements ReservationScheduleService {
    private final ReservationRepository reservationRepository;

    @Override
    // GET 날짜별 예약 보기
    public List<ReadMonthlyScheduleResponseDTO> readMonthlySchedule(int storeNumber, LocalDateTime today) {
        List<ReadMonthlyScheduleResponseDTO> reserveList = new ArrayList<>();

        for (int i = 0; i < 90; i++) {
            int cnt = (reservationRepository.countByVisitTimeAndStoreNumberAndRequestStatus(storeNumber, today.toLocalDate().atStartOfDay(), today.toLocalDate().atTime(LocalTime.MAX), RequestStatus.ACCEPT)).intValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = today.format(formatter);
            ReadMonthlyScheduleResponseDTO rmsr = new ReadMonthlyScheduleResponseDTO(formattedDate, today.toLocalDate(), cnt);
            reserveList.add(rmsr);
            today = today.plusDays(1);
        }
        return reserveList;
    }

    @Override
    // GET 해당 날짜의 예약 보기
    public List<ReservationDTO> readDailySchedule(int storeNumber, String date) {
        List<ReservationDTO> reserveList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        LocalDateTime day = localDate.atStartOfDay();

        List<Reservation> reservations = reservationRepository.findByVisitTimeAndStoreNumber(storeNumber, day.toLocalDate().atStartOfDay(), day.toLocalDate().atTime(LocalTime.MAX));
        for (Reservation r : reservations) {
            if (r.getRequestStatus() == RequestStatus.ACCEPT) {
                List<ReserveMenuResponseDTO> reserveMenu = new ArrayList<>();
                List<MenuDTO> menu = new ArrayList<>();
                for (ReserveMenu rm : r.getReserveMenu()) {
                    ReserveMenuResponseDTO rmr = new ReserveMenuResponseDTO(rm.getReservation().getReserveNumber(), rm.getMenu().getMenuNumber(), rm.getQuantity());
                    reserveMenu.add(rmr);
                    Menu gm = rm.getMenu();
                    MenuDTO m = new MenuDTO(gm.getMenuNumber(), gm.getMenuName(), gm.getPrice(), gm.getIntroduction(), gm.getImage(), gm.getStore().getStoreNumber());
                    menu.add(m);
                }
                ReservationDTO rd = new ReservationDTO(r.getReserveTime(), r.getVisitTime(), r.getUseTime(), r.getPeople(), r.getTotalPrice(), r.getOrderType(), r.getRequestStatus(), null, null, r.getStore().getStoreNumber(), reserveMenu, menu);
                reserveList.add(rd);
            }
        }
        return reserveList;
    }

    @Override
    // PUT 예약 취소하기
    public ReservationDTO deleteReservation(int reserveNumber) {
        Reservation reservation = reservationRepository.findById(reserveNumber).get();
        reservation.setRequestStatus(RequestStatus.REJECT);
        Reservation sr = reservationRepository.save(reservation);
        List<ReserveMenuResponseDTO> reserveMenu = new ArrayList<>();
        List<MenuDTO> menu = new ArrayList<>();
        for (ReserveMenu rm : sr.getReserveMenu()) {
            ReserveMenuResponseDTO rmr = new ReserveMenuResponseDTO(rm.getReservation().getReserveNumber(), rm.getMenu().getMenuNumber(), rm.getQuantity());
            reserveMenu.add(rmr);
            Menu gm = rm.getMenu();
            MenuDTO m = new MenuDTO(gm.getMenuNumber(), gm.getMenuName(), gm.getPrice(), gm.getIntroduction(), gm.getImage(), gm.getStore().getStoreNumber());
            menu.add(m);
        }
        return new ReservationDTO(sr.getReserveTime(), sr.getVisitTime(), sr.getUseTime(), sr.getPeople(), sr.getTotalPrice(), sr.getOrderType(), sr.getRequestStatus(), "가게측의 사정에 의하여 예약을 취소했습니다.", null, sr.getStore().getStoreNumber(), reserveMenu, menu);
    }
}
