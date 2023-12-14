package com.kh.fin.board.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.kh.fin.board.model.dao.BoardDao;
import com.kh.fin.board.model.vo.Board;
import com.kh.fin.board.model.vo.Reply;
import com.kh.fin.common.model.vo.PageInfo;

import com.kh.fin.board.model.vo.Board;
import com.kh.fin.board.model.vo.Plan;
import com.kh.fin.board.model.vo.Reply;
import com.kh.fin.common.model.vo.PageInfo;
import com.kh.fin.member.model.vo.Member;

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
	public ArrayList<Plan> selectPlanList(int memberNo) {
		return boardDao.selectPlanList(sqlSession,memberNo);
	}
	
	@Override
	public ArrayList<Plan> selectOneTripPlan(int tripPlanNo) {
		return boardDao.selectOneTripPlan(sqlSession,tripPlanNo);
	}
	@Override
	public int countMaxPlanDay(int tripPlanNo) {
		return boardDao.countMaxPlanDay(sqlSession,tripPlanNo);
	}
	@Override
	public int insertTogetherBoard(Board b) {
		return boardDao.insertTogetherBoard(sqlSession,b);
	}
	
	@Override
	public ArrayList<Board> selectTogetherBoard(int boardNo) {
		return boardDao.selectTogetherBoard(sqlSession,boardNo);
	}
	@Override
	public int updateTogetherBoard(Board b) {
		return boardDao.updateTogetherBoard(sqlSession,b);
	}

	@Override
	public int togetherDeleteBoard(int boardNo) {
		return boardDao.togetherDeleteBoard(sqlSession,boardNo);
	}
	
	@Override
	public ArrayList<Reply> selectTogetherReplyList(int boardNo) {
		return boardDao.selectTogetherReplyList(sqlSession,boardNo);
	}

	@Override
	public int ajaxInsertTogetherReply(Reply r) {
		return boardDao.ajaxInsertTogetherReply(sqlSession,r);
	}
	
	@Override
	public int ajaxDeleteTogetherReply(Reply r) {
		return boardDao.ajaxDeleteTogetherReply(sqlSession,r);
	}
	
	@Override
	public int ajaxUpdateTogetherReply(Reply r) {
		
		return boardDao.ajaxUpdateTogetherReply(sqlSession,r);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 후기 게시판 리스트업을 위한 카운트 함수
	@Override
	public int selectReviewListCount() {
		return boardDao.selectReviewListCount(sqlSession);
	}
	
	//review게시글 조회
	@Override
	public ArrayList<Board> selectReviewList(PageInfo pi) {
		return boardDao.selectReviewList(sqlSession, pi);
	}



	//후기 게시글 키워드 조회
	@Override
	public ArrayList<Board> searchReviewList(Board b, PageInfo pi) {
		return boardDao.searchReviewList(sqlSession,b,pi);
	}
	
	// 기존 일정 가지고 글쓰기 페이지로 가기
	@Override
	public ArrayList<Plan> selectOneTripPlanRe(int tripPlanNo) {
		return boardDao.selectOneTripPlanRe(sqlSession,tripPlanNo);
	}

	//review게시글 작성
	@Override
	public int insertReviewBoard(Board b) {
		return boardDao.insertReviewBoard(sqlSession,b);
	}
	
	//후기 게시판 디테일 페이지
	@Override
	public ArrayList<Board> selectReviewBoard(int boardNo) {
		return boardDao.selectReviewBoard(sqlSession, boardNo);
	}

	
	//review 게시판 댓글 조회
	@Override
	public ArrayList<Reply> selectReviewReplyList(int boardNo) {
		return	boardDao.selectReviewReplyList(sqlSession, boardNo);

	}

	//review 댓글 입력
	@Override
	public int ajaxInsertReply(Reply r) {
		
		return boardDao.ajaxInsertReply(sqlSession, r);
	}
	
	//review 댓글 삭제
	@Override
	public int ajaxDeleteReviewReply(Reply r) {
		return boardDao.ajaxDeleteReviewReply(sqlSession,r);
	}
	
	//댓글 수정
	@Override
	public int ajaxUpdateReviewReply(Reply r) {
		return boardDao.ajaxUpdateReviewReply(sqlSession,r);
	}

	
	

	
	//글쓰기 누르면 ajax호출해서 나의 일정리스트 가져오기
	@Override
	public ArrayList<Plan> selectPlanListRe(int memberNo) {
		return boardDao.selectPlanListRe(sqlSession,memberNo);
	}


	

	//review게시글 수정
	@Override
	public int updateReviewBoard(Board b) {
		return boardDao.updateReviewBoard(sqlSession,b);
	}

	@Override
	public int reviewDeleteBoard(int boardNo) {
		return boardDao.reviewDeleteBoard(sqlSession,boardNo);
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
