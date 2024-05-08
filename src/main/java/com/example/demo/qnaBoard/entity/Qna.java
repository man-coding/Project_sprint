package com.example.demo.qnaBoard.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Qna extends BaseEntity{
    
    /* Qna 테이블 
    * 키 : no (게시글 번호)
    * 속성: 제목, 작성자, 작성일, 내용, 조회수 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int no;

    @Column(length = 100, nullable = false)
    String title;

    @Column(length = 20, nullable = false)
    String writer;

    @Column(length = 255, nullable = false)
    String content;

    @Column(length = 10, nullable = true)
    int countView;
}
