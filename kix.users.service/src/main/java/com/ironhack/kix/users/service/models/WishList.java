package com.ironhack.kix.users.service.models;

import com.ironhack.kix.users.service.models.dto.WishDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    User user;
    String name;
    LocalDate createdDate;
    @ElementCollection
    List<String> products;

    public WishList() {
    }

    public WishList(WishDTO wishDTO, User user) {
        this.user = user;
        this.name = wishDTO.getName();
        if(wishDTO.getCreatedDate() == null){
            this.createdDate = LocalDate.now();
        } else {this.createdDate = wishDTO.getCreatedDate();}
        if(wishDTO.getId() != null){ this.id = wishDTO.getUserId();}
        this.products = wishDTO.getProducts();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
