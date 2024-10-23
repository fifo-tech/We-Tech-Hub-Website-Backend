package com.example.fifotech.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "gallery_img")
public class GalleryImage {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    @Column(length = 2000)
    private String caption;



    @ManyToOne
    @JoinColumn(name = "gallery_id")
    private Gallery gallery;


}
