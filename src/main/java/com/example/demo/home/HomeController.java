package com.example.demo.home;



import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.demo.member.dto.KakaoProfile;
import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.dto.OAuthToken;
import com.example.demo.member.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


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
	@GetMapping("/auth/kakao/callback")
	public @ResponseBody String kakaoCallback(String code) {//Data를 리턴해주는 컨트롤러 함수
		
		//Post방식으로 key = value 데이터를 요청 (카카오쪽으로)
		//Retrofit2
		//OkHttp
		//RestTemplate
		
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "32ebae90c437ccbb483cd0fb790fed86");
		params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
		params.add("code", code);
		
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
				new HttpEntity<>(params,headers);
		
		ResponseEntity<String> response =rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
		);
		
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(),OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
	
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println("카카오 엑세스 토큰 : " + oauthToken.getAccess_token());
		
		RestTemplate rt2 = new RestTemplate();
		
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer" + oauthToken.getAccess_token());
		headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
	
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = 
				new HttpEntity<>(headers2);
		
		ResponseEntity<String> response2 =rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest2,
				String.class
		);
		System.out.println(response2.getBody());
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(),KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
	
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println("카카오 아이디(번호) : " + kakaoProfile.getId());
		System.out.println("카카오 이메일: " + kakaoProfile.getKakao_account().getEmail());;
		
		System.out.println("카카오 유저네임 : " +kakaoProfile.getKakao_account().getEmail()+"_" + kakaoProfile.getId());
		System.out.println("블로그 서버 이메일" + kakaoProfile.getKakao_account().getEmail());
		
		UUID garbagePassword = UUID.randomUUID();
	
		System.out.println("블로그 서버 패스워드 : " + garbagePassword);
		
		MemberDTO member = MemberDTO.builder()
				.id(kakaoProfile.getKakao_account().getEmail()+"_" + kakaoProfile.getId())
				.password(garbagePassword)
				.build();
		
		memberService.register(member);
		
		return response.getBody();
	}
	
	
}
