package com.example.demo.qnaBoard.dto;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QnaDTO {
    int no;
    String title;
    String writer;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    String content;
    int countView;

}