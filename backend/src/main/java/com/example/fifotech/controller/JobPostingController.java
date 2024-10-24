package com.example.fifotech.controller;

import com.example.fifotech.entity.CompletedJob;
import com.example.fifotech.entity.JobPosting;
import com.example.fifotech.repository.JobPostingRepository;
import com.example.fifotech.services.CompletedJobService;
import com.example.fifotech.services.JobPostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
//@RequiredArgsConstructor
@CrossOrigin(origins = {"https://wetechhub.com", "http://localhost:4200"},  allowCredentials = "true")
public class JobPostingController {

//    @Autowired
//    private JobPostingService jobPostingService;

    @Autowired
    private CompletedJobService completedJobRepository;
//
//    @Autowired
//    private JobPostingRepository jobPostingRepository;
//
//
//    private final JobPostingService jobPostingService;
//    private final CompletedJobService completedJobService;
//
//



    private final JobPostingService jobPostingService;

    @Autowired
    public JobPostingController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
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



//    // Get all completed tasks
//    @GetMapping("/getAllCompletedTasks")
//    public List<CompletedJob> getAllCompletedTasks() {
//        return jobPostingService.getAllCompletedTasks();
//    }

    // Get all completed tasks
    @GetMapping("/getAllCompletedTasks")
    public List<CompletedJob> getAllCompletedTasks() {
        return completedJobRepository.getAllCompletedTasks();
    }

//    @GetMapping("/job-postings")
//    public List<JobPosting> getActiveJobPosts() {
//        LocalTime today = LocalTime.now();
//        return jobPostingRepository.findByPostTimeBefore(today);
//    }


    @GetMapping("/job-postings")
    public ResponseEntity<String> getActiveJobPosts() {
        jobPostingService.moveExpiredJobPostings();
        return ResponseEntity.ok("Scheduler test executed");
    }










}
