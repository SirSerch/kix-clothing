package com.ironhack.kix.search.service.services;


import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import com.ironhack.kix.search.service.models.ImageSearchResult;
import com.ironhack.kix.search.service.models.ProductView;
import com.ironhack.kix.search.service.models.utils.Utils;
import com.sun.jersey.core.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GoogleService {
    static Logger LOGGER = LoggerFactory.getLogger(GoogleService.class);

    @Value("${PROJECT_ID}")
    String projectId;

    @Value("${LOCATION_ID}")
    String locationId;

    @Value("${STORAGE_NAME}")
    String storageName;

    @Value("${PRODUCT_SET}")
    String productSet;

    @Value("${PRODUCT_CATEGORY}")
    String productCategory;

    public String uploadProductImageToProductStorage(byte[] productImage) {
        LOGGER.info(String.format("Project ID: %s, Location ID: %s, Bucket ID: %s", this.projectId, this.locationId, this.storageName));
        String imageName = Utils.bytesToHash256(productImage).concat(".jpg");
        try {
            LOGGER.info(String.format("Uploading file: %s", imageName));
            Storage storage = StorageOptions.newBuilder().setProjectId(this.projectId).build().getService();
            BlobId blobId = BlobId.of(this.storageName, imageName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
            storage.create(blobInfo, productImage);
            LOGGER.info("Uploaded!");
        } catch (Exception e) {
            LOGGER.error(String.format("Error uploading file: %s, Error: %s", imageName, e.getMessage()));
        }
        return String.format("gs://%s/%s", storageName, imageName);
    }

    public void deleteProductImageFromProductStorage(String imageId){
        String referenceImageId = imageId.replaceAll("gs://[a-z-]+/", "");
        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        LOGGER.info("Deleting image: " + referenceImageId);
        storage.delete(this.storageName, referenceImageId);
        LOGGER.info("Deleted!");
    }

    public void createProductInProjectLocation(ProductView product) {
        List<Product.KeyValue> list = new LinkedList<>();
        product.getProductTags().forEach((k,v) -> {
            list.add(Product.KeyValue.newBuilder().setKey(k).setValue(v).build());
        });
        String formattedParent = String.format("projects/%s/locations/%s", this.projectId, this.locationId);
        LOGGER.info("Creating Cloud Vision Sesion: " + formattedParent);
        try (ProductSearchClient client = ProductSearchClient.create()) {
            LOGGER.info("Cloud Vision Sesion Created!");
            Product indexingProduct = Product.newBuilder()
                    .setName(product.getProductId())
                    .setDisplayName(product.getProductId())
                    .setDescription(product.getProductName())
                    .setProductCategory(this.productCategory)
                    .addAllProductLabels(list)
                    .build();
            LOGGER.info(String.format("Creating indexingProduct: {productName=%s, displayName=%s, description=%s, labels={%s}}",
                    indexingProduct.getName(),
                    indexingProduct.getDisplayName(),
                    indexingProduct.getDescription(),
                    indexingProduct.getProductLabelsList().toString()));
            Product indexedProduct = client.createProduct(formattedParent, indexingProduct, product.getProductId());
            LOGGER.info(formattedParent);
        } catch (IOException e) {
            LOGGER.error(String.format("Error while trying to index product: %s, Error: %s", product.getProductId(), e.getMessage()));
            e.printStackTrace();
        }
    }

    public void addProductToProductSet(String productId) {
        String formattedName = String.format("projects/%s/locations/%s/productSets/%s", projectId, locationId, productSet);
        LOGGER.info("Creating Cloud Vision Sesion: " + formattedName);
        try (ProductSearchClient client = ProductSearchClient.create()) {
            LOGGER.info("Cloud Vision Sesion Created!");
            String productPath = ProductName.of(projectId, locationId, productId).toString();
            LOGGER.info("Gettint productPath: " + productPath);
            client.addProductToProductSet(formattedName, productPath);
            LOGGER.info(String.format("Product: %s, Added correctly to: %s", productId, formattedName));
        } catch (IOException e) {
            LOGGER.error(String.format("Error while trying to add product: %s to product set: %s", productId, formattedName));
        }
    }

    public void addReferenceImageToProduct(String productId, String imageURI, String referenceImageId) {
        String formattedName = String.format("projects/%s/locations/%s/products/%s", this.projectId, this.locationId, productId);
        try (ProductSearchClient client = ProductSearchClient.create()) {
            // Create a reference image.
            ReferenceImage referenceImage = ReferenceImage.newBuilder().setUri(imageURI).build();

            ReferenceImage image = client.createReferenceImage(formattedName, referenceImage, referenceImageId);
            // Display the reference image information.
            LOGGER.info("Reference image created!");
            LOGGER.info(String.format("Reference image name: %s", image.getName()));
            LOGGER.info(String.format("Reference image uri: %s", image.getUri()));
        } catch (IOException e) {
            LOGGER.error(String.format("Error while indexing reference photo: %s of product: %s on product set: %s", referenceImageId, productId, formattedName));
        }

    }

    public void deleteProductFromProjectLocation(String productId) {
        String formattedName = String.format("projects/%s/locations/%s/products/%s", this.projectId, this.locationId, productId);
        LOGGER.info("Creating Cloud Vision Sesion: " + formattedName);
        try (ProductSearchClient client = ProductSearchClient.create()) {
            LOGGER.info("Cloud Vision Sesion Created!");
            LOGGER.info("Deleting product: " + productId);
            client.deleteProduct(formattedName);
            LOGGER.info("Product Deleted!");
        } catch (IOException e) {
            LOGGER.error(String.format("Error while deleting a indexedProduct: %s, Error: %s", productId, e.getMessage()));
        }
    }

    public List<ImageSearchResult> searchProductByImage(String base64Image, String filter){
        List<ImageSearchResult> imageSearchResultList = new LinkedList<>();
        String productSetLocation = String.format("projects/%s/locations/%s/productSets/%s", this.projectId, this.locationId, this.productSet);
        LOGGER.info("Searching by Image in: " + productSetLocation);
        try (ImageAnnotatorClient queryImageClient = ImageAnnotatorClient.create()) {
            LOGGER.info("Decoding Image...");
            byte[] rawImage = Base64.decode(base64Image);
            Feature featuresElement = Feature.newBuilder().setType(Feature.Type.PRODUCT_SEARCH).build();
            LOGGER.info("Converting Image...");
            Image image = Image.newBuilder().setContent(ByteString.copyFrom(rawImage)).build();
            LOGGER.info("Creating Image Context");
            ImageContext imageContext =
                    ImageContext.newBuilder()
                            .setProductSearchParams(
                                    ProductSearchParams.newBuilder()
                                            .setProductSet(productSetLocation)
                                            .addProductCategories(this.productCategory)
                                            .setFilter(filter))
                            .build();
            LOGGER.info("Creating Request Context");
            AnnotateImageRequest annotateImageRequest =
                    AnnotateImageRequest.newBuilder()
                            .addFeatures(featuresElement)
                            .setImage(image)
                            .setImageContext(imageContext)
                            .build();
            List<AnnotateImageRequest> requests = Arrays.asList(annotateImageRequest);

            LOGGER.info("Searching...");
            BatchAnnotateImagesResponse response = queryImageClient.batchAnnotateImages(requests);
            List<ProductSearchResults.Result> similarProducts = response.getResponses(0).getProductSearchResults().getResultsList();
            for (ProductSearchResults.Result product : similarProducts) {
                ImageSearchResult result = new ImageSearchResult(product.getProduct().getDisplayName(), product.getScore());
                product.getProduct().getProductLabelsList().forEach((keyValue -> {
                    result.addTag(keyValue.getKey(), keyValue.getValue());
                }));
                imageSearchResultList.add(result);
            }
        } catch (IOException e) {
            LOGGER.error(String.format("Error while trying to search products by Image, Error: %s", e.getMessage()));
        }
        return imageSearchResultList;
    }

    public List<String> getAllReferencesImagesByProductId(String productId){
        String formattedParent = String.format("projects/%s/locations/%s/products/%s", this.projectId, this.locationId, productId);
        try (ProductSearchClient client = ProductSearchClient.create()) {
            return StreamSupport.stream(client.listReferenceImages(formattedParent).iterateAll().spliterator(), false)
                    .map(ReferenceImage::getUri).collect(Collectors.toList());
        }catch (IOException e){
            LOGGER.error("Error while trying to obtain all references photos from: " + productId);
        }
        return new LinkedList<>();
    }

    public void deleteIndexedProduct(String productId){
        this.getAllReferencesImagesByProductId(productId).forEach(this::deleteProductImageFromProductStorage);
        this.deleteProductFromProjectLocation(productId);
    }

        /*
    public void updateProduct(ProductView updatingProduct) {
        List<Product.KeyValue> list = new LinkedList<>();
        updatingProduct.getProductTags().forEach((k,v) -> {
            list.add(Product.KeyValue.newBuilder().setKey(k).setValue(v).build());
        });
        String formattedName = String.format("projects/%s/locations/%s/products/%s", this.projectId, this.locationId, updatingProduct.getProductId());
        LOGGER.info("Creating Cloud Vision Sesion: " + formattedName);
        try (ProductSearchClient client = ProductSearchClient.create()) {
            Product product =
                    Product.newBuilder()
                            .setName(updatingProduct.getProductId())
                            .setDescription(updatingProduct.getProductName())
                            .setDisplayName(updatingProduct.getProductId())
                            .addAllProductLabels(list)
                            .build();
            FieldMask updateMask = FieldMask.newBuilder()
                    .addPaths("product_labels,display_name,description")
                    .build();
            LOGGER.info("Updating product: " + updatingProduct.getProductId());
            Product updatedProduct = client.updateProduct(product, updateMask);
        } catch (IOException e) {
            LOGGER.error(String.format("Error while deleting a indexedProduct: %s, Error: %s", updatingProduct.getProductId(), e.getMessage()));
        }
    }
*/
}
