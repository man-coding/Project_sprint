package com.example.demo.joinMember.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.joinMember.entity.JoinMember;

public interface JoinMemberRepository extends JpaRepository<JoinMember, Integer> {

	List<JoinMember> findByRunningNo_No(int runningNo);

	Optional<JoinMember> findByRunningNo_noAndJoinNo(int runningNo, int joinNo);
	
	
	 Optional<JoinMember> findByRunningNo_NoAndRunnerId_Id(int runningNo, String runnerId);
}
