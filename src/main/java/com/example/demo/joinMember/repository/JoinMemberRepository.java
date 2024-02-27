package com.example.demo.joinMember.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.joinMember.entity.JoinMember;

public interface JoinMemberRepository extends JpaRepository<JoinMember, Integer> {

}
