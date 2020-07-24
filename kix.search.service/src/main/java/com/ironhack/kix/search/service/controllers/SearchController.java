package com.ironhack.kix.search.service.controllers;

import com.ironhack.kix.search.service.controllers.api.SearchApi;
import com.ironhack.kix.search.service.models.ImageSearchResult;
import com.ironhack.kix.search.service.models.IndexView;
import com.ironhack.kix.search.service.models.ProductView;
import com.ironhack.kix.search.service.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchController implements SearchApi {
    @Autowired SearchService searchService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public List<ImageSearchResult> searchProductsByImage(@RequestBody String imageBase64, @RequestParam(name= "filter", required = false, defaultValue = "") String filter) {
        return searchService.searchProductsByImage(imageBase64, filter);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, value = "/search/indexing")
    public IndexView indexProduct(@RequestBody ProductView productView) {
        return searchService.indexProduct(productView);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE, value = "/search/indexing/{productId}")
    public void deleteIndexedProduct(@PathVariable(name = "productId") String productId) {
        searchService.deleteIndexedProduct(productId);
    }
}
