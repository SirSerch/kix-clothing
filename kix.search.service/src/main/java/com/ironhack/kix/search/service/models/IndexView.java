package com.ironhack.kix.search.service.models;

import java.time.LocalDateTime;

public class IndexView {
    private String productId;
    private LocalDateTime indexedTime;

    public IndexView(ProductView productView){
        this.productId = productView.getProductId();
        this.indexedTime = LocalDateTime.now();
    }

    public IndexView() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public LocalDateTime getIndexedTime() {
        return indexedTime;
    }

    public void setIndexedTime(LocalDateTime indexedTime) {
        this.indexedTime = indexedTime;
    }
}
