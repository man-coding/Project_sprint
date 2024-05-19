package com.example.demo.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import lombok.RequiredArgsConstructor;

//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//
//	@Override
//	public void registerStompEndpoints(StompEndpointRegistry registry) {
//		//STOMP 텍스트 기반의 간단한 메시징 프로토콜
//		//클라이언트와 서버 간에 메시지를 교환하기 위한 형식과 규칙을 제공
//
//		registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS(); // withSockJS-> 웹소켓 지원되지 않는 브라우저에서도 통신 가능
//		// "/ws"는 웹소켓 통신을 위한 경로
//	}
//
//	@Override
//	public void configureMessageBroker(MessageBrokerRegistry registry) {
//		
//		registry.enableSimpleBroker("/topic", "/queue");  //토픽은 여러 세션 구독자에게 알림, 메시지 등을 전달할 수 있음
//		registry.setApplicationDestinationPrefixes("/app"); // 클라이언트가 서버로 메시지 보낼 때 목적지, 컨트롤러에서 처리함. 다른 클라이언트에게도 전달 가능
//		registry.setUserDestinationPrefix("/user");
//	}
//}

@Configuration
@RequiredArgsConstructor 
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChatHandler chatHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler, "ws/chat").setAllowedOrigins("*");
    }
}