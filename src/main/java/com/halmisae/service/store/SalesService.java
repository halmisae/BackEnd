package com.halmisae.service.store;

import java.time.LocalDateTime;
import java.util.List;

public interface SalesService {
    // GET 월별 매출 데이터 보여주기
    List<Object> readSales(int storeNumber, LocalDateTime month);
}
