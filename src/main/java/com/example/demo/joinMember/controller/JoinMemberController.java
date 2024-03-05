package com.example.demo.joinMember.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.joinMember.dto.JoinMemberDTO;
import com.example.demo.joinMember.service.JoinMemberService;

@RestController
@RequestMapping("/joinMember")
public class JoinMemberController {

	@Autowired
	JoinMemberService joinMemberService;

	@PostMapping("/joinRunning")
	public ResponseEntity<JoinMemberDTO> joinRunning(@RequestParam(name = "runningNo") int runningNo,
			Principal principal) {
		String runnerId = principal.getName();
		JoinMemberDTO joinMemberDTO = joinMemberService.joinRunning(runningNo, runnerId);
		return ResponseEntity.ok(joinMemberDTO);
	}

	@GetMapping("/joinList")
	public ResponseEntity<List<JoinMemberDTO>> getList(@RequestParam(name = "runningNo") int runningNo) {
		List<JoinMemberDTO> list = joinMemberService.getList(runningNo);
		return ResponseEntity.ok(list);
	}

	@DeleteMapping("/cancelJoin")
	public ResponseEntity<Integer> cancelJoin(@RequestParam(name = "runningNo") int runningNo,
			@RequestParam(name = "joinNo") int joinNo, Principal principal) {
		String currentUserId = principal.getName(); // 현재 로그인한 사용자의 이름을 얻습니다.
		int result = joinMemberService.cancelJoin(runningNo, joinNo, currentUserId); // 현재 로그인한 사용자의 이름을 전달합니다.
		return ResponseEntity.ok(result);
	}
}
