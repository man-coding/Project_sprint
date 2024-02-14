package com.example.demo.runningBoard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.runningBoard.entity.Comment;
import com.example.demo.runningBoard.entity.RunningBoard;

import jakarta.transaction.Transactional;

@Transactional
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	List<Comment> findByBoard(RunningBoard board);
	
	void deleteByBoard(RunningBoard board);
}
