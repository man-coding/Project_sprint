package com.example.demo.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "/home/main";
	}
	// 커스텀 로그인 페이지 반환하는 메소드	
	@GetMapping("/customlogin")
	public String customLogin() {
		return "home/login";
	}
	
	@GetMapping("/kakaologin")
	public String kakaoLogin() {
		return "login_social/kakao_login";
	}
}
