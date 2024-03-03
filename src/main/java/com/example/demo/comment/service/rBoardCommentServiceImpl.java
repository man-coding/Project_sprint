package com.example.demo.comment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.comment.dto.CommentDTO;
import com.example.demo.comment.entity.rBoardComment;
import com.example.demo.comment.repository.rBoardCommentRepository;
import com.example.demo.runningBoard.entity.Running;



@Service
public class rBoardCommentServiceImpl implements rBoardCommentService {

	@Autowired
	private rBoardCommentRepository repository;

	@Override
	public int register(CommentDTO dto) {
		rBoardComment entity = dtoToEntity(dto);
		repository.save(entity);

		return entity.getCommentNo();
	}


	@Override
	public List<CommentDTO> getListByBoardNo(int boardNo) {
		Running board = Running.builder().no(boardNo).build();  //엔티티 생성
		List<rBoardComment> entityList = repository.findByBoard(board);
		List<CommentDTO> dtoList = new ArrayList<>();
		for (rBoardComment entity : entityList) {
			CommentDTO dto = entityToDto(entity);
			dtoList.add(dto);
		}

		return dtoList;
	}


	@Override
	public void remove(int no) {
		repository.deleteById(no);
	}	

}
