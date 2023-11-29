<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모달</title>
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
<link rel="stylesheet" href="./resources/css/grade.css">
</head>
<body>
    <jsp:include page="../common/header.jsp" />
    <!-- 별점주기 모달 -->
    <main class="contents">
        <!-- Button to Open the Modal -->
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#gradeModal">
            별점주기 모달
        </button>
        
        <!-- The Modal -->
        <div class="modal fade" id="gradeModal">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content style grade">
            
                    <!-- Modal Header -->
                    <div class="modal-header">
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
            
                    <!-- Modal body -->
                    <div class="modal-body">
                        <h4 class="modal-title">성산 일출봉<span>명소</span></h4>
                        <div class="img-area">
                            <img src="./resources/images/modal_sample.jpg" alt="">
                        </div>
                        <div class="text-area">
                            <p>성산 일출봉은 명소인가요 안가봐서 모르겠습니다.</p>
                        </div>
                        <div class="grade">
                            <div class="ui-grade half" role="radiogroup">
                                <button type="button" role="radio" aria-checked="false" class="">1점</button>
                                <button type="button" role="radio" aria-checked="false" class="">2점</button>
                                <button type="button" role="radio" aria-checked="false" class="">3점</button>
                                <button type="button" role="radio" aria-checked="false" class="">4점</button>
                                <button type="button" role="radio" aria-checked="false" class="">5점</button>
                                <button type="button" role="radio" aria-checked="false" class="">6점</button>
                                <button type="button" role="radio" aria-checked="false" class="">7점</button>
                                <button type="button" role="radio" aria-checked="false" class="">8점</button>
                                <button type="button" role="radio" aria-checked="false" class="">9점</button>
                                <button type="button" role="radio" aria-checked="false" class="">10점</button>
                                <span class="result-text">0점</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>



        <!-- 신고하기 -->
        <!-- Button to Open the Modal -->
        <button type="button" data-bs-toggle="modal" data-bs-target="#reportModal">
            <i style="font-size: 1.5rem; color: #000;" class="bi bi-exclamation-triangle"></i>
        </button>
        
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
            </div>
        </div>
    </main>
    
    <script>
        // 별점주기
        let $ratings = $('.ui-grade');

		
        $ratings.each(function(){
            if( $(this).data('init') ) return;
            $(this).data('init' , true);

            let $star = $(this).find('button');

            //default
            let $checkedStar = $(this).find('[aria-checked="true"]');
            if($checkedStar){
                $checkedStar.addClass('checked');
                $checkedStar.prevAll().addClass('checked');
            }

            //mouseover
            $star.on('mouseover', function(){
                $star.not($(this).addClass('hover')).removeClass('hover');
                $(this).prevAll().addClass('hover');
            });
            $star.on('mouseleave', function(){
                $star.removeClass('hover');
            });

            //click
            $star.on('click', function(){
                let $this = $(this);
                let txt = $this.text();
                let $result = $this.parent().find('.result-text');

                $star.not($this.addClass('checked')).removeClass('checked');
                $this.prevAll().addClass('checked');
                $star.not($this.attr('aria-checked', true)).attr('aria-checked', false);

                if( txt ){
                    $result.text(txt);
                }
            });
        });
    </script>
</body>
</html>