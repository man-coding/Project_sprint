package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * 첨부 파일의 경로를 맵핑하기 위한 설정 클래스
 * */
@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Value("${webpath}")
	String webpath; //첨부 폴더 경로

	//스프링 보안문제로 외부폴더에 바로 접근할수 없음 
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {	
		//폴더와 상대경로 맵핑
		//url을 통해 첨부파일에 접근할 수 있음.
		registry.addResourceHandler("/uploadfiles/**").addResourceLocations(webpath); //핸들러를 통해 /uploadfiles/** 요청이 들어오면 매핑된 webpath에 접근
		WebMvcConfigurer.super.addResourceHandlers(registry); //생략해도 됨.
	}

}