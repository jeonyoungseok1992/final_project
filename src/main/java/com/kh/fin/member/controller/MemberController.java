package com.kh.fin.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



import com.kh.fin.member.model.service.MemberService;
import com.kh.fin.member.model.vo.Member;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	//로그인 기능
	@RequestMapping(value="/login.me")
	public ModelAndView loginMember(Member m, ModelAndView mv, HttpSession session) {
		
		Member loginUser = memberService.loginMember(m);
		
		if(loginUser==null || !bcryptPasswordEncoder.matches(m.getMemberPwd(), loginUser.getMemberPwd()) ) {
		mv.addObject("errorMsg","로그인에 실패 하였습니다. 회원 정보를 다시 확인해주세요.");
		mv.setViewName("errorPage/loginErrorPage");
	}else {
		session.setAttribute("loginUser", loginUser);
		mv.setViewName("redirect:/");
	}
		
		return mv;
	}
	
	@RequestMapping(value="/logout.me")
	public ModelAndView logoutMember(ModelAndView mv, HttpSession session) {
		session.removeAttribute("loginUser");
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@RequestMapping("/enrollForm.me")
	public String enrollForm() {
		return "member/enrollForm";
	}
	
	
	@RequestMapping("/insert.me")
	public String insertMember(Member m, HttpSession session, Model model) {
	
		
		//
		String encPwd = bcryptPasswordEncoder.encode(m.getMemberPwd());
		
		m.setMemberPwd(encPwd);
		
		int result= memberService.insertMember(m);
		
		
		
			if(result > 0) {
				session.setAttribute("alertMsg", "성공적으로 회원가입이 완료되었습니다.");
				return "redirect:/";
			} else {
				model.addAttribute("errorMsg", "회원가입 실패");
				return "common/errorPage";
		  }
	    }
	
	
	
	@ResponseBody
	@RequestMapping("/idCheck.me")
	public String idCheck(String checkId) {
	
		return memberService.idCheck(checkId) > 0 ? "NNNNN" : "NNNNY";
		
	}
	
	
	@ResponseBody
	@RequestMapping("/nickNameCheck.me")
	public String nickNameCheck(String checkNickName) {
	
		return memberService.nickNameCheck(checkNickName) > 0 ? "NNNNN" : "NNNNY";
		
	}
	
	

	
}
