package com.example.demo.qnaBoard.repository;

import com.example.demo.qnaBoard.entity.Qna;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QnaRepository extends JpaRepository<Qna, Integer> {
}
