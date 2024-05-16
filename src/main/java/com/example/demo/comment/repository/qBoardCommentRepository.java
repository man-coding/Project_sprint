package com.example.demo.comment.repository;

import com.example.demo.comment.entity.qBoardComment;
import com.example.demo.qnaBoard.entity.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface qBoardCommentRepository extends JpaRepository<qBoardComment, Integer> {
    List<qBoardComment> findByQna(Qna qna);
}
