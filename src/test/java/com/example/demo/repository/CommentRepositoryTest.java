package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.comment.entity.Comment;
import com.example.demo.comment.repository.CommentRepository;
import com.example.demo.member.entity.Member;
import com.example.demo.runningBoard.entity.Running;


@SpringBootTest
public class CommentRepositoryTest {

	@Autowired
	CommentRepository repository;
	
	@Test
	public void 댓글등록() {
		// 테이블에 있는 회원
		Member member = Member.builder().id("user1").build();
		// 테이블에 있는 게시물
		Running board  = Running.builder().no(1).build();
		Comment comment = new Comment(0, board, "댓글입니다", member);
		repository.save(comment);	
	}
	
}
