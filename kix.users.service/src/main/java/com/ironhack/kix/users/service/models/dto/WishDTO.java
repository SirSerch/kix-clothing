package com.ironhack.kix.users.service.models.dto;

import com.ironhack.kix.users.service.models.WishList;

import java.time.LocalDate;
import java.util.List;

public class WishDTO {
    Long id;
    Long userId;
    String name;
    LocalDate createdDate;
    List<String> products;

    public WishDTO() {
    }

    public WishDTO(WishList wishList){
        this.id = wishList.getId();
        this.userId = wishList.getUser().getId();
        this.name = wishList.getName();
        this.createdDate = wishList.getCreatedDate();
        this.products = wishList.getProducts();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }
}
