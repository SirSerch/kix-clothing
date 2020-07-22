package com.ironhack.kix.edge.service.models.dto;

import java.util.Map;

public class SearchDTO {
    private boolean imageSearch;
    private Map<String, String> filter;
    private String search;

    public boolean isImageSearch() {
        return imageSearch;
    }

    public void setImageSearch(boolean imageSearch) {
        this.imageSearch = imageSearch;
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

    @Override
    public String toString() {
        return "SearchDTO{" +
                "isImageSearch=" + imageSearch +
                ", filter=" + filter +
                ", search='" + search.substring(0, 30) + '\'' +
                '}';
    }
}
