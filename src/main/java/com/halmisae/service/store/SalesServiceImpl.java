package com.halmisae.service.store;

import com.halmisae.dto.store.ClosingOrderProcessingReadDTO;
import com.halmisae.dto.store.ReservationProcessingReadDTO;
import com.halmisae.dto.user.ReserveMenuCreateDTO;
import com.halmisae.entity.Enum.OrderType;
import com.halmisae.entity.User.ClosingOrder;
import com.halmisae.entity.User.Reservation;
import com.halmisae.repository.store.SalesRepository;
import com.halmisae.repository.user.ClosingOrderRepository;
import com.halmisae.repository.user.ReservationRepository;
import com.halmisae.repository.user.ReserveMenuRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SalesServiceImpl implements SalesService{
    private final ClosingOrderRepository closingOrderRepository;
    private final ReservationRepository reservationRepository;
    private final ReserveMenuRepository reserveMenuRepository;

    @Override
    // GET 월별 매출 데이터 보기
    public List<Object> readSales(int storeNumber, LocalDateTime month) {
        List<Object> monthlySales = new ArrayList<>();
        LocalDate date = month.toLocalDate();
        List<ClosingOrder> closingOrders = closingOrderRepository.findByVisitTimeAndStoreNumber(storeNumber, date.atStartOfDay(), date.atTime(LocalTime.MAX));
        List<Reservation> reservations = reservationRepository.findByVisitTimeAndStoreNumber(storeNumber, date.atStartOfDay(), date.atTime(LocalTime.MAX));
        for (Reservation r : reservations) {
            List<ReserveMenuCreateDTO> reserveMenus = reserveMenuRepository.findAllByReserveNumber(r.getReserveNumber());
            ReservationProcessingReadDTO rpr = new ReservationProcessingReadDTO(r.getReserveNumber(), r.getReserveTime(), r.getVisitTime(), r.getUseTime(), r.getPeople(), r.getTotalPrice(), r.getOrderType(), r.getRequestStatus(), null, r.getStore().getStoreNumber(), reserveMenus);
            monthlySales.add(rpr);
        }
        for (ClosingOrder co : closingOrders) {
            ClosingOrderProcessingReadDTO copr = new ClosingOrderProcessingReadDTO(co.getOrderNumber(), co.getQuantity(), co.getTotalPrice(), OrderType.CLOSING_ORDER, co.getOrderDate(), co.getRequestStatus(), null, co.getStore().getStoreNumber());
            monthlySales.add(copr);
        }
        return monthlySales;
    }
}