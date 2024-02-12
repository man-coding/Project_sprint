package com.example.demo.runningBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.runningBoard.entity.RunningBoard;

public interface RunningBoardRepository extends JpaRepository<RunningBoard, Integer> {

}
