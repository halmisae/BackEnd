package com.halmisae.dto.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuUpdateDTO {
    private int storeNumber;
    private int menuNumber;
    private String menuName;
    private String introduction;
    private int price;
    private MultipartFile image;
}
