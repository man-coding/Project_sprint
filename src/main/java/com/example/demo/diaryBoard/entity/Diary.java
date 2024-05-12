package com.example.demo.diaryBoard.entity;

import com.example.demo.comment.entity.dBoardComment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

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

	@OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, orphanRemoval = true)
	@Where(clause = "parent_comment_id is null")
	List<dBoardComment> comments = new ArrayList<>();

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