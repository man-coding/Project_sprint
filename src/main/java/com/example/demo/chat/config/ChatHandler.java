package com.example.demo.chat.config;

import java.util.LinkedHashSet;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChatHandler extends TextWebSocketHandler {

	private static LinkedHashSet<WebSocketSession> numSet = new LinkedHashSet<>(); // 웹소켓 세션 저장할 해시셋 생성

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

		if (numSet.size() >= 3) {
			WebSocketSession oldSession = numSet.iterator().next(); // 가장 오래된 세션을 가져옴
		}
	}
}
testtest
testtest