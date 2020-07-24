package com.ironhack.kix.edge.service.models.views;

import java.util.Map;

public class SearchView {
    private ProductView productView;
    private Float score;
    private Map<String, String> tags;

    public SearchView() {
    }

    public ProductView getProductView() {
        return productView;
    }

    public void setProductView(ProductView productView) {
        this.productView = productView;
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
}
