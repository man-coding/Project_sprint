package com.example.demo.marathonBoard.dto;

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
public class MarathonDTO {
	int no;
	String writer;
	String title;
	String MarathonDate;
	String location;
	String content;
	int countLike;
	String fileName;
	String filePath;
	LocalDateTime regDate;
	LocalDateTime modDate;

}
