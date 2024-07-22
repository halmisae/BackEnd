package com.halmisae.service.store;

import com.halmisae.dto.store.MenuDTO;
import com.halmisae.dto.store.ReadMonthlyScheduleResponseDTO;
import com.halmisae.dto.store.ReadScheduleRequestDTO;
import com.halmisae.dto.user.*;
import com.halmisae.entity.Enum.RequestStatus;
import com.halmisae.entity.Store.Menu;
import com.halmisae.entity.User.ClosingOrder;
import com.halmisae.entity.User.Reservation;
import com.halmisae.entity.User.ReserveMenu;
import com.halmisae.repository.user.ClosingOrderRepository;
import com.halmisae.repository.user.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationScheduleServiceImpl implements ReservationScheduleService {
    private final ReservationRepository reservationRepository;
    private final ClosingOrderRepository closingOrderRepository;

    @Override
    // GET 날짜별 예약 보기
    public List<ReadMonthlyScheduleResponseDTO> readMonthlySchedule(ReadScheduleRequestDTO today) {
        List<ReadMonthlyScheduleResponseDTO> reserveList = new ArrayList<>();
        for (int i = 0; i < 90; i++) {
            int cnt = (reservationRepository.countByVisitTimeAndStoreNumber(today.getStoreNumber(), today.getDay().toLocalDate().atStartOfDay(), today.getDay().toLocalDate().atTime(LocalTime.MAX))).intValue();
            ReadMonthlyScheduleResponseDTO rmsr = new ReadMonthlyScheduleResponseDTO(today.getDay().toLocalDate(), cnt);
            reserveList.add(rmsr);
            today.getDay().plusDays(1);
        }
        return reserveList;
    }

    @Override
    // GET 해당 날짜의 예약 보기
    public List<ReservationDTO> readDailySchedule(ReadScheduleRequestDTO day) {
        List<ReservationDTO> reserveList = new ArrayList<>();

        List<Reservation> reservations = reservationRepository.findByVisitTimeAndStoreNumber(day.getStoreNumber(), day.getDay().toLocalDate().atStartOfDay(), day.getDay().toLocalDate().atTime(LocalTime.MAX));
        for (Reservation r : reservations) {
            List<ReserveMenuResponseDTO> reserveMenu = new ArrayList<>();
            List<MenuDTO> menu = new ArrayList<>();
            for (ReserveMenu rm : r.getReserveMenu()) {
                ReserveMenuResponseDTO rmr = new ReserveMenuResponseDTO(rm.getReservation().getReserveNumber(), rm.getMenu().getMenuNumber(), rm.getQuantity());
                reserveMenu.add(rmr);
                Menu gm = rm.getMenu();
                MenuDTO m = new MenuDTO(gm.getMenuNumber(), gm.getMenuName(), gm.getPrice(), gm.getIntroduction(), gm.getImage(), gm.getStore().getStoreNumber());
                menu.add(m);
            }
            ReservationDTO rd = new ReservationDTO(r.getReserveTime(), r.getVisitTime(), r.getUseTime(), r.getPeople(), r.getTotalPrice(), r.getOrderType(), r.getRequestStatus(), null, r.getUser().getEmail(), r.getStore().getStoreNumber(), reserveMenu, menu);
            reserveList.add(rd);
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
        return new ReservationDTO(sr.getReserveTime(), sr.getVisitTime(), sr.getUseTime(), sr.getPeople(), sr.getTotalPrice(), sr.getOrderType(), sr.getRequestStatus(), "가게측의 사정에 의하여 예약을 취소했습니다.", sr.getUser().getEmail(), sr.getStore().getStoreNumber(), reserveMenu, menu);
    }

    @Override
    // PUT 마감할인상품 주문 취소하기
    public ClosingOrderDTO deleteClosingOrder(int orderNumber) {
        ClosingOrder closingOrder = closingOrderRepository.findById(orderNumber).get();
        closingOrder.setRequestStatus(RequestStatus.REJECT);
        ClosingOrder sco = closingOrderRepository.save(closingOrder);
        return new ClosingOrderDTO(sco.getOrderNumber(), sco.getQuantity(), sco.getTotalPrice(), sco.getOrderDate(), sco.getRequestStatus(), null, "가게측의 사정에 의하여 주문을 취소하였습니다.", sco.getUser().getEmail(), sco.getStore().getStoreNumber());
    }
}
