package com.example.demo.chat.chatDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ChatMessage {

	public enum MessageType{
		ENTER, TALK
	}
	
	private MessageType type; // 메시지 타입
	
	private String roomId; // 방번호
	
	private String sender; // 메시지 보낸 사람
	
	private String message; // 메시지
}
