package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.marathonBoard.entity.Marathon;
import com.example.demo.marathonBoard.repository.MarathonRepository;

@SpringBootTest
public class MarathonRepositoryTest {

	@Autowired
	MarathonRepository repository;

	@Test
	public void 마라톤등록30개등록() {

		for (int i = 1; i <= 30; i++) {
			Marathon marathon = Marathon.builder().title("삼일절단축마라톤").marathonDate("3월 1일 am 9:00").location("인천대송도캠퍼스")
					.content("문학보조경기장에서 5km 천천히 뛸 예정입니다. 많은 참여 바랍니다!").writer("인천마라톤협회").build();
			repository.save(marathon);
		}

	}

	@Test
	public void 마라톤목록조회() {

		List<Marathon> list = repository.findAll();

		for (Marathon marathon : list) {
			System.out.println(marathon);
		}

	}

	@Test
	public void 마라톤단건조회() {
		Optional<Marathon> result = repository.findById(1);

		Marathon Marathon = result.get();

		System.out.println(Marathon);

	}

	@Test
	public void 마라톤수정() {
		Optional<Marathon> result = repository.findById(1);

		Marathon marathon = result.get();

		marathon.setContent("수정합니다 4월로 변경되었습니다");

		repository.save(marathon);
	}

	@Test
	public void 마라톤삭제() {
		repository.deleteById(2);
	}
}
