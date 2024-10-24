package com.example.fifotech.services;

import com.example.fifotech.entity.CompletedJob;
import com.example.fifotech.repository.CompletedJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompletedJobService {


    private final CompletedJobRepository completedJobRepository;


    public CompletedJobService(CompletedJobRepository completedJobRepository) {
        this.completedJobRepository = completedJobRepository;
    }

    public List<CompletedJob> getAllCompletedTasks() {
        return completedJobRepository.findAll();
    }
}
