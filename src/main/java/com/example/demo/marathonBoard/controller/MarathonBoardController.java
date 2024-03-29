package com.example.demo.marathonBoard.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.marathonBoard.dto.MarathonDTO;
import com.example.demo.marathonBoard.service.MarathonBoardService;



@Controller
@RequestMapping("/marathonBoard")
public class MarathonBoardController {

	@Autowired
	MarathonBoardService service;

	@GetMapping("/list")
	public void list(@RequestParam(defaultValue = "0", name = "page") int page, Model model) {

		Page<MarathonDTO> list = service.getList(page);
		model.addAttribute("list", list);
	}

	@GetMapping("/register")
	public void register() {

	}

	@PostMapping("/register")
	public String registerPost(MarathonDTO dto, RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file, Principal principal) throws Exception {
		
		String id = principal.getName();
		dto.setWriter(id);
		
		int no = service.register(dto, file);

		redirectAttributes.addFlashAttribute("msg", no);

		return "redirect:/marathonBoard/list";
	}

	@GetMapping("/read")
	public void read(@RequestParam(name = "no") int no, @RequestParam(defaultValue = "0", name = "page") int page,
			Model model) {

		MarathonDTO dto = service.read(no);
		model.addAttribute("dto", dto);
		model.addAttribute("page", page);
	}

	@GetMapping("/modify")
	public void modify(@RequestParam(name = "no") int no, Model model) {
		MarathonDTO dto = service.read(no);
		model.addAttribute("dto", dto);
	}

	@PostMapping("/modify")
	public String modifyPost(MarathonDTO dto, MultipartFile file, RedirectAttributes redirectAttributes) {
		service.modify(dto, file);
		redirectAttributes.addAttribute("no", dto.getNo());
		return "redirect:/marathonBoard/read";
	}

	@PostMapping("/remove")
	public String removePost(@RequestParam(name = "no") int no) {
		service.remove(no);
		return "redirect:/marathonBoard/list";
	}
}
