package com.example.demo.comment.service;

import com.example.demo.comment.dto.CommentDTO;

import java.util.List;

public class qBoardCommentServiceImp implements qBoardCommentService{
    @Override
    public int register(CommentDTO dto) {
        return 0;
    }

    @Override
    public List<CommentDTO> getListByBoardNo(int boardNo) {
        return List.of();
    }
}
