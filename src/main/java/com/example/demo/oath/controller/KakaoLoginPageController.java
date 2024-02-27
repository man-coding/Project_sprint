package com.example.demo.oath.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class KakaoLoginPageController {

    
	private String client_id = "32ebae90c437ccbb483cd0fb790fed86";

    
    private String redirect_uri = "http://localhost:8080/costomlogin/kakao";

    @GetMapping("/page")
    public String loginPage(Model model) {
        String location = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+client_id+"&redirect_uri="+redirect_uri;
        model.addAttribute("location", location);

        return "login";
    }
}
