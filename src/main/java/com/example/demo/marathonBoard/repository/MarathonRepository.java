package com.example.demo.marathonBoard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.marathonBoard.entity.Marathon;




public interface MarathonRepository extends JpaRepository<Marathon, Integer> {

	@Query(value = "select * from marathon where title like %:title%", nativeQuery = true)
	List<Marathon> get1(@Param("title") String title);
	
}
