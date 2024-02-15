package com.example.demo.equipmentBoard.service;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.equipmentBoard.dto.EquipmentDTO;
import com.example.demo.equipmentBoard.entity.Equipment;
import com.example.demo.equipmentBoard.repository.EquipmentRepository;

@Service
public class EquipmentBoardServiceImpl implements EquipmentBoardService {

	@Autowired
	EquipmentRepository repository;

	@Override
	public int register(EquipmentDTO dto, MultipartFile file) throws Exception {

		String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
		UUID uuid = UUID.randomUUID();
		String fileName = uuid + "_" + file.getOriginalFilename();

		File saveFile = new File(projectPath, fileName);
		file.transferTo(saveFile);

		Equipment entity = dtoToEntity(dto);

		entity.setFileName(fileName); // 파일명 변경
		entity.setFilePath("/files/" + fileName);

		repository.save(entity);
		int newNo = entity.getNo();
		return newNo;
	}

	@Override
	public Page<EquipmentDTO> getList(int pageNumber) {

		int pageNum = (pageNumber == 0) ? 0 : pageNumber - 1;

		Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by("no").descending());

		Page<Equipment> entityPage = repository.findAll(pageable);

		Page<EquipmentDTO> dtoPage = entityPage.map(entity -> entityToDto(entity));

		return dtoPage;
	}

	@Override
	public EquipmentDTO read(int no) {
		Optional<Equipment> result = repository.findById(no);

		if (result.isPresent()) {

			Equipment Equipment = result.get();

			EquipmentDTO dto = entityToDto(Equipment);

			return dto;

		}
		return null;
	}

	@Override
	public void modify(EquipmentDTO dto, MultipartFile file) {

		Optional<Equipment> result = repository.findById(dto.getNo());

		if (result.isPresent()) {

			Equipment entity = result.get();

			entity.setContent(dto.getContent());

			repository.save(entity);
		}

	}

	@Override
	public int remove(int no) {

		Optional<Equipment> result = repository.findById(no);
		if (result.isPresent()) {
			repository.deleteById(no);

			return 1; // 성공
		} else {
			return 0; // 실패
		}

	}
}
