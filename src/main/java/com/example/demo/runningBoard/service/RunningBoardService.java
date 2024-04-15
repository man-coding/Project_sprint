package com.example.demo.runningBoard.service;

import org.springframework.data.domain.Page;

import com.example.demo.member.entity.Member;
import com.example.demo.runningBoard.dto.RunningDTO;
import com.example.demo.runningBoard.entity.Running;

public interface RunningBoardService {

	int register(RunningDTO dto);

	Page<RunningDTO> getList(int pageNumber);

	RunningDTO read(int no);

	void modify(RunningDTO dto);

	int remove(int no);

	Page<RunningDTO> getSearchList(int pageNumber, String keyword);

	default Running dtoToEntity(RunningDTO dto) {

		// Member 조회 로직 추가 (id를 사용하여 Member 조회)
		// 예시로, memberService.findById(dto.getWriterId()) 메서드를 사용한다고 가정
		// 이 부분은 실제 Member를 조회하는 로직으로 대체해야 합니다.
		Member member = Member.builder().id(dto.getWriter()).build();

		Running entity = Running.builder().no(dto.getNo()).writer(member.getId()).title(dto.getTitle())
				.runningDate(dto.getRunningDate()).location(dto.getLocation()).content(dto.getContent())
				.latitude(dto.getLatitude()).longtitude(dto.getLongtitude()).countLike(dto.getCountLike()).build();

		return entity;

	}

	default RunningDTO entityToDto(Running entity) {
		// Member 조회 로직 추가 (entity의 writer id를 사용하여 Member 조회)
		// 예시로, memberService.findById(entity.getWriter()) 메서드를 사용한다고 가정
		// 이 부분은 실제 Member를 조회하는 로직으로 대체해야 합니다.

		Member member = Member.builder().id(entity.getWriter()).build();

		RunningDTO dto = RunningDTO.builder().no(entity.getNo()).writer(entity.getWriter()).title(entity.getTitle())
				.runningDate(entity.getRunningDate()).location(entity.getLocation()).content(entity.getContent())
				.latitude(entity.getLatitude()).longtitude(entity.getLongtitude()).countLike(entity.getCountLike())
				.regDate(entity.getRegDate()).modDate(entity.getModDate()).build();

		return dto;
	}

}