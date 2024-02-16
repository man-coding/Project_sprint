package com.example.demo.runningBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.runningBoard.entity.Training;

public interface TrainingRepository extends JpaRepository<Training, Integer> {

}
