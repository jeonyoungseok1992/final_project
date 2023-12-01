package com.kh.fin.board.model.service;

import com.kh.fin.board.model.vo.Board;

public interface BoardService {


	//게시글상세조회
	Board selectBoard(int boardNo);
	
	//게시글 조회수 증가(update)
	int increaseCount(int boardNo);
	
		
	

}
