package com.kh.fin.member.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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

}
