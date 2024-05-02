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
    private rBoardCommentRepository repository; // rBoardCommentRepository에 대한 의존성 주입

    @Override
    public int register(CommentDTO dto) { 
        rBoardComment entity = dtoToEntity(dto); // DTO 객체를 엔티티 객체로 변환
        repository.save(entity); // 변환된 엔티티를 저장소에 저장

        return entity.getCommentNo(); // 저장된 엔티티의 댓글 번호 반환
    }


    @Override
    public List<CommentDTO> getListByBoardNo(int boardNo) {
        Running board = Running.builder().no(boardNo).build();  // 주어진 boardNo를 사용하여 Running 엔티티 생성
        List<rBoardComment> entityList = repository.findByBoard(board); // 생성된 Running 엔티티를 사용하여 댓글 리스트 조회
        List<CommentDTO> dtoList = new ArrayList<>(); // 조회된 엔티티 리스트를 담을 DTO 리스트 생성
        for (rBoardComment entity : entityList) {
            CommentDTO dto = entityToDto(entity); // 엔티티 객체를 DTO 객체로 변환
            dtoList.add(dto); // 변환된 DTO 객체를 리스트에 추가
        }

        return dtoList; // 완성된 DTO 리스트 반환
    }


    @Override
    public void remove(int no) {
        repository.deleteById(no); // 주어진 댓글 번호에 해당하는 댓글 삭제
    }    

}

