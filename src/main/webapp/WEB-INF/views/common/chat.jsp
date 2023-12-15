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

<script src="./resources/js/chat.js"></script>
<link rel="stylesheet" href="/mapping/resources/css/chat.css">
</head>
<body>
    <jsp:include page="../common/header.jsp" />
    <div class="container">  
       
        <div class="chat-inner">
           
            <div class="chat-inner2">
                <div class="section1"> 
                    <div class="chat-page">Chatting</div>
                    <div id="section1-list">
                    </div>

                </div>  
            <div class="section2-outer">
                <div id="section2">
                        <div id="chat-main">
                        </div>
                    <div id="text-outer">
                        <div id="text-form">
                           <button id="report-button" type="button" data-bs-toggle="modal" data-bs-target="#reportModal">
                               <i style="font-size: 1.5rem; color: #000;" class="bi bi-exclamation-triangle"></i>
                           </button>
                               <div id="chat-msg">               
                               </div>
                           </div>
                    </div>

                </div>               

            </div>
 
        </div>


    </div>
    
    
    
     <!-- The Modal -->
        <div class="modal fade" id="reportModal">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content style">
            
                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">신고하기</h4>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
            
                    <!-- Modal body -->
                    <div class="modal-body">
                        <div class="layer-contents" id="report">
                            <div class="dl-type">
                                <dl>
                                    <dt><strong>아이디</strong></dt>
                                    <dd>홍길동</dd>
                                </dl>
                                <dl>
                                    <dt><strong>내용</strong></dt>
                                    <dd><p>이것도 여행계획이라고 짠건가? ㅉㅉ 이것도 여행계획이라고 짠건가? ㅉㅉ</p></dd>
                                </dl>
                            </div>
                            <br>
                            <fieldset class="form">
                                <legend>신고정보입력</legend>
                                <div class="form-check-map-group">
                                    <div class="form-check-map checked"><input type="radio" name="report" id="report01" checked=""><label for="report01">광고 / 홍보성 댓글</label></div>
                                    <div class="form-check-map"><input type="radio" name="report" id="report02"><label for="report02">욕설 / 반말 /부적절한 언어</label></div>
                                    <div class="form-check-map"><input type="radio" name="report" id="report03"><label for="report03">음란성 메시지</label></div>
                                    <div class="form-check-map"><input type="radio" name="report" id="report04"><label for="report04">기타</label></div>
                                </div>
                                <div class="form-control-map">
                                    <textarea name="" id="" title="신고사유 입력" cols="" rows="7" placeholder="기타 신고사유를 입력해주세요."></textarea>
                                    <div class="bytes">0 / 500 자</div>
                                </div>
                                <p class="report-text">댓글 신고 사유를 선택해주세요.<br>
                                    신고 후 관리자에게 신고 사항이 전달됩니다.</p>
                            </fieldset>
                        </div>
                        <div class="layer-btns">
                            <button type="button" class="btn-lg-line cancel" data-bs-dismiss="modal">취소</button>
                            <button type="button" class="btn-lg-solid confirm">신고하기</button>
                        </div>
                    </div>

                </div>
                <input id="plz" type="hidden" value="${youNo}" >
                <input id="plz2" type="hidden" value="${loginUser.memberNo}" >
            </div>
        </div>


</body>
</html>