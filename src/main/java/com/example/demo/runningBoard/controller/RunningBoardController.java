package com.example.demo.runningBoard.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.member.service.MemberService;
import com.example.demo.runningBoard.dto.RunningDTO;
import com.example.demo.runningBoard.service.RunningBoardService;
import com.example.demo.runningBoard.service.RunningBoardServiceImpl;
import com.example.demo.weatherApi.dto.WeatherDTO;
import com.example.demo.weatherApi.service.WeatherService;

@Controller
@RequestMapping("/runningBoard")
public class RunningBoardController {
	@Autowired
	RunningBoardServiceImpl service2;
	@Autowired
	RunningBoardService service;

	@Autowired
	MemberService memberService;

	@Autowired
	WeatherService weatherService;

	@GetMapping("/list")
	public void list(@RequestParam(defaultValue = "0", name = "page") int page, Model model) {
		Page<RunningDTO> list = service.getList(page);
		model.addAttribute("list", list);

	}

	@GetMapping("/search")
	public String search(@RequestParam(defaultValue = "0", name = "page") int page,
						 @RequestParam(name = "keyword") String keyword, Model model) {
		Page<RunningDTO> list = service.getSearchList(page, keyword);
		model.addAttribute("list", list);
		return "/runningBoard/list";
	}

	@GetMapping("/register")
	public void register() {

	}

	@PostMapping("/register")
	public String registerPost(RunningDTO dto, RedirectAttributes redirectAttributes, Principal principal) {

		String id = principal.getName();

		String name = memberService.findNameById(id);

		dto.setWriter(name);

		int no = service.register(dto);

		redirectAttributes.addFlashAttribute("msg", no);

		return "redirect:/runningBoard/read?no=" + no;
	}

	@GetMapping("/read")
	public void read(@RequestParam(name = "no") int no, @RequestParam(defaultValue = "0", name = "page") int page,
					 Model model) throws IOException {

		RunningDTO dto = service.read(no);
		model.addAttribute("dto", dto);
		model.addAttribute("page", page);

		List<WeatherDTO> weather = weatherService.entityToDto();

		model.addAttribute("weather", weather);
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
		return "redirect:/runningBoard/read";
	}

	@PostMapping("/remove")
	public String removePost(@RequestParam(name = "no") int no) {
		service.remove(no);
		return "redirect:/runningBoard/list";
	}

}