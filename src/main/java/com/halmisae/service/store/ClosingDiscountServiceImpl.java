package com.halmisae.service.store;

import com.halmisae.dto.store.ClosingDcDTO;
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
public class ClosingDiscountServiceImpl implements ClosingDiscountService{
    private final ClosingDiscountRepository closingDiscountRepository;
    private final StoreRepository storeRepository;

    @Override
    public ClosingDcDTO createClosingDiscount(ClosingDcDTO cd) {
        Store store = storeRepository.findById(cd.getStoreNumber()).get();
        ClosingDiscount closingDiscount = new ClosingDiscount(cd.getStoreNumber(), cd.getClosingPrice(), cd.getPickupTime(), store);
        closingDiscountRepository.save(closingDiscount);
        return closingDiscountRepository.findDTOById(closingDiscount.getStoreNumber());
    }

    @Override
    public ClosingDcDTO readClosingDiscount(int storeNumber) {
        return null;
    }

    @Override
    public ClosingDcDTO updateClosingDiscount(ClosingDcDTO cd) {
        return null;
    }

    @Override
    public ClosingDcDTO deleteClosingDiscount(int storeNumber) {
        return null;
    }
}
