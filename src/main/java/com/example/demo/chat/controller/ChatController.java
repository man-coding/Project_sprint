package com.example.demo.chat.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.demo.chat.Message.ChatMessage;
import com.example.demo.member.service.MemberService;

@Controller
public class ChatController {
	
	@Autowired
	MemberService memberService;
	
	@MessageMapping("/chat/private-{userId}")
	public ChatMessage sendMessage(ChatMessage chatMessage, Principal principal) {
		
		String senderId = principal.getName();
	}
}