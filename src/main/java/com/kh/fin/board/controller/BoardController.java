package com.kh.fin.board.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.fin.board.model.service.BoardService;
import com.kh.fin.board.model.vo.Board;
import com.kh.fin.board.model.vo.Reply;
import com.kh.fin.common.model.vo.PageInfo;
import com.kh.fin.common.template.Pagenation;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	@RequestMapping(value="/chat.bo")
	public String moveChat() {
		return "common/chat";
	}
	
	@RequestMapping(value="/mypage.me")
	public String moveMypage() {
		return "member/myPage";
	}
	@RequestMapping(value="/recommendList.bo")
	public String moveRecommendList() {
		return "board/boardRecommend";
	}
	
	@RequestMapping(value="/profileEdit.me")
	public String moveMyprofile() {
		return "member/profileEdit";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("together.bo")
	public ModelAndView selectTogetherList(@RequestParam(value="cpage", defaultValue="1") int currentPage, ModelAndView mv){
		
		PageInfo pi = Pagenation.getPageInfo(boardService.selectTogetherListCount(), currentPage, 5, 12);
		
		mv.addObject("pi",pi)
			.addObject("list", boardService.selectTogetherList(pi))
			.setViewName("board/boardTogetherNotice");
		
		return mv;
	}
	
	
	@RequestMapping("togetherSearch.bo")
	public ModelAndView searchTogetherList(@RequestParam(value="cpage", defaultValue="1") int currentPage, ModelAndView mv , Board b){
		
		PageInfo pi = Pagenation.getPageInfo(boardService.selectTogetherListCount(), currentPage, 5, 12);
		
		
		mv.addObject("pi",pi)
			.addObject("list", boardService.togetherSearchList(b,pi))
			.setViewName("board/boardTogetherNotice");
		
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("review.bo")
	public ModelAndView selectReviewList(@RequestParam(value="cpage",defaultValue = "1")int currentPage, 
			ModelAndView mv)	{
		
		PageInfo pi = Pagenation.getPageInfo(boardService.selectReviewListCount(), currentPage, 5, 12);
		
		
		mv.addObject("pi", pi)
		.addObject("list", boardService.selectReviewList(pi))
		.setViewName("board/boardReviewNotice");
		
		return mv;
	}
	
	
	@RequestMapping("reviewSearch.bo")
	public ModelAndView searchReviewList(@RequestParam(value="cpage",defaultValue = "1")int currentPage, 
			ModelAndView mv, Board b)	{
		
		PageInfo pi = Pagenation.getPageInfo(boardService.selectReviewListCount(), currentPage, 5, 12);
		
		
		mv.addObject("pi", pi)
		.addObject("list", boardService.searchReviewList(b,pi))
		.setViewName("board/boardReviewNotice");
		
		return mv;
	}
	
	
	@RequestMapping("detail.bo")
	public String detailReview(int bno, HttpSession session) {
		
		
		
			Board b = boardService.selectReviewBoard(bno);
			
			session.setAttribute("b", b);
			
			return "board/boardDetailView";

	
		}
	
	
	
	


	@ResponseBody
	@RequestMapping(value="reviewRlist.bo", produces = "application/json; charset = UTF-8")
	public String ajaxSelectReplyList(int bno) {
		 ArrayList<Reply>list= boardService.selectReply(bno);
		 
		 return new Gson().toJson(list);
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="rinsert.bo")
	public String ajaxInsertReply(Reply r) {

	
		
		
		return  boardService.insertReviewReply(r) > 0 ? "success" :"fail";
	}
	
	
	
	//후기 댓글 수정하기
	@ResponseBody
	@RequestMapping(value="reviewReplyUpdate.bo")
	public String ajaxUpdateReviewReply(Reply r)  {
		int result = boardService.updateReivewReply(r);
		
		System.out.println(result);
		if(result > 0 ) {
			return "success";
		}else {
			return "fail";
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
