package com.example.demo.home;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.member.service.MemberService;


@Controller
@RequestMapping
public class HomeController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/")
	public String home() {
		return "/home/main";
	}
	// 커스텀 로그인 페이지 반환하는 메소드	
	@GetMapping("/customlogin")
	public String customLogin() {
		return "home/login";
	}

	
	
}
