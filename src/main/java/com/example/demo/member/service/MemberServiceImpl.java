package com.example.demo.member.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;


@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository repository;


	@Autowired
	PasswordEncoder passwordEncoder;
		
	@Override
	public Page<MemberDTO> getList(int pageNumber) {
		int pageIndex = (pageNumber == 0) ? 0 : pageNumber - 1;
		Pageable pageable = PageRequest.of(pageIndex, 10, Sort.by("regDate").descending());
		Page<Member> entityPage = repository.findAll(pageable);
		Page<MemberDTO> dtoPage = entityPage.map( entity -> entityToDto(entity) );

		return dtoPage;
	}
	
	@Override
	public boolean register(MemberDTO dto) {
		String id = dto.getId();
		MemberDTO getDto = read(id);
		if (getDto != null) {
			System.out.println("사용중인 아이디입니다.");
			return false;
		}
		Member entity = dtoToEntity(dto);
		entity.setRole("ROLE_USER");
		// 패스워드 인코더로 패스워드 암호화하기
		String enPw = passwordEncoder.encode(entity.getPassword());
		entity.setPassword(enPw);

		repository.save(entity);
		return true;


	
	
	}
	@Override
	public MemberDTO findMemberById(String id) {
		Optional<Member> optionalMember = repository.findById(id);
		if (optionalMember.isPresent()) {
			Member member = optionalMember.get();
			return entityToDto(member);
		} else {
			return null; // 또는 예외를 던지거나 다른 적절한 처리를 수행할 수 있습니다.
		}
	}

	@Override
	public MemberDTO read(String id) {
		Optional<Member> result = repository.findById(id);
		if (result.isPresent()) {
			Member member = result.get();
			return entityToDto(member);
		} else {
			return null;
		}
	}
	
	// 소셜 로그인한 이메일로 회원가입하는 메소드 추가
	@Override
	public MemberDTO saveSocialMember(String email) {
		//기존에 동일한 이메일로 가입한 회원이 있는지 조회
		Optional<Member> result = repository.findById(email);

		//해당 이메일로 등록된 회원이 있으면 반환
		if(result.isPresent()){
			return entityToDto(result.get());
		}
	
		//해당 이메일로 등록된 회원이 없으면 회원가입 진행
		Member member = Member.builder()
				.id(email) // 아이디는 이메일로 처리
				.name(email) // 이름도 이메일로 처리
				.password(passwordEncoder.encode("1111")) // 임시 비밀번호
				.fromSocial(true)
				.role("ROLE_USER") // 임시 권한
				.build();

		repository.save(member);

		result = repository.findById(email);

		return entityToDto(result.get()); // 새로운 회원정보 또는 기존 회원정보 반환
	}
	@Override
	public void modify(MemberDTO dto) {

		// 기존 회원 정보 조회
		Optional<Member> result = repository.findById(dto.getId());

		// 회원 정보 교체
		if(result.isPresent()){
			Member entity = result.get();
			entity.setName(dto.getName());
			entity.setRole(dto.getRole());

			// 기존 패스워드와 달라졌다면
			boolean matchResult1 = entity.getPassword().equals(dto.getPassword());
			//boolean matchResult2 = passwordEncoder.matches(entity.getPassword(), dto.getPassword());
			if(!matchResult1){
				System.out.println("패스워드가 변경되었습니다");
				String hashpassword = passwordEncoder.encode(dto.getPassword());
				entity.setPassword(hashpassword);
			}

			repository.save(entity);
		}

	}

}
