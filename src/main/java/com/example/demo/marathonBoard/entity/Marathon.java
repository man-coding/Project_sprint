package com.example.demo.marathonBoard.entity;

import java.time.LocalDateTime;

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

public class Marathon extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int no;

	@Column(length = 100, nullable = false)
	String title;
	
	@Column(length = 40, nullable = false)
	String writer;
	
	@Column(length = 30, nullable = false)
	LocalDateTime marathonDate;
	
	@Column(length = 30, nullable = false)
	String location;

	@Column(length = 255, nullable = false)
	String content;

	@Column(length = 10, nullable = true)
	int countLike;
	
	@Column(length = 200, nullable = true)
	private String imgPath; //첨부파일 이름

	@Column(length = 255, nullable = false)
	int countView;
}
