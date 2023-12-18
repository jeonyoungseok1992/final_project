<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- jquery 3.7.1 -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

<link rel="stylesheet" href="./resources/css/myPage.css">
<link rel="stylesheet" href="./resources/css/board-card.css">
<script src="./resources/js/myPage.js"></script>

</head>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">

<body onload="init('${loginUser.memberNo}', ${frMemberList})">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>

		<!-- 초록색 영역부분-->
		<div class="color-container"></div>

		<!-- 프로필 정보쪽-->
		<div class="profile-container">
			<c:choose>
			    <c:when test="${!empty loginUser.memberProfileImg}">
			        <img src="${loginUser.memberProfileImg}" class="title-img" onclick="chooseFile()">
			    </c:when>
			    <c:otherwise>
			        <img src="resources/images/profile.png" class="title-img"  alt="나의프로필" onclick="chooseFile()">
			    </c:otherwise>
			</c:choose>

            <form method="post"enctype="multipart/form-data">
				<c:choose>
					<c:when test="${!empty loginUser.memberProfileImg}">
						<input style="display : none" id="file" type="file" name="upfile" onchange="loadImg(this,${loginUser.memberNo},'${loginUser.memberProfileImg}')"> <br>
					</c:when>
					<c:otherwise>
						<input style="display : none" id="file" type="file" name="upfile" onchange="loadImg2(this,${loginUser.memberNo})"> <br>
					</c:otherwise>
				</c:choose>
            </form>

            
            
			<br>
			<span style="font-size: 20px;">${loginUser.memberName}</span>
			<br>
			<a href="profileEdit.me" style="color: #b2d8b5; margin-bottom: 70px;">프로필 관리 <img src="resources/images/gearIcon.png" alt="기어" width="15px" style="margin-top: -4px;"></a>
	
			<!-- 네비 바-->
			<div class="ui-tab">
				<div class="tab-menu2 button-group line-type" role="tablist" aria-label="탭 이름">
				<button role="tab2" aria-selected="true" aria-controls="tabPanel10" id="tab10">나의 일정</button>
				<button role="tab2" aria-selected="false" aria-controls="tabPanel11" id="tab11" onclick="together(${loginUser.memberNo})">같이 가요</button>
				<button role="tab2" aria-selected="false" aria-controls="tabPanel12" id="tab12" onclick="review(${loginUser.memberNo})">여행 후기</button>
				<button role="tab2" aria-selected="false" aria-controls="tabPanel13" id="tab13" onclick="fdList(${loginUser.memberNo})" >나의 친구 목록</button>
				</div>

				<!-- 나의 일정 탭 시작-->
				<div class="tab-contents2">
					<div tabindex="0" role="tabpanel" id="tabPanel10" aria-labelledby="tab10" >
						나의 일정
						<div class="button-area">
							<div>
							<button class="scheduleTap1">나의 일정</button>
							<button class="scheduleTap1" style="margin-left: 15px;">공유된 일정</button>
							</div>

							<div class="dropdown">
								<button class="dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">정렬</button>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item" href="#">최근 수정일 순</a></li>
									<li><a class="dropdown-item" href="#">가까운 날짜 순</a></li>
								  </ul>
							</div>
						</div>
						<!-- ajax 호출부 /기능구현시 - 전체일정으로 먼저 나와야하고 버튼색깔도 바꿔야함-->
						
							<!-- 나의일정 -> 전체일정 탭시작-->
							<div id="mySchedule">
								<div style="width: 180px; height: 180px;"><img id="mainImg" src="resources/images/Jeonju.jpeg" alt="전주사진"></div>

								<div class="mySchedule-content">
									<span>전주</span>
									<span style="font-size: 14px; font-weight: 400; color: gray;">나의 퇴사 여행</span>
									<span style="font-size: 18px; font-weight: 600; display: flex; justify-content: space-between;">2020-10-17 ~ 2020-11-01<span style="font-size: 13px; color: gray;">최근 수정일 2020-12-01</span></span>									
								</div>

								<div class="dropdown">
									<img class="dropdown-toggle" id="dropdownMenuButton" data-bs-toggle="dropdown"  aria-haspopup="true" aria-expanded="false" src="resources/images/moreIcons.png" alt="더보기" >
									<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
									  <li><a class="dropdown-item" href="#">공유</a></li>
									  <li><a class="dropdown-item" href="#">삭제</a></li>
									 
									</ul>
								  </div>

							</div>
							<div id="mySchedule">
								<div style="width: 180px; height: 180px;"><img id="mainImg" src="resources/images/Jeonju.jpeg" alt="전주사진"></div>

								<div class="mySchedule-content">
									<span>전주</span>
									<span style="font-size: 14px; font-weight: 400; color: gray;">나의 퇴사 여행</span>
									<span style="font-size: 18px; font-weight: 600; display: flex; justify-content: space-between;">2020-10-17 ~ 2020-11-01<span style="font-size: 13px; color: gray;">최근 수정일 2020-12-01</span></span>									
								</div>

								<div class="dropdown">
										<img class="dropdown-toggle" id="dropdownMenuButton" data-bs-toggle="dropdown"  aria-haspopup="true" aria-expanded="false" src="resources/images/moreIcons.png" alt="더보기" style="transform:rotate(90deg) translateY(50%); width: 30px; height: 30px; cursor: pointer;">
									<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
									  <li><a class="dropdown-item" href="#">공유</a></li>
									  <li><a class="dropdown-item" href="#">삭제</a></li>
									  
									</ul>
								  </div>

							</div>	
							<div id="mySchedule">
								<div style="width: 180px; height: 180px;"><img id="mainImg" src="resources/images/Jeonju.jpeg" alt="전주사진"></div>

								<div class="mySchedule-content">
									<span>전주</span>
									<span style="font-size: 14px; font-weight: 400; color: gray;">나의 퇴사 여행</span>
									<span style="font-size: 18px; font-weight: 600; display: flex; justify-content: space-between;">2020-10-17 ~ 2020-11-01<span style="font-size: 13px; color: gray;">최근 수정일 2020-12-01</span></span>									
								</div>

								<div class="dropdown">
										<img class="dropdown-toggle" id="dropdownMenuButton" data-bs-toggle="dropdown"  aria-haspopup="true" aria-expanded="false" src="resources/images/moreIcons.png" alt="더보기" >
									<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
									  <li><a class="dropdown-item" href="#">공유</a></li>
									  <li><a class="dropdown-item" href="#">삭제</a></li>
									</ul>
								  </div>

							</div>
							<!-- 나의일정 -> 전체일정 탭 끝-->	
							<!-- 나의일정 -> 공유된 일정(전체일정이랑 같은 양식으로 뿌리면 됨) -->	
					</div>
					<!-- 나의 일정 탭 끝-->

					<!-- 같이가요 탭 시작-->
					<div tabindex="0" role="tabpanel" id="tabPanel11" aria-labelledby="tab11" hidden="true" >
						같이 가요
						<div class="dropdown tapbtn">
							<button class="dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">정렬</button>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="#">최근 수정일 순</a></li>
								<li><a class="dropdown-item" href="#">가까운 날짜 순</a></li>
							  </ul>
						</div>
						<!--ajax호출부 -->		
							<div class="myTogetherSchedule">

							</div>
								
					</div>
				<!-- 같이가요 탭 끝-->	

				<!-- 여행후기 탭시작-->	
					<div tabindex="0" role="tabpanel" id="tabPanel12" aria-labelledby="tab12" hidden="true">
						여행 후기
						<div class="dropdown tapbtn">
							<button class="dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">정렬</button>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="#">최근 수정일 순</a></li>
								<li><a class="dropdown-item" href="#">가까운 날짜 순</a></li>
							  </ul>
						</div>

						<!-- ajax 부분-->
						<div class="myReviewSchedule">

						</div>
					</div>
				<!-- 여행후기 탭끝-->	

				<!-- 친구목록 시작-->
					<div tabindex="0" role="tabpanel" id="tabPanel13" aria-labelledby="tab13" hidden="true">
						나의 친구 목록
						<div class="button-area">
							<div>
							<button class="scheduleTap2" onclick="fdList(${loginUser.memberNo})">친구 목록</button>
							<button class="scheduleTap2" style="margin-left: 15px;" onclick="fdRequest(${loginUser.memberNo})">친구 요청 목록</button>
							</div>

							<div class="dropdown">
								<button class="dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">정렬</button>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item" href="#">최근 추가된 순</a></li>
									<li><a class="dropdown-item" href="#">가나다순</a></li>
								</ul>
							</div>
						</div>

						<!-- 목록 ajax 부분-->
						<div id="fdList"></div>
						
						<!-- 목록 ajax 부분-->
						<div id="fdRequest"></div>




					</div>
				</div>
	 		</div>


		</div>

			<!-- The Modal -->
			<div class="modal fade" id="myModal">
				<div class="modal-dialog">
					<div class="modal-content" style="margin-top: 300px;">
				
						<!-- Modal body -->
						<div class="modal-body" style="font-size: 18px; font-weight: 500; text-align: center;">
							삭제한 친구는 복구할 수 없습니다.<br><br> 
							정말 삭제하시겠습니까?
						</div>
				
						<!-- Modal footer -->
						<div class="modal-footer" style="display: flex; justify-content: center;">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="background: lightgray; width: 200px; height: 50px; border: none; ">Cancle</button>
							<button id="footerBtn" type="button" onclick="friendDelete()" class="btn btn-secondary" style="background: #b2d8b5; width: 200px; height: 50px; border: none;">Ok</button>
						</div>
				
					</div>
				</div>
			</div>

			<!-- The Modal -->
			<div class="modal fade" id="myModal2">
				<div class="modal-dialog">
					<div class="modal-content" style="margin-top: 300px;">
				
						<!-- Modal body -->
						<div class="modal-body" style="font-size: 18px; font-weight: 500; text-align: center;">
							<br><br> 
							정말 거절하시겠습니까?
						</div>
				
						<!-- Modal footer -->
						<div class="modal-footer" style="display: flex; justify-content: center;">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="background: lightgray; width: 200px; height: 50px; border: none; ">Cancle</button>
							<button id="footerBtn" type="button" onclick="rejectFriend()"  class="btn btn-secondary" style="background: #b2d8b5; width: 200px; height: 50px; border: none;">Ok</button>
						</div>
				
					</div>
				</div>
			</div>
			
			


	
	

</body>
</html>