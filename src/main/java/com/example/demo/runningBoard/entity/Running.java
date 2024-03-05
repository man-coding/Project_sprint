package com.example.demo.runningBoard.entity;



import java.time.LocalDate;
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

public class Running extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int no;

	@Column(length = 20, nullable = false)
	String writer;

	@Column(length = 100, nullable = false)
	String title;

	@Column(length = 30, nullable = false)
	LocalDateTime runningDate;

	@Column(length = 30, nullable = false)
	String location;

	@Column(length = 255, nullable = false)
	String content;
	
	@Column(length = 30, nullable = true)
	double latitude;
	
	@Column(length = 30, nullable = true)
	double longtitude;

	@Column(length = 10, nullable = true)
	int countLike;
	
	//파일첨부 필요없음

}
