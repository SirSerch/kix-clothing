package com.ironhack.kix.search.service.services;

import com.google.cloud.vision.v1.Product;
import com.google.cloud.vision.v1.ProductName;
import com.google.cloud.vision.v1.ProductSearchClient;
import com.google.cloud.vision.v1.ReferenceImage;
import com.google.protobuf.FieldMask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleService {
    static Logger LOGGER = LoggerFactory.getLogger(GoogleService.class);

    @Value("PROJECT_ID")
    String projectId;

    @Value("LOCATION_ID")
    String computeRegion;

    /**
     * Create one product.
     *
     * @param productId          - Id of the product.
     * @param productDisplayName - Display name of the product.
     * @param productCategory    - Category of the product.
     * @throws IOException - on I/O errors.
     */
    public com.ironhack.kix.search.service.models.Product createProduct(String productId, String productDisplayName, String productDescription, String productCategory) throws IOException {
        try (ProductSearchClient client = ProductSearchClient.create()) {

            LOGGER.info("Project ID: " + projectId + ", Compute Region: " + computeRegion);

            // A resource that represents Google Cloud Platform location.
            String formattedParent = ProductSearchClient.formatLocationName(projectId, computeRegion);
            // Create a product with the product specification in the region.
            // Multiple labels are also supported.

            LOGGER.info(String.format("Product: %s, %s, %s, %s", productId, productDisplayName, productDescription, productCategory));
            Product myProduct = Product.newBuilder()
                    .setName(productId)
                    .setDescription(productDescription)
                    .setDisplayName(productDisplayName)
                    .setProductCategory(productCategory)
                    .build();
            Product product = client.createProduct(formattedParent, myProduct, productId);
            // Display the product information
            LOGGER.info(String.format("Product name: %s", product.getName()));
            return new com.ironhack.kix.search.service.models.Product();
        }
    }

    /**
     * Add a product to a product set.
     *
     * @param productId    - Id of the product.
     * @param productSetId - Id of the product set.
     * @throws IOException - on I/O errors.
     */
    public void addProductToProductSet(String productId, String productSetId) throws IOException {
        try (ProductSearchClient client = ProductSearchClient.create()) {
            LOGGER.info("Project ID: " + projectId + ", Compute Region: " + computeRegion);
            LOGGER.info(String.format("Product ID: %s, Product Set ID: %s", productId, productSetId));
            // Get the full path of the product set.
            String formattedName = ProductSearchClient.formatProductSetName(projectId, computeRegion, productSetId);

            // Get the full path of the product.
            String productPath = ProductName.of(projectId, computeRegion, productId).toString();

            // Add the product to the product set.
            client.addProductToProductSet(formattedName, productPath);

            LOGGER.info(String.format("Product added to product set."));
        }
    }

    /**
     * Update the product labels.
     *
     * @param productId -Id of the product.
     * @param productLabels - Labels of the product.
     * @throws IOException - on I/O errors.
     */
    public void updateProductLabels(String productId, String productDescription, String productLabels) throws IOException {
        try (ProductSearchClient client = ProductSearchClient.create()) {

            // Get the full path of the product.
            String formattedName =
                    ProductSearchClient.formatProductName(projectId, computeRegion, productId);

            // Set product name, product labels and product display name.
            // Multiple labels are also supported.
            Product product =
                    Product.newBuilder()
                            .setName(formattedName)
                            .setDescription(productDescription)
                            .addProductLabels(
                                    Product.KeyValue.newBuilder()
                                            .setKey(productLabels.split(",")[0].split("=")[0])
                                            .setValue(productLabels.split(",")[0].split("=")[1])
                                            .build())
                            .build();

            // Set product update field name.
            FieldMask updateMask = FieldMask.newBuilder().addPaths("product_labels").build();

            // Update the product.
            Product updatedProduct = client.updateProduct(product, updateMask);
            // Display the product information
            LOGGER.info(String.format("Product name: %s", updatedProduct.getName()));
            LOGGER.info(String.format("Updated product labels: "));
            for (Product.KeyValue element : updatedProduct.getProductLabelsList()) {
                LOGGER.info(String.format("%s: %s", element.getKey(), element.getValue()));
            }
        }
    }

    /**
     * Create a reference image.
     *
     * @param productId - Id of the product.
     * @param referenceImageId - Id of the image.
     * @param gcsUri - Google Cloud Storage path of the input image.
     * @throws IOException - on I/O errors.
     */
    public void createReferenceImage(String productId, String referenceImageId, String gcsUri) throws IOException {
        try (ProductSearchClient client = ProductSearchClient.create()) {
            LOGGER.info(String.format("Create Reference Image: %s, %s, %s", productId, referenceImageId, gcsUri));
            // Get the full path of the product.
            String formattedParent = ProductSearchClient.formatProductName(projectId, computeRegion, productId);
            // Create a reference image.
            ReferenceImage referenceImage = ReferenceImage.newBuilder().setUri(gcsUri).build();

            ReferenceImage image =
                    client.createReferenceImage(formattedParent, referenceImage, referenceImageId);
            // Display the reference image information.
            System.out.println(String.format("Reference image name: %s", image.getName()));
            System.out.println(String.format("Reference image uri: %s", image.getUri()));
        }
    }

}
