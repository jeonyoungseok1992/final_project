package com.kh.fin.board.controller;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;
import org.springframework.web.multipart.MultipartFile;
import com.kh.fin.board.model.service.BoardService;
import com.kh.fin.board.model.vo.Board;
import com.kh.fin.board.model.vo.Plan;
import com.kh.fin.board.model.vo.Reply;
import com.kh.fin.common.model.vo.PageInfo;
import com.kh.fin.common.template.Pagenation;
import com.kh.fin.member.model.vo.Member;

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
	
	@RequestMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpSession session )  {
		String cName = saveFile(multipartFile, session);
		String changeName = "resources/uploadFiles/" + cName;
       
		return new Gson().toJson(changeName);
	}
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
	
	@RequestMapping("togetherInsert.bo")
	public String insertTogetherBoard(Board b, HttpSession session, Model model) {
		System.out.println(b);
		int result = boardService.insertTogetherBoard(b);
		if(result > 0) { //성공 => 같이가요 리스트 페이지 재요청
			session.setAttribute("alertMsg", "같이가요 게시글 작성 완료");
			return "redirect:together.bo";
		}else {
			model.addAttribute("errorMsg", "게시글 작성 실패");
			return "errorPage/500page";
		}
		
	}
	
	
	@RequestMapping("togetherDetail.bo")
	public String selectTogetherBoard(int boardNo, Model model){
		
		ArrayList<Board> list = boardService.selectTogetherBoard(boardNo);
		
		if(!(list == null) ) {
			
			model.addAttribute("list",list);
			
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
	@RequestMapping("togetherDelete.bo")
	public String togetherDeleteBoard(int boardNo, HttpSession session, Model model) {
		
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
		
		return new Gson().toJson(rlist);
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
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/goPlan.bo")
	public String goPlan() {
		return "board/boardScheduleMake2";
	}
	
	
	
	
	
	
	
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
	

	
	
	
	

}
