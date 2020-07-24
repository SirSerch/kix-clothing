package com.ironhack.kix.edge.service.models.views;

import com.ironhack.kix.edge.service.models.dto.Address;
import com.ironhack.kix.edge.service.models.dto.Cart;
import com.ironhack.kix.edge.service.models.enums.Role;

import java.util.List;

public class UserView {
    private Long id;
    private Role role;
    private String name;
    private String lastName;
    private String password;
    private String email;
    private Address address;
    private Cart cart;
    private List<Long> payments;

    public UserView() {
    }

    public UserView(UserView user) {
        this.id = user.getId();
        this.role = user.getRole();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.cart = user.getCart();
        this.payments = user.getPayments();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Long> getPayments() {
        return payments;
    }

    public void setPayments(List<Long> payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "UserView{" +
                "id=" + id +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", cart=" + cart +
                ", payments=" + payments +
                '}';
    }
}
