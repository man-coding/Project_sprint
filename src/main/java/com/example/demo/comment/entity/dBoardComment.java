package com.example.demo.comment.entity;

import com.example.demo.diaryBoard.entity.Diary;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class dBoardComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int commentNo;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "diary_id")
    Diary diary;

    @Column(length = 20, nullable = false)
    String writer;

    @Column(length = 1500)
    String content;

    /* 대댓글 */
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent_comment_id")
//    @JsonIgnore
//    dBoardComment parentComment;

//    @OneToMany(mappedBy = "parentComment")
//    private List<dBoardComment> replies = new ArrayList<>();
}