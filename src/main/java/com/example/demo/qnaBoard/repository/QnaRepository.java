package com.example.demo.qnaBoard.repository;

import com.example.demo.qnaBoard.entity.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<Qna, Integer> {
}
