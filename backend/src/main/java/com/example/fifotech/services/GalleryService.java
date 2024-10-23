package com.example.fifotech.services;

import com.example.fifotech.entity.Gallery;
import com.example.fifotech.entity.GalleryImage;
import com.example.fifotech.repository.GalleryImgRepository;
import com.example.fifotech.repository.GalleryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GalleryService {


    @Autowired
    private GalleryRepository galleryRepository;

    @Autowired
    private GalleryImgRepository galleryImgRepository;


    public Gallery createGallery(Gallery gallery) {
        return galleryRepository.save(gallery);
    }


    public List<Gallery> getAllGallery() {
        return galleryRepository.findAll();
    }

    public Optional<Gallery> getGalleryById(Long id) {
        return galleryRepository.findById(id);
    }


    @Transactional
    public Gallery updateGallery(Long id, Gallery updatedGallery, List<MultipartFile> images, MultipartFile thumbnailImage, List<String> captions) throws IOException {
        return galleryRepository.findById(id)
                .map(gallery -> {
                    try {
                        // Update thumbnail image if provided
                        if (thumbnailImage != null && !thumbnailImage.isEmpty()) {
                            gallery.setThumbnailImage(thumbnailImage.getBytes());
                        }

                        // Update other fields
                        gallery.setTitle(updatedGallery.getTitle());
                        gallery.setSubtitle(updatedGallery.getSubtitle());
                        gallery.setPostDate(updatedGallery.getPostDate());
                        gallery.setDetails(updatedGallery.getDetails());

                        // Update images if provided
                        if (images != null && !images.isEmpty()) {
                            List<GalleryImage> postImages = new ArrayList<>();
                            for (int i = 0; i < images.size(); i++) {
                                MultipartFile imageFile = images.get(i);

                                GalleryImage postImage = new GalleryImage();
                                postImage.setImg(imageFile.getBytes());
                                postImage.setCaption(captions.get(i)); // Add the corresponding caption
                                postImages.add(postImage);
                            }
                            gallery.setImages(postImages);
                        }

                        return galleryRepository.save(gallery);

                    } catch (IOException e) {
                        throw new RuntimeException("Error processing images", e);
                    }
                })
                .orElseThrow(() -> new RuntimeException("Gallery not found with id " + id));
    }


    @Transactional
    public void deleteGallery(Long id) {
        galleryRepository.deleteById(id);

        galleryImgRepository.deleteByGalleryId(id);


    }


}




