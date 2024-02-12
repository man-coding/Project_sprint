package com.example.demo.runningBoard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

public class RunningBoard extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int no;

	@Column(length = 100, nullable = false)
	String title;

	@Column(length = 255, nullable = false)
	String content;

	@ManyToOne
	@Column(length = 20, nullable = false)
	String writer;

	@Column(length = 100, nullable = true)
	String fileName;

	@Column(length = 100, nullable = true)
	String filePathe;
}
