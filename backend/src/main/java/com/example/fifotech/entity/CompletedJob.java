package com.example.fifotech.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "completed_job_postings")
public class CompletedJob {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String jobTitle;
    @Column(length = 5000)
    private String jobDescription;
    private String salary;
    private String experienceRequired;
    private String educationQualification;

    @Temporal(TemporalType.DATE)
    private LocalDate applicationDeadline;

    private String contactInformation;


    @ElementCollection
    @Column(length = 5000)
    private List<String> responsibilities;

    @ElementCollection
    @Column(length = 5000)
    private List<String> requirements;

    @ElementCollection
    @Column(length = 5000)
    private List<String> whatWeOffer;

    private LocalTime postTime;
}
