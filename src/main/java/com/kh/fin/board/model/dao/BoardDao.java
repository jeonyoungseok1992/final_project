package com.kh.fin.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.fin.board.model.vo.Board;
import com.kh.fin.board.model.vo.Plan;
import com.kh.fin.board.model.vo.Recommend;
import com.kh.fin.board.model.vo.Region;
import com.kh.fin.board.model.vo.Reply;
import com.kh.fin.board.model.vo.Report;
import com.kh.fin.board.model.vo.Star;
import com.kh.fin.common.model.vo.PageInfo;
import com.kh.fin.member.model.vo.Member;

@Repository
public class BoardDao {

	public Board selectBoard(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.selectOne("boardMapper.selectBoard", boardNo);
	}
	
	public int increaseCount(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.update("boardMapper.increaseCount", boardNo);
	}
	
	//메인페이지에서 지역하나 선택하면 그 지역정보가지고 플랜짜는 페이지로 이동(지역정보셀렉트)
	public Region selectOneRegion(SqlSessionTemplate sqlSession,int regionNo) {
		return sqlSession.selectOne("boardMapper.selectOneRegion", regionNo);
	}
	
	// 후기 등록 게시판 별점주기 
	public int ajaxInsertReviewStars(SqlSessionTemplate sqlSession,Star s) {
		return sqlSession.insert("boardMapper.ajaxInsertReviewStars",s);
	}
	
	//게시글 및 댓글 신고
	public int reportContents(SqlSessionTemplate sqlSession, Report r) {
		return sqlSession.insert("boardMapper.reportContents", r);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 같이가요 게시판 리스트업을 위한 카운트 함수
	public int selectTogetherListCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("boardMapper.selectTogetherListCount");
	}
	// 같이가요 게시글 리스트 조회
	public ArrayList<Board> selectTogetherList(SqlSessionTemplate sqlSession, PageInfo pi){
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		int limit= pi.getBoardLimit();
		
		RowBounds rowBounds = new RowBounds(offset,limit);
		return (ArrayList)sqlSession.selectList("boardMapper.selectTogetherList",null,rowBounds);
	}
	//같이가요 키워드 검색
	public ArrayList<Board> togetherSearchList(SqlSessionTemplate sqlSession, Board b,PageInfo pi){
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		int limit= pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		return (ArrayList)sqlSession.selectList("boardMapper.togetherSearchList",b,rowBounds);
	}
	//글쓰기 누르면 ajax호출해서 나의 일정리스트 가져오기
	public ArrayList<Plan> selectPlanList(SqlSessionTemplate sqlSession, int memberNo){
		return (ArrayList)sqlSession.selectList("boardMapper.selectPlanList",memberNo);
	}
	// 기존 일정 가지고 글쓰기 페이지로 가기
	public ArrayList<Plan> selectOneTripPlan(SqlSessionTemplate sqlSession, int tripPlanNo){
		return (ArrayList)sqlSession.selectList("boardMapper.selectOneTripPlan",tripPlanNo);
	}
	// plan max day가져오기
	public int countMaxPlanDay(SqlSessionTemplate sqlSession, int tripPlanNo) {
		return sqlSession.selectOne("boardMapper.countMaxPlanDay",tripPlanNo);
	}
	//같이가요 게시판 글작성
	public int insertTogetherBoard(SqlSessionTemplate sqlSession, Board b) {
		return sqlSession.insert("boardMapper.insertTogetherBoard",b);
	}
	//같이가요 게시판 디테일 페이지
	public ArrayList<Board> selectTogetherBoard(SqlSessionTemplate sqlSession, int boardNo) {
		return (ArrayList)sqlSession.selectList("boardMapper.selectTogetherBoard", boardNo);
	}
	//같이가요 게시판 글 수정
	public int updateTogetherBoard(SqlSessionTemplate sqlSession,Board b) {
		return sqlSession.update("boardMapper.updateTogetherBoard",b);
	}
	//같이가요 게시판 글 삭제
	public int togetherDeleteBoard(SqlSessionTemplate sqlSession,int boardNo) {
		return sqlSession.update("boardMapper.togetherDeleteBoard",boardNo);
	}
	//같이가요 게시판 댓글 부르기
	public ArrayList<Reply> selectTogetherReplyList(SqlSessionTemplate sqlSession,int boardNo){
		return (ArrayList)sqlSession.selectList("boardMapper.selectTogetherReplyList",boardNo);
	}
	//같이가요 게시판 댓글 추가
	public int ajaxInsertTogetherReply(SqlSessionTemplate sqlSession,Reply r) {
		return sqlSession.insert("boardMapper.ajaxInsertTogetherReply",r);
	}
	//같이가요 게시판 댓글 삭제
	public int ajaxDeleteTogetherReply(SqlSessionTemplate sqlSession,Reply r) {
		return sqlSession.update("boardMapper.ajaxDeleteTogetherReply",r);
	}
	//같이가요 게시판 댓글 수정
	public int ajaxUpdateTogetherReply(SqlSessionTemplate sqlSession,Reply r) {
		return sqlSession.update("boardMapper.ajaxUpdateTogetherReply",r);
	}
	//같이가요 게시판 대댓글 작성 기능
	public int ajaxInsertTogetherReReply(SqlSessionTemplate sqlSession,Reply r) {
		return sqlSession.insert("boardMapper.ajaxInsertTogetherReReply",r);
	}
	
	//메인페이지 지역리스트 가져오는 ajax
	public ArrayList<Region> ajaxselectRegionList(SqlSessionTemplate sqlSession){
		return (ArrayList)sqlSession.selectList("boardMapper.ajaxselectRegionList");
	}
	//같이가요 게시글 삭제시 해당게시글 컨텐츠 가져와서 이미지 추출하기위함
	public Board togetherSelectBoardOne(SqlSessionTemplate sqlSession,int boardNo) {
		return sqlSession.selectOne("boardMapper.togetherSelectBoardOne",boardNo);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int selectReviewListCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("boardMapper.selectReviewListCount");
	}
	
	
	public ArrayList<Board> selectReviewList(SqlSessionTemplate sqlSession, PageInfo pi){
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectReviewList", null, rowBounds);
	}

	public ArrayList<Board> searchReviewList(SqlSessionTemplate sqlSession, Board b, PageInfo pi) {
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		int limit= pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		return (ArrayList)sqlSession.selectList("boardMapper.searchReviewList",b,rowBounds);
	}
	

	
	public ArrayList<Board> selectReviewBoard(SqlSessionTemplate sqlSession, int boardNo) {
		return (ArrayList)sqlSession.selectList("boardMapper.selectReviewBoard", boardNo);
	}
	
	public ArrayList<Reply> selectReviewReplyList(SqlSessionTemplate sqlSession, int boardNo){
		return (ArrayList)sqlSession.selectList("boardMapper.selectReviewReplyList",boardNo); //list들어오므로 (ArrayList)
	}
	
	public int ajaxInsertReply(SqlSessionTemplate sqlSession, Reply r) {
		return sqlSession.insert("boardMapper.ajaxInsertReply", r);
	}
	
	
	public int updateReivewReply(SqlSessionTemplate sqlSession, Reply r) {
		return sqlSession.update("boardMapper.updateReivewReply", r);
	}
	
	
	
	
	//review
	public ArrayList<Plan> selectOneTripPlanRe(SqlSessionTemplate sqlSession, int tripPlanNo){
		return (ArrayList)sqlSession.selectList("boardMapper.selectOneTripPlanRe",tripPlanNo);
	}
	
	
	//review
		public ArrayList<Plan> selectPlanListRe(SqlSessionTemplate sqlSession, int memberNo){
			return (ArrayList)sqlSession.selectList("boardMapper.selectPlanListRe",memberNo);
		}
		
	
	
		public int insertReviewBoard(SqlSessionTemplate sqlSession, Board b) {
			return sqlSession.insert("boardMapper.insertReviewBoard",b);
		}
	
	
	
		public int updateReviewBoard(SqlSessionTemplate sqlSession,Board b) {
			return sqlSession.update("boardMapper.updateReviewBoard",b);
		}
	
		
		public int reviewDeleteBoard(SqlSessionTemplate sqlSession,int boardNo) {
			return sqlSession.update("boardMapper.reviewDeleteBoard",boardNo);
		}
	
	
		public int ajaxDeleteReviewReply(SqlSessionTemplate sqlSession,Reply r) {
			return sqlSession.update("boardMapper.ajaxDeleteReviewReply",r);
		}
	
		public int ajaxUpdateReviewReply(SqlSessionTemplate sqlSession,Reply r) {
			return sqlSession.update("boardMapper.ajaxUpdateReviewReply",r);
		}
		
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//마이페이지 같이가요 리스트업
	public int myTogetherListCount(SqlSessionTemplate sqlSession, Member m) {
		
		return sqlSession.selectOne("boardMapper.myTogetherListCount", m);
	}
	public ArrayList<Board> myTogetherList(SqlSessionTemplate sqlSession, PageInfo pi, Member m){
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		int limit= pi.getBoardLimit();
		
		RowBounds rowBounds = new RowBounds(offset,limit);
		return (ArrayList)sqlSession.selectList("boardMapper.myTogetherList",m,rowBounds);
	}
	
	
	//마이페이지 후기 리스트업
	public int myReviewListCount(SqlSessionTemplate sqlSession, Member m) {
		
		return sqlSession.selectOne("boardMapper.myReviewListCount", m);
	}
	public ArrayList<Board> myReviewList(SqlSessionTemplate sqlSession, PageInfo pi, Member m){
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		int limit= pi.getBoardLimit();
		
		RowBounds rowBounds = new RowBounds(offset,limit);
		return (ArrayList)sqlSession.selectList("boardMapper.myReviewList",m,rowBounds);
	}
	
//	public ArrayList<LocationInfomation> makePlan(SqlSessionTemplate sqlSession, HashMap<String,Object> map){
//		return sqlSession.insert("boardMapper.makePlan", map);
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//박수현 추천여행지 Main
	public ArrayList<Recommend> ajaxrecommendMain(SqlSessionTemplate sqlSession){
		return (ArrayList)sqlSession.selectList("boardMapper.ajaxrecommendMain");
	}
	//박수현 추천여행지 더보기 클릭시 전체보기
	public ArrayList<Recommend> ajaxrecommendList(SqlSessionTemplate sqlSession){
		return (ArrayList)sqlSession.selectList("boardMapper.ajaxrecommendList");
	}
	//박수현 추천여행지 디테일 페이지
	public ArrayList<Recommend> selectRecommendBoard(SqlSessionTemplate sqlSession, int selectRecommendBoard) {
		return (ArrayList)sqlSession.selectList("boardMapper.selectRecommendBoard", selectRecommendBoard);
	}
	//박수현 신고테이블 전체
//	public ArrayList<Report> reportInfor(SqlSessionTemplate sqlSession) {
//		return (ArrayList)sqlSession.selectList("boardMapper.reportInfor");
//	}
}
