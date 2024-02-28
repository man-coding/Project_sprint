package com.example.demo.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.comment.dto.CommentDTO;
import com.example.demo.comment.service.rBoardCommentService;


@SpringBootTest
public class CommentServiceTest {

	@Autowired
	rBoardCommentService service;
	
	@Test
	public void 댓글등록() {

		// 테이블에 있는 게시물번호와 유저아이디 사용해야함
		CommentDTO dto = CommentDTO.builder().boardNo(1).content("댓글입니다.").writer("ryuji").
			

		service.register(dto);
	}

	@Test
	public void 게시물별_댓글목록조회() {
		List<CommentDTO> list = service.getListByBoardNo(3);
		for(CommentDTO dto : list) {
			System.out.println(dto);
		}
	}
	
	@Test
	public void 삭제() {
		service.remove(1);
	}
	
}
