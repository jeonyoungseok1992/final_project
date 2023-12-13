package com.kh.fin.board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.fin.board.model.vo.Board;

import com.kh.fin.board.model.vo.Plan;
import com.kh.fin.board.model.vo.Reply;
import com.kh.fin.common.model.vo.PageInfo;
import com.kh.fin.data.vo.LocationInfomation;
import com.kh.fin.member.model.vo.Member;

@Repository
public class BoardDao {

	public Board selectBoard(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.selectOne("boardMapper.selectBoard", boardNo);
	}
	
	public int increaseCount(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.update("boardMapper.increaseCount", boardNo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int selectTogetherListCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("boardMapper.selectTogetherListCount");
	}
	public ArrayList<Board> selectTogetherList(SqlSessionTemplate sqlSession, PageInfo pi){
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		int limit= pi.getBoardLimit();
		
		RowBounds rowBounds = new RowBounds(offset,limit);
		return (ArrayList)sqlSession.selectList("boardMapper.selectTogetherList",null,rowBounds);
	}
	
	public ArrayList<Board> togetherSearchList(SqlSessionTemplate sqlSession, Board b,PageInfo pi){
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		int limit= pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		return (ArrayList)sqlSession.selectList("boardMapper.togetherSearchList",b,rowBounds);
	}
	
	public ArrayList<Plan> selectPlanList(SqlSessionTemplate sqlSession, int memberNo){
		return (ArrayList)sqlSession.selectList("boardMapper.selectPlanList",memberNo);
	}
	public ArrayList<Plan> selectOneTripPlan(SqlSessionTemplate sqlSession, int tripPlanNo){
		return (ArrayList)sqlSession.selectList("boardMapper.selectOneTripPlan",tripPlanNo);
	}
	public int countMaxPlanDay(SqlSessionTemplate sqlSession, int tripPlanNo) {
		return sqlSession.selectOne("boardMapper.countMaxPlanDay",tripPlanNo);
	}
	public int insertTogetherBoard(SqlSessionTemplate sqlSession, Board b) {
		return sqlSession.insert("boardMapper.insertTogetherBoard",b);
	}
	
	public ArrayList<Board> selectTogetherBoard(SqlSessionTemplate sqlSession, int boardNo) {
		return (ArrayList)sqlSession.selectList("boardMapper.selectTogetherBoard", boardNo);
	}
	public int updateTogetherBoard(SqlSessionTemplate sqlSession,Board b) {
		return sqlSession.update("boardMapper.updateTogetherBoard",b);
	}
	public int togetherDeleteBoard(SqlSessionTemplate sqlSession,int boardNo) {
		return sqlSession.update("boardMapper.togetherDeleteBoard",boardNo);
	}
	public ArrayList<Reply> selectTogetherReplyList(SqlSessionTemplate sqlSession,int boardNo){
		return (ArrayList)sqlSession.selectList("boardMapper.selectTogetherReplyList",boardNo);
	}
	
	public int ajaxInsertTogetherReply(SqlSessionTemplate sqlSession,Reply r) {
		return sqlSession.insert("boardMapper.ajaxInsertTogetherReply",r);
	}
	
	public int ajaxDeleteTogetherReply(SqlSessionTemplate sqlSession,Reply r) {
		return sqlSession.update("boardMapper.ajaxDeleteTogetherReply",r);
	}
	
	public int ajaxUpdateTogetherReply(SqlSessionTemplate sqlSession,Reply r) {
		return sqlSession.update("boardMapper.ajaxUpdateTogetherReply",r);
	}
	
	public int ajaxInsertTogetherReReply(SqlSessionTemplate sqlSession,Reply r) {
		return sqlSession.insert("boardMapper.ajaxInsertTogetherReReply",r);
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
	
	public Board selectReviewBoard(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.selectOne("boardMapper.selectReviewBoard", boardNo);
	}
	
	public ArrayList<Reply> selectReplyList(SqlSessionTemplate sqlSession, int bno){
		return (ArrayList)sqlSession.selectList("boardMapper.selectReplyList",bno); //list들어오므로 (ArrayList)
	}
	
	public int insertReviewReply(SqlSessionTemplate sqlSession, Reply r) {
		return sqlSession.insert("boardMapper.insertReviewReply", r);
	}
	
	
	public int updateReivewReply(SqlSessionTemplate sqlSession, Reply r) {
		return sqlSession.update("boardMapper.updateReivewReply", r);
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
	
}
