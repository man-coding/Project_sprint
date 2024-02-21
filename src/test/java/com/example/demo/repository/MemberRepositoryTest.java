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
	public void 회원등록() {
		List<Member> list = new ArrayList<>();
		
		list.add(new Member("chch2857", "1234", "둘리", "USER"));
		
		repository.saveAll(list);
	}
	@Test
	public void 회원목록조회() {

		List<Member> list = repository.findAll();
		for(Member member : list) {
			System.out.println(member);
		}
	}
	
	@Test
	public void 회원단건조회() {
		Optional<Member> result = repository.findById("user1");
		if(result.isPresent()) {
			Member member = result.get();
			System.out.println(member);
		}
	}
	
	@Test
	public void 회원수정() {
		Optional<Member> result = repository.findById("user1");
		Member member = result.get();
		member.setName("또치");
		repository.save(member);
	}
	
	//게시물이 없는 회원은 삭제해도 문제가 없지만
	//게시물이 있는 경우에는 삭제할 수 없음
	
	//데이터 생성 : 부모 > 자식
	//데이터 삭제 : 자식 > 부모 
	
	@Test
	public void 회원삭제() {
		repository.deleteById("user1");
	}
	
	
}
