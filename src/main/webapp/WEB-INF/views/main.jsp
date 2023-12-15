<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/mapping/resources/css/main.css">
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
<script src="./resources/js/main.js"></script>
</head>
<body>

	<jsp:include page="common/header.jsp" />
	<div id="section1">
        <div id="section1-inner">
            <div class="section1-search">
                <input class="section1-input" placeholder="어디로 여행을 떠나시나요?">
                <button>
                    <img class="section1-search-icon" src="/mapping/resources/images/icon_search.svg"/> 
                </button>
               
            </div>
        </div>
    </div>

    <div id="section2">
        <div class="recommend-title">추천여행</div>
        <div class="recommend-plus"><a href="recommendList.bo">더보기</a></div>
        <div class="recommend-content">
            
            
        </div>
    </div>
    <div id="section3">
        <div class="lc-question">어디로 여행을 떠나시나요?</div>
        <div class="section3-search">
            <input class="section3-input" placeholder="도시명으로 검색해보세요.?">
            <button>
                <img class="section3-search-icon" src="/mapping/resources/images/search2.png"/> 
            </button>   
        </div>
        <div id="section3-lc">
            <div class="section3-lc-inner" onclick="redirectToPlan()">
                
            </div>
        </div>
    </div>
</body>
</html>







