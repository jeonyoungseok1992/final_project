<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<!-- <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script> -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- jquery 3.7.1 -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="./resources/js/common.js"></script>
<script src="./resources/js/scheduleAjax.js"></script>
<script src="./resources/js/mapApi.js"></script>
<script src="./resources/js/boardSchedule.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c9f8a44c92b8ee5a74706b1ce8e6194e&libraries=LIBRARY&libraries=services&libraries=services,clusterer,drawing"></script>
<link rel="stylesheet" href="./resources/css/common.css">
<link rel="stylesheet" href="./resources/css/font.css">
<link rel="stylesheet" href="./resources/css/schedule-view.css">
<link rel="stylesheet" href="./resources/css/schedule-make2.css">

</head>
<body onload='init(`${region}`, `${loginUser.memberNo}`)'>

    <div class="travel-wrap">
        <div class="header-fixed">
            <a href="${pageContext.request.contextPath}" class="logo"><img src="./resources/images/logo_001.png" alt=""></a>
            <div class="dropdown">
                <button type="button" class="btn btn-primary dropdown-toggle profile" data-bs-toggle="dropdown"><img src="./resources/images/profile.png" alt=""></button>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="#">1:1 채팅</a></li>
                    <li><a class="dropdown-item" href="#">마이페이지</a></li>
                    <li><a class="dropdown-item" href="#">로그아웃</a></li>
                </ul>
            </div>
        </div>
        <div class="travel-full">
            <div class="schedule-wrap early">
                <div class="schedule-menu">
                    <ul class="tab-menu">
                        <li><button id="step1" class="active">STEP1<br>날짜 확인</button></li>
                        <li><button id="step2">STEP2<br>장소 선택</button></li>
                        <li><button id="step3">STEP3<br>숙소 설정</button></li>
                        <li><button id="step4">STEP4<br>교통수단<br>선택</button></li>
                    </ul>
                    <div>
                        <button id="prev-btn">이전</button>
                        <button id="next-btn">다음</button>
                    </div>
                </div>
                <div id="content-zone" class="schedule">
                    
                    
                </div>
            </div>
            <div id="side-modal" class="side-modal" style="display: none;">
                
            </div>
            <div class="map-wrap">
                <div id="map" style="width:100%;height:100vh;"></div>
            </div>
        </div>
        
    </div>
</body>
</html>