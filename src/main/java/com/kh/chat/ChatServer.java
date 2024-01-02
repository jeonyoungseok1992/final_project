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
		Integer myNo2 = (Integer) session.getAttributes().get("myNo");
		Member mem = ((Member)session.getAttributes().get("loginUser"));
        WebSocketSession existingSession = userSessions.get(myNo);
        //String senderId = getMemberId(session);
        if (existingSession != null && existingSession.isOpen()) {
            // 이미 세션이 존재하면 해당 세션을 종료시킴
            existingSession.close();
        }
		if(myNo2 != null) {
		System.out.println("afterConnectionEstablished 연결됨!!");
		log.info("{} 연결됨", myNo);

		userSessions.put(myNo, session);
		}
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage msgData) throws Exception {
		System.out.println("핸들러 연결됨!!");
		int myNo = (int)session.getAttributes().get("myNo");
		Member mem = ((Member)session.getAttributes().get("loginUser"));
		JsonObject obj = new JsonParser().parse(msgData.getPayload()).getAsJsonObject();
		MsgVo vo = new MsgVo();
		vo.setMessage(obj.get("message").getAsString());
		vo.setMyNo(myNo);
		vo.setYouNo(obj.get("target").getAsInt());
		vo.setTime(new Date());
		vo.setMemberProfileImg(mem.getMemberProfileImg());
		vo.setChatCheck(myNo);

		
		memberService.insertChat(vo);
		
		sendMessageToUser(vo);
	}
	
	private void sendMessageToUser(MsgVo vo) throws IOException {
		System.out.println("send핸들러 연결됨");
		WebSocketSession targetSession = userSessions.get(vo.getYouNo());
		WebSocketSession mySession = userSessions.get(vo.getMyNo());
		

			String  str = new Gson().toJson(vo);
			TextMessage msg = new TextMessage(str);
			mySession.sendMessage(msg);
			
			
			if(targetSession != null && targetSession.isOpen()) {
				targetSession.sendMessage(msg);
				System.out.println("타겟연결됨");
			}
			
			
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		int myNo = (int)session.getAttributes().get("myNo");
		log.info("{} 연결끊김", myNo);
		userSessions.remove(myNo, session);
	}
	private void log(String logmsg) {
		System.out.println(new Date() + " : " + logmsg);
	}


}
