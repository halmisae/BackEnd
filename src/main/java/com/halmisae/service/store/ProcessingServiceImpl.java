package com.halmisae.service.store;

import com.halmisae.dto.store.*;
import com.halmisae.dto.user.ClosingOrderDTO;
import com.halmisae.dto.user.ReservationDTO;
import com.halmisae.dto.user.ReserveMenuCreateDTO;
import com.halmisae.dto.user.ReserveMenuResponseDTO;
import com.halmisae.entity.Enum.DoneType;
import com.halmisae.entity.Enum.OrderType;
import com.halmisae.entity.Enum.RequestStatus;
import com.halmisae.entity.Store.Menu;
import com.halmisae.entity.Store.Sales;
import com.halmisae.entity.User.ClosingOrder;
import com.halmisae.entity.User.Reservation;
import com.halmisae.entity.User.ReserveMenu;
import com.halmisae.repository.store.MenuRepository;
import com.halmisae.repository.store.SalesRepository;
import com.halmisae.repository.user.ClosingOrderRepository;
import com.halmisae.repository.user.ReservationRepository;
import com.halmisae.repository.user.ReserveMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProcessingServiceImpl implements ProcessingService{
    private final ClosingOrderRepository closingOrderRepository;
    private final ReservationRepository reservationRepository;
    private final ReserveMenuRepository reserveMenuRepository;
    private final MenuRepository menuRepository;
    private final SalesRepository salesRepository;


    @Override
    // GET 오늘의 예약, 마감 주문 전체 보기 (ClosingOrder, Reservation)
    public List<Object> readDailySchedule(int storeNumber) {
        List<Object> dailySchedule = new ArrayList<>();
        List<ClosingOrderProcessingReadDTO> closingOrders = closingOrderRepository.findAllByStoreNumber(storeNumber);
        List<Reservation> reservations = reservationRepository.findAllByStoreNumber(storeNumber);
        for (Reservation r :reservations) {
            List<ReserveMenuCreateDTO> reserveMenus = reserveMenuRepository.findAllByReserveNumber(r.getReserveNumber());
            ReservationProcessingReadDTO rpr = new ReservationProcessingReadDTO(r.getReserveNumber(), r.getReserveTime(), r.getVisitTime(), r.getUseTime(), r.getPeople(), r.getTotalPrice(), r.getOrderType(), r.getRequestStatus(), r.getUser().getEmail(), r.getStore().getStoreNumber(), reserveMenus);
            dailySchedule.add(rpr);
        }
        dailySchedule.addAll(closingOrders);
        return dailySchedule;
    }

    @Override
    // POST 신규 마감할인상품 수락
    public ClosingOrderDTO closingOrderAccept(int orderNumber) {
        ClosingOrder closingOrder = closingOrderRepository.findById(orderNumber).get();
        closingOrder.setRequestStatus(RequestStatus.ACCEPT);
        ClosingOrder sco = closingOrderRepository.save(closingOrder);
        return new ClosingOrderDTO(sco.getOrderNumber(), sco.getQuantity(), sco.getTotalPrice(), sco.getOrderDate(), sco.getRequestStatus(), null, sco.getUser().getEmail(), sco.getStore().getStoreNumber());
    }

    @Override
    // POST 신규 마감할인상품 거절
    public ClosingOrderDTO closingOrderReject(ClosingOrderRejectRequestDTO corr) {
        ClosingOrder closingOrder = closingOrderRepository.findById(corr.getOrderNumber()).get();
        closingOrder.setRequestStatus(RequestStatus.REJECT);
        ClosingOrder sco = closingOrderRepository.save(closingOrder);
        return new ClosingOrderDTO(sco.getOrderNumber(), sco.getQuantity(), sco.getTotalPrice(), sco.getOrderDate(), sco.getRequestStatus(), corr.getRejectMessage(), sco.getUser().getEmail(), sco.getStore().getStoreNumber());
    }

    @Override
    // POST 신규 예약 수락;
    public ReservationDTO reservationAccept(int reserveNumber) {
        Reservation reservation = reservationRepository.findById(reserveNumber).get();
        reservation.setRequestStatus(RequestStatus.ACCEPT);
        Reservation sr = reservationRepository.save(reservation);
        List<ReserveMenuResponseDTO> rmList = new ArrayList<>();
        List<MenuDTO> mList = new ArrayList<>();
        for (ReserveMenu rm : sr.getReserveMenu()) {
            ReserveMenuResponseDTO rmr = new ReserveMenuResponseDTO(sr.getReserveNumber(), rm.getMenu().getMenuNumber(), rm.getQuantity());
            rmList.add(rmr);
            Menu menu = menuRepository.findById(rmr.getMenuNumber()).get();
            MenuDTO m = new MenuDTO(menu.getMenuNumber(), menu.getMenuName(), menu.getPrice(), menu.getIntroduction(), menu.getImage(), menu.getStore().getStoreNumber());
            mList.add(m);
        }
        return new ReservationDTO(sr.getReserveTime(), sr.getVisitTime(), sr.getUseTime(), sr.getPeople(), sr.getTotalPrice(), sr.getOrderType(), sr.getRequestStatus(), null, sr.getUser().getEmail(), sr.getStore().getStoreNumber(), rmList, mList);
    }

    @Override
    // POST 신규 예약 거절;
    public ReservationDTO reservationReject(ReservationRejectRequestDTO rrr) {
        Reservation reservation = reservationRepository.findById(rrr.getReserveNumber()).get();
        reservation.setRequestStatus(RequestStatus.REJECT);
        Reservation sr = reservationRepository.save(reservation);
        List<ReserveMenuResponseDTO> rmList = new ArrayList<>();
        List<MenuDTO> mList = new ArrayList<>();
        for (ReserveMenu rm : sr.getReserveMenu()) {
            ReserveMenuResponseDTO rmr = new ReserveMenuResponseDTO(sr.getReserveNumber(), rm.getMenu().getMenuNumber(), rm.getQuantity());
            rmList.add(rmr);
            Menu menu = menuRepository.findById(rmr.getMenuNumber()).get();
            MenuDTO m = new MenuDTO(menu.getMenuNumber(), menu.getMenuName(), menu.getPrice(), menu.getIntroduction(), menu.getImage(), menu.getStore().getStoreNumber());
            mList.add(m);
        }
        return new ReservationDTO(sr.getReserveTime(), sr.getVisitTime(), sr.getUseTime(), sr.getPeople(), sr.getTotalPrice(), sr.getOrderType(), sr.getRequestStatus(), null, sr.getUser().getEmail(), sr.getStore().getStoreNumber(), rmList, mList);
    }

    @Override
    // POST 진행중 예약 완료;
    public ReservationDoneResponseDTO reservationDone(int reserveNumber) {
        Reservation reservation = reservationRepository.findById(reserveNumber).get();

        // 이용시간 초과 계산
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime visitTime = reservation.getVisitTime();
        LocalDateTime leaveTime = visitTime.plusMinutes(reservation.getUseTime());
        DoneType doneType = null;
        if (now.isAfter(leaveTime)) doneType = DoneType.OVER_TIME;
        else doneType = DoneType.COMPLETE;

        Reservation sr = reservationRepository.save(reservation);
        List<ReserveMenuResponseDTO> rmList = new ArrayList<>();
        List<MenuDTO> mList = new ArrayList<>();
        for (ReserveMenu rm : sr.getReserveMenu()) {
            ReserveMenuResponseDTO rmr = new ReserveMenuResponseDTO(sr.getReserveNumber(), rm.getMenu().getMenuNumber(), rm.getQuantity());
            rmList.add(rmr);
            Menu menu = menuRepository.findById(rmr.getMenuNumber()).get();
            MenuDTO m = new MenuDTO(menu.getMenuNumber(), menu.getMenuName(), menu.getPrice(), menu.getIntroduction(), menu.getImage(), menu.getStore().getStoreNumber());
            mList.add(m);
        }
        // 판매 내역에 추가해야함
        Sales sales = new Sales(0, reservation.getTotalPrice(), now, OrderType.RESERVATION, doneType, null, reservation, reservation.getStore());
        salesRepository.save(sales);
        return new ReservationDoneResponseDTO(sr.getReserveTime(), visitTime, sr.getUseTime(), sr.getPeople(), sr.getTotalPrice(), sr.getOrderType(), sr.getRequestStatus(), doneType, sr.getUser().getEmail(), sr.getStore().getStoreNumber(), rmList, mList);
    }

    @Override
    // POST 진행중 예약 노쇼;
    public ReservationDTO reservationNoShow(int reserveNumber) {
        return null;
    }

    @Override
    // POST 마감할인상품 완료;
    public ClosingOrderDTO closingOrderDone(int orderNumber) {
        return null;
    }

    @Override
    // POST 마감할인상품 노쇼;
    public ClosingOrderDTO closingOrderNoShow(int orderNumber) {
        return null;
    }
}
