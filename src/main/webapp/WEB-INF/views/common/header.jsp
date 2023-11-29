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
		   <!-- Bootstrap CSS -->
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
   <!-- Optional JavaScript -->
   <!-- jQuery first, then Popper.js, then Bootstrap JS -->
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
   <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
   <!-- jquery 3.7.1 -->
   <script src="https://code.jquery.com/jquery-3.7.1.min.js"
      integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
   <link rel="stylesheet" href="./resources/css/common.css">
   <link rel="stylesheet" href="./resources/css/font.css">
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
		                    <button id="join"><a href="enrollForm.me">회원가입</a> </button>
        				</form>
                   </div>
               </div>
           </div>
       </div>
    </header>
    <!--// header  -->   

</body>
</html>