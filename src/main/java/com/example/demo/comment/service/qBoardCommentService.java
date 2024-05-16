package com.example.demo.comment.service;

import com.example.demo.comment.dto.CommentDTO;

import java.util.*;

public interface qBoardCommentService {
    int register(CommentDTO dto);
    List<CommentDTO> getListByBoardNo(int boardNo);
}
