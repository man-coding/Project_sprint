package com.example.demo.chat.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.demo.chat.Message.ChatMessage;

@Controller
public class ChatController {

	@Autowired
	private SimpMessagingTemplate template;

	@MessageMapping("/chat/private-{userId}")
	public void sendMessage(@DestinationVariable String userId, ChatMessage chatMessage, Principal principal) {

		String senderId = principal.getName();    //발신자

		chatMessage.setSender(senderId);

		template.convertAndSendToUser(userId, "/queue/reply", chatMessage); //userId는 수신자

	}
}
TEST