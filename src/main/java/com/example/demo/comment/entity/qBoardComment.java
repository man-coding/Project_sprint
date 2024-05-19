package com.example.demo.comment.entity;
import com.example.demo.qnaBoard.entity.Qna;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class qBoardComment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int commentNo;

    @ManyToOne
    Qna qna;

    @Column(length = 1500)
    String content;

    @Column(length = 20, nullable = false)
    String writer;
}
