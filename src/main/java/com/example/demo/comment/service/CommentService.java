package com.example.demo.comment.service;


import java.util.List;

import com.example.demo.comment.dto.CommentDTO;
import com.example.demo.comment.entity.Comment;
import com.example.demo.member.entity.Member;
import com.example.demo.runningBoard.entity.Running;


public interface CommentService {

	int register(CommentDTO dto);

//	List<CommentDTO> getList();
	
	List<CommentDTO> getListByBoardNo(int boardNo);

//	CommentDTO read(int no);

//	void modify(CommentDTO dto);

	void remove(int no);

	default Comment dtoToEntity(CommentDTO dto) {

		Member member = Member.builder().id(dto.getWriter()).build(); //엔티티 생성

		Running board = Running.builder().no(dto.getBoardNo()).build();  //엔티티 생성

		Comment entity = Comment.builder()
				.commentNo(dto.getCommentNo())
				.board(board)
				.content(dto.getContent())
				.writer(member)
				.build();
		
		return entity;
	}

	default CommentDTO entityToDto(Comment entity) {

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
