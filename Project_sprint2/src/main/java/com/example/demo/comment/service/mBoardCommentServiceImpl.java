package com.example.demo.comment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.comment.dto.CommentDTO;
import com.example.demo.comment.entity.mBoardComment;
import com.example.demo.comment.repository.mBoardCommentRepository;
import com.example.demo.marathonBoard.entity.Marathon;

@Service
public class mBoardCommentServiceImpl implements mBoardCommentService {
	
	@Autowired
	private mBoardCommentRepository repository;
	
	@Override
	public int register(CommentDTO dto) {
		mBoardComment entity = dtoToEntity(dto);
		repository.save(entity);

		return entity.getCommentNo();
	}
	
	@Override
	public List<CommentDTO> getListByBoardNo(int boardNo) {
		Marathon board = Marathon.builder().no(boardNo).build();  //엔티티 생성
		List<mBoardComment> entityList = repository.findByBoard(board);
		List<CommentDTO> dtoList = new ArrayList<>();
		for (mBoardComment entity : entityList) {
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
