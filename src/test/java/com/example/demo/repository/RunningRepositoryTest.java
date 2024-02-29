package com.example.demo.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	void 게시물추가() {
		List<Running> list = new ArrayList<>();
		list.add(new Running(0, "user1", "제목입니다", LocalDate.of(2024, 02, 29), "인천 송도", "내용입니다", 0, 0, 0));
		
		repository.saveAll(list);
	}
	
	@Test
	void 게시물찾기() {
		List<Running> list = repository.get1("제목");
		for(Running running : list) {
			System.out.println(running);
		}
	}
	
}
