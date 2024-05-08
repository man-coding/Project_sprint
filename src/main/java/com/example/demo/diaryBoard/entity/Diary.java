package com.example.demo.diaryBoard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Diary extends BaseEntity {

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
	int countLike;

	@Column(length = 200, nullable = true)
	private String imgPath; // 첨부파일 이름
}