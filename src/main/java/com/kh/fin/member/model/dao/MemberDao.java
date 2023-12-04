package com.kh.fin.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.fin.member.model.vo.Member;

@Repository
public class MemberDao {
	public Member loginMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.selectOne("memberMapper.loginMember",m);
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
