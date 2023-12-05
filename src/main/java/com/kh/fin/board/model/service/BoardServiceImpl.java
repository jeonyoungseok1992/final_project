package com.kh.fin.board.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.fin.board.model.dao.BoardDao;
import com.kh.fin.board.model.vo.Board;
import com.kh.fin.common.model.vo.PageInfo;
import com.kh.fin.member.model.vo.Member;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	@Override
	public Board selectBoard(int boardNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int increaseCount(int boardNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public int selectTogetherListCount() {

		return boardDao.selectTogetherListCount(sqlSession);
	}

	@Override
	public ArrayList<Board> selectTogetherList(PageInfo pi) {

		return boardDao.selectTogetherList(sqlSession,pi);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 마이페이지 같이가요 리스트업을 위한 카운트 함수
	@Override
	public int myTogetherListCount(Member m) {

		return boardDao.myTogetherListCount(sqlSession, m);
	}

	
	// 마이페이지 같이가요 게시글 리스트 조회
	@Override
	public ArrayList<Board> myTogetherList(PageInfo pi, Member m) {

		return boardDao.myTogetherList(sqlSession,pi, m);
	}
	
	
	// 마이페이지 후기 리스트업을 위한 카운트 함수
	@Override
	public int myReviewListCount(Member m) {

		return boardDao.myReviewListCount(sqlSession, m);
	}

	
	// 마이페이지 같이가요 게시글 리스트 조회
	@Override
	public ArrayList<Board> myReviewList(PageInfo pi, Member m) {

		return boardDao.myReviewList(sqlSession,pi, m);
	}
	
	
	
	
	
}
