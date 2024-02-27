package com.example.demo.joinMember.service;

import java.util.List;

import com.example.demo.joinMember.dto.JoinMemberDTO;
import com.example.demo.joinMember.entity.JoinMember;

public interface JoinMemberService {

	// 참석자 명단 조회
	List<JoinMemberDTO> getList();

	default JoinMember dtoToEntity(JoinMemberDTO dto) {

		JoinMember entity = JoinMember.builder().runningNo(dto.getRunningNo()).joinNo(dto.getJoinNo()).build();

		return entity;
	}
}
