<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no, viewport-fit=cover">
    <meta name="description" content="">
    <meta name="keyword" content="">
    <title>mapping</title>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<!-- jquery 3.7.1 -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"
		integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<link rel="stylesheet" href="./resources/css/common.css">
    <link rel="stylesheet" href="./resources/css/header.css">

    
</head>
<body style="width: 100%; min-width: 1200px; min-height: 100%;">

    <c:if test="${ !empty alertMsg }">
		<script>
		alertify.alert('알림', "${alertMsg}");
		</script>
		<c:remove var="alertMsg" scope="session"/>
	</c:if>
	
	<!-- header -->
    <header class="header" id="header">
        <div class="inner-wrap">
            <div class="gnb">
                <nav class="nav">
                    <h1><a href=""><img src="resources/images/logo_001.png" alt="mapping logo" style="width: 100px;"><span class="sr-only"><span></a></h1>
                <c:choose>
                	<c:when test="${empty loginUser}">
                    <!-- case1. 로그인 전 -->
					<ul class="menu">
                        <li><a href="">여행지</a></li>
                        <li><a href="">같이가요</a></li>
						<li style="margin-right: 40px;"><a href="">여행후기</a></li>
						<div class="menu-notuser">
						<li><a data-bs-toggle="modal" data-bs-target="#loginModal">로그인</a></li>
						<li>|</li>
						<li><a href="">회원가입</a></li>
						</div>
                    </ul>
					</c:when>
					<c:otherwise>
					<!-- case2. 로그인 후 -->
					<ul class="menu">
					   <li><a href="">여행지</a></li>
					   <li><a href="">같이가요</a></li>
					   <li><a href="">여행후기</a></li>
					   <li><a href=""><img src="resources/images/profile.png" alt="나의프로필" style="border-radius: 45px; width: 50px;"></a></li>
				   </ul>
				   </c:otherwise> 
        		</c:choose>
    
    <!-- 로그인 클릭 시 뜨는 모달 -->    		
	<div class="modal fade" id="loginModal">
           <div class="modal-dialog modal-dialog-centered">
               <div class="modal-content">
           
                   <!-- Modal Header -->
                  <div class="modal-header" style="border-bottom: none;padding: 30px;display: block;">
	                   <div style="display: flex; justify-content: center; align-items: center; flex-direction: column; gap: 20px;">
	                   	 <h1 align="center"><img src="resources/images/logo_001.png" alt="mapping logo" style="width:100px"> <span class="sr-only"><span></a></h1>
	                   	 <h4 class="modal-title updatest">로그인</h4>
	                   </div>
                   		<button type="button" class="btn-close" data-bs-dismiss="modal" style="position: absolute; top: 20px; right: 20px;"></button>
                   </div>
                   <!-- Modal body -->
                   <div class="modal-body login" align="center" style="padding: 24px;">
				        <form action="login.me" id="login-form" method="post">
			                <label align="center">아이디</label>
			                <input type="text" name="memberId" placeholder="아이디를 입력해주세요" required/>
			                <label align="center">비밀번호</label>
			                <input type="password" name="memberPwd" placeholder="비밀번호를 입력해주세요" required/>
		                    <button type="submit" id="mem" class="btn btn-sm btn-secondary updatest">로그인</button>
		                    <div id="link-box">
								<button id="join"><a data-bs-toggle="modal" data-bs-target="#searchId">아이디찾기</a></button>
								<button id="join"><a href="#">비밀번호찾기</a></button>
								<button id="join"><a href="#">회원가입</a></button>
        					</div>
						</form>
                   </div>
               </div>
           </div>
       </div>

	<!-- 아이디찾기 누르면 뜨는 모달 -->    		
	<div class="modal fade" id="searchId">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
		
				<!-- Modal Header -->
			   <div class="modal-header" style="border-bottom: none;padding: 30px;display: block;">
					<div style="display: flex; justify-content: center; align-items: center; flex-direction: column; gap: 20px;">
						 <h1 align="center"><img src="resources/images/logo_001.png" alt="mapping logo" style="width:100px"> <span class="sr-only"><span></a></h1>
						 <h4 class="modal-title updatest">아이디 찾기</h4>
						 <span >회원가입시 등록한 휴대전화를 인증하여 아이디를 찾을 수 있습니다.</span>
					</div>
						<button type="button" class="btn-close" data-bs-dismiss="modal" style="position: absolute; top: 20px; right: 20px;"></button>
				</div>
				<!-- Modal body -->
				<div class="modal-body login" align="center" style="padding: 24px;">
					 <form action="#" id="search-form" method="post">
						 <label align="center" for="phone">핸드폰번호</label>
						<div style="display: flex; width: 100%; gap: 10px;">
						 	<input type="tel" name="phone" id="phone" placeholder="핸드폰번호를입력해주세요(-없이)" required style="width: 300px;"/>
						 	<button type="submit" id="mem" class="btn btn-sm btn-secondary updatest edit">인증</button>
						</div>
					</form>
					<form action="#" id="search-form" method="post">
						 <label align="center" for="serialNum">인증번호</label>
						 <input type="password" id="serialNum" name="memberPwd" placeholder="비밀번호를 입력해주세요" required/>
						 <button type="submit" id="mem" class="btn btn-sm btn-secondary updatest">다음</button>
					 </form>
				</div>
			</div>
		</div>
	</div>
    </header>
    <!--// header  -->   

</body>
</html>