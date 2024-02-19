package com.example.demo.Board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Board.entity.Marathon;


public interface MarathonRepository extends JpaRepository<Marathon, Integer> {

}
