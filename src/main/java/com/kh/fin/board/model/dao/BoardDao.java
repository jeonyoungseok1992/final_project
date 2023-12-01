package com.kh.fin.board.model.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.kh.fin.board.model.vo.Board;

public class BoardDao {

	public Board selectBoard(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.selectOne("boardMapper.selectBoard", boardNo);
	}
	
	public int increaseCount(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.update("boardMapper.increaseCount", boardNo);
	}
}
