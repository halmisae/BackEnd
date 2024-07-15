package com.halmisae.entity.User;

import com.halmisae.entity.Store.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class FavoriteID implements Serializable {
    private String email;
    private int storeNumber;
}
