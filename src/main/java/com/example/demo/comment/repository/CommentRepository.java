package com.example.demo.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.comment.entity.mBoardComment;
import com.example.demo.comment.entity.rBoardComment;
import com.example.demo.marathonBoard.entity.Marathon;
import com.example.demo.runningBoard.entity.Running;

import jakarta.transaction.Transactional;

@Transactional
public interface CommentRepository extends JpaRepository<rBoardComment, Integer>{
	List<rBoardComment> findByBoard(Running rBoard);
	
	void deleteByBoard(Running rBoard);
	
	List<mBoardComment> finfindByBoard(Marathon mBoard);
	
	void deleteByBoard(Marathon mBoard);
	
	
}
