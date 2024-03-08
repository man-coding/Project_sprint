package com.example.demo.runningBoard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.runningBoard.entity.Running;

public interface RunningRepository extends JpaRepository<Running, Integer> {

	Page<Running> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
}