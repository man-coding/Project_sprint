package com.example.demo.runningBoard.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.runningBoard.dto.RunningDTO;
import com.example.demo.runningBoard.service.RunningBoardService;

@Controller
@RequestMapping("/runningBoard")
public class RunningBoardController {

	@Autowired
	RunningBoardService service;

	@GetMapping("/list")
	public void list(@RequestParam(defaultValue = "0", name = "page") int page, Model model) {
		Page<RunningDTO> list = service.getList(page); 
		model.addAttribute("list", list);	
		
	}
	
	@GetMapping("/register")
	public void register() {

	}

	@PostMapping("/register")
	public String registerPost(RunningDTO dto, RedirectAttributes redirectAttributes, Principal principal) {
		
		String id = principal.getName();
		dto.setWriter(id);
		
		int no = service.register(dto);

		redirectAttributes.addFlashAttribute("msg", no);

		return "redirect:/runningBoard/list";
	}

	@GetMapping("/read")
	public void read(@RequestParam(name = "no") int no, @RequestParam(defaultValue = "0", name = "page") int page,
			Model model) {

		RunningDTO dto = service.read(no);
		model.addAttribute("dto", dto);
		model.addAttribute("page", page);
	}

	@GetMapping("/modify")
	public void modify(@RequestParam(name = "no") int no, Model model) {
		RunningDTO dto = service.read(no);
		model.addAttribute("dto", dto);
	}

	@PostMapping("/modify")
	public String modifyPost(RunningDTO dto, RedirectAttributes redirectAttributes) {
		service.modify(dto);
		redirectAttributes.addAttribute("no", dto.getNo());
		return "redirect:/Board/read";
	}

	@PostMapping("/remove")
	public String removePost(@RequestParam(name = "no") int no) {
		service.remove(no);
		return "redirect:/Board/list";
	}

}
