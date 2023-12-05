package com.kh.fin.board.model.service;

import java.util.ArrayList;

import com.kh.fin.board.model.vo.Board;
import com.kh.fin.common.model.vo.PageInfo;

public interface BoardService {

	//게시글 상세조회
	Board selectBoard(int boardNo);
	
	//게시글 조회수증가(update)
	int increaseCount(int boardNo);
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 같이가요 게시판 리스트업을 위한 카운트 함수
	int selectTogetherListCount();

	// 같이가요 게시글 리스트 조회
	ArrayList<Board> selectTogetherList(PageInfo pi);
}
