package com.example.fifotech.services;

import com.example.fifotech.entity.CompletedJob;
import com.example.fifotech.entity.JobPosting;
import com.example.fifotech.repository.CompletedJobRepository;
import com.example.fifotech.repository.JobPostingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class JobSchedulerService {


    //    // Scheduled to run daily at midnight
//
////    @Scheduled(cron = "0 0/1 * * * *")
//    @Scheduled(cron = "0 0 0 * * *")
//public void archiveExpiredJobs() {
//
//
//    LocalDate currentDate = LocalDate.now();
//
//    List<JobPosting> expiredJobs = jobPostingRepository.findByApplicationDeadlineBefore(currentDate);
//
//    for (JobPosting jobPosting : expiredJobs) {
//
//        CompletedJob completedJob = new CompletedJob();
//
//        completedJob.setJobTitle(jobPosting.getJobTitle());
//        completedJob.setJobDescription(jobPosting.getJobDescription());
//        completedJob.setSalary(jobPosting.getSalary());
//        completedJob.setExperienceRequired(jobPosting.getExperienceRequired());
//        completedJob.setEducationQualification(jobPosting.getEducationQualification());
//        completedJob.setApplicationDeadline(jobPosting.getApplicationDeadline());
//        completedJob.setContactInformation(jobPosting.getContactInformation());
//        completedJob.setResponsibilities(jobPosting.getResponsibilities());
//        completedJob.setRequirements(jobPosting.getRequirements());
//        completedJob.setWhatWeOffer(jobPosting.getWhatWeOffer());
//
//
//        completedJob.setArchivedDate(new Date()); // Set archived date to current date
//
//        // Save to completed jobs table
//        completedJobRepository.save(completedJob);
//
//        // Remove from active job postings
//        jobPostingRepository.delete(jobPosting);
//    }
//}
//
//    @Autowired
//    private JobPostingService jobPostingService;


//    private JobPostingRepository jobPostingRepository;
//    private CompletedJobRepository completedJobRepository;
//
//
//    public void JobScheduler(JobPostingRepository jobPostingRepository, CompletedJobRepository completedJobRepository) {
//        this.jobPostingRepository = jobPostingRepository;
//        this.completedJobRepository = completedJobRepository;
//    }
//
//    @Scheduled(cron = "0 0 0 * * ?") // Runs every day at midnight
//    @Transactional
//    public void moveExpiredJobPostings() {
//        LocalDate now = LocalDate.now();
//        List<JobPosting> expiredJobs = jobPostingRepository.findByApplicationDeadlineBefore(now);
//        for (JobPosting job : expiredJobs) {
//            CompletedJob completedJob = new CompletedJob();
//            BeanUtils.copyProperties(job, completedJob);
//            completedJobRepository.save(completedJob);
//            jobPostingRepository.delete(job);
//        }
//
//    }

    private final JobPostingService jobPostingService;

    @Autowired
    public JobSchedulerService(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }



    //    @Scheduled(cron = "0 0 0 * * ?") // Runs every day at midnight
    @Scheduled(cron = "0 */1 * * * ?") // Runs every minute for testing
    @Transactional
    public void scheduleJobPostCheck() {
        jobPostingService.moveExpiredJobPostings();
    }

}
