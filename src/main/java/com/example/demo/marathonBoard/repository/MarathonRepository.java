package com.example.demo.marathonBoard.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.marathonBoard.entity.Marathon;



public interface MarathonRepository extends JpaRepository<Marathon, Integer> {

	Page<Marathon> findByTitleContaining(String keyword, Pageable pageable);
}
