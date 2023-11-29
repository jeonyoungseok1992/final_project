<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

<!-- Bootstrap JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

<link rel="stylesheet" href="./resources/css/myPage.css">

</head>

<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<!-- 초록색 영역부분-->
		<div class="color-container"></div>

		<!-- 프로필 정보쪽-->
		<div class="profile-container">
			<img src="resources/images/profile.png" alt="나의프로필" style="border-radius: 45px;">
			<br>
			<span style="font-size: 20px;">로그인유저 프로필이름입력 자리</span>
			<br>
			<a href="#" style="color: #b2d8b5; margin-bottom: 70px;">프로필 관리 <img src="resources/images/gearIcon.png" alt="기어" width="15px" style="margin-top: -4px;"></a>
	
			<!-- 네비 바-->
			<div class="ui-tab">
				<div class="tab-menu2 button-group line-type" role="tablist" aria-label="탭 이름">
				<button role="tab2" aria-selected="true" aria-controls="tabPanel10" id="tab10">나의 일정</button>
				<button role="tab2" aria-selected="false" aria-controls="tabPanel11" id="tab11">같이 가요</button>
				<button role="tab2" aria-selected="false" aria-controls="tabPanel12" id="tab12">여행 후기</button>
				<button role="tab2" aria-selected="false" aria-controls="tabPanel13" id="tab13" >나의 친구 목록</button>
				</div>

				<!-- 나의 일정 탭 시작-->
				<div class="tab-contents2">
					<div tabindex="0" role="tabpanel" id="tabPanel10" aria-labelledby="tab10" >
						나의 일정
						<div class="button-area">
							<div>
							<button >전체 일정</button>
							<button style="margin-left: 15px;">공유된 일정</button>
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
									<img class="dropdown-toggle" id="dropdownMenuButton" data-bs-toggle="dropdown"  aria-haspopup="true" aria-expanded="false" src="resources/images/moreIcons.png" alt="더보기" 
									style="transform:rotate(90deg) translateY(50%); width: 30px; height: 30px; cursor: pointer;">
									<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
									  <li><a class="dropdown-item" href="#">공유</a></li>
									  <li><a class="dropdown-item" href="#">삭제</a></li>
									  <li><a class="dropdown-item" href="#">프린트</a></li>
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
									  <li><a class="dropdown-item" href="#">프린트</a></li>
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
									  <li><a class="dropdown-item" href="#">프린트</a></li>
									</ul>
								  </div>

							</div>
							<!-- 나의일정 -> 전체일정 탭 끝-->	
							<!-- 나의일정 -> 공유된 일정(전체일정이랑 같은 양식으로 뿌리면 됨) -->	
					</div>
					<!-- 나의 일정 탭 끝-->

					<!-- 같이가요 탭 시작-->
					<div tabindex="0" role="tabpanel" id="tabPanel11" aria-labelledby="tab11" hidden="true">
						같이 가요
						<div class="dropdown tapbtn">
							<button class="dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">정렬</button>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="#">최근 수정일 순</a></li>
								<li><a class="dropdown-item" href="#">가까운 날짜 순</a></li>
							  </ul>
						</div>
						<!--ajax호출부 -->
						<div id="mySchedule">
							<div style="width: 180px; height: 180px;"><img id="mainImg" src="resources/images/Jeonju.jpeg" alt="전주사진"></div>

							<div class="mySchedule-content">
								<span>겨울 커플여행</span>
								<span style="font-size: 14px; font-weight: 400; color: gray;">전주</span>
								<span style="font-size: 18px; font-weight: 600; display: flex; justify-content: space-between;">2020-10-17 ~ 2020-11-01<span style="font-size: 13px; color: gray;">최근 수정일 2020-12-01</span></span>									
							</div>

							<div class="dropdown">
									<img class="dropdown-toggle" id="dropdownMenuButton" data-bs-toggle="dropdown"  aria-haspopup="true" aria-expanded="false" src="resources/images/moreIcons.png" alt="더보기" style="transform:rotate(90deg) translateY(50%); width: 30px; height: 30px; cursor: pointer;">
								<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
								  <li><a class="dropdown-item" href="#">공유</a></li>
								  <li><a class="dropdown-item" href="#">삭제</a></li>
								  <li><a class="dropdown-item" href="#">프린트</a></li>
								</ul>
							  </div>
						</div>

						<div id="mySchedule">
							<div style="width: 180px; height: 180px;"><img id="mainImg" src="resources/images/Jeonju.jpeg" alt="전주사진"></div>

							<div class="mySchedule-content">
								<span>겨울 커플여행</span>
								<span style="font-size: 14px; font-weight: 400; color: gray;">전주</span>
								<span style="font-size: 18px; font-weight: 600; display: flex; justify-content: space-between;">2020-10-17 ~ 2020-11-01<span style="font-size: 13px; color: gray;">최근 수정일 2020-12-01</span></span>									
							</div>

							<div class="dropdown">
									<img class="dropdown-toggle" id="dropdownMenuButton" data-bs-toggle="dropdown"  aria-haspopup="true" aria-expanded="false" src="resources/images/moreIcons.png" alt="더보기" style="transform:rotate(90deg) translateY(50%); width: 30px; height: 30px; cursor: pointer;">
								<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
								  <li><a class="dropdown-item" href="#">공유</a></li>
								  <li><a class="dropdown-item" href="#">삭제</a></li>
								  <li><a class="dropdown-item" href="#">프린트</a></li>
								</ul>
							  </div>

						</div>
						<div id="mySchedule">
							<div style="width: 180px; height: 180px;"><img id="mainImg" src="resources/images/Jeonju.jpeg" alt="전주사진"></div>

							<div class="mySchedule-content">
								<span>겨울 커플여행</span>
								<span style="font-size: 14px; font-weight: 400; color: gray;">전주</span>
								<span style="font-size: 18px; font-weight: 600; display: flex; justify-content: space-between;">2020-10-17 ~ 2020-11-01<span style="font-size: 13px; color: gray;">최근 수정일 2020-12-01</span></span>									
							</div>

							<div class="dropdown">
									<img class="dropdown-toggle" id="dropdownMenuButton" data-bs-toggle="dropdown"  aria-haspopup="true" aria-expanded="false" src="resources/images/moreIcons.png" alt="더보기" style="transform:rotate(90deg) translateY(50%); width: 30px; height: 30px; cursor: pointer;">
								<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
								  <li><a class="dropdown-item" href="#">공유</a></li>
								  <li><a class="dropdown-item" href="#">삭제</a></li>
								  <li><a class="dropdown-item" href="#">프린트</a></li>
								</ul>
							  </div>

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
						<div id="mySchedule">
							<div style="width: 180px; height: 180px;"><img id="mainImg" src="resources/images/Jeonju.jpeg" alt="전주사진"></div>

							<div class="mySchedule-content">
								<span>친구와 전주 먹부림</span>
								<span style="font-size: 14px; font-weight: 400; color: gray;">전주</span>
								<span style="font-size: 18px; font-weight: 600; display: flex; justify-content: space-between;">2020-10-17 ~ 2020-11-01<span style="font-size: 13px; color: gray;">최근 수정일 2020-12-01</span></span>									
							</div>

							<div class="dropdown">
									<img class="dropdown-toggle" id="dropdownMenuButton" data-bs-toggle="dropdown"  aria-haspopup="true" aria-expanded="false" src="resources/images/moreIcons.png" alt="더보기" style="transform:rotate(90deg) translateY(50%); width: 30px; height: 30px; cursor: pointer;">
								<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
								  <li><a class="dropdown-item" href="#">공유</a></li>
								  <li><a class="dropdown-item" href="#">삭제</a></li>
								  <li><a class="dropdown-item" href="#">프린트</a></li>
								</ul>
							  </div>
						</div>
						<div id="mySchedule">
							<div style="width: 180px; height: 180px;"><img id="mainImg" src="resources/images/Jeonju.jpeg" alt="전주사진"></div>

							<div class="mySchedule-content">
								<span>친구와 전주 먹부림</span>
								<span style="font-size: 14px; font-weight: 400; color: gray;">전주</span>
								<span style="font-size: 18px; font-weight: 600; display: flex; justify-content: space-between;">2020-10-17 ~ 2020-11-01<span style="font-size: 13px; color: gray;">최근 수정일 2020-12-01</span></span>									
							</div>

							<div class="dropdown">
									<img class="dropdown-toggle" id="dropdownMenuButton" data-bs-toggle="dropdown"  aria-haspopup="true" aria-expanded="false" src="resources/images/moreIcons.png" alt="더보기" style="transform:rotate(90deg) translateY(50%); width: 30px; height: 30px; cursor: pointer;">
								<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
								  <li><a class="dropdown-item" href="#">공유</a></li>
								  <li><a class="dropdown-item" href="#">삭제</a></li>
								  <li><a class="dropdown-item" href="#">프린트</a></li>
								</ul>
							  </div>
						</div>
						<div id="mySchedule">
							<div style="width: 180px; height: 180px;"><img id="mainImg" src="resources/images/Jeonju.jpeg" alt="전주사진"></div>

							<div class="mySchedule-content">
								<span>친구와 전주 먹부림</span>
								<span style="font-size: 14px; font-weight: 400; color: gray;">전주</span>
								<span style="font-size: 18px; font-weight: 600; display: flex; justify-content: space-between;">2020-10-17 ~ 2020-11-01<span style="font-size: 13px; color: gray;">최근 수정일 2020-12-01</span></span>									
							</div>

							<div class="dropdown">
									<img class="dropdown-toggle" id="dropdownMenuButton" data-bs-toggle="dropdown"  aria-haspopup="true" aria-expanded="false" src="resources/images/moreIcons.png" alt="더보기" style="transform:rotate(90deg) translateY(50%); width: 30px; height: 30px; cursor: pointer;">
								<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
								  <li><a class="dropdown-item" href="#">공유</a></li>
								  <li><a class="dropdown-item" href="#">삭제</a></li>
								  <li><a class="dropdown-item" href="#">프린트</a></li>
								</ul>
							  </div>
						</div>
					</div>
				<!-- 여행후기 탭끝-->	

				<!-- 친구목록 시작-->
					<div tabindex="0" role="tabpanel" id="tabPanel13" aria-labelledby="tab13" hidden="true">
						나의 친구 목록
						<div class="button-area">
							<div>
							<button >친구 목록</button>
							<button style="margin-left: 15px;">친구 요청 목록</button>
							</div>

							<div class="dropdown">
								<button class="dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">정렬</button>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item" href="#">최근 추가된 순</a></li>
									<li><a class="dropdown-item" href="#">가나다순</a></li>
								</ul>
							</div>
						</div>

						<!-- 목록 ajax 부분(친구목록 버튼 초록글씨로 변경)-->
						<div id="myfriend">
							<div>
								<img src="resources/images/profile.png" alt="나의프로필" style="border-radius: 45px; width: 50px;">
								<span style="font-size: 20px; margin-left: 10px;">userId</span>
							</div>
							<div>
								<a href="#"><img src="resources/images/talkIcons.png" alt="채팅" style="width: 30px; height: 30px;"></a>
								<a href="#" data-bs-toggle="modal" data-bs-target="#myModal"><img src="resources/images/xIcons.png" alt="x" style="width: 30px; height: 30px;"></a>
							</div>	
						</div>
						<div id="myfriend">
							<div>
								<img src="resources/images/profile.png" alt="나의프로필" style="border-radius: 45px; width: 50px;">
								<span style="font-size: 20px; margin-left: 10px;">userId</span>
							</div>
							<div>
								<a href="#"><img src="resources/images/talkIcons.png" alt="채팅" style="width: 30px; height: 30px;"></a>
								<a href="#" data-bs-toggle="modal" data-bs-target="#myModal"><img src="resources/images/xIcons.png" alt="x" style="width: 30px; height: 30px;"></a>
							</div>	
						</div>
						<div id="myfriend">
							<div>
								<img src="resources/images/profile.png" alt="나의프로필" style="border-radius: 45px; width: 50px;">
								<span style="font-size: 20px; margin-left: 10px;">userId</span>
							</div>
							<div>
								<a href="#"><img src="resources/images/talkIcons.png" alt="채팅" style="width: 30px; height: 30px;"></a>
								<a href="#" data-bs-toggle="modal" data-bs-target="#myModal"><img src="resources/images/xIcons.png" alt="x" style="width: 30px; height: 30px;"></a>
							</div>	
						</div>
						<div id="myfriend">
							<div>
								<img src="resources/images/profile.png" alt="나의프로필" style="border-radius: 45px; width: 50px;">
								<span style="font-size: 20px; margin-left: 10px;">userId</span>
							</div>
							<div>
								<a href="#"><img src="resources/images/talkIcons.png" alt="채팅" style="width: 30px; height: 30px;"></a>
								<a href="#" data-bs-toggle="modal" data-bs-target="#myModal"><img src="resources/images/xIcons.png" alt="x" style="width: 30px; height: 30px;"></a>
							</div>	
						</div>
						<!-- 목록 ajax 부분(친구 요청 목록 버튼 초록글씨로 변경)-->
						<div id="myfriend">
							<div>
								<img src="resources/images/profile.png" alt="나의프로필" style="border-radius: 45px; width: 50px;">
								<span style="font-size: 20px; margin-left: 10px;">userId</span>
							</div>
							<div>
								<a href="#" style="font-size: 18px;">수락</a>
								<span style="font-size: 18px;">|</span>
								<a href="#" style="font-size: 18px;">거절</a>
							</div>	
						</div>



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
							<button type="button" onclick="location.href=''" class="btn btn-secondary" style="background: #b2d8b5; width: 200px; height: 50px; border: none;">Ok</button>
						</div>
				
					</div>
				</div>
			</div>
			
			

	<script>
		
		$(document).ready(function () {
            let $tab2 = $('.ui-tab');

            $tab2.each(function(){
                if( $(this).data('init') ) return;
                $(this).data('init' , true);

                let $this2 = $(this);
                let $tabMenu2 = $this2.find('.tab-menu2');
                let $tabBtn2 = $('[role="tab2"]', $tabMenu2);
                let $tabContents2 = $this2.find('.tab-contents2>div');
                
                $tabBtn2.on('click.default' , function(e){
                    let _this2 = $(this);
                    let indexNum2 = _this2.index();
                    let _tabContent2 = $tabContents2.eq( indexNum2 );
                    
                    $tabBtn2.not(_this2).removeClass('active');
                    $tabBtn2.not(_this2).attr('aria-selected', false);
                    _this2.addClass('active');
                    _this2.attr('aria-selected', true);
                    $tabContents2.not(_tabContent2).attr('hidden', true);
                    _tabContent2.removeAttr('hidden');
                    
                    if($(this).is('a')){
                        e.preventDefault();
                    }
                });
            });
        });
	</script>
</body>
</html>