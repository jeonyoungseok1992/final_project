package com.kh.fin.member.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.chat.MsgVo;
import com.kh.fin.member.model.vo.Member;

@Repository
public class MemberDao {
	public Member loginMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.selectOne("memberMapper.loginMember",m);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 아이디 이메일 체크 !!
	public int idEmailCheck(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.selectOne("memberMapper.idEmailCheck",m);
	}
	public int setTemPwd(SqlSessionTemplate sqlSession,Member m) {
		return sqlSession.update("memberMapper.setTemPwd",m);
	}
	
	public int insertMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.insert("memberMapper.insertMember",m);
		}
	public int idCheck(SqlSessionTemplate sqlSession,String checkId) {
		return sqlSession.selectOne("memberMapper.idCheck",checkId);
	}
	
	public int nickNameCheck(SqlSessionTemplate sqlSession,String checkNickName) {
		return sqlSession.selectOne("memberMapper.nickNameCheck",checkNickName);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//프로필이미지 등록
	public int updateProfileImg(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.update("memberMapper.updateProfileImg",m);
	}
	
	public Member UpdateImgMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.selectOne("memberMapper.UpdateImgMember",m);
	}

//회원탈퇴
	public int deleteMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.update("memberMapper.deleteMember", m);
	}
	
	//아이디 찾기
	public Member phoneIdCheck(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.selectOne("memberMapper.phoneIdCheck",m);
	}
	//마이페이지 나의 친구목록 눌렀을 때 친구리스트
	public ArrayList<Member> friendList(SqlSessionTemplate sqlSession, Member m) {
		return (ArrayList)sqlSession.selectList("memberMapper.friendList",m);
	}
	
	//마이페이지 친구요청 눌렀을 때 친구리스트
	public ArrayList friendRequest(SqlSessionTemplate sqlSession, Member m) {
		return (ArrayList)sqlSession.selectList("memberMapper.friendRequest",m);
	}
	//친구삭제
	public int friendDelete(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.update("memberMapper.friendDelete", m);
	}
	//친구 삭제 후 친구리스트
	public ArrayList refriendDelete(SqlSessionTemplate sqlSession, int mno) {
		return (ArrayList)sqlSession.selectList("memberMapper.refriendDelete", mno);
	}
	
	//프로필 수정페이지 저장버튼 눌렀을 때
	public int updateMember(SqlSessionTemplate sqlSession,Member m) {
		return sqlSession.update("memberMapper.updateMember",m);
	}
	//프로필 수정 후 다시그려주는 메서드 (select)
	public Member reloginMember(SqlSessionTemplate sqlSession, String memberId) {
		return sqlSession.selectOne("memberMapper.loginMember", memberId);
		
	}
	
	//친구요청 거절 눌렀을 때
	public int rejectFriend(SqlSessionTemplate sqlSession, Member m, int friendNo) {
	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("memberNo", m.getMemberNo());
	    paramMap.put("friendNo", friendNo);
	    System.out.println("paramMap: " + paramMap);
		return sqlSession.update("memberMapper.rejectFriend", paramMap);
	}
	
	//친구요청 수락 눌렀을 때
	public int acceptFriend(SqlSessionTemplate sqlSession, Member m, int friendNo) {
	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("memberNo", m.getMemberNo());
	    paramMap.put("friendNo", friendNo);
	    System.out.println("paramMap: " + paramMap);
		return sqlSession.update("memberMapper.acceptFriend", paramMap);
	}
	
	//친구요청 수락 눌렀을 때 friend insert
	public int insertFriend(SqlSessionTemplate sqlSession, Member m, int friendNo) {
	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("memberNo", m.getMemberNo());
	    paramMap.put("friendNo", friendNo);
	    System.out.println("paramMap: " + paramMap);
		return sqlSession.insert("memberMapper.insertFriend", paramMap);
	}
	
	//친구요청 수 friend LOG insert
	public int requestFriend(SqlSessionTemplate sqlSession, Member m, int friendNo) {
	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("memberNo", m.getMemberNo());
	    paramMap.put("friendNo", friendNo);
	    System.out.println("paramMap: " + paramMap);
		return sqlSession.insert("memberMapper.requestFriend", paramMap);
	}
	
	//채팅  insert
	public int insertChat(SqlSessionTemplate sqlSession, MsgVo vo) {
		return sqlSession.insert("memberMapper.insertChat", vo);

	}
	
	
	//채팅페이지 상대방 쪽
	public ArrayList<MsgVo> leftChatList(SqlSessionTemplate sqlSession, MsgVo MsgVo) {
		return (ArrayList)sqlSession.selectList("memberMapper.leftChatList", MsgVo);
	}
	
	//채팅방 목록
	public ArrayList<MsgVo> chatList(SqlSessionTemplate sqlSession, int myNo) {
		return (ArrayList)sqlSession.selectList("memberMapper.chatList", myNo);
	}
	
	//친구요청 수락 눌렀을 때
	public Member requestFriendList(SqlSessionTemplate sqlSession, int boardNo, Member m) {
	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("memberNo", m.getMemberNo());
	    paramMap.put("boardNo", boardNo);
	    System.out.println("paramMap: " + paramMap);
		return sqlSession.selectOne("memberMapper.requestFriendList", paramMap);
	}
	


}
