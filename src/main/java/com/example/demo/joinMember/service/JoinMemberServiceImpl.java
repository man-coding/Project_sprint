package com.example.demo.joinMember.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.joinMember.dto.JoinMemberDTO;
import com.example.demo.joinMember.entity.JoinMember;
import com.example.demo.joinMember.repository.JoinMemberRepository;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.runningBoard.entity.Running;
import com.example.demo.runningBoard.repository.RunningRepository;

@Service
public class JoinMemberServiceImpl implements JoinMemberService {

	@Autowired
	JoinMemberRepository joinMemberRepository;
	@Autowired
	RunningRepository runningRepository;
	@Autowired
	MemberRepository memberRepository;

	@Transactional
	@Override
	public JoinMemberDTO joinRunning(int runningNo) {

		Optional<Running> result1 = runningRepository.findById(runningNo);
		Running running = result1.get();
		
		JoinMember joinMember = JoinMember.builder().runningNo(running).build();
		joinMemberRepository.save(joinMember);

		return entityToDto(joinMember);
	}

	@Transactional
	@Override
	public List<JoinMemberDTO> getList(int runningNo) {
		List<JoinMember> joinList = joinMemberRepository.findByRunningNo_No(runningNo);

		return joinList.stream().map(this::entityToDto).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public int cancelJoin(int runningNo, int joinNo) {
		Optional<JoinMember> result = joinMemberRepository.findByRunningNo_noAndJoinNo(runningNo, joinNo);

		if (result.isPresent()) {
			joinMemberRepository.deleteById(joinNo);
			return 1; // 성공
		} else {
			return 0; // 실패
		}
	}
}
