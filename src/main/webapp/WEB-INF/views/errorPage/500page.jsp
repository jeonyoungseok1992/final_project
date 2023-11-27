<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error-404-page</title>
<link rel="stylesheet" href="./resources/css/common.css">
<link rel="stylesheet" href="./resources/css/erroPage.css">

</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <div class="container-500">
        <span class="etitle">500 Error</span>
        <br>
        <span class="etitle">페이지를 찾을 수 없습니다.</span>
        <br><br><br>
        <span class="econtent">입력하신 주소가 정확한지 다시 한 번 확인해주세요.</span>
        <br><br><br><br>
        <div><img src="resources/images/errorIcon.png" alt="경고사진" style="width: 100px;" ></div>
        <br><br><br><br>
        <div id="ebutton">
            <button class="btn btn-light">이전페이지</button>
            <button class="btn btn-success" style="background: #b2d8b5; border: none;">홈</button>
        </div>
    </div>
</body>
</html>