package com.example.demo.runningBoard.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication.Running;
import org.springframework.stereotype.Service;

import com.example.demo.runningBoard.dto.CommentDTO;
import com.example.demo.runningBoard.entity.Comment;
import com.example.demo.runningBoard.entity.RunningBoard;
import com.example.demo.runningBoard.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl2 implements CommentService {

	@Autowired
	private CommentRepository repository;

	@Override
	public int register(CommentDTO dto) {
		Comment entity = dtoToEntity(dto);
		repository.save(entity);

		return entity.getCommentNo();
	}

//	@Override
//	public List<CommentDTO> getList() {
//		List<Comment> entityList = repository.findAll();
//		List<CommentDTO> dtoList = new ArrayList<>();
//		for (Comment entity : entityList) {
//			CommentDTO dto = entityToDto(entity);
//			dtoList.add(dto);
//		}
//
//		return dtoList;
//	}
	
	@Override
	public List<CommentDTO> getListByBoardNo(int boardNo) {
		RunningBoard board = RunningBoard.builder().no(boardNo).build();  //엔티티 생성
		List<Comment> entityList = repository.findByBoard(board);
		List<CommentDTO> dtoList = new ArrayList<>();
		for (Comment entity : entityList) {
			CommentDTO dto = entityToDto(entity);
			dtoList.add(dto);
		}

		return dtoList;
	}

//	@Override
//	public CommentDTO read(int no) {
//		Optional<Comment> result = repository.findById(no);
//		if(result.isPresent()) {
//			Comment entity = result.get();
//			return entityToDto(entity);
//		}
//		return null;
//	}

//	@Override
//	public void modify(CommentDTO dto) {
//		int commentNo = dto.getCommentNo();
//		Optional<Comment> result = repository.findById(commentNo);
//		if(result.isPresent()) {
//			Comment entity = result.get();
//			entity.setContent(dto.getContent());
//			repository.save(entity);
//		}
//	}

	@Override
	public void remove(int no) {
		repository.deleteById(no);
	}	

}
