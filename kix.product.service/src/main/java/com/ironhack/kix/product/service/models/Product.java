package com.ironhack.kix.product.service.models;

import com.ironhack.kix.product.service.models.dto.ProductDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
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

    public Product() {
    }
    public Product(ProductDTO productDTO, Long productGallery){
        this.productName = productDTO.getProductName();
        this.productDescription = productDTO.getProductDescription();
        this.productPrice = productDTO.getProductPrice();
        this.productGallery = productGallery;
        this.productTags = productDTO.getProductTags();
    }

    public Product(ProductDTO productDTO){
        this.productId = productDTO.getProductId();
        this.productName = productDTO.getProductName();
        this.productDescription = productDTO.getProductDescription();
        this.productPrice = productDTO.getProductPrice();
        this.productGallery = productGallery;
        this.productTags = productDTO.getProductTags();
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
}
