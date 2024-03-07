package com.example.demo.runningBoard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.runningBoard.dto.RunningDTO;
import com.example.demo.runningBoard.entity.Running;
import com.example.demo.runningBoard.repository.RunningRepository;

import jakarta.transaction.Transactional;

@Service
public class RunningBoardServiceImpl implements RunningBoardService {

	@Autowired
	RunningRepository repository;

	@Override
	public int register(RunningDTO dto) {

		Running entity = dtoToEntity(dto);
		repository.save(entity);
		int newNo = entity.getNo();
		return newNo;
	}

	@Override
	public Page<RunningDTO> getList(int pageNumber) {

		int pageNum = (pageNumber == 0) ? 0 : pageNumber - 1;

		Pageable pageable = PageRequest.of(pageNum, 5, Sort.by("no").descending());

		Page<Running> entityPage = repository.findAll(pageable);

		Page<RunningDTO> dtoPage = entityPage.map(entity -> entityToDto(entity));

		return dtoPage;
	}

	@Override
	public RunningDTO read(int no) {
		Optional<Running> result = repository.findById(no);

		if (result.isPresent()) {

			Running running = result.get();

			RunningDTO dto = entityToDto(running);

			return dto;

		}
		return null;
	}

	@Override
	public void modify(RunningDTO dto) {

		Optional<Running> result = repository.findById(dto.getNo());

		if (result.isPresent()) {

			Running entity = result.get();

			entity.setTitle(dto.getTitle());
			entity.setRunningDate(dto.getRunningDate());
			entity.setLocation(dto.getLocation());
			entity.setContent(dto.getContent());

			repository.save(entity);
		}

	}

	@Override
	public int remove(int no) {

		Optional<Running> result = repository.findById(no);
		if (result.isPresent()) {
			repository.deleteById(no);

			return 1; // 성공
		} else {
			return 0; // 실패
		}

	}
	@Transactional
	public List<RunningDTO> search(String keyword) {
		
		List<Running> board = repository.findByTitleContaining(keyword);
		List<RunningDTO> boarDtoList = new ArrayList<>();
		
		if(board.isEmpty()) {
			return boarDtoList;
		}
		
		for(Running running : board) {
			boarDtoList.add(this.converEntityToDto(running));
		}
		
		return boarDtoList;
		
	}

	private RunningDTO converEntityToDto(Running board) {
		return RunningDTO.builder()
				.writer(board.getWriter())
				.title(board.getTitle())
				.runningDate(board.getRunningDate())
				.location(board.getLocation())
				.content(board.getContent())
				.latitude(board.getLatitude())
				.longtitude(board.getLongtitude())
				.build();
	}


}
