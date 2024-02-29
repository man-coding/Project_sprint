package com.example.demo.comment.service;


import java.util.List;

import com.example.demo.comment.dto.CommentDTO;
import com.example.demo.comment.entity.rBoardComment;
import com.example.demo.member.entity.Member;
import com.example.demo.runningBoard.entity.Running;


public interface rBoardCommentService {

	int register(CommentDTO dto);

	
	List<CommentDTO> getListByBoardNo(int boardNo);



	void remove(int no);

	default rBoardComment dtoToEntity(CommentDTO dto) {

		Member member = Member.builder().id(dto.getWriter()).build(); 
		
		Running board = Running.builder().no(dto.getBoardNo()).build();  

		rBoardComment entity = rBoardComment.builder()
				.commentNo(dto.getCommentNo())
				.board(board)
				.content(dto.getContent())
				.writer(member)
				.build();
		
		return entity;
	}

	default CommentDTO entityToDto(rBoardComment entity) {

		CommentDTO dto = CommentDTO.builder()
				.commentNo(entity.getCommentNo())
				.boardNo(entity.getBoard().getNo())
				.content(entity.getContent())
				.writer(entity.getWriter().getId())
				.regDate(entity.getRegDate()) 
				.modDate(entity.getModDate())
				.build();

		return dto;
	}

}
