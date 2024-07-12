package com.example.websocketdemo;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class WebsocketTestHandler extends AbstractWebSocketHandler {
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		TextMessage textMessage = new TextMessage(("Socket Connected : " + session.getId()).getBytes());
		session.sendMessage(textMessage);
		super.afterConnectionEstablished(session);
		System.out.println("Websocket connection established, " + session.getId());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		var payLoad = message.getPayload();
		if ("greet".equals(payLoad)) {
			var messageStr = "Welcome " + session.getAttributes().get("name") + "!!! ===> " + session.getId();
			TextMessage textMessage = new TextMessage(messageStr.getBytes());
			session.sendMessage(textMessage);
		} else if ("printmessage".equals(payLoad)) {
			var messageStr = "Websocket demo  ===> " + session.getId();
			TextMessage textMessage = new TextMessage(messageStr.getBytes());
			session.sendMessage(textMessage);
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		System.out.println("Websocket connection closed, " + session.getId());
	}
}
