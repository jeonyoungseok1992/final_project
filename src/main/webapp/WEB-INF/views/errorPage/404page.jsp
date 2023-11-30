<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error-404-page</title>
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

<link rel="stylesheet" href="./resources/css/404error.css">

</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <div class="container-404">
        <span class="etitle" style="font-weight: 700;">404 Error</span>
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