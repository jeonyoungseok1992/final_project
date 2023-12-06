package com.kh.fin.board.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.fin.board.model.vo.Board;
import com.kh.fin.board.model.vo.Plan;
import com.kh.fin.common.model.vo.PageInfo;
import com.kh.fin.board.model.dao.BoardDao;

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

	@Override
	public ArrayList<Board> togetherSearchList(Board b, PageInfo pi) {
		
		return boardDao.togetherSearchList(sqlSession,b,pi);
	}

	@Override
	public ArrayList<Plan> selectPlanList(int memberNo) {
		return boardDao.selectPlanList(sqlSession,memberNo);
	}
	
}
