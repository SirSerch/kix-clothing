package com.ironhack.kix.edge.service.services;

import com.ironhack.kix.edge.service.exceptions.SearchImageNotValidException;
import com.ironhack.kix.edge.service.models.dto.SearchPetitionDTO;
import com.ironhack.kix.edge.service.models.views.ImageSearchResult;
import com.ironhack.kix.edge.service.models.views.SearchView;
import com.ironhack.kix.edge.service.repositories.clients.SearchClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SearchService {
    private @Autowired SearchClient searchClient;
    private @Autowired ProductService productService;

    protected List<SearchView> searchByImage(String base64Image, String filter) {
        if( ! isBase64(base64Image)) throw new SearchImageNotValidException();
        List<SearchView> view = new LinkedList<>();
        List<ImageSearchResult> results = searchClient.searchProductsByImage(base64Image, filter);
        results.forEach(result -> {
            SearchView search = new SearchView();
            search.setScore(result.getScore());
            search.setTags(result.getTags());
            search.setProductView(productService.getProductById(result.getProductId()));
            view.add(search);
        });
        return view;
    }

    private boolean isBase64(String base64){
        return base64.matches("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$");
    }
}
