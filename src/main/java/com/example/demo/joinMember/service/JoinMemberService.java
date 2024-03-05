package com.example.demo.joinMember.service;

import java.util.List;

import com.example.demo.joinMember.dto.JoinMemberDTO;
import com.example.demo.joinMember.entity.JoinMember;
import com.example.demo.member.entity.Member;
import com.example.demo.runningBoard.entity.Running;

public interface JoinMemberService {

	// 러닝 참석
	JoinMemberDTO joinRunning(int runningNo, String runnerId);

	// 참석자 명단 조회
	List<JoinMemberDTO> getList(int runningNo);

	// 참석 취소
	int cancelJoin(int runningNo, int joinNo, String runnerId);
	
	default JoinMember dtoToEntity(JoinMemberDTO dto) {

		Running running = Running.builder().no(dto.getRunningNo()).build();

		Member member = Member.builder().id(dto.getRunnerId()).build();

		JoinMember entity = JoinMember.builder().runningNo(running).joinNo(dto.getJoinNo()).runnerId(member).build();

		return entity;
	}

	default JoinMemberDTO entityToDto(JoinMember entity) {

		JoinMemberDTO dto = JoinMemberDTO.builder().runningNo(entity.getRunningNo().getNo()).joinNo(entity.getJoinNo())
				.runnerId(entity.getRunnerId().getId()).build();

		return dto;
	}

}
