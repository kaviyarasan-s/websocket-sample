package com.example.websocketdemo;

import java.util.Arrays;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebsocketConfiguration2 implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		System.out.println("Inside websocket handler register");
		registry.addHandler(new WebsocketTestHandler(), "/customwebsocket")
				.addInterceptors(websocketTestInterceptor2());
	}

	@Bean
	public HandshakeInterceptor websocketTestInterceptor2() {
		return new HandshakeInterceptor() {

			@Override
			public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
					WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
				System.out.println("Before Handshake");
				var uri = request.getURI();
				var params = uri.getQuery().split("&");
				Arrays.stream(params).forEach(param -> attributes.put(param.split("=")[0], param.split("=")[1]));
				System.out.println(attributes.toString());
				return true;
			}

			@Override
			public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
					WebSocketHandler wsHandler, Exception exception) {
				System.out.println("After Handshake");
			}
		};
	}

}
