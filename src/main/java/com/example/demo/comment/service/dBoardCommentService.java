package com.example.demo.comment.service;

import com.example.demo.comment.dto.CommentDTO;
import com.example.demo.comment.entity.dBoardComment;
import com.example.demo.diaryBoard.entity.Diary;
import com.example.demo.member.entity.Member;

import java.util.List;

public interface dBoardCommentService {

    int register(CommentDTO dto);

    List<CommentDTO> getListByBoardNo(int boardNo);

    void remove(int no);

    default dBoardComment dtoToEntity(CommentDTO dto) {
        //멤버 조회
        Member member = Member.builder().id(dto.getWriter()).build();
        Diary diary = Diary.builder().no(dto.getBoardNo()).build();
        //entity 생성
        dBoardComment entity = dBoardComment.builder().commentNo(dto.getCommentNo()).diary(diary)
                .content(dto.getContent()).writer(member.getId()).build();

        return entity;
    }

    default CommentDTO entityToDto(dBoardComment entity) {

        //dto 생성
        CommentDTO dto = CommentDTO.builder().commentNo(entity.getCommentNo()).boardNo(entity.getDiary().getNo())
                .content(entity.getContent()).writer(entity.getWriter()).regDate(entity.getRegDate())
                .modDate(entity.getModDate()).build();

        return dto;
    }
}

