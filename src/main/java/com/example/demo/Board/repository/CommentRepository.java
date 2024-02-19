package com.example.demo.Board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Board.entity.Comment;
import com.example.demo.Board.entity.Running;

import jakarta.transaction.Transactional;

@Transactional
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	List<Comment> findByBoard(Running board);
	
	void deleteByBoard(Running board);
}
