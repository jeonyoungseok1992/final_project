package com.kh.fin.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.fin.member.model.vo.Member;
import com.kh.fin.member.model.dao.MemberDao;

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
	public int deleteMember(String userId) {
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

}
