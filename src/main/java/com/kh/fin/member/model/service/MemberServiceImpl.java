package com.kh.fin.member.model.service;



import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.chat.MsgVo;
import com.kh.fin.member.model.dao.MemberDao;
import com.kh.fin.member.model.vo.Member;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public Member loginMember(Member m) {
		return memberDao.loginMember(sqlSession,m);
	}

	
	@Override
	public int insertMember(Member m) {
		return memberDao.insertMember(sqlSession, m);

	}
	
	@Override
	public int idCheck(String checkId) {
		return memberDao.idCheck(sqlSession, checkId);

	}

	@Override
	public int nickNameCheck(String checkNickName) {
		return memberDao.nickNameCheck(sqlSession, checkNickName);

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public int idEmailCheck(Member m) {
		
		return memberDao.idEmailCheck(sqlSession, m);
	}

	@Override
	public int setTemPwd(Member m) {
	
		return memberDao.setTemPwd(sqlSession,m);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//프로필이미지 등록 
	@Override
	public Member updateProfileImg(Member m) {
		int result = memberDao.updateProfileImg(sqlSession,m);
        if(result > 0) {      
        	memberDao.UpdateImgMember(sqlSession,m);
        }
         
         return memberDao.UpdateImgMember(sqlSession,m);
		
	}
	
	//회원탈퇴
	@Override
	public int deleteMember(Member m) {
		
		return memberDao.deleteMember(sqlSession, m);
	}
	
	//핸드폰 인증할 때 번호 체크
	public void certifiedPhoneNumber(String userPhoneNumber, int randomNumber) {
		System.out.println("왔어요");
		String api_key = "NCSLBVD7LLNVRKXS";
	    String api_secret = "U5YW5JZGJ5OGYADOGUD4NKV5TCU98RYS";
	    Message coolsms = new Message(api_key, api_secret);

	    // 4 params(to, from, type, text) are mandatory. must be filled
	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", userPhoneNumber);    // 수신전화번호
	    params.put("from", "01025293109");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
	    params.put("type", "SMS");
	    params.put("text", "[TEST] 인증번호는" + "["+randomNumber+"]" + "입니다."); // 문자 내용 입력
	    params.put("app_version", "test app 1.2"); // application name and version

	    try {
	        JSONObject obj = (JSONObject) coolsms.send(params);
	        System.out.println(obj.toString());
	      } catch (CoolsmsException e) {
	        System.out.println(e.getMessage());
	        System.out.println(e.getCode());
	      }
	    
	}

	//핸드폰 인증할 때 아이디 체크
	@Override
	public Member phoneIdCheck(Member m) {
		
		return memberDao.phoneIdCheck(sqlSession, m);
	}
	
	
	//마이페이지 나의 친구목록 눌렀을 때 친구리스트
	@Override
	public ArrayList friendList(Member m) {
		
		return memberDao.friendList(sqlSession, m);
	}
	
	//마이페이지 친구요청 눌렀을 때 친구리스트
	@Override
	public ArrayList friendRequest(Member m) {
		
		return memberDao.friendRequest(sqlSession, m);
	}
	//마이페이지 친구 삭제
	@Override
	public int friendDelete(Member m) {
		
		return memberDao.friendDelete(sqlSession, m);
	}
	
	//친구 삭제 후 리스트업
	@Override
	public ArrayList refriendDelete(int mno) {
		return memberDao.refriendDelete(sqlSession, mno);
	}
	
	//프로필수정
	@Override
	public int updateMember(Member m) {
		return memberDao.updateMember(sqlSession, m);

	}
	
	//프로필 수정 후 다시그려주는 메서드 (select)
	public Member reloginMember(String memberId) {
		return memberDao.reloginMember(sqlSession, memberId);
	}
	
	//친구요청 거절 눌렀을 때
	@Override
	public int rejectFriend(Member m, int friendNo) {
		return memberDao.rejectFriend(sqlSession, m, friendNo);

	}
	
	//친구요청 수락 눌렀을 때
	@Override
	public int acceptFriend(Member m, int friendNo) {
		return memberDao.acceptFriend(sqlSession, m, friendNo);

	}
	
	//친구요청 수락 눌렀을 때 friend insert
	@Override
	public int insertFriend(Member m, int friendNo) {
		return memberDao.insertFriend(sqlSession, m, friendNo);

	}
	
	//친구요청 수 friend LOG insert
	@Override
	public int requestFriend(Member m, int friendNo) {
		return memberDao.requestFriend(sqlSession, m, friendNo);

	}
	
	//채팅  insert
	@Override
	public int insertChat(MsgVo vo) {
		return memberDao.insertChat(sqlSession, vo);

	}
	
	//채팅페이지 상대방 쪽
	@Override
	public ArrayList<MsgVo> leftChatList(MsgVo MsgVo) {
		return memberDao.leftChatList(sqlSession, MsgVo);
	}

	//채팅방 목록
	@Override
	public ArrayList<MsgVo> chatList(int myNo) {
		
		return memberDao.chatList(sqlSession, myNo);
	}

}
