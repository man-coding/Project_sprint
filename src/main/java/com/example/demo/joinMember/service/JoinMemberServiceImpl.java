package com.example.demo.joinMember.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.joinMember.dto.JoinMemberDTO;
import com.example.demo.joinMember.repository.JoinMemberRepository;

@Service
public class JoinMemberServiceImpl implements JoinMemberService {

	@Autowired
	JoinMemberRepository repository;

	@Override
	public List<JoinMemberDTO> getList() {
		
	
		return null;
	}
	
	
	
}
