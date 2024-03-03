package com.example.demo.member.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.service.MemberService;


@Controller
//@RequestMapping("/member") //중간경로 제거
public class MemberController {

	@Autowired
	private MemberService service;

	//	@GetMapping("/list")
	@GetMapping("/member/list") //주소수정
	public void list(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Page<MemberDTO> list = service.getList(page);
		model.addAttribute("list", list);	
	}

	@GetMapping("/register")
	public String register() { // 리턴타입 수정
		return "member/register"; //html 경로 직접 작성
	}

	@PostMapping("/register")
	public String registerPost(MemberDTO dto, RedirectAttributes redirectAttributes) {

		boolean isSuccess = service.register(dto);

		if(isSuccess) {
			return "redirect:/"; // 주소 수정
		}else {
			redirectAttributes.addFlashAttribute("msg", "아이디가 중복되어 등록에 실패하였습니다");
			return "redirect:/register"; // 주소 수정
		}

	}

	//	@GetMapping("/read")
	@GetMapping("/member/read") // 주소수정
	public void read(@RequestParam(name = "id") String id, @RequestParam(name = "page", defaultValue = "0") int page, Model model) { //파라미터 추가
		MemberDTO dto = service.read(id);
		model.addAttribute("dto", dto);
		model.addAttribute("page", page);
	}

	// 회원 정보 수정 메소드 추가
	@GetMapping("/member/modify")
	public void modify(@RequestParam(name = "id") String id, Model model) {
		MemberDTO dto = service.read(id);
		model.addAttribute("dto", dto);
	}

	// 회원 정보 수정 메소드 추가
	@PostMapping("/member/modify")
	public String modifyPost(MemberDTO dto, RedirectAttributes redirectAttributes) {
		service.modify(dto);
		redirectAttributes.addAttribute("id", dto.getId());
		return "redirect:/member/read";
	}
}
