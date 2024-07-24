package com.halmisae.service.store;

import com.halmisae.dto.store.ClosingOrderProcessingReadDTO;
import com.halmisae.dto.store.MenuDTO;
import com.halmisae.dto.store.ReservationProcessingReadDTO;
import com.halmisae.dto.user.ReserveMenuCreateDTO;
import com.halmisae.dto.user.ReserveMenuResponseDTO;
import com.halmisae.entity.Enum.DoneType;
import com.halmisae.entity.Enum.OrderType;
import com.halmisae.entity.Store.Menu;
import com.halmisae.entity.User.ClosingOrder;
import com.halmisae.entity.User.Reservation;
import com.halmisae.entity.User.ReserveMenu;
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
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SalesServiceImpl implements SalesService{
    private final ClosingOrderRepository closingOrderRepository;
    private final ReservationRepository reservationRepository;
    private final ReserveMenuRepository reserveMenuRepository;
    private final SalesRepository salesRepository;

    @Override
    // GET 월별 매출 데이터 보기
    public List<Object> readSales(int storeNumber, LocalDateTime month) {
        List<Object> monthlySales = new ArrayList<>();
        int y = month.getYear();
        int m = month.getMonthValue();
        YearMonth ym = YearMonth.of(y, m);
        List<DoneType> doneTypes = new ArrayList<>();
        doneTypes.add(DoneType.COMPLETE);
        doneTypes.add(DoneType.OVER_TIME);
        List<ClosingOrder> closingOrders = closingOrderRepository.findByVisitTimeAndStoreNumberAndDoneType(storeNumber, ym.atDay(1).atStartOfDay(), ym.atEndOfMonth().atTime(LocalTime.MAX), doneTypes);
        List<Reservation> reservations = reservationRepository.findByVisitTimeAndStoreNumberAndDoneType(storeNumber, ym.atDay(1).atStartOfDay(), ym.atEndOfMonth().atTime(LocalTime.MAX), doneTypes);
        for (Reservation r : reservations) {
            List<MenuDTO> menu = new ArrayList<>();
            for (ReserveMenu rm : r.getReserveMenu()) {
                Menu gm = rm.getMenu();
                MenuDTO md = new MenuDTO(gm.getMenuNumber(), gm.getMenuName(), gm.getPrice(), gm.getIntroduction(), gm.getImage(), gm.getStore().getStoreNumber());
                menu.add(md);
            }
            ReservationProcessingReadDTO rpr = new ReservationProcessingReadDTO(r.getReserveNumber(), r.getReserveTime(), r.getVisitTime(), r.getUseTime(), r.getPeople(), r.getTotalPrice(), r.getOrderType(), r.getRequestStatus(), null, r.getStore().getStoreNumber(), menu);
            monthlySales.add(rpr);
        }
        for (ClosingOrder co : closingOrders) {
            ClosingOrderProcessingReadDTO copr = new ClosingOrderProcessingReadDTO(co.getOrderNumber(), co.getQuantity(), co.getTotalPrice(), OrderType.CLOSING_ORDER, co.getOrderDate(), co.getRequestStatus(), null, co.getStore().getStoreNumber());
            monthlySales.add(copr);
        }
        return monthlySales;
    }
}