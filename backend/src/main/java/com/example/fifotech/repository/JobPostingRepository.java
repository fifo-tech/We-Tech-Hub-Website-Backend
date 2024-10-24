package com.example.fifotech.repository;

import com.example.fifotech.entity.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {



    List<JobPosting> findByApplicationDeadlineBefore(LocalDate currentDate);

    List<JobPosting> findByApplicationDeadlineAfter(LocalDate currentDate);

//-----------Testing
List<JobPosting> findByPostTimeBefore(LocalTime time);

}
