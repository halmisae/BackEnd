package com.halmisae.service.store;

import com.halmisae.dto.store.ClosingDcAndFoodDTO;
import com.halmisae.dto.store.ReservationDcPreorderDTO;
import com.halmisae.dto.store.ReservationDcUnitTimeDTO;
import com.halmisae.entity.Store.ClosingDiscount;
import com.halmisae.entity.Store.Store;
import com.halmisae.repository.store.ClosingDiscountRepository;
import com.halmisae.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final ClosingDiscountRepository closingDiscountRepository;
    private final StoreRepository storeRepository;

    @Override
    public ReservationDcPreorderDTO readReservationPreOrder(ReservationDcPreorderDTO rdu) {
        return null;
    }

    @Override
    public ReservationDcPreorderDTO updateReservationPreOrder(ReservationDcPreorderDTO rdu) {
        return null;
    }

    @Override
    public ReservationDcUnitTimeDTO readReservationUnit(ReservationDcUnitTimeDTO rdu) {
        return null;
    }

    @Override
    public ReservationDcUnitTimeDTO updateReservationUnit(ReservationDcUnitTimeDTO rdu) {
        return null;
    }

    @Override
    public ClosingDcAndFoodDTO readClosingDiscountAndFood(ClosingDcAndFoodDTO cdf) {
        return null;
    }

    @Override
    public ClosingDcAndFoodDTO updateClosingDiscountAndFood(ClosingDcAndFoodDTO cdf) {
        return null;
    }


//    @Override
//    public ClosingDcAndFoodDTO createClosingDiscount(ClosingDcAndFoodDTO cd) {
//        Store store = storeRepository.findById(cd.getStoreNumber()).get();
//        ClosingDiscount closingDiscount = new ClosingDiscount(cd.getStoreNumber(), cd.getClosingPrice(), cd.getPickupTime(), store);
//        closingDiscountRepository.save(closingDiscount);
//        return closingDiscountRepository.findDTOById(closingDiscount.getStoreNumber());
//    }
}
