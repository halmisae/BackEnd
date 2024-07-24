package com.halmisae.service.store;

import com.halmisae.dto.store.*;
import com.halmisae.dto.user.ClosingOrderDTO;
import com.halmisae.dto.user.ReservationDTO;
import com.halmisae.dto.user.ReserveMenuCreateDTO;
import com.halmisae.dto.user.ReserveMenuResponseDTO;
import com.halmisae.entity.Enum.DoneType;
import com.halmisae.entity.Enum.OrderType;
import com.halmisae.entity.Enum.RequestStatus;
import com.halmisae.entity.Store.*;
import com.halmisae.entity.User.ClosingOrder;
import com.halmisae.entity.User.Reservation;
import com.halmisae.entity.User.ReserveMenu;
import com.halmisae.repository.store.ClosingFoodRepository;
import com.halmisae.repository.store.MenuRepository;
import com.halmisae.repository.store.NoShowFoodRepository;
import com.halmisae.repository.store.SalesRepository;
import com.halmisae.repository.user.ClosingOrderRepository;
import com.halmisae.repository.user.ReservationRepository;
import com.halmisae.repository.user.ReserveMenuRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProcessingServiceImpl implements ProcessingService {
    private final ClosingOrderRepository closingOrderRepository;
    private final ReservationRepository reservationRepository;
    private final ReserveMenuRepository reserveMenuRepository;
    private final MenuRepository menuRepository;
    private final SalesRepository salesRepository;
    private final NoShowFoodRepository noShowFoodRepository;
    private final ClosingFoodRepository closingFoodRepository;

    // SalesRepository 키 값 AUTO_INCREMENT 중복 처리 코드
//    @PersistenceContext
//    private EntityManager entityManager;
//    @Override
//    @Transactional
//    public void init() {
//        Query maxIdQuery = entityManager.createNativeQuery("SELECT MAX(sales_number) FROM sales");
//        Integer maxId = (Integer) maxIdQuery.getSingleResult();
//        if (maxId == null) {
//            maxId = 0;
//        }
//
//        // Reset the AUTO_INCREMENT value
//        Query resetAutoIncrementQuery = entityManager.createNativeQuery("ALTER TABLE sales AUTO_INCREMENT = :nextId");
//        resetAutoIncrementQuery.setParameter("nextId", maxId + 1);
//        resetAutoIncrementQuery.executeUpdate();
////        int maxSalesNumber = salesRepository.findMaxSalesNumber();
////        int newAutoIncrementValue = maxSalesNumber + 1;
////        salesRepository.resetAutoIncrement(newAutoIncrementValue);
//    }


    @Override
    // GET 오늘의 예약, 마감 주문 전체 보기 (ClosingOrder, Reservation)
    public List<Object> readDailySchedule(int storeNumber) {
        // 오늘것만 가지고 오도록 수정하기
        List<Object> dailySchedule = new ArrayList<>();
        LocalDate today = LocalDate.now();
        List<ClosingOrder> closingOrders = closingOrderRepository.findByVisitTimeAndStoreNumber(storeNumber, today.atStartOfDay(), today.atTime(LocalTime.MAX));
        List<Reservation> reservations = reservationRepository.findByVisitTimeAndStoreNumber(storeNumber, today.atStartOfDay(), today.atTime(LocalTime.MAX));
        List<Reservation> newR = reservationRepository.findByReserveTimeAndStoreNumber(storeNumber, today.atStartOfDay(), today.atTime(LocalTime.MAX));
        for (ClosingOrder co : closingOrders) {
            ClosingOrderProcessingReadDTO copr = new ClosingOrderProcessingReadDTO(co.getOrderNumber(), co.getQuantity(), co.getTotalPrice(), OrderType.CLOSING_ORDER, co.getOrderDate(), co.getRequestStatus(), null, co.getStore().getStoreNumber());
            dailySchedule.add(copr);
        }
        for (Reservation r : reservations) {
            List<ReserveMenuCreateDTO> reserveMenus = reserveMenuRepository.findAllByReserveNumber(r.getReserveNumber());
            ReservationProcessingReadDTO rpr = new ReservationProcessingReadDTO(r.getReserveNumber(), r.getReserveTime(), r.getVisitTime(), r.getUseTime(), r.getPeople(), r.getTotalPrice(), r.getOrderType(), r.getRequestStatus(), null, r.getStore().getStoreNumber(), reserveMenus);
            dailySchedule.add(rpr);
        }
        for (Reservation r : newR) {
            List<ReserveMenuCreateDTO> reserveMenus = reserveMenuRepository.findAllByReserveNumber(r.getReserveNumber());
            ReservationProcessingReadDTO rpr = new ReservationProcessingReadDTO(r.getReserveNumber(), r.getReserveTime(), r.getVisitTime(), r.getUseTime(), r.getPeople(), r.getTotalPrice(), r.getOrderType(), r.getRequestStatus(), null, r.getStore().getStoreNumber(), reserveMenus);
            dailySchedule.add(rpr);
        }
        return dailySchedule;
    }

    @Override
    // POST 신규 마감할인상품 수락
    public ClosingOrderDTO closingOrderAccept(int orderNumber) {
        ClosingOrder closingOrder = closingOrderRepository.findById(orderNumber).get();
        closingOrder.setRequestStatus(RequestStatus.ACCEPT);
        ClosingOrder sco = closingOrderRepository.save(closingOrder);
        return new ClosingOrderDTO(sco.getOrderNumber(), sco.getQuantity(), sco.getTotalPrice(), sco.getOrderDate(), sco.getRequestStatus(), DoneType.NOT_YET, null, null, sco.getStore().getStoreNumber());
    }

    @Override
    // POST 신규 마감할인상품 거절
    public ClosingOrderDTO closingOrderReject(ClosingOrderRejectRequestDTO corr) {
        ClosingOrder closingOrder = closingOrderRepository.findById(corr.getOrderNumber()).get();
        closingOrder.setRequestStatus(RequestStatus.REJECT);
        ClosingOrder sco = closingOrderRepository.save(closingOrder);
        return new ClosingOrderDTO(sco.getOrderNumber(), sco.getQuantity(), sco.getTotalPrice(), sco.getOrderDate(), sco.getRequestStatus(), DoneType.NOT_YET, corr.getRejectMessage(), null, sco.getStore().getStoreNumber());
    }

    @Override
    // POST 신규 예약 수락;
    public ReservationDTO reservationAccept(int reserveNumber) {
        Reservation r = reservationRepository.findById(reserveNumber).get();
        OrderType orderType = r.getOrderType();
        if (orderType == OrderType.RESERVATION) {
            // 신규 예약일 경우
            r.setRequestStatus(RequestStatus.ACCEPT);
            Reservation sr = reservationRepository.save(r);
            List<ReserveMenuResponseDTO> rmList = new ArrayList<>();
            List<MenuDTO> mList = new ArrayList<>();
            for (ReserveMenu rm : r.getReserveMenu()) {
                ReserveMenuResponseDTO rmr = new ReserveMenuResponseDTO(r.getReserveNumber(), rm.getMenu().getMenuNumber(), rm.getQuantity());
                rmList.add(rmr);
                Menu menu = menuRepository.findById(rmr.getMenuNumber()).get();
                MenuDTO m = new MenuDTO(menu.getMenuNumber(), menu.getMenuName(), menu.getPrice(), menu.getIntroduction(), menu.getImage(), menu.getStore().getStoreNumber());
                mList.add(m);
            }
            return new ReservationDTO(r.getReserveTime(), r.getVisitTime(), r.getUseTime(), r.getPeople(), r.getTotalPrice(), r.getOrderType(), r.getRequestStatus(), null, null, r.getStore().getStoreNumber(), rmList, mList);
        } else {
            // 노쇼 예약일 경우
//            List<ReserveMenu> originalrmList = r.getReserveMenu();
//            List<MenuDTO> mList = new ArrayList<>();
//            for (ReserveMenu rm : r.getReserveMenu()) {
//                ReserveMenuResponseDTO rmr = new ReserveMenuResponseDTO(r.getReserveNumber(), rm.getMenu().getMenuNumber(), rm.getQuantity());
//                rmList.add(rmr);
//                Menu menu = menuRepository.findById(rmr.getMenuNumber()).get();
//                MenuDTO m = new MenuDTO(menu.getMenuNumber(), menu.getMenuName(), menu.getPrice(), menu.getIntroduction(), menu.getImage(), menu.getStore().getStoreNumber());
//                mList.add(m);
//            }
//            return new ReservationDTO(r.getReserveTime(), r.getVisitTime(), r.getUseTime(), r.getPeople(), r.getTotalPrice(), r.getOrderType(), r.getRequestStatus(), null, r.getUser().getEmail(), r.getStore().getStoreNumber(), r.getReserveMenu(),);
            return null;
        }
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
        return new ReservationDTO(sr.getReserveTime(), sr.getVisitTime(), sr.getUseTime(), sr.getPeople(), sr.getTotalPrice(), sr.getOrderType(), sr.getRequestStatus(), null, null, sr.getStore().getStoreNumber(), rmList, mList);
    }

    @Override
    // POST 진행중 예약 완료;
    public ReservationDoneResponseDTO reservationDone(int reserveNumber) {
        // 진행중 Accept 인지 확인
        Reservation reservation = reservationRepository.findById(reserveNumber).get();

        // 이용시간 초과 계산
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime visitTime = reservation.getVisitTime();
        LocalDateTime leaveTime = visitTime.plusMinutes(reservation.getUseTime());
        DoneType doneType = null;
        if (now.isAfter(leaveTime)) doneType = DoneType.OVER_TIME;
        else doneType = DoneType.COMPLETE;

        List<ReserveMenuResponseDTO> rmList = new ArrayList<>();
        List<MenuDTO> mList = new ArrayList<>();
        for (ReserveMenu rm : reservation.getReserveMenu()) {
            ReserveMenuResponseDTO rmr = new ReserveMenuResponseDTO(reservation.getReserveNumber(), rm.getMenu().getMenuNumber(), rm.getQuantity());
            rmList.add(rmr);
            Menu menu = menuRepository.findById(rmr.getMenuNumber()).get();
            MenuDTO m = new MenuDTO(menu.getMenuNumber(), menu.getMenuName(), menu.getPrice(), menu.getIntroduction(), menu.getImage(), menu.getStore().getStoreNumber());
            mList.add(m);
        }
        // 판매 내역에 추가
        Sales sales = new Sales(0, reservation.getTotalPrice(), now, OrderType.RESERVATION, doneType, null, reservation, reservation.getStore());
        salesRepository.save(sales);
        reservation.setSales(sales);
        reservationRepository.save(reservation);
        return new ReservationDoneResponseDTO(reservation.getReserveTime(), visitTime, reservation.getUseTime(), reservation.getPeople(), reservation.getTotalPrice(), reservation.getOrderType(), reservation.getRequestStatus(), doneType, null, reservation.getStore().getStoreNumber(), rmList, mList);
    }

    @Override
    // POST 진행중 예약 노쇼;
    public ReservationNoShowResponseDTO reservationNoShow(int reserveNumber) {
        // 진행중 Accept 인지 우선 확인, 시간이 초과되었는지 확인하기
        Reservation reservation = reservationRepository.findById(reserveNumber).get();

        List<ReserveMenuResponseDTO> rmList = new ArrayList<>();
        List<MenuDTO> mList = new ArrayList<>();
        for (ReserveMenu rm : reservation.getReserveMenu()) {
            ReserveMenuResponseDTO rmr = new ReserveMenuResponseDTO(reservation.getReserveNumber(), rm.getMenu().getMenuNumber(), rm.getQuantity());
            rmList.add(rmr);
            Menu menu = menuRepository.findById(rmr.getMenuNumber()).get();
            // 노쇼 수량 더하기
            menu.setNoShowCount(menu.getNoShowCount() + 1);
            menuRepository.save(menu);
            MenuDTO m = new MenuDTO(menu.getMenuNumber(), menu.getMenuName(), menu.getPrice(), menu.getIntroduction(), menu.getImage(), menu.getStore().getStoreNumber());
            mList.add(m);
        }
//        // 판매 내역에 추가
//        Sales sales = new Sales(0, reservation.getTotalPrice(), LocalDateTime.now(), OrderType.RESERVATION, DoneType.NO_SHOW, null, reservation, reservation.getStore());
//        salesRepository.save(sales);
        // 노쇼 상품에 추가
        NoShowFood noShowFood = new NoShowFood(LocalDateTime.now(), new NoShowFoodID(reservation.getStore().getStoreNumber(), reserveNumber), reservation.getStore(), reservation);
        noShowFoodRepository.save(noShowFood);
        // 예약 내역의 요청 상태 노쇼로 바꾸기
        reservation.setRequestStatus(RequestStatus.NOT_YET);
        reservationRepository.save(reservation);
        return new ReservationNoShowResponseDTO(reservation.getReserveTime(), reservation.getVisitTime(), reservation.getUseTime(), reservation.getPeople(), reservation.getTotalPrice(), reservation.getOrderType(), reservation.getRequestStatus(), DoneType.NO_SHOW, null, reservation.getStore().getStoreNumber(), rmList, mList);
    }

    @Override
    // POST 마감할인상품 완료;
    public ClosingOrderDTO closingOrderDone(int orderNumber) {
        ClosingOrder closingOrder = closingOrderRepository.findById(orderNumber).get();
        // 판매 내역에 추가
        Sales sales = new Sales(0, closingOrder.getTotalPrice(), LocalDateTime.now(), OrderType.CLOSING_ORDER, DoneType.COMPLETE, closingOrder, null, closingOrder.getStore());
        salesRepository.save(sales);
        closingOrder.setSales(sales);
        closingOrderRepository.save(closingOrder);
        return new ClosingOrderDTO(closingOrder.getOrderNumber(), closingOrder.getQuantity(), closingOrder.getTotalPrice(), closingOrder.getOrderDate(), closingOrder.getRequestStatus(), DoneType.COMPLETE, null, null, closingOrder.getStore().getStoreNumber());
    }

    @Override
    // POST 마감할인상품 노쇼;
    public ClosingOrderDTO closingOrderNoShow(int orderNumber) {
        ClosingOrder closingOrder = closingOrderRepository.findById(orderNumber).get();
//        // 판매 내역에 추가
//        Sales sales = new Sales(0, closingOrder.getTotalPrice(), LocalDateTime.now(), OrderType.CLOSING_ORDER, DoneType.NO_SHOW, closingOrder, null, closingOrder.getStore());
//        salesRepository.save(sales);
        // 마감할인상품 개수 더하기
        int originalQuantity = closingOrder.getStore().getClosingFood().getQuantity();
        closingOrder.getStore().getClosingFood().setQuantity(originalQuantity + closingOrder.getQuantity());
        closingOrder.setRequestStatus(RequestStatus.NOT_YET);
        closingOrderRepository.save(closingOrder);
        return new ClosingOrderDTO(closingOrder.getOrderNumber(), closingOrder.getQuantity(), closingOrder.getTotalPrice(), closingOrder.getOrderDate(), closingOrder.getRequestStatus(), DoneType.NO_SHOW, null, null, closingOrder.getStore().getStoreNumber());
    }

    @Override
    // PUT 마감할인상품 주문 취소하기
    public ClosingOrderDTO deleteClosingOrder(int orderNumber) {
        // Accept 만 취소하기로 변경
        ClosingOrder closingOrder = closingOrderRepository.findById(orderNumber).get();
        closingOrder.setRequestStatus(RequestStatus.REJECT);
        ClosingOrder sco = closingOrderRepository.save(closingOrder);
        ClosingFood closingFood = closingFoodRepository.findById(sco.getStore().getStoreNumber()).get();
        closingFood.setQuantity(closingFood.getQuantity() + sco.getQuantity());
        closingFoodRepository.save(closingFood);
        return new ClosingOrderDTO(sco.getOrderNumber(), sco.getQuantity(), sco.getTotalPrice(), sco.getOrderDate(), sco.getRequestStatus(), null, "가게측의 사정에 의하여 주문을 취소하였습니다.", null, sco.getStore().getStoreNumber());
    }
}
