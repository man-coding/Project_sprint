package com.example.demo.weatherApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.weatherApi.service.WeatherService;


@RestController

public class WeatherController {
	
	@Autowired
	WeatherService service;
	
//	@GetMapping("/weather")
//	public WeatherDTO getWeather() {
//		String weather = service.getWeather();
//		Root root = service.jsonToDto();
		
//		WeatherDTO dto = WeatherDTO.builder().temperature(root.).weather().rainpossi().
	
	}
	
	


