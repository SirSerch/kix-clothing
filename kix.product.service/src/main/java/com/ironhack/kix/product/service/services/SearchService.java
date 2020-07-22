package com.ironhack.kix.product.service.services;

import com.ironhack.kix.product.service.clients.SearchClient;
import com.ironhack.kix.product.service.models.dto.ImageSearchResult;
import com.ironhack.kix.product.service.models.dto.ProductView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SearchService {
    @Autowired
    SearchClient searchClient;
    @Autowired
    ProductService productService;

    final static Logger LOGGER = LoggerFactory.getLogger(SearchService.class);

    List<ImageSearchResult> searchByImage(String imageBase64, Map<String, String> mapFilter){
        LOGGER.info("Search by Image: " + imageBase64.substring(0, 30).concat("..."));
        LOGGER.info(mapFilter.toString().replaceAll("[{}]", ""));
        return searchClient.searchProductsByImage(imageBase64, mapFilter.toString().replaceAll("[{}]",""));
    }
}
