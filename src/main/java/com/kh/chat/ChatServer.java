package com.kh.chat;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("chatServer")
public class ChatServer extends TextWebSocketHandler {
	private final Map<String, WebSocketSession> userSessions = new ConcurrentHashMap();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String myNick = (String)session.getAttributes().get("myNick");
		log.info("{} 연결됨", myNick);
		userSessions.put(myNick, session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String myNick = (String)session.getAttributes().get("myNick");
		JsonObject obj = new JsonParser().parse(message.getPayload()).getAsJsonObject();
	
		MsgVo vo = new MsgVo();
		vo.setMsg(obj.get("message").getAsString());
		vo.setName(myNick);
		vo.setTime(new Date().toLocaleString());
	
		sendMessageToUser(obj.get("target").getAsString(), vo);
	}
	
	private void sendMessageToUser(String youNick, MsgVo msgVo) {
		WebSocketSession targetSession = userSessions.get(youNick);
		WebSocketSession mySession = userSessions.get(msgVo.getName());
		
		if(targetSession != null && targetSession.isOpen()) {
			String  str = new Gson().toJson(msgVo);
			TextMessage msg = new TextMessage(str);
			try {
				mySession.sendMessage(msg);
				targetSession.sendMessage(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String myNick = (String)session.getAttributes().get("myNick");
		log.info("{} 연결끊김", myNick);
		userSessions.remove(myNick, session);
	}
	


}
