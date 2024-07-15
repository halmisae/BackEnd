package com.halmisae.service.store;

import com.halmisae.dto.store.ClosingDcDTO;
import com.halmisae.entity.Store.ClosingDiscount;
import com.halmisae.entity.Store.Store;
import com.halmisae.repository.store.ClosingDiscountRepository;
import com.halmisae.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class ClosingDiscountService {
    private final ClosingDiscountRepository closingDiscountRepository;
    private final StoreRepository storeRepository;

    public ClosingDiscount createClosingDiscount(ClosingDcDTO cd) {
        Store store = storeRepository.findById(cd.getStoreNumber()).get();
        ClosingDiscount closingDiscount = new ClosingDiscount(cd.getStoreNumber(), cd.getClosingPrice(), cd.getPickupTime(), store);
        return closingDiscountRepository.save(closingDiscount);
    }
}
