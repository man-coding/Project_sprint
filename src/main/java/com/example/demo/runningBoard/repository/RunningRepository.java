package com.example.demo.runningBoard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.runningBoard.entity.Running;



public interface RunningRepository extends JpaRepository<Running, Integer> {
	@Query(value = "select * from running where title like %:title%", nativeQuery = true)
	List<Running> get1(@Param("title") String title);
}
