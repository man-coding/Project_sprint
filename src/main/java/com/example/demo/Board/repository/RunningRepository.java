package com.example.demo.Board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Board.entity.Running;

public interface RunningRepository extends JpaRepository<Running, Integer> {

}
