package com.kh.fin.member.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

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
	public ArrayList friendList(SqlSessionTemplate sqlSession, Member m) {
		return (ArrayList)sqlSession.selectList("memberMapper.friendList",m);
	}
	
	//마이페이지 친구요청 눌렀을 때 친구리스트
	public ArrayList friendRequest(SqlSessionTemplate sqlSession, Member m) {
		return (ArrayList)sqlSession.selectList("memberMapper.friendRequest",m);
	}
	
	public int friendDelete(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.update("memberMapper.friendDelete", m);
	}
	
	public ArrayList refriendDelete(SqlSessionTemplate sqlSession, int mno) {
		return (ArrayList)sqlSession.selectList("memberMapper.refriendDelete", mno);
	}
	

	
	
	


}
