package com.halmisae.service.store;

import com.halmisae.dto.store.*;
import com.halmisae.entity.Store.Store;

public interface StoreService {
    // POST 회원가입
    Store createStore(StoreCreateDTO sc);
    // POST 마이페이지 비밀번호 입력 (나중에 인증, 인가로 바꾸기)
    Boolean passwordCheck(StorePasswordCheckDTO spc);
    // GET 업주 정보 보기
    StoreReadOwnerDTO readStoreOwner(int storeNumber);
    // PATCH 업주 정보 수정
    StoreUpdateOwnerDTO updateStoreOwner(StoreUpdateOwnerDTO uo);
    // GET 가게 정보 보기
    StoreDTO readStore(int storeNumber);
    // PATCH 가게 정보 수정
    StoreDTO updateStore(StoreDTO us);
    // DELETE 회원 탈퇴
    Boolean deleteStore(int storeNumber);
}
