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
<!-- <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script> -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- jquery 3.7.1 -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.6.0/kakao.min.js"
  integrity="sha384-6MFdIr0zOira1CHQkedUqJVql0YtcZA1P0nbPrQYJXVJZUkTk/oX4U9GhUIs3/z8" crossorigin="anonymous"></script>
  <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c9f8a44c92b8ee5a74706b1ce8e6194e&libraries=LIBRARY&libraries=services&libraries=services,clusterer,drawing"></script>
  <script src="./resources/js/boardScheduleView.js"></script>
<script src="./resources/js/boardSchedule.js"></script>
<link rel="stylesheet" href="./resources/css/common.css">
<link rel="stylesheet" href="./resources/css/font.css">
<link rel="stylesheet" href="./resources/css/schedule-make.css">
<link rel="stylesheet" href="./resources/css/schedule-view.css">
<!-- You can use Open Graph tags to customize link previews.
Learn more: https://developers.facebook.com/docs/sharing/webmasters -->
<meta property="og:url"           content="http://localhost:8009/mapping/finalPlan.bo?tripPlanNo=${list[0].tripPlanNo}"/>
<meta property="og:type"          content="website" />
<meta property="og:title"         content="Mapping" />
<meta property="og:description"   content="#함께하면 더욱 즐거운 여행, 같이가요 우리" />
<meta property="og:image"         content="https://img.freepik.com/premium-photo/bukchon-hanok-village-with-seoul-city-skyline-cityscape-of-south-korea_255553-4703.jpg" />
</head>
<body onload='init2(`${list2}`)'>
    <div class="travel-wrap">
        <div class="header-fixed">
            <a href="${pageContext.request.contextPath}" class="logo"><img src="./resources/images/logo_001.png" alt=""></a>
            <div class="dropdown">
                <c:choose>
                    <c:when test="${not empty loginUser.memberProfileImg}">
                    <button type="button" class="dropdown-toggle profile" data-bs-toggle="dropdown"><img class="title-img" src="${loginUser.memberProfileImg}" alt=""></button>
                    </c:when>
                    <c:otherwise>
                    <button type="button" class="dropdown-toggle profile" data-bs-toggle="dropdown"><img src="/mapping/resources/images/profile.png" alt=""></button>
                    </c:otherwise>	   
                </c:choose>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="#">1:1 채팅</a></li>
                    <li><a class="dropdown-item" href="#">마이페이지</a></li>
                    <li><a class="dropdown-item" href="#">로그아웃</a></li>
                </ul>
            </div>
        </div>
        <div class="travel-full">
            <div class="schedule-wrap all">
                <div class="schedule-menu">
                    <ul class="tab-menu">
                        <li><button tabindex="0" role="tab" aria-selected="true" aria-controls="tabPanel1" id="tab1" class="active">전체일정</button></li>
                    </ul>
                    <div class="btn-wrap">
                        <button class="edit-btn">편집</button>
                        <button type="button" data-bs-toggle="modal" data-bs-target="#shareModal">친구공유</button>
                    </div>
                    

                    <!-- 친구공유 modal -->
                    <div class="modal fade" id="shareModal">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content style">
                        
                                <!-- Modal Header -->
                                <div class="modal-header">
                                    <h4 class="modal-title">공유하기</h4>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                </div>
                        
                                <!-- Modal body -->
                                <div class="modal-body">
                                    <div class="layer-contents" id="snsCont">
                                        <div class="sns-share">
                                            <button type="button" title="새창"><img src="resources/images/logo_001.png" alt="mapping logo" style="width: 100px; height: 48px; padding: 10px;">친구공유</button>
                                            <button type="button" title="새창"><a id="kakaotalk-sharing-btn" href="javascript:shareMessage(${list[0].tripPlanNo})"><i class="ico-kakao"></i></a>카카오톡</button>
                                            <button type="button" onclick="shareFacebook()" title="새창"><i class="ico-facebook"></i>페이스북</button>
                                            <button type="button" onclick="shareNaverblog()" title="새창"><i class="ico-blog"></i>네이버 블로그</button>
                                        </div>
                                        <div class="form-control-map">
                                            <input class="copy-value" type="text" value="https://mapping/" title="공유 URL">
                                            <button onclick="copyText()" type="button" ><strong class="color">복사</strong></button>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                    <!--// 친구공유 modal -->
                </div>
                <div class="schedule">
                    <div class="tab-contents">
                        <div tabindex="0" role="tabpane2" id="tabPanel2" aria-labelledby="tab2">
                            <div class="step2">
                                <h4>${list[0].regionName}</h4>
                                <div class="date-time">${list[0].tripStartDate.substring(0,10)} - ${list[0].tripEndDate.substring(0,10)}</div>
                            </div>
                            <div class="schedule-wrap-all">
                                <div class="scroll">
                                <c:forEach var="i" begin = "1" end = "${maxNday}" step="1">
                                        <div>
                                            <div class="day-date">
                                                <h5>${i}일차</h5>
                                                <!-- <div>11.26(일)</div> -->
                                            </div>
                                            <ul>
                                            <c:set var="num" value="0" />
                                                <c:forEach var="p" items="${list}">
                                                    <c:if test="${i eq p.tripNday}">
                                                    	<c:set var="num" value="${num + 1}" />
                                                        <li><span class="number"><c:out value="${num}" /></span><div><div><span>${p.categoryName}</span><h5>${p.attractionName}</h5></div><img src="${p.attractionChangeNameImg}" alt=""></div></li>
                                                    </c:if>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                </c:forEach>
    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="map-wrap">
                <div id="map2" style="width:100%;height:100vh;"></div>
            </div>
        </div>
    </div>
    <form id="myform">
        <input type="hidden" id="url" value="http://localhost:8009/mapping/finalPlan.bo?tripPlanNo=${list[0].tripPlanNo}"><br/>
        <input type="hidden" id="title" value="mapping 일정 공유하기"><br/>
    </form>
</body>
</html>