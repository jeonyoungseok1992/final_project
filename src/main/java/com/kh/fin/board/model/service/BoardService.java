package com.kh.fin.board.model.service;

import java.util.ArrayList;

import com.kh.fin.board.model.vo.Board;
import com.kh.fin.board.model.vo.Plan;
import com.kh.fin.board.model.vo.Region;
import com.kh.fin.board.model.vo.Reply;
import com.kh.fin.common.model.vo.PageInfo;

import com.kh.fin.member.model.vo.Member;

public interface BoardService {

	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 같이가요 게시판 리스트업을 위한 카운트 함수
	int selectTogetherListCount();

	// 같이가요 게시글 리스트 조회
	ArrayList<Board> selectTogetherList(PageInfo pi);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 마이페이지 같이가요 리스트업을 위한 카운트 함수
	int myTogetherListCount(Member m);

	// 마이페이지 같이가요 게시글 리스트 조회
	ArrayList<Board> myTogetherList(PageInfo pi, Member m);
	
	
	// 마이페이지 후기 리스트업을 위한 카운트 함수
	int myReviewListCount(Member m);

	// 마이페이지 후기 게시글 리스트 조회
	ArrayList<Board> myReviewList(PageInfo pi, Member m);

	//같이가요 키워드 검색
	ArrayList<Board> togetherSearchList(Board b, PageInfo pi);

	

	//글쓰기 누르면 ajax호출해서 나의 일정리스트 가져오기
	ArrayList<Plan> selectPlanList(int memberNo);
	
	//같이가요 게시판 글작성
	int insertTogetherBoard(Board b);
	
	//같이가요 게시판 디테일 페이지
	ArrayList<Board> selectTogetherBoard(int boardNo);

	// plan max day가져오기
	int countMaxPlanDay(int tripPlanNo);
	
	//같이가요 게시판 글 수정
	int updateTogetherBoard(Board b);
	
	//같이가요 게시판 글 삭제
	int togetherDeleteBoard(int boardNo);
	
	//같이가요 게시판 댓글 부르기
	ArrayList<Reply> selectTogetherReplyList(int boardNo);
	
	//같이가요 게시판 댓글 추가
	int ajaxInsertTogetherReply(Reply r);
	
	//같이가요 게시판 댓글 삭제
	int ajaxDeleteTogetherReply(Reply r);
	
	//같이가요 게시판 댓글 수정
	int ajaxUpdateTogetherReply(Reply r);
	
	//같이가요 게시판 대댓글 작성
	int ajaxInsertTogetherReReply(Reply r);
	
	//메인페이지 지역 ajax
	ArrayList<Region> ajaxselectRegionList();
	
	//같이가요 게시판 하나 가져오는 함수
	Board togetherSelectBoardOne(int boardNo);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Review게시글 총 갯수 가져오기
	int selectReviewListCount();
	
	//Review게시글 리스트 조회
	ArrayList<Board> selectReviewList(PageInfo pi);
	
	//Review게시글 키워드로 조회
	ArrayList<Board> searchReviewList(Board b,PageInfo pi);
	
	//Review게시글 상세조회
	ArrayList<Board> selectReviewBoard(int boardNo);
	
	
	//댓글리스트 조회
		ArrayList<Reply> selectReviewReplyList(int boardNo);
		
	//댓글 작성
	int ajaxInsertReply(Reply r);
	
	//후기 게시판 글작성
	int insertReviewBoard(Board b);
	
	// 기존 일정 가지고 글쓰기 페이지로 가기
	ArrayList<Plan> selectOneTripPlanRe(int tripPlanNo);
	
	//후기 게시글 삭제
	int reviewDeleteBoard(int boardNo);
	
	//후기게시글 수정
	int updateReviewBoard(Board b);
	
	// 기존 일정 가지고 글쓰기 페이지로 가기
	ArrayList<Plan> selectOneTripPlan(int tripPlanNo);
	

	//후기  글쓰기 누르면 ajax호출해서 나의 일정리스트 가져오기 
	ArrayList<Plan> selectPlanListRe(int memberNo);
	
	//후기 게시판 댓글 삭제
	int ajaxDeleteReviewReply(Reply r);
	
	//후기 게시판 댓글 수정
	int ajaxUpdateReviewReply(Reply r);
	
	
	
	
	
	
	



	// 메인페이지 일정만들기 시작
	ArrayList<LocationInfomation> makePlan(HashMap<String,Object> map);

}
