package com.halmisae.service.store;

import com.halmisae.dto.store.ClosingOrderProcessingReadDTO;
import com.halmisae.dto.store.ReservationProcessingReadDTO;
import com.halmisae.dto.user.ClosingOrderDTO;
import com.halmisae.dto.user.ReservationDTO;
import com.halmisae.dto.user.ReserveMenuDTO;
import com.halmisae.entity.User.ClosingOrder;
import com.halmisae.entity.User.Reservation;
import com.halmisae.entity.User.ReserveMenu;
import com.halmisae.repository.user.ClosingOrderRepository;
import com.halmisae.repository.user.ReservationRepository;
import com.halmisae.repository.user.ReserveMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProcessingServiceImpl implements ProcessingService{
    private ClosingOrderRepository closingOrderRepository;
    private ReservationRepository reservationRepository;
    private ReserveMenuRepository reserveMenuRepository;

    @Override
    public List<Object> readDailySchedule(int storeNumber) {
        List<Object> dailySchedule = new ArrayList<>();
        List<ClosingOrderProcessingReadDTO> closingOrders = closingOrderRepository.findAllByStoreNumber(storeNumber);
        List<Reservation> reservations = reservationRepository.findAllByStoreNumber(storeNumber);
        for (Reservation r :reservations) {
            List<ReserveMenuDTO> reserveMenus = reserveMenuRepository.findAllByReserveNumber(r.getReserveNumber());
            ReservationProcessingReadDTO rpr = new ReservationProcessingReadDTO(r.getReserveNumber(), r.getReserveTime(), r.getVisitTime(), r.getUseTime(), r.getPeople(), r.getTotalPrice(), r.getRequestStatus(), r.getUser().getEmail(), r.getStore().getStoreNumber(), reserveMenus);
            dailySchedule.add(rpr);
        }
        dailySchedule.addAll(closingOrders);
        return dailySchedule;
    }

    @Override
    public ClosingOrderDTO closingOrderAccept(int orderNumber) {
        return null;
    }

    @Override
    public ClosingOrderDTO closingOrderReject(int orderNumber) {
        return null;
    }

    @Override
    public ReservationDTO reservationAccept(int reserveNumber) {
        return null;
    }

    @Override
    public ReservationDTO reservationReject(int reserveNumber) {
        return null;
    }

    @Override
    public ReservationDTO reservationDone(int reserveNumber) {
        return null;
    }

    @Override
    public ReservationDTO reservationNoShow(int reserveNumber) {
        return null;
    }

    @Override
    public ClosingOrderDTO closingOrderDone(int orderNumber) {
        return null;
    }

    @Override
    public ClosingOrderDTO closingOrderNoShow(int orderNumber) {
        return null;
    }
}
