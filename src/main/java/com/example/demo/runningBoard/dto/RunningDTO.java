package com.example.demo.runningBoard.dto;


import java.time.LocalDateTime;

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

public class RunningDTO {

	int no;
	String writer;
	String title;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime runningDate;
	String location;
	String content;
	double latitude;
	double longtitude;
	int countLike;
	LocalDateTime regDate;
	LocalDateTime modDate;
	
	private String keyword; // 검색 키워드
}
