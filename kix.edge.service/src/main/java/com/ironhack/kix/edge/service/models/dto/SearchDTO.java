package com.ironhack.kix.product.service.models.dto;

import java.util.Map;

public class SearchDTO {
    private boolean isImageSearch;
    private Map<String, String> filter;
    private String search;

    public boolean isImageSearch() {
        return isImageSearch;
    }

    public void setImageSearch(boolean imageSearch) {
        isImageSearch = imageSearch;
    }

    public Map<String, String> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, String> filter) {
        this.filter = filter;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
