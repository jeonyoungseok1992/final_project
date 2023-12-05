package com.kh.fin.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.fin.board.model.vo.Board;
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int selectTogetherListCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("boardMapper.selectTogetherListCount");
	}
	public ArrayList<Board> selectTogetherList(SqlSessionTemplate sqlSession, PageInfo pi){
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		int limit= pi.getBoardLimit();
		
		RowBounds rowBounds = new RowBounds(offset,limit);
		return (ArrayList)sqlSession.selectList("boardMapper.selectTogetherList",null,rowBounds);
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
	
	
	
}
