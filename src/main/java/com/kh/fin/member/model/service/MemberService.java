package com.kh.fin.member.model.service;

import com.kh.fin.member.model.vo.Mail;
import com.kh.fin.member.model.vo.Member;

public interface MemberService {
	
	//로그인 서비스 (select)
	Member loginMember(Member m);
	
	//회원가입 서비스 (insert)
	int insertMember(Member m);
	
	//회원정보수정서비스 (update)
	int updateMember(Member m);
	
	//회원탈퇴 서비스 (update)
	int deleteMember(String userId);
	
	//아이디중복체크 서비스 (select)
	int idCheck(String checkId);
	

	//닉네임중복체크 서비스 (select)
	int nickNameCheck(String checkNickName);

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//이메일과 아이디 있는지 체크 후 임시비번 보내주는 메서드
	int idEmailCheck(Member m);
	
	// 임시 비밀번호를 기존 멤버정보에 업데이트 해줌
	int setTemPwd(Member m);
	
}
