package com.kh.fin.board.model.service;

import java.util.ArrayList;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.kh.fin.board.model.dao.BoardDao;
import com.kh.fin.board.model.vo.Board;
import com.kh.fin.board.model.vo.Reply;
import com.kh.fin.common.model.vo.PageInfo;


import com.kh.fin.board.model.vo.Plan;
import com.kh.fin.board.model.vo.Region;
import com.kh.fin.member.model.vo.Member;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 같이가요 게시판 리스트업을 위한 카운트 함수
	@Override
	public int selectTogetherListCount() {

		return boardDao.selectTogetherListCount(sqlSession);
	}
	// 같이가요 게시글 리스트 조회
	@Override
	public ArrayList<Board> selectTogetherList(PageInfo pi) {

		return boardDao.selectTogetherList(sqlSession,pi);
	}

	//같이가요 키워드 검색
	@Override
	public ArrayList<Board> togetherSearchList(Board b, PageInfo pi) {
		
		return boardDao.togetherSearchList(sqlSession,b,pi);
	}
	//글쓰기 누르면 ajax호출해서 나의 일정리스트 가져오기
	@Override
	public ArrayList<Plan> selectPlanList(int memberNo) {
		return boardDao.selectPlanList(sqlSession,memberNo);
	}
	// 기존 일정 가지고 글쓰기 페이지로 가기
	@Override
	public ArrayList<Plan> selectOneTripPlan(int tripPlanNo) {
		return boardDao.selectOneTripPlan(sqlSession,tripPlanNo);
	}
	// plan max day가져오기
	@Override
	public int countMaxPlanDay(int tripPlanNo) {
		return boardDao.countMaxPlanDay(sqlSession,tripPlanNo);
	}
	//같이가요 게시판 글작성
	@Override
	public int insertTogetherBoard(Board b) {
		return boardDao.insertTogetherBoard(sqlSession,b);
	}
	//같이가요 게시판 디테일 페이지
	@Override
	public ArrayList<Board> selectTogetherBoard(int boardNo) {
		return boardDao.selectTogetherBoard(sqlSession,boardNo);
	}
	//같이가요 게시판 글 수정
	@Override
	public int updateTogetherBoard(Board b) {
		return boardDao.updateTogetherBoard(sqlSession,b);
	}
	//같이가요 게시판 글 삭제
	@Override
	public int togetherDeleteBoard(int boardNo) {
		return boardDao.togetherDeleteBoard(sqlSession,boardNo);
	}
	//같이가요 게시판 댓글 부르기
	@Override
	public ArrayList<Reply> selectTogetherReplyList(int boardNo) {
		return boardDao.selectTogetherReplyList(sqlSession,boardNo);
	}
	//같이가요 게시판 댓글 추가
	@Override
	public int ajaxInsertTogetherReply(Reply r) {
		return boardDao.ajaxInsertTogetherReply(sqlSession,r);
	}
	//같이가요 게시판 댓글 삭제
	@Override
	public int ajaxDeleteTogetherReply(Reply r) {
		return boardDao.ajaxDeleteTogetherReply(sqlSession,r);
	}
	//같이가요 게시판 댓글 수정
	@Override
	public int ajaxUpdateTogetherReply(Reply r) {
		
		return boardDao.ajaxUpdateTogetherReply(sqlSession,r);
	}
	//같이가요 게시판 대댓글 작성 기능
	@Override
	public int ajaxInsertTogetherReReply(Reply r) {
		return boardDao.ajaxInsertTogetherReReply(sqlSession,r);
	}
	//메인페이지 지역리스트 가져오는 ajax
	@Override
	public ArrayList<Region> ajaxselectRegionList() {
		return boardDao.ajaxselectRegionList(sqlSession);
	}
	//같이가요 게시글 삭제시 해당게시글 컨텐츠 가져와서 이미지 추출하기위함
	@Override
	public Board togetherSelectBoardOne(int boardNo) {
		return boardDao.togetherSelectBoardOne(sqlSession,boardNo);
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






















































































































































	
	

//	public ArrayList<LocationInfomation> makePlan(HashMap<String,Object> map){
//	
//	return boardDao.makePlan(sqlSession, map);
//	}

	
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
