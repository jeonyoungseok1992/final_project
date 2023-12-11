package com.kh.fin.board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.fin.board.model.vo.Board;
import com.kh.fin.board.model.vo.Plan;
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int selectReviewListCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("boardMapper.selectReviewListCount");
	}
	
	
	public ArrayList<Board> selectReviewList(SqlSessionTemplate sqlSession, PageInfo pi){
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectReviewList", null, rowBounds);
	}
	
	
	
	public Board selectListBoard(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.selectOne("boardMapper.selectListBoard", boardNo);
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
