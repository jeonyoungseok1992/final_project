package com.kh.fin.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.fin.board.model.service.BoardService;
import com.kh.fin.common.model.vo.PageInfo;
import com.kh.fin.common.template.Pagenation;
import com.kh.fin.member.model.vo.Member;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	@RequestMapping(value="/review.bo")
	public String moveReview() {
		return "board/boardReviewNotice";
	}
	
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//마이페이지 같이 가요 리스트업
	@ResponseBody
	@RequestMapping(value="myTogetherList.bo", produces="application/json; charset=UTF-8")
	public String myTogetherList(@RequestParam(value="cpage", defaultValue="1") int currentPage, ModelAndView mv, Member m){

		PageInfo pi = Pagenation.getPageInfo(boardService.myTogetherListCount(m), currentPage, 10, 5);

		mv.addObject("pi",pi)
			.addObject("list", boardService.myTogetherList(pi, m));

		return new Gson().toJson(boardService.myTogetherList(pi, m));
	}
	
	//마이페이지 후기 리스트업
	@ResponseBody
	@RequestMapping(value="myReviewList.bo", produces="application/json; charset=UTF-8")
	public String myReviewList(@RequestParam(value="cpage", defaultValue="1") int currentPage, ModelAndView mv, Member m){

		PageInfo pi = Pagenation.getPageInfo(boardService.myReviewListCount(m), currentPage, 10, 5);

		mv.addObject("pi",pi)
			.addObject("list", boardService.myReviewList(pi, m));

		return new Gson().toJson(boardService.myReviewList(pi, m));
	}
	
	
}
