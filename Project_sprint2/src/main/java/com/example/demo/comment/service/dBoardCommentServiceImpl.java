package com.example.demo.comment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.comment.dto.CommentDTO;
import com.example.demo.comment.entity.dBoardComment;
import com.example.demo.comment.repository.dBoardCommentRepository;
import com.example.demo.diaryBoard.entity.Diary;

@Service
public class dBoardCommentServiceImpl implements dBoardCommentService {

    @Autowired
    private dBoardCommentRepository repository;

    @Override
    public int register(CommentDTO dto) {
        dBoardComment entity = dtoToEntity(dto);
        repository.save(entity);

        return entity.getCommentNo();
    }

    @Override
    public List<CommentDTO> getListByBoardNo(int boardNo) {

        Diary board = Diary.builder().no(boardNo).build(); // 게시물 조회

        List<dBoardComment> entityList = repository.findByBoard(board);

        List<CommentDTO> dtoList = new ArrayList<>();

        for (dBoardComment entity : entityList) {
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