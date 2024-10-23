package com.example.fifotech.repository;

import com.example.fifotech.entity.GalleryImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryImgRepository extends JpaRepository<GalleryImage, Long> {


    @Modifying
    @Query(value = "DELETE FROM gallery_image WHERE gallery_id = :galleryId", nativeQuery = true)
    void deleteByGalleryId(@Param("galleryId") Long galleryId);
}
