package com.kh.fin.member.model.service;



import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.fin.member.model.vo.Member;

import com.kh.fin.member.model.dao.MemberDao;
import com.kh.fin.member.model.vo.Member;

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
	
	
	
	
	

}
