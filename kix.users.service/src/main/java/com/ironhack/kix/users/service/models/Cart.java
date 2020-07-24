package com.ironhack.kix.users.service.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    @ElementCollection
    private List<String> products;

    public Cart() {
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public void addProduct(String productId){
        products.add(productId);
    }

    public void removeProduct(String productId){
        products.remove(productId);
    }
}
