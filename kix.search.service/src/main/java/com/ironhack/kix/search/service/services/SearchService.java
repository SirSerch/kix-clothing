package com.ironhack.kix.search.service.services;

import com.ironhack.kix.search.service.clients.ImageClient;
import com.ironhack.kix.search.service.controllers.api.SearchApi;
import com.ironhack.kix.search.service.models.ImageSearchResult;
import com.ironhack.kix.search.service.models.IndexView;
import com.ironhack.kix.search.service.models.ProductView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SearchService implements SearchApi {
    static Logger LOGGER = LoggerFactory.getLogger(SearchService.class);

    @Autowired ImageClient imageClient;
    @Autowired GoogleService googleService;

    @Override
    public List<ImageSearchResult> searchProductsByImage(String imageBase64, String filter) {
        return googleService.searchProductByImage(imageBase64, filter);
    }

    @Override
    public IndexView indexProduct(ProductView productView) {
        if(productView.isIndexed() || productView.getLastIndexedTime().getYear() > 1970)
            this.deleteIndexedProduct(productView.getProductId());

            LOGGER.info("Indexing product: " + productView.getProductId());
            List<byte[]> productImages = new LinkedList<>();
            productView.getProductImages().getImages().forEach((imageId -> {
                productImages.add(imageClient.getImageWithImageId(
                        Long.valueOf(imageId.replaceAll("http(|s):/.*/images/", "")), null).getBody()
                );
            }));
            googleService.createProductInProjectLocation(productView);
            googleService.addProductToProductSet(productView.getProductId());
            productImages.stream().map(googleService::uploadProductImageToProductStorage).forEach(gscURI -> {
                LOGGER.info("gscURI: " + gscURI);
                String referenceImageId = gscURI.replaceAll("gs://[a-z-]+/", "").replaceAll(".jpg", "");
                LOGGER.info("referenceImageId: " + referenceImageId);
                googleService.addReferenceImageToProduct(productView.getProductId(), gscURI, referenceImageId);
            });
            return new IndexView(productView);
       }

    @Override
    public void deleteIndexedProduct(String productId) {
        googleService.deleteIndexedProduct(productId);
    }

       /*
    @Override
    public IndexView updateProduct(ProductView productView) {
        List<byte[]> productImages = new LinkedList<>();
        productView.getProductImages().getImages().forEach((imageId -> {
            productImages.add(imageClient.getImageWithImageId(
                    Long.valueOf(imageId.replaceAll("/images/", "")), null).getBody()
            );
        }));
        googleService.updateProduct(productView);
        productImages.stream().map(googleService::uploadProductImageToProductStorage).forEach(gscURI -> {
            LOGGER.info("gscURI: " + gscURI);
            String referenceImageId = gscURI.replaceAll("gs://[a-z-]+/", "").replaceAll(".jpg", "");
            LOGGER.info("referenceImageId: " + referenceImageId);
            googleService.addReferenceImageToProduct(productView.getProductId(), gscURI, referenceImageId);
        });
        return null;
    }
*/
}
