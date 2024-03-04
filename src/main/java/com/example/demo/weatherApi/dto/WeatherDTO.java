package com.example.demo.weatherApi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class WeatherDTO {
	
	int when;
	int temperature;
	String weather;
	int rainPossi;
	

}
