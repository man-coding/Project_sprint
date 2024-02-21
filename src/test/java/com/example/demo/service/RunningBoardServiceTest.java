package com.example.demo.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.runningBoard.dto.RunningDTO;
import com.example.demo.runningBoard.service.RunningBoardService;

@SpringBootTest
public class RunningBoardServiceTest {

	@Autowired
	RunningBoardService service;

	@Test
	public void 러닝30개등록() {
		for (int i = 1; i <= 30; i++) {

			RunningDTO dto = RunningDTO.builder().title("문학경기장 5k 런").runningDate("3월 1일 am 9:00").location("인천대송도캠퍼스")
					.build();
//				MultipartFile file = null;    러닝게시판은 파일첨부 없음
			service.register(dto);

		}

	}

	@Test
	public void 러닝단건등록() {

		RunningDTO dto = RunningDTO.builder().title("문학경기장 5k 런").runningDate("3월 1일 am 9:00").location("인천대송도캠퍼스").build();

//		MultipartFile file = null;   파일첨부 없음
		int no = service.register(dto);
		System.out.println(no);

	}

	@Test
	public void 러닝목록조회() {
		Page<RunningDTO> page = service.getList(0);
		List<RunningDTO> list = page.getContent();

		for (RunningDTO dto : list) {
			System.out.println(dto);
		}
	}

	@Test
	public void 러닝단건조회() {
		RunningDTO dto = service.read(1);
		System.out.println(dto);
	}

	@Test
	public void 러닝수정() {
		RunningDTO dto = service.read(1);
		dto.setRunningDate("내용이수정되었습니다~");
//		MultipartFile file = null; 파일첨부 없음
		service.modify(dto);
	}

	@Test
	public void 러닝삭제() {
		service.remove(1);
	}

}