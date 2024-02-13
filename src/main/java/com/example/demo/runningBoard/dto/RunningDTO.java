package com.example.demo.runningBoard.dto;

import java.time.LocalDateTime;

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
	String title;
	String runningDate;
	String place;
	String content;
	String writer;
	String fileName;
	String filePath;
	LocalDateTime regDate;
	LocalDateTime modDate;
	
}
