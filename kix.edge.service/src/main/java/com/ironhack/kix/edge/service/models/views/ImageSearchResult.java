package com.ironhack.kix.edge.service.models.views;

import java.util.HashMap;
import java.util.Map;

public class ImageSearchResult {
    private String productId;
    private Float score;
    private Map<String, String> tags;

    public ImageSearchResult(String productId, Float score) {
        this.productId = productId;
        this.score = score;
        tags = new HashMap<>();
    }

    public ImageSearchResult() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public void addTag(String key, String value){
        tags.put(key, value);
    }
}
