<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	body{
		margin-top: 72px;
		position: relative;
	}
	.color-container{
		min-width: 768px;
		height: 300px;
		background-color: #b2d8b5;
	}
	.profile-container{
		display: flex;
		flex-direction: column;
		position: absolute;
		align-items: center;
		top: 19%;
		margin-top: 100px;
		width: 100%;
	}
	
	/* 네비 바*/
	

	.button-group.line-type>* {
        height: 48px;
    }
    .button-group>* {
        position: relative;
        color: #000;
        font-weight: 700;
        transition: .2s;
		font-size: 20px;
    }
    .button-group>*[aria-checked="true"], .button-group>*[aria-selected="true"] {
        color: #b2d8b5;
        font-weight: 700;
    }
    .button-group.line-type>*[aria-checked="true"]:before, .button-group.line-type>*[aria-selected="true"]:before {
        left: 0;
        right: 0;
        background-color: #b2d8b5;
    }
    .button-group.line-type>*:before {
        content: '';
        position: absolute;
        bottom: 0;
        left: 50%;
        right: 50%;
        height: 2px;
        transition: .2s;
    }
	.tab-menu2 {
		width: 800px;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
	}
	.tab-contents2{
		font-size: 23px;
		font-weight: 700;
		margin-top: 50px;
	}

</style>
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