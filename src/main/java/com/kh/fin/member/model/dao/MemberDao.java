package com.kh.fin.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.fin.member.model.vo.Mail;
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

}
