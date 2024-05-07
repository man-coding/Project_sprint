package com.example.demo.chat.config;

import java.util.LinkedHashSet;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChatHandler extends TextWebSocketHandler{

	private static LinkedHashSet<WebSocketSession> numSet = new LinkedHashSet<>(); //웹소켓 세션 저장할 해시셋 생성
	
	
}
