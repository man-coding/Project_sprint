package com.example.demo.member.service;

import org.springframework.data.domain.Page;

import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.entity.Member;


public interface MemberService {
	
	Page<MemberDTO> getList(int pageNumber); //회원 목록조회
	
	boolean register(MemberDTO dto); //회원 등록

	MemberDTO read(String id); //회원 단건 조회
	
	// 회원 정보 수정 메소드 추가
	void modify(MemberDTO dto);

	// 소셜 로그인한 이메일로 회원가입하는 메소드 추가
	MemberDTO saveSocialMember(String email);
	
	//엔티티를 DTO로 변환하는 메소드
	default MemberDTO entityToDto(Member entity) {
		MemberDTO dto = MemberDTO.builder()
				.id(entity.getId())
				.password(entity.getPassword())
				.name(entity.getName())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.role(entity.getRole()) //등급 추가
				.fromSocial(entity.isFromSocial())
				.build();

		return dto;
	}

	//DTO를 엔티티로 변환하는 메소드
	default Member dtoToEntity(MemberDTO dto) {
		Member entity = Member.builder()
				.id(dto.getId())
				.password(dto.getPassword())
				.name(dto.getName())
				.role(dto.getRole()) //등급 추가
				.fromSocial(dto.isFromSocial())
				.build();
		return entity;
	}



}
