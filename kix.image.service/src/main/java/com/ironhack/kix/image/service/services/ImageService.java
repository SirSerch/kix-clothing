package com.ironhack.kix.image.service.services;

import com.ironhack.kix.image.service.exceptions.GalleryNotFoundException;
import com.ironhack.kix.image.service.exceptions.ImageNotFoundException;
import com.ironhack.kix.image.service.models.Image;
import com.ironhack.kix.image.service.models.ImageGallery;
import com.ironhack.kix.image.service.models.dto.GalleryDTO;
import com.ironhack.kix.image.service.models.dto.GalleryView;
import com.ironhack.kix.image.service.repositories.GalleryRepository;
import com.ironhack.kix.image.service.repositories.ImageRepository;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {
    private static Logger LOGGER = LoggerFactory.getLogger(ImageService.class);

    @Autowired
    GalleryRepository galleryRepository;
    @Autowired
    ImageRepository imageRepository;

    @Autowired private DiscoveryClient discoveryClient;

    private static HttpHeaders header;

    public ImageService() {
        header = new HttpHeaders();
        header.setContentType(MediaType.IMAGE_JPEG);
    }

    @Transactional
    public GalleryView createNewGallery(GalleryDTO base64ImageGallery) {
        LOGGER.info("Creating new Gallery");
        ImageGallery gallery = new ImageGallery();
        List<Image> images = base64ImageGallery.getBase64Images().stream().map(Image::new).collect(Collectors.toList());
        images.forEach((image -> image.setGallery(gallery)));
        imageRepository.saveAll(images);
        return new GalleryView(gallery.getGalleryId(), this.getAllImageUriByGallery(gallery));
    }


    public HttpEntity<byte[]> getImageWithImageId(Long imageId, Double scale) throws IOException {
        Image image = this.getImageByImageId(imageId);
        if (scale != null) image.setImage(this.resizeImage(image, scale));
        return new HttpEntity<byte[]>(image.getImage(), header);
    }


    public GalleryView getGalleryByProductId(Long productId) {
        this.getImageGalleryById(productId);
        ImageGallery gallery = this.getImageGalleryById(productId);
        return new GalleryView(productId, this.getAllImageUriByGallery(gallery));
    }


    public void deleteAllImagesByGalleryId(Long galleryId) {
        galleryRepository.deleteById(galleryId);
    }


    public void deleteImageByImageId(Long imageId) {
        imageRepository.deleteById(imageId);
    }


    private Image getImageByImageId(Long imageid) {
        return imageRepository.findById(imageid).orElseThrow(ImageNotFoundException::new);
    }

    private ImageGallery getImageGalleryById(Long productId) {
        return galleryRepository.findById(productId).orElseThrow(GalleryNotFoundException::new);
    }

    private byte[] resizeImage(Image image, Double scale) throws IOException {
        BufferedImage scaledImage = ImageIO.read(new ByteArrayInputStream(image.getImage()));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Thumbnails.of(scaledImage).scale(scale).outputFormat("jpg").toOutputStream(outputStream);
        return outputStream.toByteArray();
    }

    private String getImageServiceURL() {
        List<ServiceInstance> list = discoveryClient.getInstances("kix-image-service");
        if (list != null && list.size() > 0 ) {
            if(list.get(0).getUri().toString() != null)
                return list.get(0).getUri().toString();
        }
        return "";
    }

    private List<String> getAllImageUriByGallery(ImageGallery gallery){
        return galleryRepository.getAllImageUriByGalleryId(gallery.getGalleryId())
                .stream().map(image -> this.getImageServiceURL().concat(image)).collect(Collectors.toList());
    }
}
