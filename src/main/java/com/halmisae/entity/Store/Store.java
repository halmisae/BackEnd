package com.halmisae.entity.Store;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    private String Id;
    private String name;
    private String address;
    private String businessNumber;
    private String email;
    private String status;
    private
}
