package com.example.fifotech.repository;

import com.example.fifotech.entity.CompletedJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletedJobRepository extends JpaRepository<CompletedJob, Long> {
}
