package com.example.demo.runningBoard.service;

import org.springframework.data.domain.Page;

import com.example.demo.runningBoard.dto.RunningDTO;
import com.example.demo.runningBoard.entity.Running;

public interface RunningBoardService {

	int register(RunningDTO dto);

	Page<RunningDTO> getList(int pageNumber);

	RunningDTO read(int no);

	void modify(RunningDTO dto);

	int remove(int no);

	default Running dtoToEntity(RunningDTO dto) {

		Running entity = Running.builder().no(dto.getNo()).title(dto.getTitle()).runningDate(dto.getRunningDate())
				.location(dto.getLocation()).content(dto.getContent()).writer(dto.getWriter()).build();

		return entity;

	}

	default RunningDTO entityToDto(Running entity) {

		RunningDTO dto = RunningDTO.builder().no(entity.getNo()).title(entity.getTitle())
				.runningDate(entity.getRunningDate()).location(entity.getLocation()).content(entity.getContent())
				.writer(entity.getWriter()).regDate(entity.getRegDate()).modDate(entity.getModDate()).build();

		return dto;
	}

}
