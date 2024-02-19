package com.example.demo.Board.controller;

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

import com.example.demo.Board.dto.MarathonDTO;
import com.example.demo.Board.service.MarathonBoardService;





@Controller
@RequestMapping("/MarathonBoard")
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
	public String registerPost(MarathonDTO dto, RedirectAttributes redirectAttributes, MultipartFile file) throws Exception {

		int no = service.register(dto, file);

		redirectAttributes.addFlashAttribute("msg", no);

		return "redirect:/MarathonBoard/list";
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
		return "redirect:/MarathonBoard/read";
	}

	@PostMapping("/remove")
	public String removePost(@RequestParam(name = "no") int no) {
		service.remove(no);
		return "redirect:/MarathonBoard/list";
	}
}
