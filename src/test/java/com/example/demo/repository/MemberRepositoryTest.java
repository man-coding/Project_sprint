package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;



@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository repository;
	
	@Test
	void 멤버추가() {
		
		List<Member> list = new ArrayList<>();
		
		for(int i = 1; i <= 10; i++) {
			
			list.add(new Member("user"+ i, "1234", "user"+i, "USER"));
			repository.saveAll(list);
		}
		
	}
	
	@Test
	void 멤버단건조회() {
		Optional<Member> list = repository.findById("user1");
		
		System.out.println(list);
	}
	
}
