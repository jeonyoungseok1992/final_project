package com.kh.fin.member.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kh.chat.MsgVo;
import com.kh.fin.board.model.vo.Reply;
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
//		ArrayList<Member> frMembers = memberService.friendList(loginUser);
//
//		
//		JsonArray frMemberList = new JsonArray();
//		for (Member mem : frMembers) {		
//			JsonObject frMember = new JsonObject();
//			frMember.addProperty("frMemberNo", mem.getMemberNo());
//			frMemberList.add(frMember);
//		}
			
		
		
		
		if (loginUser == null || !bcryptPasswordEncoder.matches(m.getMemberPwd(), loginUser.getMemberPwd())) {
			mv.addObject("errorMsg", "로그인에 실패 하였습니다. 회원 정보를 다시 확인해주세요.");
			mv.setViewName("errorPage/loginErrorPage");
		} else {
			//System.out.println(frMembers);
			//System.out.println(frMemberList);
			
			session.setAttribute("loginUser", loginUser);
			//session.setAttribute("frMemberList", frMembers);
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
			System.out.println(m.getMemberProfileImg());
			
			if(m.getMemberProfileImg() != null) {
				deletePreviousProfilePic(m.getMemberProfileImg(), session);
			}
			
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
		//1. 암호화된 비밀번호 가져오기
		String encPwd = ((Member)session.getAttribute("loginUser")).getMemberPwd();
		if(bcryptPasswordEncoder.matches(userPwd, encPwd)) {
			// 비밀번호 일치 => 탈퇴가능
			int result = memberService.deleteMember(m);
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

	
	//핸드폰 인증할 때 번호 체크
	@RequestMapping(value = "/phoneCheck", method = RequestMethod.GET)
	@ResponseBody
	public String sendSMS(@RequestParam("phone") String userPhoneNumber) { // 휴대폰 문자보내기
		int randomNumber = (int)((Math.random()* (9999 - 1000 + 1)) + 1000);//난수 생성

		memberService.certifiedPhoneNumber(userPhoneNumber,randomNumber);
		
		return Integer.toString(randomNumber);
	}
	
	//핸드폰 인증할 때 아이디 체크
	@ResponseBody
	@RequestMapping(value="phoneIdCheck.me", produces="application/json; charset=UTF-8")
	public Member phoneIdCheck(Member m) {
		System.out.println(memberService.phoneIdCheck(m));
		return memberService.phoneIdCheck(m);
	}

	
	
	//마이페이지 나의 친구목록 눌렀을 때 친구리스트
	@ResponseBody
	@RequestMapping(value="friendList.me", produces="application/json; charset=UTF-8")
	public ArrayList<Member> friendList(Member m, HttpSession session) {
		
		ArrayList<Member> friendList = memberService.friendList(m);
		session.setAttribute("friendList", friendList);
		return memberService.friendList(m);
	}
	
	//마이페이지 친구요청 목록 리스트
	@ResponseBody
	@RequestMapping(value="friendRequest.me", produces="application/json; charset=UTF-8")
	public ArrayList<Member> friendRequest(Member m) {

		
		return memberService.friendRequest(m);
	}
	
	//마이페이지 친구삭제 눌렀을 때 친구목록 리스트
	@ResponseBody
	@RequestMapping(value="refriendDelete.me", produces="application/json; charset=UTF-8")
	public ArrayList<Member> refriendDelete(Member m, HttpSession session) {
		ArrayList<Member> list = null;
		int mno = ((Member)session.getAttribute("loginUser")).getMemberNo();
		int del = memberService.friendDelete(m);
		if(del > 0) {
			list = memberService.refriendDelete(mno);
		}
		
		return list;
	}
	
	//프로필 회원정보 수정페이지 저장버튼 눌렀을 때
	@RequestMapping("/update.me")
	public String updateMember(Member m, HttpSession session, Model model, String memberId) {
		
		String encPwd = bcryptPasswordEncoder.encode(m.getMemberPwd());
		
		m.setMemberPwd(encPwd);
	
		int result= memberService.updateMember(m);
		System.out.println(m);
			if(result > 0) {
				session.setAttribute("alertMsg", "성공적으로 회원정보가 수정되었습니다.");
				Member loginUser = memberService.reloginMember(memberId);
				session.setAttribute("loginUser", loginUser);
				
				return "redirect:/mypage.me";
			} else {
				model.addAttribute("errorMsg", "회원정보수정 실패");
				return "common/errorPage";
		  }
	    }
	
	//친구 거절버튼 눌렀을 때
	@ResponseBody
	@RequestMapping(value="rejectFriend.me")
	public int rejectFriend(int friendNo, HttpSession session) {
		System.out.println(friendNo);
		Member m = ((Member)session.getAttribute("loginUser"));
		int result = memberService.rejectFriend(m, friendNo);
		System.out.println(result);
		return result > 0 ? m.getMemberNo() : 0;
		
		
	}
	
	
	//친구 수락버튼 눌렀을 때
	@ResponseBody
	@RequestMapping(value="acceptFriend.me")
	public int acceptFriend(int friendNo, HttpSession session) {
		System.out.println(friendNo);
		Member m = ((Member)session.getAttribute("loginUser"));
		int result = memberService.acceptFriend(m, friendNo);
		int result2 = memberService.insertFriend(m, friendNo);
		int result3 = memberService.reverseInsertFriend(m, friendNo);
		System.out.println(result);
		System.out.println(result2);
		return result*result2*result3 > 0 ? m.getMemberNo() : 0;
	}
	
	//친구신청
	@ResponseBody
	@RequestMapping(value="requestFriend.me")
	public String requestFriend(int friendNo, HttpSession session) {
		System.out.println(friendNo);
		Member m = ((Member)session.getAttribute("loginUser"));
		int result = memberService.requestFriend(m, friendNo);
		
		
		if(result > 0 ) {
			return "success";
		}else {
			return "fail";
		}
	}


	
	
	
	
	//프로필 사진 변경 시 이전 사진 삭제
	  public boolean deletePreviousProfilePic(String previousPicPath, HttpSession session) {
		  	String savePath = session.getServletContext().getRealPath(previousPicPath);
		  	System.out.println(savePath);
	        File previousPic = new File(savePath);
	        System.out.println(previousPicPath);
	        if (previousPic.exists()) {
	            if (previousPic.delete()) {
	                System.out.println("이전 프로필 사진 삭제 성공");
	                return true;
	            } else {
	                System.out.println("이전 프로필 사진 삭제 실패");
	                return false;
	            }
	        } else {
	            System.out.println("이전 프로필 사진이 존재하지 않습니다.");
	            return false;
	        }
	    }
	  
	  //채팅페이지 보내주는 메서드
		@RequestMapping("chat.me")
		public String chatPage(HttpSession session, int youNo) {
			int myNo = ((Member)session.getAttribute("loginUser")).getMemberNo();
			Member m = ((Member)session.getAttribute("loginUser"));
			String memberId = ((Member)session.getAttribute("loginUser")).getMemberId();
			session.setAttribute("myNo", myNo);
			session.setAttribute("youNo", youNo);
			session.setAttribute("memberId", youNo);
			return "common/chat";
		}
		
		
		//현재 연결된 채팅방 화면
		@ResponseBody
		@RequestMapping(value="leftChat.ch", produces="application/json; charset=UTF-8")
		public ArrayList<MsgVo> leftChatList(HttpSession session) {
			int myNo = (int)session.getAttribute("myNo");
			int youNo = (int)session.getAttribute("youNo");
			MsgVo vo = new MsgVo();
			vo.setMyNo(myNo);
			vo.setYouNo(youNo);
				

			System.out.println(memberService.leftChatList(vo));
			return memberService.leftChatList(vo);
		}
		
		//채팅방 목록
		@ResponseBody
		@RequestMapping(value="chatList.ch", produces="application/json; charset=UTF-8")
		public ArrayList<MsgVo> chatList(HttpSession session) {
			int myNo = (int)session.getAttribute("myNo");
			int youNo = (int)session.getAttribute("youNo");

			return memberService.chatList(myNo);
		}
	
	
		//채팅리스트에서 클릭 시 연결 채팅방 화면
		@ResponseBody
		@RequestMapping(value="goChat.ch", produces="application/json; charset=UTF-8")
		public ArrayList<MsgVo> goChat(HttpSession session, int youNo) {
			int myNo = (int)session.getAttribute("myNo");
			MsgVo vo = new MsgVo();
			vo.setMyNo(myNo);
			vo.setYouNo(youNo);
				

			System.out.println(memberService.leftChatList(vo));
			return memberService.leftChatList(vo);
		}
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//박수현 회원정보 전체
		@ResponseBody
		@RequestMapping(value="memberInfor.me", produces="application/json; charset=UTF-8")
		public String memberInfor() {
			System.out.println(memberService.memberInfor());
		    return new Gson().toJson(memberService.memberInfor());
		}
		

	
}
