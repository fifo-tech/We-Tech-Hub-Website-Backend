package com.example.fifotech.services;

import com.example.fifotech.entity.GlobalBPO;
import com.example.fifotech.entity.imageGB;
import com.example.fifotech.repository.GlobalBpoRepository;
import com.example.fifotech.repository.ImageGBRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GlobalBPOService {


    @Autowired
    private GlobalBpoRepository globalBPORepository;

    @Autowired
    private ImageGBRepository imageGBRepository;

//    @Transactional
//    public GlobalBPO createGlobalBPO(GlobalBPO globalBPO, List<MultipartFile> files, List<String> captions) throws IOException {
//        // Ensure that the list of images is initialized
//        if (globalBPO.getImages() == null) {
//            globalBPO.setImages(new ArrayList<>());
//        }
//
//        for (int i = 0; i < files.size(); i++) {
//            MultipartFile file = files.get(i);
//            imageGB image = new imageGB();
//
//            // Convert file to byte[]
//            image.setImg(file.getBytes());
//
//            // Set the corresponding caption
//            if (i < captions.size()) {
//                image.setCaption(captions.get(i));
//            } else {
//                image.setCaption(""); // Optionally set an empty string or null if no caption is provided
//            }
//
//            // Set the GlobalBPO reference
//            image.setGlobalBPO(globalBPO);
//
//            // Add the image to the GlobalBPO's list of images
//            globalBPO.getImages().add(image);
//        }
//
//        // Save the GlobalBPO entity along with its images
//        return globalBPORepository.save(globalBPO);
//    }
//

    public GlobalBPO createGlobalBPO(GlobalBPO globalBPO){
        return globalBPORepository.save(globalBPO);
    }


    public List<GlobalBPO> getAllGlobalBPOs() {
        return globalBPORepository.findAll();
    }

    public Optional<GlobalBPO> getGlobalBPOById(Long id) {
        return globalBPORepository.findById(id);
    }







    @Transactional
    public GlobalBPO updateGlobalBPO(Long id, GlobalBPO updatedGlobalBPO, List<MultipartFile> images, MultipartFile thumbnailImage, List<String> captions) throws IOException {
        return globalBPORepository.findById(id)
                .map(globalBPO -> {
                    try {
                        // Update thumbnail image if provided
                        if (thumbnailImage != null && !thumbnailImage.isEmpty()) {
                            globalBPO.setThumbnailImage(thumbnailImage.getBytes());
                        }

                        // Update other fields
                        globalBPO.setTitle(updatedGlobalBPO.getTitle());
                        globalBPO.setSubtitle(updatedGlobalBPO.getSubtitle());
                        globalBPO.setPostDate(updatedGlobalBPO.getPostDate());
                        globalBPO.setDetails(updatedGlobalBPO.getDetails());

                        // Update images if provided
                        if (images != null && !images.isEmpty()) {
                            List<imageGB> postImages = new ArrayList<>();
                            for (int i = 0; i < images.size(); i++) {
                                MultipartFile imageFile = images.get(i);
                                imageGB postImage = new imageGB();
                                postImage.setImg(imageFile.getBytes());
                                postImage.setCaption(captions.get(i)); // Add the corresponding caption
                                postImages.add(postImage);
                            }
                            globalBPO.setImages(postImages);
                        }

                        return globalBPORepository.save(globalBPO);

                    } catch (IOException e) {
                        throw new RuntimeException("Error processing images", e);
                    }
                })
                .orElseThrow(() -> new RuntimeException("GlobalBPO not found with id " + id));
    }




    @Transactional
    public void deleteGlobalBPO(Long id) {
        globalBPORepository.deleteById(id);

        imageGBRepository.deleteByGlobalBpoId(id);


    }



}


//
////    create
//public GlobalBPO createNewGlobalBPO(GlobalBPO globalBPO) {
//    return globalBpoRepository.save(globalBPO);
//}
//
//// show from db to website
//public List<GlobalBPO> getAllGlobalBPO() {
//    return (List<GlobalBPO>) globalBpoRepository.findAll();
//
//}
//
//
//// delete by id
//public void deleteGlobalBPO(Long id) {
//    globalBpoRepository.deleteById(id);
//}
//
//
//// edit
//public GlobalBPO getGlobalBPOById(Long id) {
//    return globalBpoRepository.findById(id).get();
//}
//
//;
