//package com.example.demo.chat.controller;
//
//import java.security.Principal;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import jakarta.servlet.http.HttpSession;
//
//
//
//@Controller
//public class ChatController {
//
////	@Autowired
////	private SimpMessagingTemplate template;
////
////	@MessageMapping("/chat/private-{userId}")
////	public void sendMessage(@DestinationVariable("userId") String userId, ChatMessage chatMessage, Principal principal) {
////
////		String senderId = principal.getName();    //발신자
////
////		chatMessage.setSender(senderId);
////
////		template.convertAndSendToUser(userId, "/queue/reply", chatMessage); //userId는 수신자
////
////	}
//	@GetMapping("/chat")
//    public String index(){
//        return "error";
//    }
//
//    @GetMapping("/{id}")
//    public String chattingRoom(@PathVariable String id, HttpSession session, Model model){
//        if(id.equals("guest")){
//            model.addAttribute("name", "guest");
//        }else if(id.equals("master")){
//            model.addAttribute("name", "master");
//        }else if(id.equals("loose")){
//            model.addAttribute("name", "loose");
//        }else{
//            return "error";
//        }
//        return "chattingRoom";
//    }
//}