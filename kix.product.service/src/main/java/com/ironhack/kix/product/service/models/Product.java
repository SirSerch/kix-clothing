package com.ironhack.kix.product.service.models;

import com.ironhack.kix.product.service.models.dto.ProductDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Map;

@Document
public class Product {
    @Id
    private String productId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private Long productGallery;
    private Map<String, String> productTags;
    private boolean isIndexed;
    private LocalDateTime createdTime;
    private LocalDateTime lastUpdateTime;
    private LocalDateTime lastIndexedTime;

    public Product() {
    }
    public Product(ProductDTO productDTO, Long productGallery){
        this.productName = productDTO.getProductName();
        this.productDescription = productDTO.getProductDescription();
        this.productPrice = productDTO.getProductPrice();
        this.productGallery = productGallery;
        this.productTags = productDTO.getProductTags();

        this.isIndexed = false;
        this.createdTime = LocalDateTime.now();
        this.lastIndexedTime = LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC);
        this.lastUpdateTime = LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC);
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

    public Long getProductGallery() {
        return productGallery;
    }

    public void setProductGallery(Long productGallery) {
        this.productGallery = productGallery;
    }

    public Map<String, String> getProductTags() {
        return productTags;
    }

    public void setProductTags(Map<String, String> productTags) {
        this.productTags = productTags;
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

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice=" + productPrice +
                ", productGallery=" + productGallery +
                ", productTags=" + productTags.toString() +
                ", isIndexed=" + isIndexed +
                ", createdTime=" + createdTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", lastIndexedTime=" + lastIndexedTime +
                '}';
    }
}
