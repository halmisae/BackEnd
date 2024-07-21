package com.halmisae.service.user;

import com.halmisae.dto.user.*;
import com.halmisae.entity.Enum.RequestStatus;
import com.halmisae.entity.Store.Menu;
import com.halmisae.entity.Store.Store;
import com.halmisae.entity.User.Reservation;
import com.halmisae.entity.User.ReserveMenu;
import com.halmisae.entity.User.ReserveMenuID;
import com.halmisae.entity.User.User;
import com.halmisae.repository.store.MenuRepository;
import com.halmisae.repository.store.NoShowFoodRepository;
import com.halmisae.repository.store.SalesRepository;
import com.halmisae.repository.store.StoreRepository;
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

    @Override
    public ReservationReadDetailDTO readReservationDetail(ReservationReadDetailRequestDTO rrdr) {
        return null;
    }

    @Override
    public ReservationCreateResponseDTO createReservation(ReservationCreateRequestDTO rr) {
        User user = userRepository.findById(rr.getEmail()).get();
        Store store = storeRepository.findById(rr.getStoreNumber()).get();
        Reservation reservation = new Reservation(0, LocalDateTime.now(), rr.getVisitTime(), rr.getUseTime(), rr.getPeople(), rr.getTotalPrice(), rr.getOrderType(), RequestStatus.NOT_YET, user, store, null, null, null, new ArrayList<>());
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
        return new ReservationCreateResponseDTO(result, r.getUser().getEmail(), r.getStore().getStoreNumber(), r.getVisitTime(), r.getUseTime(), r.getPeople(), r.getTotalPrice(), r.getOrderType(), r.getRequestStatus(), rms);
    }
}
