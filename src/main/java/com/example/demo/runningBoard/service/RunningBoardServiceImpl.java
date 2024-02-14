package com.example.demo.runningBoard.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.runningBoard.dto.RunningBoardDTO;
import com.example.demo.runningBoard.entity.RunningBoard;
import com.example.demo.runningBoard.repository.RunningBoardRepository;

@Service
public class RunningBoardServiceImpl implements RunningBoardService {

	@Autowired
	RunningBoardRepository repository;

	@Override
	public int register(RunningBoardDTO dto) {

		RunningBoard entity = dtoToEntity(dto);
		repository.save(entity);
		int newNo = entity.getNo();
		return newNo;
	}

	@Override
	public Page<RunningBoardDTO> getList(int pageNumber) {

		int pageNum = (pageNumber == 0) ? 0 : pageNumber - 1;

		Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by("no").descending());

		Page<RunningBoard> entityPage = repository.findAll(pageable);

		Page<RunningBoardDTO> dtoPage = entityPage.map(entity -> entityToDto(entity));

		return dtoPage;
	}

	@Override
	public RunningBoardDTO read(int no) {
		Optional<RunningBoard> result = repository.findById(no);

		if (result.isPresent()) {

			RunningBoard running = result.get();

			RunningBoardDTO dto = entityToDto(running);

			return dto;

		}
		return null;
	}

	@Override
	public void modify(RunningBoardDTO dto) {

		Optional<RunningBoard> result = repository.findById(dto.getNo());

		if (result.isPresent()) {

			RunningBoard entity = result.get();

			entity.setTitle(dto.getTitle());
			entity.setRunningDate(dto.getRunningDate());
			entity.setLocation(dto.getLocation());
			entity.setContent(dto.getContent());

			repository.save(entity);
		}

	}

	@Override
	public int remove(int no) {

		Optional<RunningBoard> result = repository.findById(no);
		if (result.isPresent()) {
			repository.deleteById(no);

			return 1; // 성공
		} else {
			return 0; // 실패
		}

	}

}
