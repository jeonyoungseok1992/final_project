<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추천 여행지 상세페이지</title>
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
<link rel="stylesheet" href="./resources/css/recommend.css">
</head>
<body>
    <jsp:include page="../common/header.jsp" />
    <main class="contents recommend">
        <div class="inner-wrap">
            <div class="board-view">
                <div class="board-header">
                    <div>
                        <span class="category">여행지</span>
                        <h3 class="board-title">추천여행지 상세페이지 입니다.</h3>
                        <div class="additional-info">
                            <span>관리자</span>
                            <span>23.11.28</span>
                        </div>
                    </div>
                </div>
                <div class="board-contents">
                    <div class="img-area">
                        <img src="./resources/images/recommend_sample_01.jpg" alt="">
                    </div>
                    <p class="text-area">
                        11월 제주도는 귤, 감귤, 밤 등
                        <br>
                        제주도의 제철 과일을 맛볼 수 있는 달이기도,
                        <br>
                        붉게 물든 단풍나무, 노랗게 물든 은행나무 등
                        <br>
                        아름다운 가을 풍경을 감상하며, 
                        <br>
                        가을의 끝자락을 느낄 수 있는 달입니다.
                        <br>
                        <br>
                        이러한 기대감으로
                        <br>
                        23년 11월에 제주도를 방문 예정인 여러분께서
                        <br>
                        날씨, 온도 정보 뿐만이 아닌
                        <br>
                        옷은 어떻게 챙겨 가야하는지? 
                        <br>
                        1주차 부터 4주차까지 안내해드리도록 하겠습니다
                    </p>
                    <div class="img-area">
                        <img src="./resources/images/recommend_sample_02.jpg" alt="">
                    </div>
                    <p class="text-area">
                        11월 제주도는 귤, 감귤, 밤 등
                        <br>
                        제주도의 제철 과일을 맛볼 수 있는 달이기도,
                        <br>
                        붉게 물든 단풍나무, 노랗게 물든 은행나무 등
                        <br>
                        아름다운 가을 풍경을 감상하며, 
                        <br>
                        가을의 끝자락을 느낄 수 있는 달입니다.
                    </p>
                </div>
                <div class="btn-wrap">
                    <button type="button" class="btn-style">삭제</button>
                    <button type="button" class="btn-style">수정</button>
                </div>
            </div>
            <div class="btn-wrap justify">
                <div>
                    <button href="#none" class="btn-style">목록</button>
                </div>
                <div>
                    <button type="button" class="btn-style">이전</button>
                    <button type="button" class="btn-style">다음</button>
                </div>
            </div>
        </div>
    </main>
</body>
</html>