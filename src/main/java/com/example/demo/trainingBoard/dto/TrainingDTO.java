package com.example.demo.trainingBoard.dto;
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
public class TrainingDTO {
	int no;
	String title;
	String trainingDate;
	String location;
	String content;
	String writer;
	String fileName;
	String filePath;
	LocalDateTime regDate;
	LocalDateTime modDate;
	
}
