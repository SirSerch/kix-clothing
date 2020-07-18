package com.ironhack.kix.users.service.models;

import com.ironhack.kix.users.service.models.enums.Role;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Role role;
    String name;
    String lastName;
    String email;
    String password;
    @ElementCollection
    List<Long> payments;
    Integer cartId;

    public User() {
    }

    public User(String name, String lastName, String email, String password, List<Long> payments, Integer cartId) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.payments = payments;
        this.cartId = cartId;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Long> getPayments() {
        return payments;
    }

    public void setPayments(List<Long> payments) {
        this.payments = payments;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }
}
