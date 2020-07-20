package com.ironhack.kix.edge.service.models.dto;

import java.util.Map;

public class SearchPetitionDTO {
    private String searchText;
    private String imageBase64;
    private Map<String, String> filter;

    public SearchPetitionDTO() {
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public Map<String, String> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, String> filter) {
        this.filter = filter;
    }
}
