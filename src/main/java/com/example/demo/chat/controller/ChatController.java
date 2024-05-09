package com.example.demo.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.demo.chat.Message.ChatMessage;

@Controller
public class ChatController {

	@MessageMapping("/chat.private.{userId}")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(ChatMessage chatMessage) {
		
		return chatMessage;
	}
}