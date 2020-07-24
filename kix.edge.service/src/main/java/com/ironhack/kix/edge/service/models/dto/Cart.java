package com.ironhack.kix.edge.service.models.dto;

import java.util.List;

public class Cart {
    private Long cartId;
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

    public void addProduct(String productId) {
        products.add(productId);
    }

    public void removeProduct(String productId) {
        products.remove(productId);
    }
}
