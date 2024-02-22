package com.example.demo.marathonBoard.entity;

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
	
	@Column(length = 20, nullable = false)
	String writer;
	
	@Column(length = 30, nullable = false)
	String marathonDate;
	
	@Column(length = 30, nullable = false)
	String location;

	@Column(length = 255, nullable = false)
	String content;

	@Column(length = 10, nullable = true)
	int countLike;
	
	@Column(length = 100, nullable = true)
	String fileName;

	@Column(length = 100, nullable = true)
	String filePath;
}
