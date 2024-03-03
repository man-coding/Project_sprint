package com.example.demo.weatherApi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.weatherApi.dto.WeatherDTO;
import com.example.demo.weatherApi.service.WeatherService;

@RestController

public class WeatherController {

	@Autowired
	WeatherService service;

	@GetMapping("/weather")
	public ResponseEntity<WeatherDTO> getWeather() throws IOException {

		Root root = service.jsonToDto();

		if (root != null && root.getResponse() != null && root.getResponse().getBody() != null
				&& root.getResponse().getBody().getItems() != null) {
			// WeatherDTO 구성
			WeatherDTO dto = WeatherDTO.builder()
					.temperature(root.getResponse().getBody().getItems().getItem().get(0).getTa())
					.weather(root.getResponse().getBody().getItems().getItem().get(0).getWf())
					.rainpossi(root.getResponse().getBody().getItems().getItem().get(0).getRnSt()).build();

			return new ResponseEntity<>(dto, HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	} // ResponseEntity를 통해 클라이언트 측에서 에러 응답 및 입력 검증 가능

	@ExceptionHandler(IOException.class)
	public ResponseEntity<String> handleIOException(IOException e) {
		return new ResponseEntity<>("Failed to get weather data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/weather")
	public String getWeather(Model model) throws IOException {
	    Root root = service.jsonToDto();
	    
	    if (root != null && root.getResponse() != null && 
	        root.getResponse().getBody() != null && 
	        root.getResponse().getBody().getItems() != null) 
	    {
	        WeatherDTO dto = WeatherDTO.builder()
	            .temperature(root.getResponse().getBody().getItems().getItem().get(0).getTa())
	            .weather(root.getResponse().getBody().getItems().getItem().get(0).getWf())
	            .rainpossi(root.getResponse().getBody().getItems().getItem().get(0).getRnSt())
	            .build();

	        model.addAttribute("weather", dto);
	    }

	    return "read";
	}
}