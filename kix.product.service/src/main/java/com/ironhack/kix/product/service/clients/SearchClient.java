package com.ironhack.kix.product.service.clients;

import com.ironhack.kix.product.service.models.dto.ImageSearchResult;
import com.ironhack.kix.product.service.models.dto.IndexView;
import com.ironhack.kix.product.service.models.dto.ProductView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("kix-search-service")
public interface SearchClient {

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, value = "/search")
    List<ImageSearchResult> searchProductsByImage(@RequestBody String imageBase64, @RequestParam("filter") String filter);

    @RequestMapping(method = RequestMethod.POST, value = "/search/indexing")
    IndexView indexProduct(@RequestBody ProductView productView);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE, value = "/search/indexing/{productId}")
    void deleteIndexedProduct(@PathVariable(name = "productId") String productId);
}
