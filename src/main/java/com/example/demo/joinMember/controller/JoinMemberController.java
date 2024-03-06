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
	public ResponseEntity<String> joinRunning(@RequestParam(name = "runningNo") int runningNo, Principal principal) {

		String runnerId = principal.getName();

		// 이미 참가한 상태인지 확인
		if (joinMemberService.isAlreadyJoined(runningNo, runnerId)) {

			joinMemberService.cancelJoin(runningNo, runnerId);
			return ResponseEntity.ok("cancelled");
		} else {
			// 그렇지 않으면 참가 등록

			JoinMemberDTO joinMemberDTO = joinMemberService.joinRunning(runningNo, runnerId);
			return ResponseEntity.ok("joined");
		}
	}

	@GetMapping("/joinList")
	public ResponseEntity<List<JoinMemberDTO>> getList(@RequestParam(name = "runningNo") int runningNo) {
		List<JoinMemberDTO> list = joinMemberService.getList(runningNo);
		return ResponseEntity.ok(list);
	}

	@DeleteMapping("/cancelJoin")
	public ResponseEntity<Integer> cancelJoin(@RequestParam(name = "runningNo") int runningNo,
			@RequestParam(name = "joinNo") int joinNo, String runnerId) {
		int result = joinMemberService.cancelJoin(runningNo, runnerId);
		return ResponseEntity.ok(result);
	}
}