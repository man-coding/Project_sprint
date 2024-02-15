package com.example.demo.equipmentBoard.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.equipmentBoard.dto.EquipmentDTO;
import com.example.demo.equipmentBoard.entity.Equipment;

public interface EquipmentBoardService {

	int register(EquipmentDTO dto, MultipartFile file) throws Exception;

	Page<EquipmentDTO> getList(int pageNumber);

	EquipmentDTO read(int no);

	void modify(EquipmentDTO dto, MultipartFile file);

	int remove(int no);

	default Equipment dtoToEntity(EquipmentDTO dto) {

		Equipment entity = Equipment.builder().no(dto.getNo()).content(dto.getContent()).writer(dto.getWriter())
				.build();

		return entity;

	}

	default EquipmentDTO entityToDto(Equipment entity) {

		EquipmentDTO dto = EquipmentDTO.builder().no(entity.getNo()).content(entity.getContent())
				.writer(entity.getWriter()).regDate(entity.getRegDate()).modDate(entity.getModDate()).build();

		return dto;
	}
}
