package com.example.demo.trainingBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.trainingBoard.entity.Training;

public interface TrainingRepository extends JpaRepository<Training, Integer> {

}
