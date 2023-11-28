<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./resources/css/common.css">
<link rel="stylesheet" href="./resources/css/myPage.css">
</head>

<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<div class="color-container"></div>
		<div class="profile-container">
			<img src="resources/images/profile.png" alt="나의프로필" style="border-radius: 45px; width: 50px;">
			<br>
			<span>로그인유저 프로필이름입력 자리</span>
			<br>
			<a href="" style="color: #b2d8b5; margin-bottom: 70px;">프로필 관리 <img src="resources/images/gearIcon.png" alt="기어" width="15px" style="margin-top: -4px;"></a>
	
			<!-- 네비 바-->
			<div class="ui-tab">
				<div class="tab-menu2 button-group line-type" role="tablist" aria-label="탭 이름">
				<button role="tab2" aria-selected="true" aria-controls="tabPanel10" id="tab10">나의 일정</button>
				<button role="tab2" aria-selected="false" aria-controls="tabPanel11" id="tab11">같이 가요</button>
				<button role="tab2" aria-selected="false" aria-controls="tabPanel12" id="tab12">여행 후기</button>
				<button role="tab2" aria-selected="false" aria-controls="tabPanel13" id="tab13" >나의 친구 목록</button>
				</div>

				<div class="tab-contents2">
					<div tabindex="0" role="tabpanel" id="tabPanel10" aria-labelledby="tab10" >
						나의 일정
						<!-- ajax 호출부 /기능구현시 - 전체일정으로 먼저 나와야하고 버튼색깔도 바꿔야함-->
						<div class="button-area">
							<div>
							<button >전체 일정</button>
							<button style="margin-left: 15px;">공유된 일정</button>
							</div>
							<div>
								<button>최근 수정일 순<img src="resources/images/arrowIcons.png" alt="아래로화살표" width="15px"></button>
							</div>
						</div>
							<!-- 전체일정 -->
							<ul id="mySchedule">
								<li><img src="resources/images/Jeonju.jpeg" alt="전주사진"></li>

								<li class="mySchedule-content">
									<span>전주</span>
									<span style="font-size: 14px; font-weight: 400; color: gray;">나의 퇴사 여행</span>
									<span style="font-size: 18px; font-weight: 600;">2020-10-17 ~ 2020-11-01</span>
									<span>최근 수정일 2020-12-01</span>
								</li>
								<li><img src="resources/images/moreIcons.png" alt="더보기" ></li>
							</ul>
						

							<!-- 공유된 일정(전체일정이랑 같은 양식으로 뿌리면 됨) -->


						
					</div>
					<div tabindex="0" role="tabpanel" id="tabPanel11" aria-labelledby="tab11" hidden="true">
						같이 가요
					</div>
					<div tabindex="0" role="tabpanel" id="tabPanel12" aria-labelledby="tab12" hidden="true">
						여행 후기
					</div>
					<div tabindex="0" role="tabpanel" id="tabPanel13" aria-labelledby="tab13" hidden="true">
						나의 친구 목록
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