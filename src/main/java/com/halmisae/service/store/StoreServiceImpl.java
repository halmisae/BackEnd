package com.halmisae.service.store;

import com.halmisae.dto.store.*;
import com.halmisae.entity.Enum.Status;
import com.halmisae.entity.Enum.Weekday;
import com.halmisae.entity.Store.*;
import com.halmisae.repository.store.*;
import com.halmisae.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService{
    private final StoreRepository storeRepository;
    private final ReservationDiscountRepository reservationDiscountRepository;
    private final ClosingDiscountRepository closingDiscountRepository;
    private final ClosingFoodRepository closingFoodRepository;
    private final StoreHolidayRepository storeHolidayRepository;
    private final S3Uploader s3Uploader;

    @Override
    // POST 회원가입
    public StoreCreateResponseDTO createStore(StoreCreateRequestDTO sc) {
        ReservationDiscount reservationDiscount = new ReservationDiscount();
        ClosingDiscount closingDiscount = new ClosingDiscount();
        ClosingFood closingFood = new ClosingFood();

        List<StoreHoliday> storeHoliday = new ArrayList<>();
        Store store = new Store(0, sc.getId(), sc.getPassword(), sc.getName(), sc.getPhone(), sc.getBusinessNumber(), sc.getEmail(), sc.getStoreName(), sc.getImage(), sc.getAverageRating(), sc.getAddress(), sc.getAddressDetail(), sc.getStorePhone(), sc.getWeekdayOpen(), sc.getWeekdayClose(), sc.getWeekendOpen(), sc.getWeekendClose(), sc.getBreakStart(), sc.getBreakEnd(), LocalDateTime.now(), Status.AVAILABLE, 0, new ArrayList<>(), storeHoliday, reservationDiscount, new ArrayList<>(), closingDiscount, closingFood, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Store savedStore = storeRepository.save(store);
        for (Weekday wd : sc.getStoreHoliday()) {
            StoreHoliday sh = new StoreHoliday(new StoreHolidayID(savedStore.getStoreNumber(), wd), savedStore);
            storeHolidayRepository.save(sh);
            storeHoliday.add(sh);
        }
        savedStore.setStoreHoliday(storeHoliday);
        reservationDiscount.setStore(store);
        closingDiscount.setStore(store);
        closingFood.setStore(store);
        reservationDiscountRepository.save(reservationDiscount);
        closingDiscountRepository.save(closingDiscount);
        closingFoodRepository.save(closingFood);
        Store s = storeRepository.save(store);
        boolean result = s.getStoreNumber() > -1;
        return new StoreCreateResponseDTO(result, s.getName(), s.getPhone(), s.getStoreName(), s.getAddress(), s.getAddressDetail(), s.getStorePhone());
    }


    @Override
    // 마이페이지
    // POST 마이페이지 비밀번호 입력 (나중에 인증, 인가로 바꾸기)
    public Boolean passwordCheck(StorePasswordCheckDTO spc) {
        return null;
    }

//    @Override
//    // GET 업주 정보 보기
//    public StoreReadOwnerDTO readStoreOwner(int storeNumber) {
//        return null;
//    }
//
//    @Override
//    // PATCH 업주 정보 수정
//    public StoreUpdateOwnerDTO updateStoreOwner(StoreUpdateOwnerDTO uo) {
//        return null;
//    }

    @Override
    // GET 가게 정보 보기
    public StoreDTO readStore(int storeNumber) {
        Store s = storeRepository.findById(storeNumber).get();
        List<StoreHoliday> storeHoliday = s.getStoreHoliday();
        List<Weekday> wl = new ArrayList<>();
        for (StoreHoliday holiday : storeHoliday)
            wl.add(holiday.getId().getDayOfWeek());
        return new StoreDTO(s.getStoreNumber(), s.getStoreName(), s.getImage(), s.getAddress(), s.getAddressDetail(), s.getStorePhone(), s.getWeekdayOpen(), s.getWeekdayClose(), s.getWeekendOpen(), s.getWeekendClose(),s.getBreakStart(), s.getBreakEnd(), wl);
    }

    @Override
    // PATCH 가게 정보 수정
    public StoreDTO updateStore(StoreUpdateDTO s) throws IOException {
        Store store = storeRepository.findById(s.getStoreNumber()).get();
        store.setStoreName(s.getStoreName());
        store.setAddress(s.getAddress());
        store.setAddressDetail(s.getAddressDetail());
        store.setStorePhone(s.getStorePhone());
        String updatedImage = s3Uploader.upload(s.getImage(), "storeImage");
        store.setImage(updatedImage);
        store.setWeekdayOpen(s.getWeekdayOpen());
        store.setWeekdayClose(s.getWeekdayClose());
        store.setWeekendOpen(s.getWeekendOpen());
        store.setWeekendClose(s.getWeekendClose());
        store.setBreakStart(s.getBreakStart());
        store.setBreakEnd(s.getBreakEnd());
        storeHolidayRepository.deleteAllByStoreNumber(store.getStoreNumber());
        List<StoreHoliday> shl = new ArrayList<>();
        List<Weekday> wl = new ArrayList<>();
        for (Weekday w : s.getStoreHoliday()) {
            StoreHolidayID shid = new StoreHolidayID(s.getStoreNumber(), w);
            StoreHoliday sh = new StoreHoliday(shid, store);
            StoreHoliday ssh = storeHolidayRepository.save(sh);
            shl.add(ssh);
            wl.add(w);
        }
        store.setStoreHoliday(shl);
        Store ss = storeRepository.save(store);
        return new StoreDTO(ss.getStoreNumber(), ss.getStoreName(), ss.getImage(), ss.getAddress(), ss.getAddressDetail(), ss.getStorePhone(), ss.getWeekdayOpen(), ss.getWeekdayClose(), ss.getWeekendOpen(), ss.getWeekendClose(),ss.getBreakStart(), ss.getBreakEnd(), wl);
    }

    @Override
    // DELETE 회원 탈퇴
    public Boolean deleteStore(int storeNumber) {
        return null;
    }
}
