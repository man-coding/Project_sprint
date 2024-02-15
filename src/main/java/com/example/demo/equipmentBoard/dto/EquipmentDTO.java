package com.example.demo.equipmentBoard.dto;
import java.time.LocalDateTime;

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
public class EquipmentDTO {
	
	int no;
	String content;
	String writer;
	String fileName;
	String filePath;
	LocalDateTime regDate;
	LocalDateTime modDate;
	
}
