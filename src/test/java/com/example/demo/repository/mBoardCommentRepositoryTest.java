package com.example.demo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.comment.entity.mBoardComment;
import com.example.demo.comment.repository.mBoardCommentRepository;
import com.example.demo.marathonBoard.entity.Marathon;
import com.example.demo.member.entity.Member;

@SpringBootTest
public class mBoardCommentRepositoryTest {

	@Autowired
	mBoardCommentRepository repository;
	
	@Test
	public void 댓글등록() {
		// 테이블에 있는 회원
		Member member = Member.builder().id("member1").build();
		// 테이블에 있는 게시물
		Marathon board  = Marathon.builder().no(1).build();
		mBoardComment comment = new mBoardComment(0, board, "댓글입니다", member);
		repository.save(comment);	
	}
}
