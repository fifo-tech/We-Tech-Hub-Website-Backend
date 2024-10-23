package com.example.fifotech.controller;

import com.example.fifotech.entity.Gallery;
import com.example.fifotech.entity.GalleryImage;
import com.example.fifotech.services.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://wetechhub.com", "http://localhost:4200"},  allowCredentials = "true")
public class GalleryController {


    @Autowired
    private GalleryService galleryService;




    @PostMapping("/createGallery")
    public ResponseEntity<Gallery> createGallery(
            @RequestParam("title") String title,
            @RequestParam("subtitle") String subtitle,
            @RequestParam("postDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate postDate,
            @RequestParam("details") String details,
            @RequestParam("captions") List<String> captions, // Captions list for each image
            @RequestParam("images") List<MultipartFile> images,  // Multiple images
            @RequestParam("thumbnailImage") MultipartFile thumbnailImage // New parameter for thumbnail

    ) throws IOException {

        Gallery bpo = new Gallery();
        bpo.setTitle(title);
        bpo.setSubtitle(subtitle);
        bpo.setPostDate(postDate);
        bpo.setDetails(details);

        // Set thumbnail image
        if (thumbnailImage != null && !thumbnailImage.isEmpty()) {
            bpo.setThumbnailImage(thumbnailImage.getBytes());
        }


        List<GalleryImage> postImages = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {

            MultipartFile imageFile = images.get(i);

            GalleryImage postImage = new GalleryImage();
            postImage.setImg(imageFile.getBytes());
            postImage.setCaption(captions.get(i)); // Add the corresponding caption
            postImages.add(postImage);
        }

        bpo.setImages(postImages);  // Attach images with captions to the post
        Gallery savedPost = galleryService.createGallery(bpo);
        System.out.println("Received Captions: " + captions);

        return ResponseEntity.ok(savedPost);

    }




    @GetMapping("/getAllGallery")
    public ResponseEntity<List<Gallery>> getAllGallery() {
        List<Gallery> gallery = galleryService.getAllGallery();
        return ResponseEntity.ok(gallery);
    }





    @GetMapping("/getGalleryById/{id}")
    public ResponseEntity<Gallery> getGalleryById(@PathVariable Long id) {
        return galleryService.getGalleryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }








    @PutMapping("/updateGallery/{id}")
    public ResponseEntity<Gallery> updateGallery(
            @PathVariable Long id,
            @RequestParam("title") String title,
            @RequestParam("subtitle") String subtitle,
            @RequestParam("postDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate postDate,
            @RequestParam("details") String details,
            @RequestParam(value = "captions", required = false) List<String> captions,
            @RequestParam(value = "images", required = false) List<MultipartFile> images,
            @RequestParam(value = "thumbnailImage", required = false) MultipartFile thumbnailImage
    ) throws IOException {

        Gallery updatedGallery = new Gallery();
        updatedGallery.setTitle(title);
        updatedGallery.setSubtitle(subtitle);
        updatedGallery.setPostDate(postDate);
        updatedGallery.setDetails(details);

        Gallery gallery = galleryService.updateGallery(id, updatedGallery, images, thumbnailImage, captions);

        return ResponseEntity.ok(gallery);
    }









    @DeleteMapping("/deleteGallery/{id}")
    public ResponseEntity<Void> deleteGallery(@PathVariable Long id) {
        galleryService.deleteGallery(id);
        return ResponseEntity.noContent().build();
    }






}

