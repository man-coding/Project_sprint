package com.example.demo.runningBoard.service;

import org.springframework.data.domain.Page;

import com.example.demo.runningBoard.dto.RunningBoardDTO;
import com.example.demo.runningBoard.entity.RunningBoard;

public interface RunningBoardService {

	int register(RunningBoardDTO dto);

	Page<RunningBoardDTO> getList(int pageNumber);

	RunningBoardDTO read(int no);

	void modify(RunningBoardDTO dto);

	int remove(int no);

	default RunningBoard dtoToEntity(RunningBoardDTO dto) {

		RunningBoard entity = RunningBoard.builder().no(dto.getNo()).title(dto.getTitle()).runningDate(dto.getRunningDate())
				.location(dto.getLocation()).content(dto.getContent()).writer(dto.getWriter()).build();

		return entity;

	}

	default RunningBoardDTO entityToDto(RunningBoard entity) {

		RunningBoardDTO dto = RunningBoardDTO.builder().no(entity.getNo()).title(entity.getTitle())
				.runningDate(entity.getRunningDate()).location(entity.getLocation()).content(entity.getContent())
				.writer(entity.getWriter()).regDate(entity.getRegDate()).modDate(entity.getModDate()).build();

		return dto;
	}

}
