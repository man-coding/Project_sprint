package com.example.demo.marathonBoard.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.marathonBoard.dto.MarathonDTO;
import com.example.demo.marathonBoard.entity.Marathon;







public interface MarathonBoardService {

	int register(MarathonDTO dto, MultipartFile file) throws Exception;

	Page<MarathonDTO> getList(int pageNumber);

	MarathonDTO read(int no);

	void modify(MarathonDTO dto,MultipartFile file);

	int remove(int no);

	default Marathon dtoToEntity(MarathonDTO dto) {

		Marathon entity = Marathon.builder().no(dto.getNo()).title(dto.getTitle()).marathonDate(dto.getMarathonDate())
				.location(dto.getLocation()).content(dto.getContent()).writer(dto.getWriter()).build();

		return entity;

	}

	default MarathonDTO entityToDto(Marathon entity) {

		MarathonDTO dto = MarathonDTO.builder().no(entity.getNo()).title(entity.getTitle())
				.MarathonDate(entity.getMarathonDate()).location(entity.getLocation()).content(entity.getContent())
				.writer(entity.getWriter()).regDate(entity.getRegDate()).modDate(entity.getModDate()).build();

		return dto;
	}
}
