package com.example.demo.weatherApi.entity;

import org.springframework.stereotype.Service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Entity
public class Weather {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(length = 20, nullable = false)
	int when;
	
	@Column(length = 20, nullable = false)
	int temperature;
	
	@Column(length = 20, nullable = false)
	String weather;
	
	@Column(length = 20, nullable = false)
	int rainPossi;
	
}
