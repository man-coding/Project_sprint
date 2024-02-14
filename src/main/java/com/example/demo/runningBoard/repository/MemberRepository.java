package com.example.demo.runningBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.runningBoard.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{

	
	
}
