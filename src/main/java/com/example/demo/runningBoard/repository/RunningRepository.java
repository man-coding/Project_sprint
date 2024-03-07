package com.example.demo.runningBoard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.runningBoard.entity.Running;

public interface RunningRepository extends JpaRepository<Running, Integer> {

	List<Running> findByTitleContaining(String keyword);
}
