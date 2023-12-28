package com.kh.fin.board.controller;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kh.fin.board.model.service.BoardService;
import com.kh.fin.board.model.vo.Board;
import com.kh.fin.board.model.vo.Good;
import com.kh.fin.board.model.vo.Plan;
import com.kh.fin.board.model.vo.Recommend;
import com.kh.fin.board.model.vo.Region;
import com.kh.fin.board.model.vo.Reply;
import com.kh.fin.board.model.vo.Report;
import com.kh.fin.board.model.vo.ScheduleDTO;
import com.kh.fin.board.model.vo.Star;
import com.kh.fin.board.model.vo.attractionDTO;
import com.kh.fin.board.model.vo.hotelDTO;
import com.kh.fin.common.model.vo.PageInfo;
import com.kh.fin.common.template.Pagenation;
import com.kh.fin.member.model.service.MemberService;
import com.kh.fin.member.model.vo.Member;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private MemberService memberService;
	
	
	@RequestMapping(value="/chat.bo")
	public String moveChat() {
		return "common/chat";
	}
	
	@RequestMapping(value="/mypage.me")
	public String moveMypage() {
		return "member/myPage";
	}
	@RequestMapping(value="/recommendMore.bo")
	public String moveRecommendList() {
		return "board/boardRecommend";
	}
	
	@RequestMapping(value="/profileEdit.me")
	public String moveMyprofile() {
		return "member/profileEdit";
	}
	
	//메인페이지에서 지역하나 선택하면 그 지역정보가지고 플랜짜는 페이지로 이동
	@RequestMapping(value="/goPlan.bo")
	public ModelAndView moveMakePlan(@RequestParam(value="regionNo")int regionNo,ModelAndView mv) {
		mv.addObject("region", new Gson().toJson(boardService.selectOneRegion(regionNo)))
		.setViewName("board/boardScheduleMake2");
		
		return mv;
	}
	
	//게시판 신고기능
	@ResponseBody
	@RequestMapping(value="report.bo")
	public String reportContents(Report r) {
		int result = boardService.reportContents(r);
		
		if(result > 0 ) {
			return "success";
		}else {
			return "fail";
		}
	}

	
	
	
	
	
	//모든 일정 저장해주고 짜여진 일정 넣어주기
	@ResponseBody
	@RequestMapping(value="/totalScheduleMake.bo")
	public String totalScheduleMake(@RequestBody ScheduleDTO schedule) {
		System.out.println(schedule);
	
		if(schedule.getTransportation().equals("대중교통")) {
			schedule.setTransportationNo(1);
		}else if(schedule.getTransportation().equals("승용차")){
			schedule.setTransportationNo(2);
		}
		
		// 문자열을 Instant으로 변환
        Instant e = Instant.parse(schedule.getEndDate());
        Instant s = Instant.parse(schedule.getStartDate());

        // Instant을 LocalDate로 변환 (시간 부분을 제외)
        LocalDate en = e.atZone(ZoneId.of("UTC")).toLocalDate();
        LocalDate st = s.atZone(ZoneId.of("UTC")).toLocalDate();

        // LocalDate를 다른 형식으로 출력
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String endDate = en.format(outputFormatter);
        String startDate = st.format(outputFormatter);

        // 문자열을 LocalDate로 변환
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        
        // 두 날짜 간의 날짜 목록 생성
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate currentDate = start;

        while (!currentDate.isAfter(end)) {
            dateList.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }
		
		List<attractionDTO> attractionDTOList = new ArrayList<>();
		//날짜 가공해야하는부분
		
			for (attractionDTO attraction : schedule.getPlaceInfo()) {
				// attractionDTO 객체 생성
			    attractionDTO newAttraction = new attractionDTO();
				if(attraction.getCategory().equals("추천 숙소")) {
					newAttraction.setAttractionCategoryNo(4);;
				}else if(attraction.getCategory().equals("행사")){
					newAttraction.setAttractionCategoryNo(3);
				}else if(attraction.getCategory().equals("식당")){
					newAttraction.setAttractionCategoryNo(2);
				}else {
					newAttraction.setAttractionCategoryNo(1);
				}
				//n일차 넣어주는곳
				for (int i = 0; i < dateList.size(); i++) {
					Instant ins = Instant.parse(attraction.getDate());
					LocalDate inss = ins.atZone(ZoneId.of("UTC")).toLocalDate();
					String thisDate = inss.format(outputFormatter);
					LocalDate date = dateList.get(i);
					if(outputFormatter.format(date).equals(thisDate)) {
						newAttraction.setTripNday(i+1);
					}
				}

		   
			    newAttraction.setRegionNo(schedule.getRegionNo());
			    newAttraction.setSrc(attraction.getSrc());
			    newAttraction.setTitle(attraction.getTitle());
			    newAttraction.setDate(attraction.getDate());
			    newAttraction.setMapX(attraction.getMapX());
			    newAttraction.setMapY(attraction.getMapY());
			    
			    // 생성된 attractionDTO를 리스트에 추가
			    attractionDTOList.add(newAttraction);
			}
	
		
		List<hotelDTO> hotelDTOlist = new ArrayList<>();
			for (hotelDTO hotel : schedule.getLodgingInfo()) {
				// attractionDTO 객체 생성
				hotelDTO newHotel = new hotelDTO();
				if(hotel.getCategory().equals("추천 숙소")) {
					newHotel.setAttractionCategoryNo(4);
				}else if(hotel.getCategory().equals("행사")){
					newHotel.setAttractionCategoryNo(3);
				}else if(hotel.getCategory().equals("식당")){
					newHotel.setAttractionCategoryNo(2);
				}else {
					newHotel.setAttractionCategoryNo(1);
				}
				
				//n일차 넣어주는곳
				for (int i = 0; i < dateList.size(); i++) {
					Instant ins = Instant.parse(hotel.getDate());
					LocalDate inss = ins.atZone(ZoneId.of("UTC")).toLocalDate();
					String thisDate = inss.format(outputFormatter);
					LocalDate date2 = dateList.get(i);
					if(outputFormatter.format(date2).equals(thisDate)) {
						newHotel.setTripNday(i+1);
					}
				}
	
			   
				newHotel.setRegionNo(schedule.getRegionNo());
				newHotel.setSrc(hotel.getSrc());
				newHotel.setTitle(hotel.getTitle());
				newHotel.setDate(hotel.getDate());
				newHotel.setMapX(hotel.getMapX());
				newHotel.setMapY(hotel.getMapY());
	
	
			    // 생성된 attractionDTO를 리스트에 추가
				hotelDTOlist.add(newHotel);
			}
			//가공한 모든정보 한번에 insert 
			int result = boardService.insertTotalScheduleMake(schedule,attractionDTOList, hotelDTOlist);
		
		if(result >0) {
			return  Integer.toString(schedule.getTripPlanNo());
		}
		else { 
		
			return "fail";
		}
	}
	
	//내가 짠 최종일정 보여주는 페이지
	@RequestMapping("finalPlan.bo")
	public ModelAndView selectFinalSchedule(int tripPlanNo, ModelAndView mv){
		
		mv.addObject("list",boardService.selectOneTripPlan(tripPlanNo))
		.addObject("maxNday",boardService.countMaxPlanDay(tripPlanNo))
		.setViewName("board/boardScheduleView");
		
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//같이가요게시판 처음들어가는 메서드, 목록 가져와서보여주기
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
	public JSONObject myTogetherList(@RequestParam(value="cpage", defaultValue="1") int currentPage, ModelAndView mv, Member m){

		PageInfo pi = Pagenation.getPageInfo(boardService.myTogetherListCount(m), currentPage, 10, 5);

		JSONObject obb = new JSONObject();
		obb.put("pi",pi);
		obb.put("list",boardService.myTogetherList(pi, m));
		System.out.println(boardService.myTogetherList(pi, m));


		return obb;
	}
	
	//마이페이지 후기 리스트업
	@ResponseBody
	@RequestMapping(value="myReviewList.bo", produces="application/json; charset=UTF-8")
	public JSONObject myReviewList(@RequestParam(value="cpage", defaultValue="1") int currentPage, ModelAndView mv, Member m){

		PageInfo pi = Pagenation.getPageInfo(boardService.myReviewListCount(m), currentPage, 10, 5);

		JSONObject obj = new JSONObject();
		obj.put("pi",pi);
		obj.put("list",boardService.myReviewList(pi, m));


		return obj;
	}
	
	

	@RequestMapping("togetherSearch.bo")
	public ModelAndView searchTogetherList(@RequestParam(value="cpage", defaultValue="1") int currentPage, ModelAndView mv , Board b){
		System.out.println(b);
		PageInfo pi = Pagenation.getPageInfo(boardService.selectTogetherListCount(), currentPage, 5, 12);
		
		
		mv.addObject("pi",pi)
			.addObject("list", boardService.togetherSearchList(b,pi))
			.setViewName("board/boardTogetherNotice");
		
		return mv;
	}
	


	@ResponseBody
	@RequestMapping(value="scheduleList.bo", produces="application/json; charset=utf-8")
	public String selectReplyList(int memberNo) {
		ArrayList<Plan> list = boardService.selectPlanList(memberNo);
		
		return new Gson().toJson(list);
	}
	@RequestMapping("togetherEdit.bo")
	public ModelAndView moveTogetherEnrollForm(@RequestParam(value="ppage") int tripPlanNo, ModelAndView mv){

		
		mv.addObject("list", boardService.selectOneTripPlan(tripPlanNo))
		.addObject("maxNday",boardService.countMaxPlanDay(tripPlanNo))
		.setViewName("board/togetherEnrollForm");
		
		return mv;
	}
	//서머노트 작성시 이미지파일 올렸을때 내 실제 경로 폴더에도 올려주는 메서드
	@RequestMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpSession session )  {
		String cName = saveFile(multipartFile, session);
		String changeName = "resources/uploadFiles/" + cName;
       
		return new Gson().toJson(changeName);
	}
	// 서머노트 작성시 이미지파일 삭제했을때 내 실제 경로 폴더에도 삭제하는 메서드 
	@RequestMapping(value="/deleteSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String deleteSummernoteImageFile(@RequestParam("file") String file, HttpSession session )  {
	
		new File(session.getServletContext().getRealPath("resources/uploadFiles/"+file)).delete();
		
		return "yes!";
	}
	
	
	public String saveFile(MultipartFile upfile, HttpSession session) {
		//파일명 수정 후 서버 업로드 시키기(기존파일명 -> 202311091027+5자리랜덤숫자+파일형식)
		//년월일시분초 + 랜덤숫자 5개 + 확장자 
		
		//원래파일명
		String originName = upfile.getOriginalFilename();
		
		//시간정보(년월일시분초)
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		//랜덤숫자 5자리
		int ranNum = (int)(Math.random()* 90000) + 10000;// 10000부터 99999까지
		
		//확장자
		String ext = originName.substring(originName.lastIndexOf("."));
		
		//변경된 이름
		String changeName = currentTime + ranNum + ext;
		
		//첨부파일 저장할 폴더의 물리적인 경로
		String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");
		
		try {
			upfile.transferTo(new File(savePath + changeName));//업로드 파일의 정보를 변경해서넣어줘 
		} catch (IllegalStateException | IOException e) {
			
			e.printStackTrace();
		}
		return changeName;
	}
	
	//같이가요 게시판 글추가
	@RequestMapping("togetherInsert.bo")
	public String insertTogetherBoard(Board b, HttpSession session, Model model) {
		int result = boardService.insertTogetherBoard(b);
		if(result > 0) { //성공 => 같이가요 리스트 페이지 재요청
			session.setAttribute("alertMsg", "같이가요 게시글 작성 완료");
			return "redirect:together.bo";
		}else {
			model.addAttribute("errorMsg", "게시글 작성 실패");
			return "errorPage/500page";
		}
		
	}
	
	//같이가요 디테일페이지 불러오기 
	@RequestMapping("togetherDetail.bo")
	public String selectTogetherBoard(int boardNo, Model model, HttpSession session){
		
		ArrayList<Board> list = boardService.selectTogetherBoard(boardNo);
		Member m = ((Member)session.getAttribute("loginUser"));
		Member mem = memberService.pageFriend(boardNo, m);
		
		//총 좋아요 개수 가져오기
		int count = boardService.selectGoodCount(boardNo);
		//내가 좋아요 누른지 여부 가져오기
		Good g = boardService.selectGood(m.getMemberNo(),boardNo );
		//Member frMember = memberService.requestFriendList(boardNo, m);
		if(!(list == null) ) {
			
			model.addAttribute("list", list);
			model.addAttribute("friend",mem);
			model.addAttribute("count",count);
			model.addAttribute("Good",g);
			
			//model.addAttribute("frMember", frMember);
			
			return "board/boardTogetherDetailView";
		}else {
			model.addAttribute("errorMsg", "같이가요 게시글 조회 실패");
			return "errorPage/500page";
		}
	}
	
	@RequestMapping("updateTogetherForm.bo")
	public String updateTogetherForm(int boardNo, Model model ) {
		//현재 내가 수정하기를 클릭한 같이가요 게시글에 대한 정보를 가지고 이동
		model.addAttribute("list",boardService.selectTogetherBoard(boardNo));
		
		return "board/togetherUpdateForm";
	}
	//같이가요 게시판 글 수정
	@RequestMapping("togetherUpdate.bo")
	public String updateTogetherBoard(Board b, HttpSession session, Model model) {
		int result = boardService.updateTogetherBoard(b);
		//b객체 update
		
		//성공유무 확인 후 페이지 리턴
		if(result >0) {
			session.setAttribute("alertMsg", "게시글 수정 완료");
			return "redirect:togetherDetail.bo?boardNo="+b.getBoardNo();
		}else {
			model.addAttribute("errorMsg", "게시글 수정 실패"); // model은 리퀘스트 영역이라 재요청 방식으로는 데이터를 담아 보내줄 수 없다.
			return "errorPage/500page";
		}
		
	}
	//같이가요 게시판 글 삭제
	@RequestMapping("togetherDelete.bo")
	public String togetherDeleteBoard(int boardNo, HttpSession session, Model model) {
		Board b = boardService.togetherSelectBoardOne(boardNo);
		//boardNo 으로 조회된 이미지 패스 삭제 
		List<String> imgPaths = extractImgPathFromContent(b.getBoardContent());
		//파일삭제
		
		for(String imgPath : imgPaths) {
			new File(session.getServletContext().getRealPath(imgPath)).delete();
		}
		
		int result = boardService.togetherDeleteBoard(boardNo);
		
		if(result > 0) {//삭제 성공
			
			session.setAttribute("alertMsg", "게시글 삭제에 성공하였습니다.");
			return "redirect:together.bo";
			
		}else {
			model.addAttribute("errorMsg", "게시글 삭제 실패");
			return "errorPage/500page";
		}
		
		
	}
	//일정 수정 버튼 눌렀을때 다시 그려주는
	@ResponseBody
	@RequestMapping(value="reDraw.bo", produces="application/json; charset=utf-8")
	public String ajaxReDrawSchedule(@RequestParam(value="tripPlanNo") int tripPlanNo) {
		ArrayList<Plan> list = boardService.selectOneTripPlan(tripPlanNo);
		
		return new Gson().toJson(list);
	}
	
	// 같이가요 댓글 그려주기 
	@ResponseBody
	@RequestMapping(value="togetherRlist.bo", produces="application/json; charset=utf-8")
	public String selectTogetherReplyList(@RequestParam(value="boardNo") int boardNo) {
		ArrayList<Reply> rlist = boardService.selectTogetherReplyList(boardNo);
		
		JsonArray replyList = new JsonArray();
		for (Reply r : rlist) {
			if(r.getReplyGroup() != 0)
				continue;
			
			JsonObject newReply = new JsonObject();
			newReply.addProperty("replyNo", r.getReplyNo());
			newReply.addProperty("memberProfileImg", r.getMemberProfileImg());
			newReply.addProperty("replyWriter", r.getReplyWriter());
			newReply.addProperty("replyModifyDate", r.getReplyModifyDate());
			newReply.addProperty("replyContent", r.getReplyContent());
			
			
			JsonArray replyReList2 = new JsonArray();
			for (Reply tmpR : rlist) {
				if(r.getReplyNo() == tmpR.getReplyGroup()) {
					JsonObject RReply = new JsonObject();
					RReply.addProperty("replyNo", tmpR.getReplyNo());
					RReply.addProperty("memberProfileImg", tmpR.getMemberProfileImg());
					RReply.addProperty("replyWriter", tmpR.getReplyWriter());
					RReply.addProperty("replyModifyDate", tmpR.getReplyModifyDate());
					RReply.addProperty("replyContent", tmpR.getReplyContent());
					RReply.addProperty("replyGroup", tmpR.getReplyGroup());
					
					replyReList2.add(RReply);
				}
				
			}
			newReply.add("rlist", replyReList2);
			
			replyList.add(newReply);
		}
		
		
		
		
		return new Gson().toJson(replyList);
	}
	//같이가요 댓글 넣어주기
	@ResponseBody
	@RequestMapping(value="togetherRinsert.bo")
	public String ajaxInsertTogetherReply(Reply r) {
		int result = boardService.ajaxInsertTogetherReply(r);
		
		if(result > 0 ) {
			return "success";
		}else {
			return "fail";
		}
		
	}
	
	//같이가요 댓글 삭제하기
	@ResponseBody
	@RequestMapping(value="togetherReplyDelete.bo")
	public String ajaxDeleteTogetherReply(Reply r)  {
		int result = boardService.ajaxDeleteTogetherReply(r);
		
		if(result > 0 ) {
			return "success";
		}else {
			return "fail";
		}
		
	}
	
	//같이가요 댓글 수정하기
		@ResponseBody
		@RequestMapping(value="togetherReplyUpdate.bo")
		public String ajaxUpdateTogetherReply(Reply r)  {
			int result = boardService.ajaxUpdateTogetherReply(r);
			
			if(result > 0 ) {
				return "success";
			}else {
				return "fail";
			}
			
		}
		//같이가요 대댓글 넣어주기
		@ResponseBody
		@RequestMapping(value="togetherRRinsert.bo")
		public String ajaxInsertTogetherReReply(Reply r) {
			int result = boardService.ajaxInsertTogetherReReply(r);
			
			if(result > 0 ) {
				return "success";
			}else {
				return "fail";
			}
			
		}
		
		//mainPage 로드시 지역들 그려주는 ajax
		@ResponseBody
		@RequestMapping(value="regionList.bo", produces = "application/json; charset = UTF-8")
		public String ajaxselectRegionList() {
			ArrayList<Region> list = boardService.ajaxselectRegionList();
			
			return new Gson().toJson(list);
		}
		//서머노트 내용중 이미지추출하는 메서드
		private List<String> extractImgPathFromContent(String sContent){
			List<String> imgPaths = new ArrayList<>();
			
			//이미지 경로추출
			int startIndex = sContent.indexOf("<img");
			
			while(startIndex != -1) {
				int srcIndex = sContent.indexOf("src=", startIndex);
				int startQuoteIndex = sContent.indexOf("\"", srcIndex); //resources 시작위치
				int endQuoteIndex = sContent.indexOf("\"", startQuoteIndex +1); 
				String imgPath = sContent.substring(startQuoteIndex +1 , endQuoteIndex); // resources이후 "/ 부터" 
				imgPaths.add(imgPath);
				startIndex = sContent.indexOf("<img", endQuoteIndex);
			}
			return imgPaths;
			
		}
	
	
	
	
		
		
	
	
		
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//review게시판 
	@RequestMapping("review.bo")
	public ModelAndView selectReviewList(@RequestParam(value="cpage",defaultValue = "1")int currentPage, 
			ModelAndView mv)	{
		
		PageInfo pi = Pagenation.getPageInfo(boardService.selectReviewListCount(), currentPage, 5, 12);
		
		
		mv.addObject("pi", pi)
		.addObject("list", boardService.selectReviewList(pi))
		.setViewName("board/boardReviewNotice");
		
		return mv;

	}
	
	//review게시글 검색
	@RequestMapping("reviewSearch.bo")
	public ModelAndView searchReviewList(@RequestParam(value="cpage",defaultValue = "1")int currentPage, 
			ModelAndView mv, Board b)	{
		
		PageInfo pi = Pagenation.getPageInfo(boardService.selectReviewListCount(), currentPage, 5, 12);
		
		
		mv.addObject("pi", pi)
		.addObject("list", boardService.searchReviewList(b,pi))
		.setViewName("board/boardReviewNotice");
		
		return mv;
	}
	
	
	
	
	//reviewDetail
	@RequestMapping("detail.bo")
	public String selectReviewBoard(int boardNo, Model model, HttpSession session){
	    System.out.println(boardNo);
		Member m = ((Member)session.getAttribute("loginUser"));
		ArrayList<Board> list = boardService.selectReviewBoard(boardNo);
		
		//총 좋아요 개수 가져오기
		int count = boardService.selectGoodCount(boardNo);
		//내가 좋아요 누른지 여부 가져오기
		Good g = boardService.selectGood(m.getMemberNo(),boardNo );
		
		Member mem = memberService.pageFriend(boardNo, m);
		if(!(list == null) ) {
			model.addAttribute("friend",mem);
			model.addAttribute("list",list);
			model.addAttribute("count",count);
			model.addAttribute("Good",g);
			return "board/reviewDetailView";
		}else {
			model.addAttribute("errorMsg", "같이가요 게시글 조회 실패");
			return "errorPage/500page";
		}
	}
	
	
	
	
	
	
	
	
	//후기 댓글 그려주기 
	@ResponseBody
	@RequestMapping(value="reviewRlist.bo", produces="application/json; charset=utf-8")
	public String selectReviewReplyList(@RequestParam(value="boardNo") int boardNo) {
		ArrayList<Reply> rlist = boardService.selectReviewReplyList(boardNo);
		
		JsonArray replyList = new JsonArray();
		for (Reply r : rlist) {
			if(r.getReplyGroup() != 0)
				continue;
			
			JsonObject newReply = new JsonObject();
			newReply.addProperty("replyNo", r.getReplyNo());
			newReply.addProperty("memberProfileImg", r.getMemberProfileImg());
			newReply.addProperty("replyWriter", r.getReplyWriter());
			newReply.addProperty("replyModifyDate", r.getReplyModifyDate());
			newReply.addProperty("replyContent", r.getReplyContent());
			
			
			JsonArray replyReList2 = new JsonArray();
			for (Reply tmpR : rlist) {
				if(r.getReplyNo() == tmpR.getReplyGroup()) {
					JsonObject RReply = new JsonObject();
					RReply.addProperty("replyNo", tmpR.getReplyNo());
					RReply.addProperty("memberProfileImg", tmpR.getMemberProfileImg());
					RReply.addProperty("replyWriter", tmpR.getReplyWriter());
					RReply.addProperty("replyModifyDate", tmpR.getReplyModifyDate());
					RReply.addProperty("replyContent", tmpR.getReplyContent());
					RReply.addProperty("replyGroup", tmpR.getReplyGroup());
					
					replyReList2.add(RReply);
				}
				
			}
			newReply.add("rlist", replyReList2);
			
			replyList.add(newReply);
		}
			return new Gson().toJson(replyList);
	}
	
	
	//review 댓글입력
	@ResponseBody
	@RequestMapping(value="reviewReplyInsert.bo")
	public String ajaxInsertReply(Reply r) {

	return  boardService.ajaxInsertReply(r) > 0 ? "success" :"fail";
	}
	

	
	//일정가져오기
	@ResponseBody
	@RequestMapping(value="scheduleListRe.bo", produces="application/json; charset=utf-8")
	public String selectPlanListRe(int memberNo) {
		ArrayList<Plan> list = boardService.selectPlanListRe(memberNo);
		
		return new Gson().toJson(list);

	}
	
	
	
	//일정 수정 버튼 눌렀을때 다시 그려주는
	@ResponseBody
	@RequestMapping(value="reviewDraw.bo", produces="application/json; charset=utf-8")
	public String ajaxReDrawScheduleRe(@RequestParam(value="tripPlanNo") int tripPlanNo) {
		ArrayList<Plan> list = boardService.selectOneTripPlanRe(tripPlanNo);
		
		return new Gson().toJson(list);
	}
	
	
	@RequestMapping("reviewEdit.bo")
	public ModelAndView reviewEnrollForm(@RequestParam(value="ppage") int tripPlanNo, ModelAndView mv){
		
		
		
		ArrayList<Plan> plist = boardService.selectOneTripPlanRe(tripPlanNo);
		Set<Plan> set = new HashSet<Plan>();
			for (int i = 0; i < plist.size(); i++) {
		    	if(plist.get(i).getAttractionNo() != 0){
		    		set.add(plist.get(i));
		    	}
		    }
			
		mv.addObject("list", boardService.selectOneTripPlanRe(tripPlanNo))
		.addObject("maxNday",boardService.countMaxPlanDay(tripPlanNo))
		.addObject("alist", set)
		.setViewName("board/reviewEnrollForm");
		
		
		
		
		return mv;
	}
		
	
	//review게시글 작성
	@RequestMapping("reviewInsert.bo")
	public String insertReviewBoard(Board b, HttpSession session, Model model) {
		System.out.println(b);
		int result = boardService.insertReviewBoard(b);
		if(result > 0) { //성공 => 같이가요 리스트 페이지 재요청
			session.setAttribute("alertMsg", "후기 게시글 작성 완료");
			return "redirect:review.bo";
		}else {
			model.addAttribute("errorMsg", "게시글 작성 실패");
			return "errorPage/500page";
		}
			
		}
		
	
		
		
		@RequestMapping("updateReviewForm.bo")
		public String updateReviewForm(int boardNo, Model model ) {
			//현재 내가 수정하기를 클릭한 같이가요 게시글에 대한 정보를 가지고 이동
			model.addAttribute("list",boardService.selectReviewBoard(boardNo));
			
			return "board/reviewUpdateForm";
		}
		
		
	
		//review Update
		@RequestMapping("reviewUpdate.bo")
		public String updateReviewBoard(Board b, HttpSession session, Model model) {
			int result = boardService.updateReviewBoard(b);
			//b객체 update
			
			//성공유무 확인 후 페이지 리턴
			if(result >0) {
				session.setAttribute("alertMsg", "게시글 수정 완료");
				return "redirect:detail.bo?boardNo="+b.getBoardNo();
			}else {
				model.addAttribute("errorMsg", "게시글 수정 실패"); 
				return "errorPage/500page";
			}
			
		}
		
		
		
		@RequestMapping("reviewDelete.bo")
		public String reviewDeleteBoard(int boardNo, HttpSession session, Model model) {
			Board b = boardService.togetherSelectBoardOne(boardNo);
			//boardNo 으로 조회된 이미지 패스 삭제 
			List<String> imgPaths = extractImgPathFromContent(b.getBoardContent());
			//파일삭제
			
			for(String imgPath : imgPaths) {
				new File(session.getServletContext().getRealPath(imgPath)).delete();
			}
			
			int result = boardService.reviewDeleteBoard(boardNo);
			
			if(result > 0) {//삭제 성공
				
				session.setAttribute("alertMsg", "게시글 삭제에 성공하였습니다.");
				return "redirect:review.bo";
				
			}else {
				model.addAttribute("errorMsg", "게시글 삭제 실패");
				return "errorPage/500page";
			}
			
			
		}
		
		
		
	
		
		
		//후기 댓글 삭제하기
		@ResponseBody
		@RequestMapping(value="reviewReplyDelete.bo")
		public String ajaxDeleteReviewReply(Reply r)  {
			int result = boardService.ajaxDeleteReviewReply(r);
			
			if(result > 0 ) {
				return "success";
			}else {
				return "fail";
			}
			
		}
		
		
		//후기 댓글 수정하기
		@ResponseBody
		@RequestMapping(value="reviewReplyUpdate.bo")
		public String ajaxUpdateReviewReply(Reply r)  {
			int result = boardService.ajaxUpdateReviewReply(r);
			
			if(result > 0 ) {
				return "success";
			}else {
				return "fail";
			}
			
		}
//		후기 게시판 글 작성시 별점주기
		@ResponseBody
		@RequestMapping(value="insertStars.bo", produces="html/text; charset=UTF-8")
		public String ajaxInsertReviewStars(String stars)  {
			
			Star[] obj = new Gson().fromJson(stars, Star[].class);
			ArrayList<Star> list = new ArrayList<Star>();
	        
			for(int i = 0; i < obj.length; i++) {
				list.add(obj[i]);
			}
			Star s = new Star();
			int result = 0;
			for(int p = 0; p < list.size(); p++) {
				s = list.get(p);
				result += boardService.ajaxInsertReviewStars(s);
			}
			System.out.println(result);
			
			
			if(result == list.size() ) {
				return "success";
			}else {
				return "fail";
			}
			
		}
		
		
		//좋아요 누르기
		@ResponseBody
		@RequestMapping(value="like.bo")
		public String ajaxInsertLike(int memberNo, int boardNo)  {
			Map<String, Integer> paramMap = new HashMap<>();
		    paramMap.put("boardNo", boardNo);
		    paramMap.put("memberNo", memberNo);
		    
		    //내가 좋아요 누른지 여부 가져오기
		    int result = 0;
		    String str = null;
			Good g = boardService.selectGood(memberNo, boardNo);
			if(g == null) {
				result = boardService.ajaxInsertLike(paramMap);
				str = "i";
			}else {
				result = boardService.ajaxDeleteLike(paramMap);
				str = "d";
			}
		    
			if(result > 0 ) {
				//총 좋아요 개수 다시 가져오기
				int count = boardService.selectGoodCount(boardNo);
				return str+Integer.toString(count);
			}else {
				return "fail";
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
	
	
	
	
//	@RequestMapping(value="/goPlan.bo")
//	public String goPlan() {
//		return "board/boardScheduleMake2";
//	}
	
	
	
	
	
	
	
//	@RequestMapping("makePlan.bo")
//	public ModelAndView makePlan(LocationInfomation loca, Member m, HttpSession session, ModelAndView mv){
//		HashMap<String,Object> map = new HashMap();
//		map.put("loca", loca);
//		map.put("m", m);
//		mv.addObject("list", boardService.makePlan(map))
//		.setViewName("board/togetherEnrollForm");
//		
//		return mv;
//	}
		
		
		
		
		
		//메인페이지 키워드 검색
		@ResponseBody
		@RequestMapping(value ="regionSearch.bo", produces="application/json; charset=UTF-8")
		public String regionSearch(@RequestParam(value="cpage",defaultValue = "1")int currentPage, 
				ModelAndView mv, @RequestParam(value="keyword", required=false) String keyword)	{
			
			System.out.println("키워드 검색 시작");
			System.out.println(keyword);
			PageInfo pi = Pagenation.getPageInfo(boardService.regionListCount(), currentPage, 5, 12);

			
			ArrayList<Region> list = boardService.regionSearch(pi, keyword);
			System.out.println(list);
			return new Gson().toJson(list);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//박수현 추천여행지 Main
		@ResponseBody
		@RequestMapping(value="recommendMain.bo", produces = "application/json; charset = UTF-8")
		public String ajaxrecommendMain() {
			ArrayList<Recommend> main = boardService.ajaxrecommendMain();
			
			return new Gson().toJson(main);
		}
		
		//박수현 추천여행지 더보기 클릭시 전체보기
		@ResponseBody
		@RequestMapping(value="recommendList.bo", produces = "application/json; charset = UTF-8")
		public String ajaxrecommendList() {
			ArrayList<Recommend> list = boardService.ajaxrecommendList();
			
			return new Gson().toJson(list);
		}
		
		//박수현 추천여행지 디테일 페이지 불러오기
		@RequestMapping("recommendDetail.bo")
		public String selectRecommendBoard(int recommendBoardNo, Model model){
			
			ArrayList<Recommend> list = boardService.selectRecommendBoard(recommendBoardNo);
			
			if(!(list == null) ) {
				
				model.addAttribute("list", list);
				
				return "board/boardRecommendDetailView";
			}else {
				model.addAttribute("errorMsg", "추천여행지 게시글 조회 실패");
				return "errorPage/500page";
			}
		}
		//박수현 신고테이블 전체
//		@ResponseBody
//		@RequestMapping(value="reportInfor.bo", produces="application/json; charset=UTF-8")
//		public String memberInfor() {
//			System.out.println(boardService.reportInfor());
//		    return new Gson().toJson(boardService.reportInfor());
//		}
	

}