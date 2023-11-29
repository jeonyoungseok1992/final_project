<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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


<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
<link rel="stylesheet" href="./resources/css/boardEnrollForm.css">

</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
 
    <div class="schedule-container">
        <div id="map-container"></div>
        <div id="schedule-area">
            <div class="nDay-content"> 
                <div class="nDay">n일차</div>
                <div class="location">
                    <div class="location-img"><img src="resources/images/Jeonju.jpeg" alt="전주"></div>
                    <div class="location-img"></div>
                    <div class="location-img"></div>
                    <div class="location-img"></div>
                    <div class="location-img"></div>
                </div>
                <div class="nDay">n일차</div>
                <div class="location">
                    <div class="location-img"></div>
                    <div class="location-img"></div>
                    <div class="location-img"></div>
                    <div class="location-img"></div>
                    <div class="location-img"></div>
                
                   
                </div>
                <div class="nDay">n일차</div>
                <div class="location">
                    <div class="location-img"></div>
                    <div class="location-img"></div>
                    <div class="location-img"></div>
                    <div class="location-img"></div>
                    <div class="location-img"></div>
                </div>
                <div class="nDay">n일차</div>
                <div class="location">
                    <div class="location-img"></div>
                    <div class="location-img"></div>
                    <div class="location-img"></div>
                    <div class="location-img"></div>
                    <div class="location-img"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="notice-up-wrap" align="center">
        <form action="#" id="enroll-form" method="post">
	        <dl class="title">
	        	<dt><label>제목</label></dt>
	        	<dd><input type="text" name="title" required></dd>
	        </dl>
	        <dl class="content">
	            <dt><label>내용</label></dt>
	            <dd><textarea name="content" id="summernote" rows="10" style="resize: none;" required></textarea></dd>
	        </dl>  
            <div class="btn-wrap">
                <button type="button" onclick="history.back();">뒤로가기</button>
                <button type="reset">초기화</button>
                <button class="submit" type="button" onclick="goWrite(this.form)">등록하기</button>
            </div>
        </form>
    </div>

    <script>
        //서머노트 그려주는 함수
        $(document).ready(function() {
        $('#summernote').summernote({
            lang: 'ko-KR',
            tabsize: 2,
            width:1100,
            height: 270,
            toolbar: [
                ['style', ['style']],
                ['font', ['bold', 'underline', 'clear']],
                ['color', ['color']],
                ['para', ['ul', 'ol', 'paragraph']],
                ['table', ['table']],
                ['insert', ['link', 'picture', 'video']],
                ['view', ['fullscreen', 'codeview', 'help']]
            ]
        });
        });

        //서머노트에 있는 name값 데이터 가져와서 넘겨주는 함수
        function goWrite(frm) {
            var title = frm.title.value;
            var content = frm.content.value;
            
            if (title.trim() == ''){
                alert("제목을 입력해주세요");
                return false;
            }
            if (content.trim() == ''){
                alert("내용을 입력해주세요");
                return false;
            }
            frm.submit();
        }
    </script>

</body>
</html>