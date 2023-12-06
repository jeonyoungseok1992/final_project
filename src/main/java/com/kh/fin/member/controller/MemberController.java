package com.kh.fin.member.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;

import com.kh.fin.member.model.service.MemberService;
import com.kh.fin.member.model.vo.Member;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

	// 로그인 기능
	@RequestMapping(value = "/login.me")
	public ModelAndView loginMember(Member m, ModelAndView mv, HttpSession session) {

		Member loginUser = memberService.loginMember(m);

		if (loginUser == null || !bcryptPasswordEncoder.matches(m.getMemberPwd(), loginUser.getMemberPwd())) {
			mv.addObject("errorMsg", "로그인에 실패 하였습니다. 회원 정보를 다시 확인해주세요.");
			mv.setViewName("errorPage/loginErrorPage");
		} else {
			session.setAttribute("loginUser", loginUser);
			mv.setViewName("redirect:/");
		}

		return mv;
	}

	@RequestMapping(value = "/logout.me")
	public ModelAndView logoutMember(ModelAndView mv, HttpSession session) {
		session.removeAttribute("loginUser");
		mv.setViewName("redirect:/");
		return mv;
	}


	@RequestMapping(value = "/enrollForm.me")
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
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 프로필 이미지 변경
	@ResponseBody
	@RequestMapping(value = "/updateImg.me")
	public Member updateProfileImg(Member m, HttpSession session, MultipartFile upfile, ModelAndView mv) throws IllegalStateException, IOException, ServletException {

//		int maxSize = 10 * 1024 * 1024;
//
//		// 1_2)전달된 파일을 저장시킬 폴더의 경로 알아내기
//		String savePath = request.getSession().getServletContext().getRealPath("/resources/member_upfile/");
//
//		MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());


		if (upfile != null && !upfile.getOriginalFilename().equals("")) {
			String changeName = saveFile(upfile, session);
			m.setMemberProfileImg("resources/member_upfile/" + changeName);
		}
		Member updateMember = memberService.updateProfileImg(m);
		session.setAttribute("loginUser", updateMember);
		
		return updateMember;

	}
//프로필 사진 변경할 때 사진이름 바꿔주는 메서드
	public String saveFile(MultipartFile upfile, HttpSession session) throws IllegalStateException, IOException {
		// 파일명 수정 후 서버 업로드시키기("123.PNG" => 2023109102712345.jpg)
		// 년월일시분초 + 랜덤숫자 5개 + 확장자

		// 원래 파일명
		String originName = upfile.getOriginalFilename();

		// 시간정보 (년월일시분초)
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		// 랜덤숫자 5자리
		int ranNum = (int) (Math.random() * 90000) + 10000;

		// 확장자
		String ext = originName.substring(originName.lastIndexOf("."));

		// 변경된 이름
		String changeName = currentTime + ranNum + ext;

		// 첨부파일 저장할 폴더의 물리적인 경우
		String savePath = session.getServletContext().getRealPath("/resources/member_upfile/");

		upfile.transferTo(new File(savePath + changeName));

		return changeName;
	}
	
//회원탈퇴
	@RequestMapping("/delete.me")
	public String deleteMember(Member m, HttpSession session, String userPwd) {
		System.out.println(111);
		//1. 암호화된 비밀번호 가져오기
		String encPwd = ((Member)session.getAttribute("loginUser")).getMemberPwd();
		System.out.println(encPwd);
		System.out.println(m.getMemberPwd());
		System.out.println(userPwd);
		if(bcryptPasswordEncoder.matches(userPwd, encPwd)) {
			// 비밀번호 일치 => 탈퇴가능
			System.out.println(222);
			int result = memberService.deleteMember(m);
			System.out.println(result);
			if(result > 0) { // 탈퇴처리 성공
				session.removeAttribute("loginUser");
				session.setAttribute("alertMsg", "탈퇴가 성공적으로 이루어졌습니다.");
				
				return "redirect:/";
			} else { // 탈퇴처리 실패
				session.setAttribute("alertMsg", "탈퇴처리 실패");
				return "redirect:/mypage.me";
			}
			
		} else {
			// 비밀번호 불일치 => 탈퇴불가
			session.setAttribute("alertMsg", "비밀번호를 다시 확인해주세요");
			return "redirect:/mypage.me";
		}
	}

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
