package com.kh.fin.board.model.service;

import java.util.ArrayList;

import com.kh.fin.board.model.vo.Board;
import com.kh.fin.common.model.vo.PageInfo;
import com.kh.fin.board.model.vo.Reply;

public interface BoardService {

	

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 같이가요 게시판 리스트업을 위한 카운트 함수
	int selectTogetherListCount();

	// 같이가요 게시글 리스트 조회
	ArrayList<Board> selectTogetherList(PageInfo pi);
	
	//같이가요 키워드 검색
	ArrayList<Board> togetherSearchList(Board b, PageInfo pi);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Review게시글 총 갯수 가져오기
	int selectReviewListCount();
	
	//Review게시글 리스트 조회
	ArrayList<Board> selectReviewList(PageInfo pi);
	
	//Review게시글 키워드로 조회
	ArrayList<Board> searchReviewList(Board b,PageInfo pi);
	
	//Review게시글 상세조회
	Board selectReviewBoard(int boardNo);
	
	
	//댓글리스트 조회
		ArrayList<Reply> selectReply(int bno);
		
	//댓글 작성
	int insertReviewReply(Reply r);
	
	//댓글 업데이트
	int updateReivewReply(Reply r);
	



}
