<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추천 여행지 목록</title>
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
<script src="./resources/js/recommend.js"></script>
</head>
<body>
    <jsp:include page="../common/header.jsp" />
    <main class="contents recommend">
        <div class="inner-wrap">
            <h4 class="board-title">추천지 전체보기</h4>
            <div id="post-wrap" class="post-wrap">
               
            </div>
            <button id="btn-more" type="button" class="btn-more" onclick="recommendListMore">
                <i class="bi bi-plus-lg" style="color: #b2d8b5;font-size: 1.5rem;"></i>
                <em>목록 더보기</em>
            </button>
        </div>
    </main>
</body>
</html>