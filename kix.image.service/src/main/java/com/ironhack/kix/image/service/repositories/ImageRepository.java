package com.ironhack.kix.image.service.repositories;

import com.ironhack.kix.image.service.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
