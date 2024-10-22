package com.example.fifotech.services;

import com.example.fifotech.entity.JobPosting;
import com.example.fifotech.repository.JobPostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPostingService {

    @Autowired
    private JobPostingRepository jobPostingRepository;

    //    create
    public JobPosting postNewJob(JobPosting jobPosting) {

        return jobPostingRepository.save(jobPosting);
    }

    // show from db to website
    public List<JobPosting> getAllJobPosts() {
        return (List<JobPosting>) jobPostingRepository.findAll();

    }


    // delete by id
    public void deleteJobPost(Long id) {
        jobPostingRepository.deleteById(id);
    }


    // edit & Details page
    public JobPosting getJobPostById(Long id) {
        return jobPostingRepository.findById(id).get();
    }

    ;


    // update job post
    public JobPosting updateJobPost(Long id, JobPosting updatedJobPosting) {
        // Find the existing job post
        JobPosting existingJobPosting = jobPostingRepository.findById(id).orElse(null);

        if (existingJobPosting != null) {
            // Update the fields with the new values from the request
            existingJobPosting.setJobTitle(updatedJobPosting.getJobTitle());
            existingJobPosting.setJobDescription(updatedJobPosting.getJobDescription());
            existingJobPosting.setSalary(updatedJobPosting.getSalary());
            existingJobPosting.setExperienceRequired(updatedJobPosting.getExperienceRequired());
            existingJobPosting.setEducationQualification(updatedJobPosting.getEducationQualification());
            existingJobPosting.setApplicationDeadline(updatedJobPosting.getApplicationDeadline());
            existingJobPosting.setContactInformation(updatedJobPosting.getContactInformation());
            existingJobPosting.setResponsibilities(updatedJobPosting.getResponsibilities());
            existingJobPosting.setRequirements(updatedJobPosting.getRequirements());
            existingJobPosting.setWhatWeOffer(updatedJobPosting.getWhatWeOffer());

            // Save the updated job posting back to the database
            return jobPostingRepository.save(existingJobPosting);
        }

        return null;  // Return null if the job post with the given ID was not found
    }



}
