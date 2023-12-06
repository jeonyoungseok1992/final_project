package com.kh.fin.board.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.fin.board.model.service.BoardService;
import com.kh.fin.board.model.vo.Board;
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
	
	
	
	
	
	
	
}
