package com.ironhack.kix.edge.service.models.dto;

import com.ironhack.kix.edge.service.models.enums.Role;

import java.util.LinkedList;
import java.util.List;

public class UserDTO {
    private Role role;
    private String name;
    private String lastName;
    private String password;
    private String email;
    private Address address;
    private Cart cart;
    private List<Long> payments;

    public UserDTO() {
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        if(this.address != null)
            return address;
        return new Address();
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Cart getCart() {
        if(this.cart != null)
            return cart;
        return new Cart();
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Long> getPayments() {
        if(payments != null)
            return payments;
        return new LinkedList<>();
    }

    public void setPayments(List<Long> payments) {
        this.payments = payments;
    }
}
