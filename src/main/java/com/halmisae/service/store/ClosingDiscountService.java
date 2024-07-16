package com.halmisae.service.store;

import com.halmisae.dto.store.ClosingDcDTO;
import com.halmisae.entity.Store.ClosingDiscount;

public interface ClosingDiscountService {
    ClosingDcDTO createClosingDiscount(ClosingDcDTO cd);
    ClosingDcDTO readClosingDiscount(int storeNumber);
    ClosingDcDTO updateClosingDiscount(ClosingDcDTO cd);
    ClosingDcDTO deleteClosingDiscount(int storeNumber);
}
