package com.ironhack.kix.product.service.clients.fallbacks;

import com.ironhack.kix.product.service.clients.SearchClient;
import com.ironhack.kix.product.service.models.dto.ImageSearchResult;
import com.ironhack.kix.product.service.models.dto.IndexView;
import com.ironhack.kix.product.service.models.dto.ProductView;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class SearchClientFallback implements SearchClient {

    //Releated products are 0.27 or above
    //Best Results are 0.5 or above


    @Override
    public List<ImageSearchResult> searchProductsByImage(String imageBase64, String filter) {
        return new LinkedList<>();
    }

    @Override
    public IndexView indexProduct(ProductView productView) {
        return null;
    }

    @Override
    public void deleteIndexedProduct(String productId) {

    }
}
