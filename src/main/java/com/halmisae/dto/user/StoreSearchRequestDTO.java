package com.halmisae.dto.user;

import com.halmisae.entity.Enum.Filter;
import com.halmisae.entity.Enum.OrderType;
import lombok.Data;

@Data
public class StoreSearchRequestDTO {
    private String email;
    private String searchText;
    private Filter filter;
}
