package com.ironhack.kix.users.service.models;

import com.ironhack.kix.users.service.models.dto.UserDTO;
import com.ironhack.kix.users.service.models.enums.Role;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Role role;
    private String name;
    private String lastName;
    private String password;
    @Column(unique = true)
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @ElementCollection
    private List<Long> payments;
    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;

    public User() {
    }

    public User(UserDTO userDTO){
        this.role = Role.CLIENT;
        this.name = userDTO.getName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.address = userDTO.getAddress();
        this.cart = userDTO.getCart();
        this.payments = userDTO.getPayments();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
