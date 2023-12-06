package com.kh.fin.board.model.service;

import java.util.ArrayList;

import com.kh.fin.board.model.vo.Board;
import com.kh.fin.board.model.vo.Plan;
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
	
	//같이가요 키워드 검색
	ArrayList<Board> togetherSearchList(Board b, PageInfo pi);
	
	//글쓰기 누르면 ajax호출해서 나의 일정리스트 가져오기
	ArrayList<Plan> selectPlanList(int memberNo);
}
