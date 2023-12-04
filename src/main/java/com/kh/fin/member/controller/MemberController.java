package com.kh.fin.member.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.fin.member.model.service.MemberService;
import com.kh.fin.member.model.vo.Mail;
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
	
	@RequestMapping(value="/enrollForm.me")
	public String enrollForm() {
		return "member/enrollForm";
	}
//	@RequestMapping(value="/insert.me")
//	public String insertMember(Member m, HttpSession session, Model model) {//인코딩 설정은 web.xml에서 해준다
////		System.out.println(m);
//		//1. 한글깨짐문제발생 ->> web.xml에 스프링에서 제공하는 인코딩 필터 등록
//		//2. 나이를 입력하지 않을 경우 int 자료형에 빈 문자열이 넘어와 자료형이 맞지 않는 문제발생
//		// (400 Bad Request Error 발생)
//		// Member 클래스의 age필드 자료형을 int => String 으로 변경
//		// 3.비밀번호가 사용자가 입력한 있는 그대로의 평문
//		// Bcrypt방식을 이용해서 암호화를 한 후 저장을 하겠다
//		// => 스프링 시큐리팅 모듈에서 제공<pom.xml> 라이브러리 추가 
//		
//		//암호화 작업
//		String encPwd = bcryptPasswordEncoder.encode(m.getMemberPwd());
////		System.out.println("암호문: "+encPwd);
//		
//		m.setMemberPwd(encPwd);//Member객체에 userPwd필드에 평문이 아닌 암호문으로 변경
//		
//		int result = memberService.insertMember(m);
//		
//		if(result > 0) {
//			session.setAttribute("alertMsg", "성공적으로 회원가입이 완료되었습니다.");
//			return "redirect:/";
//		}else {
//			model.addAttribute("errorMsg", "회원가입실패");
//			return "common/errorPage";
//		}
//		
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Autowired
    private JavaMailSender sender;
	
	
	// 일단 아이디 이메일 유효한지 찾아
	@ResponseBody
	@RequestMapping(value="/emailDuplication")
	public String emailDuplication(Member m){
		
		int result = memberService.idEmailCheck(m);
		if(result > 0 ) {
			return "success";
		}else {
			return "fail";
		}
		
	}

    //랜덤함수로 임시비밀번호 구문 만들기
    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }
    
    @RequestMapping("sendEmail")
	public ModelAndView hyperMail(Member m,ModelAndView mv) throws MessagingException {
    	String temPwd = getTempPassword();
    	
    	String encPwd = bcryptPasswordEncoder.encode(temPwd);
    	
    	m.setMemberPwd(encPwd);
    	
    	int result = memberService.setTemPwd(m);
    	
    	if(result > 0 ) {
    		MimeMessage message = sender.createMimeMessage();
    			
    		MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
    			
			String[] to = {m.getMemberEmail()};
			
			helper.setTo(to);
			
			helper.setSubject("[mapping] 임시 비밀번호 전송");
			helper.setText("임시비밀번호는 "+ temPwd+ " 입니다. 로그인 후 비밀번호를 변경하여 이용해주세요.");
			
			sender.send(message);
			
			mv.setViewName("redirect:/");
		
		}else {
			mv.setViewName("errorPage/500page");
			
		}
    	
    	return mv;
   
	
}
	
	
	
	
	
	
	
	
	
	
	
	
}
