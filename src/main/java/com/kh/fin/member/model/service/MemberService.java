package com.kh.fin.member.model.service;


import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.kh.chat.MsgVo;
import com.kh.fin.member.model.vo.Member;

public interface MemberService {
	
	//로그인 서비스 (select)
	Member loginMember(Member m);
	
	//회원가입 서비스 (insert)
	int insertMember(Member m);
	
	
	//아이디중복체크 서비스 (select)
	int idCheck(String checkId);
	

	//닉네임중복체크 서비스 (select)
	int nickNameCheck(String checkNickName);

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//이메일과 아이디 있는지 체크 후 임시비번 보내주는 메서드
	int idEmailCheck(Member m);
	
	// 임시 비밀번호를 기존 멤버정보에 업데이트 해줌
	int setTemPwd(Member m);
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//프로필 이미지 변경 (update)
	Member updateProfileImg(Member m);
	
	
	//회원탈퇴 서비스 (update)
	int deleteMember(Member m);

	//아이디찾기
	void certifiedPhoneNumber(String userPhoneNumber, int randomNumber);
	
	//아이디찾기 후 아이디가져오기
	public Member phoneIdCheck(Member m);
	
	//마이페이지 나의 친구목록 눌렀을 때 친구리스트
	public ArrayList friendList(Member m);
	
	//마이페이지 나의 친구요청 눌렀을 때 친구리스트
	public ArrayList friendRequest(Member m);
	
	//친구삭제
	public int friendDelete(Member m);
	
	//친구 삭제 후 친구리스트
	public ArrayList refriendDelete(int mno);
	
	//프로필 수정페이지 저장버튼 눌렀을 때
	int updateMember(Member m);
	
	//프로필 수정 후 다시그려주는 메서드 (select)
	Member reloginMember(String memberId);
	
	//친구요청 거절 눌렀을 때
	int rejectFriend(Member m, int friendNo);
	
	//채팅  insert
	int insertChat(MsgVo vo);
	
	//채팅페이지 상대방 쪽
	ArrayList<MsgVo> leftChatList(MsgVo MsgVo);
	
	//채팅방 목록
	ArrayList<MsgVo> chatList(int myNo);
	
	
	//박수현 회원정보 전체
	ArrayList<Member> memberInfor();
	
}
