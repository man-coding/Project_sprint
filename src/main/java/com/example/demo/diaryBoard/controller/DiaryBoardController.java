package com.example.demo.diaryBoard.controller;

import java.security.Principal;

import com.example.demo.diaryBoard.entity.Diary;
import com.example.demo.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.diaryBoard.dto.DiaryDTO;
import com.example.demo.diaryBoard.service.DiaryBoardService;

@Controller
@RequestMapping("/diaryBoard")
public class DiaryBoardController {

	@Autowired
	DiaryBoardService service;
	@Autowired
	MemberService memberService;

	@GetMapping("/list")
	public void list(@RequestParam(defaultValue = "0", name = "page") int page, Model model) {

		Page<DiaryDTO> list = service.getList(page);
		model.addAttribute("list", list);
	}

	@GetMapping("/register")
	public void register() {

	}

	@PostMapping("/register")
	public String registerPost(DiaryDTO dto, RedirectAttributes redirectAttributes, Principal principal)
			throws Exception {

		String id = principal.getName();
		String name = memberService.findNameById(id);
		dto.setWriter(name);

		int no = service.register(dto);

		redirectAttributes.addFlashAttribute("msg", no);

		return "redirect:/diaryBoard/read?no=" + no;
	}

	@GetMapping("/read")
	public void read(@RequestParam(name = "no") int no, @RequestParam(defaultValue = "0", name = "page") int page,
					 Model model) {

		DiaryDTO dto = service.read(no);
		model.addAttribute("dto", dto);
		model.addAttribute("page", page);
	}

	@GetMapping("/modify")
	public void modify(@RequestParam(name = "no") int no, Model model) {
		DiaryDTO dto = service.read(no);
		model.addAttribute("dto", dto);
	}

	@PostMapping("/modify")
	public String modifyPost(DiaryDTO dto, RedirectAttributes redirectAttributes) {
		service.modify(dto);
		redirectAttributes.addAttribute("no", dto.getNo());
		return "redirect:DiaryBoard/read";
	}

	@PostMapping("/remove")
	public String removePost(@RequestParam(name = "no") int no) {
		service.remove(no);
		return "redirect:/DiaryBoard/list";
	}

	@PostMapping("/like")
	public ResponseEntity<DiaryDTO> likeDiary(@RequestParam int no) {
		DiaryDTO diary = service.read(no);
		diary.setCountLike(service.likeDiary(no).getCountLike());
		return ResponseEntity.ok(diary);
	}

	@PostMapping("/unlike")
	public ResponseEntity<DiaryDTO> unlikeDiary(@RequestParam int no) {
		DiaryDTO diary = service.read(no);
		diary.setCountLike(service.unlikeDiary(no).getCountLike());
		return ResponseEntity.ok(diary);
	}

}