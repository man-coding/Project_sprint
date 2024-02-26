package com.example.demo.marathonBoard.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.marathonBoard.dto.MarathonDTO;
import com.example.demo.marathonBoard.entity.Marathon;
import com.example.demo.member.entity.Member;

public interface MarathonBoardService {

	int register(MarathonDTO dto, MultipartFile file) throws Exception;

	Page<MarathonDTO> getList(int pageNumber);

	MarathonDTO read(int no);

	void modify(MarathonDTO dto, MultipartFile file);

	int remove(int no);

	default Marathon dtoToEntity(MarathonDTO dto) {
		Member member = Member.builder().id(dto.getWriter()).build();
		Marathon entity = Marathon.builder().no(dto.getNo()).writer(member.getId()).title(dto.getTitle())
				.marathonDate(dto.getMarathonDate()).location(dto.getLocation()).content(dto.getContent())
				.countLike(dto.getCountLike()).fileName(dto.getFileName()).filePath(dto.getFilePath()).build();

		return entity;

	}

	default MarathonDTO entityToDto(Marathon entity) {

		MarathonDTO dto = MarathonDTO.builder().no(entity.getNo()).writer(entity.getWriter()).title(entity.getTitle())
				.MarathonDate(entity.getMarathonDate()).location(entity.getLocation()).content(entity.getContent())
				.countLike(entity.getCountLike()).fileName(entity.getFileName()).filePath(entity.getFilePath())
				.regDate(entity.getRegDate()).modDate(entity.getModDate()).build();

		return dto;
	}
}
