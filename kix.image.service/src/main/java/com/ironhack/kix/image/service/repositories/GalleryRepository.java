package com.ironhack.kix.image.service.repositories;

import com.ironhack.kix.image.service.models.ImageGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GalleryRepository extends JpaRepository<ImageGallery, String> {
    @Query("SELECT CONCAT('/images/', im.imageId) FROM ImageGallery g LEFT JOIN g.gallery im WHERE g.productId = :productId")
    List<String> getAllImageIdWithProductId(@Param(value = "productId") String productId);

}
