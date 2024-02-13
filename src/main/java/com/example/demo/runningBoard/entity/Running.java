package com.example.demo.runningBoard.entity;

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

	@Column(length = 100, nullable = false)
	String title;
	
	@Column(length = 30, nullable = false)
	String runningDate;
	
	@Column(length = 30, nullable = false)
	String place;

	@Column(length = 255, nullable = false)
	String content;

	
	@Column(length = 20, nullable = false)
	String writer;

	
}
