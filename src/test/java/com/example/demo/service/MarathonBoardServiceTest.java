package com.example.demo.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.marathonBoard.dto.MarathonDTO;
import com.example.demo.marathonBoard.service.MarathonBoardService;

@SpringBootTest
public class MarathonBoardServiceTest {

	@Autowired
	MarathonBoardService service;

	@Test
	public void 러닝30개등록() throws Exception {
		for (int i = 1; i <= 30; i++) {

			MarathonDTO dto = MarathonDTO.builder().title("삼일절마라톤").MarathonDate("3월 1일 am 9:00").location("인천대송도캠퍼스")
					.build();
			MultipartFile file = null;
			service.register(dto, file);

		}

	}

	@Test
	public void 러닝단건등록() throws Exception {

		MarathonDTO dto = MarathonDTO.builder().title("삼일절마라톤").MarathonDate("3월 1일 am 9:00").location("인천대송도캠퍼스")
				.build();

		MultipartFile file = null;
		int no = service.register(dto, file);
		System.out.println(no);

	}

	@Test
	public void 러닝목록조회() {
		Page<MarathonDTO> page = service.getList(0);
		List<MarathonDTO> list = page.getContent();

		for (MarathonDTO dto : list) {
			System.out.println(dto);
		}
	}

	@Test
	public void 러닝단건조회() {
		MarathonDTO dto = service.read(1);
		System.out.println(dto);
	}

	@Test
	public void 러닝수정() {
		MarathonDTO dto = service.read(1);
		dto.setMarathonDate("내용이수정되었습니다~");
		MultipartFile file = null;
		service.modify(dto, file);
	}

	@Test
	public void 러닝삭제() {
		service.remove(1);
	}

}