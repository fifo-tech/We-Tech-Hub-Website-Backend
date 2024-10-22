package com.example.fifotech.controller;

import com.example.fifotech.entity.JobPosting;
import com.example.fifotech.repository.JobPostingRepository;
import com.example.fifotech.services.JobPostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://wetechhub.com", "http://localhost:4200"},  allowCredentials = "true")
public class JobPostingController {

    @Autowired
    private JobPostingService jobPostingService;

    @Autowired
    private JobPostingRepository jobPostingRepository;

    // Get all active job postings (where application deadline hasn't passed)
    @GetMapping("/active-jobs")
    public List<JobPosting> getActiveJobPosts() {
        LocalDate currentDate = LocalDate.now();
        return jobPostingRepository.findByApplicationDeadlineAfter(currentDate);
    }


    // create
    @PostMapping({"/postNewJob"})
    public JobPosting addNewJobPosting(@RequestBody JobPosting jobPosting) {

        return jobPostingService.postNewJob(jobPosting);

    }



    // show
    @GetMapping({"/getAllJobPosts"})
    public List<JobPosting> getAllJobPosts() {
        return jobPostingService.getAllJobPosts();
    }

    ;

    // delete
    @DeleteMapping({"/deleteJobPost/{id}"})
    public void deleteJobPost(@PathVariable("id") Long id) {
        jobPostingService.deleteJobPost(id);
    }

//    for DP
    @GetMapping({"/getJobPostById/{id}"})
    public JobPosting getJobPostById(@PathVariable("id") Long id) {

        return jobPostingService.getJobPostById(id);
    }

    ;

//    edit  >>  Update

    // Update job post
    @PutMapping("/updateJobPost/{id}")
    public JobPosting updateJobPost(@PathVariable("id") Long id, @RequestBody JobPosting updatedJobPosting) {
        return jobPostingService.updateJobPost(id, updatedJobPosting);
    }






}
