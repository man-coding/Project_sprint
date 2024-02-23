package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

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
	void 게시물추가() {
		List<Marathon> list = new ArrayList<>();
		list.add(new Marathon(0, "제목입니다", "2024-02-23", "인천", "내용입니다", "둘리", null, null));
		
		repository.saveAll(list);
	}
	
	@Test
	void 게시물찾기() {
		List<Marathon> list = repository.get1("제목");
		for(Marathon marathon : list) {
			System.out.println(marathon);
		}
	}
	
}
