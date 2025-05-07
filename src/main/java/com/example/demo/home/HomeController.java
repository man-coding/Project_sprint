package com.example.demo.home;

import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class HomeController {

	@Autowired
	MemberService memberService;

	@GetMapping("/")
	public String home(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated()
				&& !"anonymousUser".equals(authentication.getPrincipal())) {

			String loggedInUserId = authentication.getName();
			MemberDTO loggedInUser = memberService.findMemberById(loggedInUserId);
			model.addAttribute("loggedInUser", loggedInUser);
		}

		return "/home/main";
	}

	/* 로그인 페이지와 에러 메시지 처리 */
	@GetMapping("/login")
	public String customLogin(@RequestParam(value = "error", required = false) String error,
							  @RequestParam(value = "exception", required = false) String exception,
							  Model model) {
		// 에러와 예외가 있을 경우 모델에 추가합니다.
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		return "home/login";
	}
}
