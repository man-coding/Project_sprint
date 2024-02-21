package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.runningBoard.entity.Running;
import com.example.demo.runningBoard.repository.RunningRepository;

@SpringBootTest
public class RunningRepositoryTest {

	@Autowired
	RunningRepository repository;

	@Test
	public void 러닝등록30개등록() {

		for (int i = 1; i <= 30; i++) {
			Running running = Running.builder().title("문학경기장 5km").runningDate("2월 14일 pm8:00").location("문학보조경기장")
					.content("문학보조경기장에서 5km 천천히 뛸 예정입니다. 많은 참여 바랍니다!").writer("만코딩").build();
			repository.save(running);

			
		}

	}

	@Test
	public void 러닝목록조회() {

		List<Running> list = repository.findAll();

		for (Running running : list) {
			System.out.println(running);
		}

	}

	@Test
	public void 러닝단건조회() {
		Optional<Running> result = repository.findById(1);

		Running running = result.get();

		System.out.println(running);

	}

	@Test
	public void 상품수정() {
		Optional<Running> result = repository.findById(1);

		Running running = result.get();

		running.setContent("수정합니다 5km 천천히 뜁니다 입문자 환영합니다!!");

		repository.save(running);
	}

	@Test
	public void 러닝삭제() {
		repository.deleteById(2);
	}
}
