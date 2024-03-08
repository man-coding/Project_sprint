package com.example.demo.weatherApi.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Entity
@Table
public class Weather extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int no;

	@Column(length = 20, nullable = true)
	int forecastTime;

	@Column(length = 20, nullable = true)
	int temperature;

	@Column(length = 20, nullable = true)
	String weather;

	@Column(length = 20, nullable = true)
	int rainPossi;

}
