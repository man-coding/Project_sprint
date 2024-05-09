package com.example.demo.diaryBoard.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

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
public class DiaryDTO {

	int no;
	String writer;
	String title;
	String content;
	int countLike;
	MultipartFile uploadFile; // 파일 스트림
	String imgPath; // 파일 이름
	LocalDateTime regDate;
	LocalDateTime modDate;
	
	private String keyword; // 검색 키워드

	// 좋아요를 추가하는 메서드
	public void addLike() {
		this.countLike++;
	}

	// 좋아요를 삭제하는 메서드
	public void removeLike() {
		if (this.countLike > 0) {
			this.countLike--;
		}
	}
}
