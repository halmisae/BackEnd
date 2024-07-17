package com.halmisae.service.user;

import com.halmisae.dto.user.*;

import java.util.List;

public interface MainService {
    // GET 메인페이지(전체)
    List<StoreReadMainDTO> readAllStore(StoreReadMainRequestDTO srmr);
    // GET 메인페이지(노쇼할인)
    List<StoreReadMainDTO> readNoShowStore(StoreReadMainRequestDTO srmr);
    // GET 메인페이지(마감할인)
    List<StoreReadMainDTO> readClosingDiscountStore(StoreReadMainRequestDTO srmr);
    // GET 메인페이지(찜)
    List<StoreReadMainDTO> readFavoriteStore(StoreReadMainRequestDTO srmr);
    // GET 검색 결과 페이지
    List<StoreReadMainDTO> searchStore(StoreSearchRequestDTO ssr);
    // GET 가게 상세 정보 보기
    StoreReadDetailDTO readStoreDetail(StoreDetailRequestDTO sdr);
    // POST 가게 찜하기
    FavoriteDTO createFavorite(FavoriteDTO f);
    // DELETE 가게 찜 취소
    boolean deleteFavorite(FavoriteDTO f);
    // POST 마감할인상품 주문
    ClosingOrderDTO createClosingOrder(ClosingOrderRequestDTO cor);
}
