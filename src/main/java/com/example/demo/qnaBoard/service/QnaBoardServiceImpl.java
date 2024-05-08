package com.example.demo.qnaBoard.service;

import com.example.demo.qnaBoard.dto.QnaDTO;
import com.example.demo.qnaBoard.entity.Qna;
import com.example.demo.qnaBoard.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QnaBoardServiceImpl implements QnaBoardService{

    @Autowired
    QnaRepository repository;

    @Override
    public int register(QnaDTO dto) {
        Qna entity = dtoToEntity(dto);
        repository.save(entity);
        int newNo = entity.getNo();
        return newNo;
    }

    @Override
    public Page<QnaDTO> getList(int pageNumber) {
        int pageNum = (pageNumber == 0) ? 0 : pageNumber - 1;

        Pageable pageable = PageRequest.of(pageNum, 5, Sort.by("no").descending());

        Page<Qna> entityPage = repository.findAll(pageable);

        Page<QnaDTO> dtoPage = entityPage.map(entity -> entityToDto(entity));

        return dtoPage;
    }

    @Override
    public QnaDTO read(int no) {
        Optional<Qna> result = repository.findById(no);

        if (result.isPresent()) {
            Qna qna = result.get();
            QnaDTO dto = entityToDto(qna);

            return dto;
        }
        return null;
    }

    @Override
    public void modify(QnaDTO dto) {
        Optional<Qna> result = repository.findById(dto.getNo());

        if(result.isPresent()){
            Qna entity = result.get();
            entity.setTitle(dto.getTitle());
            entity.setContent(dto.getContent());
            repository.save(entity);
        }
    }

    @Override
    public int remove(int no) {
        Optional<Qna> result = repository.findById(no);
        if (result.isPresent()) {
            repository.delete(result.get());
            return 1;
        }else {
            return 0;
        }
        }
}
