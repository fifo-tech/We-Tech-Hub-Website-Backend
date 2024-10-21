package com.example.fifotech.services;

import com.example.fifotech.entity.CompletedJob;
import com.example.fifotech.entity.JobPosting;
import com.example.fifotech.repository.CompletedJobRepository;
import com.example.fifotech.repository.JobPostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class JobSchedulerService {

    @Autowired
    private JobPostingRepository jobPostingRepository;

    @Autowired
    private CompletedJobRepository completedJobRepository;


    // Scheduled to run daily at midnight
//    @Scheduled(cron = "0 0 0 * * *")
    @Scheduled(cron = "0 0/1 * * * *")
    public void archiveExpiredJobs() {
        LocalDate currentDate = LocalDate.now();
        List<JobPosting> expiredJobs = jobPostingRepository.findByApplicationDeadlineBefore(currentDate);

        for (JobPosting jobPosting : expiredJobs) {
            // Create a CompletedJob object to move expired job
            CompletedJob completedJob = new CompletedJob();

            completedJob.setJobTitle(jobPosting.getJobTitle());
            completedJob.setJobDescription(jobPosting.getJobDescription());
            completedJob.setSalary(jobPosting.getSalary());
            completedJob.setExperienceRequired(jobPosting.getExperienceRequired());
            completedJob.setEducationQualification(jobPosting.getEducationQualification());
            completedJob.setApplicationDeadline(jobPosting.getApplicationDeadline());
            completedJob.setContactInformation(jobPosting.getContactInformation());
            completedJob.setResponsibilities(jobPosting.getResponsibilities());
            completedJob.setRequirements(jobPosting.getRequirements());
            completedJob.setWhatWeOffer(jobPosting.getWhatWeOffer());


            completedJob.setArchivedDate(new Date()); // Set archived date to current date

            // Save to completed jobs table
            completedJobRepository.save(completedJob);

            // Remove from active job postings
            jobPostingRepository.delete(jobPosting);
        }
    }

}
