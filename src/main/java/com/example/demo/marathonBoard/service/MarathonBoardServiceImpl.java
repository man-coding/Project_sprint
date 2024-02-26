package com.example.demo.marathonBoard.service;

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

import com.example.demo.marathonBoard.dto.MarathonDTO;
import com.example.demo.marathonBoard.entity.Marathon;
import com.example.demo.marathonBoard.repository.MarathonRepository;

@Service
public class MarathonBoardServiceImpl implements MarathonBoardService {

	@Autowired
	MarathonRepository repository;

	@Override
	public int register(MarathonDTO dto, MultipartFile file) throws Exception {

		String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
		UUID uuid = UUID.randomUUID();
		String fileName = uuid + "_" + file.getOriginalFilename();

		File saveFile = new File(projectPath, fileName);
		file.transferTo(saveFile);

		Marathon entity = dtoToEntity(dto);

		entity.setFileName(fileName); // 파일명 변경
		entity.setFilePath("/files/" + fileName);

		repository.save(entity);
		int newNo = entity.getNo();
		return newNo;
	}

	@Override
	public Page<MarathonDTO> getList(int pageNumber) {

		int pageNum = (pageNumber == 0) ? 0 : pageNumber - 1;

		Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by("no").descending());

		Page<Marathon> entityPage = repository.findAll(pageable);

		Page<MarathonDTO> dtoPage = entityPage.map(entity -> entityToDto(entity));

		return dtoPage;
	}

	@Override
	public MarathonDTO read(int no) {
		Optional<Marathon> result = repository.findById(no);

		if (result.isPresent()) {

			Marathon Marathon = result.get();

			MarathonDTO dto = entityToDto(Marathon);

			return dto;

		}
		return null;
	}

	@Override
	public void modify(MarathonDTO dto, MultipartFile file) {

		Optional<Marathon> result = repository.findById(dto.getNo());

		if (result.isPresent()) {

			Marathon entity = result.get();

			entity.setTitle(dto.getTitle());
			entity.setMarathonDate(dto.getMarathonDate());
			entity.setLocation(dto.getLocation());
			entity.setContent(dto.getContent());
			entity.setFileName(dto.getFileName());
			entity.setFilePath(dto.getFilePath());

			repository.save(entity);
		}

	}

	@Override
	public int remove(int no) {

		Optional<Marathon> result = repository.findById(no);
		if (result.isPresent()) {
			repository.deleteById(no);

			return 1; // 성공
		} else {
			return 0; // 실패
		}

	}
}
