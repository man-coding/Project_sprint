package com.example.demo.weatherApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.runningBoard.dto.RunningDTO;
import com.example.demo.runningBoard.service.RunningBoardService;

@RestController
@RequestMapping("/runningBoard")
public class WeatherController {
	
	@Autowired
	RunningBoardService service;
	
	@PostMapping
	public ResponseEntity<Integer> register(@RequestBody RunningDTO dto){
		
		int no = service.register(dto);
		
		return new ResponseEntity<>(no, HttpStatus.CREATED);
	}
	
//	@GetMapping
//	public ResponseEntity<List<RunningDTO>> getList(){
//		List<RunningDTO> list = service.getList(1);
//		return new ResponseEntity<>(list, HttpStatus.OK);
//	}
	
	@GetMapping("/{no}")
	public ResponseEntity<RunningDTO> read(@PathVariable("no") int no){
		RunningDTO dto = service.read(no);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
