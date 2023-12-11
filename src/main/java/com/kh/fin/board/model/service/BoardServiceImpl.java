package com.kh.fin.board.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.fin.board.model.dao.BoardDao;
import com.kh.fin.board.model.vo.Board;
import com.kh.fin.board.model.vo.Reply;
import com.kh.fin.common.model.vo.PageInfo;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
	public int selectReviewListCount() {
		return boardDao.selectReviewListCount(sqlSession);
	}

	@Override
	public ArrayList<Board> selectReviewList(PageInfo pi) {
		return boardDao.selectReviewList(sqlSession, pi);
	}



	@Override
	public ArrayList<Board> searchReviewList(Board b, PageInfo pi) {
		return boardDao.searchReviewList(sqlSession,b,pi);
	}
	
	@Override
	public Board selectReviewBoard(int boardNo) {
		return boardDao.selectReviewBoard(sqlSession, boardNo);
	}

	

	
	
	@Override
	public ArrayList<Reply> selectReply(int bno) {
		return	boardDao.selectReplyList(sqlSession, bno);

	}

	@Override
	public int insertReviewReply(Reply r) {
		
		return boardDao.insertReviewReply(sqlSession, r);
	}

	
	@Override
	public int updateReivewReply(Reply r) {
		
		return boardDao.updateReivewReply(sqlSession, r);
	}
	



































































	




































































	
	
	
}
