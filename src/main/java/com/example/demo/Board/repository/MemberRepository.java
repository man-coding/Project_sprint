package com.example.demo.Board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Board.entity.Member;



public interface MemberRepository extends JpaRepository<Member, String> {

}
