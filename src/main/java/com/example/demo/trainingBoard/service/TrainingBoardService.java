package com.example.demo.trainingBoard.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.trainingBoard.dto.TrainingDTO;
import com.example.demo.trainingBoard.entity.Training;

public interface TrainingBoardService {

	int register(TrainingDTO dto, MultipartFile file) throws Exception;

	Page<TrainingDTO> getList(int pageNumber);

	TrainingDTO read(int no);

	void modify(TrainingDTO dto,MultipartFile file);

	int remove(int no);

	default Training dtoToEntity(TrainingDTO dto) {

		Training entity = Training.builder().no(dto.getNo()).title(dto.getTitle()).trainingDate(dto.getTrainingDate())
				.location(dto.getLocation()).content(dto.getContent()).writer(dto.getWriter()).build();

		return entity;

	}

	default TrainingDTO entityToDto(Training entity) {

		TrainingDTO dto = TrainingDTO.builder().no(entity.getNo()).title(entity.getTitle())
				.trainingDate(entity.getTrainingDate()).location(entity.getLocation()).content(entity.getContent())
				.writer(entity.getWriter()).regDate(entity.getRegDate()).modDate(entity.getModDate()).build();

		return dto;
	}
}
