package com.kh.chat;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.fin.member.model.service.MemberService;
import com.kh.fin.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("chatServer")
public class ChatServer extends TextWebSocketHandler {
	
	@Autowired
	private MemberService memberService;
	
	private final Map<Integer, WebSocketSession> userSessions = new ConcurrentHashMap();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		int myNo = (int)session.getAttributes().get("myNo");
		int youNo = (int)session.getAttributes().get("youNo");
		System.out.println(session.getAttributes().get("nick"));
		System.out.println("연결됨!!");
		log.info("{} 연결됨", myNo);
		userSessions.put(myNo, session);
		userSessions.put(youNo, session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage msgData) throws Exception {
		System.out.println("핸들러 연결됨!!");
		int myNo = (int)session.getAttributes().get("myNo");
		JsonObject obj = new JsonParser().parse(msgData.getPayload()).getAsJsonObject();
	
		MsgVo vo = new MsgVo();
		vo.setMessage(obj.get("message").getAsString());
		vo.setMyNo(myNo);
		vo.setYouNo(obj.get("target").getAsInt());
		vo.setTime(new Date());
		System.out.println(myNo);
		System.out.println(obj.get("target").getAsInt());
		
		memberService.insertChat(vo);
	
		sendMessageToUser(vo);
	}
	
	private void sendMessageToUser(MsgVo vo) {
		System.out.println("send핸들러 연결됨");
		WebSocketSession targetSession = userSessions.get(vo.getYouNo());
		WebSocketSession mySession = userSessions.get(vo.getMyNo());
		System.out.println(vo.getYouNo());
		System.out.println(vo.getMyNo());
		System.out.println(targetSession);
		System.out.println(mySession);
		
		
		if(targetSession != null && targetSession.isOpen()) {
			String  str = new Gson().toJson(vo);
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
