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
import com.example.demo.member.entity.Member;
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
	public JoinMemberDTO joinRunning(int runningNo, String runnerId) {

	    Optional<Running> result1 = runningRepository.findById(runningNo);
	    Running running = result1.get();

	    // runnerId에 해당하는 Member를 찾습니다.
	    Optional<Member> result2 = memberRepository.findById(runnerId);
	    Member member = result2.get();

	    JoinMember joinMember = JoinMember.builder().runningNo(running).runnerId(member).build();

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
	public int cancelJoin(int runningNo, String runnerId) {
	    // 주어진 참가 번호와 회원 ID에 해당하는 참가 정보를 찾습니다.
	    Optional<JoinMember> result = joinMemberRepository.findByRunningNo_NoAndRunnerId_Id(runningNo, runnerId);

	    if (result.isPresent()) {
	        // 참가 정보가 존재하면 해당 참가를 취소합니다.
	        joinMemberRepository.delete(result.get());
	        return 1; // 성공
	    } else {
	        return 0; // 실패
	    }
	}
	@Override
    public boolean isAlreadyJoined(int runningNo, String runnerId) {
        // 주어진 참가 번호와 회원 ID에 해당하는 참가 정보를 찾습니다.
        Optional<JoinMember> result = joinMemberRepository.findByRunningNo_NoAndRunnerId_Id(runningNo, runnerId);

        // 참가 정보가 존재하면 true를, 그렇지 않으면 false를 반환합니다.
        return result.isPresent();
    }
}
