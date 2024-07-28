package com.halmisae.dto.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuUpdateDTO {
    private int menuNumber;
    private String menuName;
    private int price;
    private String introduction;
    private MultipartFile image;
    private int storeNumber;
}
