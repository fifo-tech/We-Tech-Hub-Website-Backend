package com.example.fifotech.controller;

import com.example.fifotech.entity.CompletedJob;
import com.example.fifotech.repository.CompletedJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://wetechhub.com", "http://localhost:4200"},  allowCredentials = "true")
public class CompletedJobController {



    @Autowired
    private CompletedJobRepository completedJobRepository;

    // Get all completed (archived) jobs
    @GetMapping("/completed-jobs")
    public List<CompletedJob> getCompletedJobs() {
        return completedJobRepository.findAll();
    }
}
