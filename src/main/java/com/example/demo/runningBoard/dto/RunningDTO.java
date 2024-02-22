package com.example.demo.runningBoard.dto;

import java.time.LocalDate;
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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate runningDate;
	String location;
	String content;
	int countLike;
	LocalDateTime regDate;
	LocalDateTime modDate;
	
}
