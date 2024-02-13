package com.example.demo.trainingBoard.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.trainingBoard.dto.TrainingDTO;
import com.example.demo.trainingBoard.entity.Training;
import com.example.demo.trainingBoard.repository.TrainingRepository;

@Service
public class TraningBoardServiceImpl implements TrainingBoardService {

	@Autowired
	TrainingRepository repository;

	@Override
	public int register(TrainingDTO dto) {

		Training entity = dtoToEntity(dto);
		repository.save(entity);
		int newNo = entity.getNo();
		return newNo;
	}

	@Override
	public Page<TrainingDTO> getList(int pageNumber) {

		int pageNum = (pageNumber == 0) ? 0 : pageNumber - 1;

		Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by("no").descending());

		Page<Training> entityPage = repository.findAll(pageable);

		Page<TrainingDTO> dtoPage = entityPage.map(entity -> entityToDto(entity));

		return dtoPage;
	}

	@Override
	public TrainingDTO read(int no) {
		Optional<Training> result = repository.findById(no);

		if (result.isPresent()) {

			Training Training = result.get();

			TrainingDTO dto = entityToDto(Training);

			return dto;

		}
		return null;
	}

	@Override
	public void modify(TrainingDTO dto) {

		Optional<Training> result = repository.findById(dto.getNo());

		if (result.isPresent()) {

			Training entity = result.get();

			entity.setTitle(dto.getTitle());
			entity.setTrainingDate(dto.getTrainingDate());
			entity.setPlace(dto.getPlace());
			entity.setContent(dto.getContent());

			repository.save(entity);
		}

	}

	@Override
	public int remove(int no) {

		Optional<Training> result = repository.findById(no);
		if (result.isPresent()) {
			repository.deleteById(no);

			return 1; // 성공
		} else {
			return 0; // 실패
		}

	}
}
