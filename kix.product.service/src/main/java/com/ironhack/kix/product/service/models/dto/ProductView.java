package com.ironhack.kix.product.service.models.dto;

import com.ironhack.kix.product.service.models.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ProductView {
    private String productId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private Map<String, String> productTags;
    private GalleryView productImages;
    //Used by Search Engine
    private boolean isIndexed;
    private LocalDateTime createdTime;
    private LocalDateTime lastUpdateTime;
    private LocalDateTime lastIndexedTime;
    private Float score;

    public ProductView() {
    }

    public ProductView(Product product, GalleryView galleryView) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.productDescription = product.getProductDescription();
        this.productPrice = product.getProductPrice();
        this.productTags = product.getProductTags();
        this.productImages = galleryView;
        this.isIndexed = product.isIndexed();
        this.createdTime = product.getCreatedTime();
        this.lastUpdateTime = product.getLastUpdateTime();
        this.lastIndexedTime = product.getLastIndexedTime();
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Map<String, String> getProductTags() {
        return productTags;
    }

    public void setProductTags(Map<String, String> productTags) {
        this.productTags = productTags;
    }

    public GalleryView getProductImages() {
        return productImages;
    }

    public void setProductImages(GalleryView productImages) {
        this.productImages = productImages;
    }

    public boolean isIndexed() {
        return isIndexed;
    }

    public void setIndexed(boolean indexed) {
        isIndexed = indexed;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public LocalDateTime getLastIndexedTime() {
        return lastIndexedTime;
    }

    public void setLastIndexedTime(LocalDateTime lastIndexedTime) {
        this.lastIndexedTime = lastIndexedTime;
    }
}
