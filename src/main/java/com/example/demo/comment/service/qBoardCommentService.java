package com.example.demo.comment.service;

import com.example.demo.comment.dto.CommentDTO;
import com.example.demo.comment.entity.qBoardComment;
import com.example.demo.member.entity.Member;
import com.example.demo.qnaBoard.entity.Qna;

import java.util.*;

public interface qBoardCommentService {
    int register(CommentDTO dto);

    List<CommentDTO> getListByBoardNo(int boardNo);

    void remove(int no);

    default qBoardComment dtoToEntity(CommentDTO dto) {
        Member member = Member.builder().id(dto.getWriter()).build();
        Qna qna = Qna.builder().no(dto.getBoardNo()).build();
        qBoardComment entity = qBoardComment.builder()
                .commentNo(dto.getCommentNo())
                .qna(qna)
                .content(dto.getContent())
                .writer(member.getId())
                .build();
        return entity;
    }

    default CommentDTO entityToDto(qBoardComment entity) {
        CommentDTO dto = CommentDTO.builder()
                .commentNo(entity.getCommentNo()).boardNo(entity.getQna().getNo())
                .content(entity.getContent()).writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate()).build();

        return dto;
    }
}
