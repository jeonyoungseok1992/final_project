package com.kh.fin.member.model.service;



import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public int updateMember(Member m) {
		// TODO Auto-generated method stub
		return 0;
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
	@Override
	public int friendDelete(Member m) {
		
		return memberDao.friendDelete(sqlSession, m);
	}
	@Override
	public ArrayList refriendDelete(int mno) {
		return memberDao.refriendDelete(sqlSession, mno);
	}
	
	
	

}
