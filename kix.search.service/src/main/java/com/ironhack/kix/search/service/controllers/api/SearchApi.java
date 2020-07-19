package com.ironhack.kix.search.service.controllers.api;

import com.ironhack.kix.search.service.models.ImageSearchResult;
import com.ironhack.kix.search.service.models.IndexView;
import com.ironhack.kix.search.service.models.ProductView;

import java.util.List;

public interface SearchApi {
    List<ImageSearchResult> searchProductsByImage(String imageBase64, String filter);
    IndexView indexProduct(ProductView productView);
    void deleteIndexedProduct(String productId);
}
