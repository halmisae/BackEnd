package com.halmisae.service.store;

import com.halmisae.dto.user.ClosingOrderDTO;
import com.halmisae.dto.user.ReservationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProcessingServiceImpl implements ProcessingService{

    @Override
    public List<Object> readDailySchedule(LocalDateTime day) {

        return null;
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
