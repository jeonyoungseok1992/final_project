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
<!-- <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script> -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- jquery 3.7.1 -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="./resources/css/common.css">
<link rel="stylesheet" href="./resources/css/font.css">
<link rel="stylesheet" href="./resources/css/schedule-make.css">
<link rel="stylesheet" href="./resources/css/schedule-view.css">
</head>
<body>
    <div class="travel-wrap">
        <div class="header-fixed">
            <a href="" class="logo"><img src="./resources/images/logo_001.png" alt=""></a>
            <div class="dropdown">
                <button type="button" class="profile" data-bs-toggle="dropdown"><img src="./resources/images/profile.png" alt=""></button>
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
                                            <button type="button" title="새창"><img src="resources/images/logo_001.png" alt="mapping logo" style="width: 100px; height: 48px; padding-bottom: 10px;">친구공유</button>
                                            <button type="button" title="새창"><i class="ico-kakao"></i>카카오톡</button>
                                            <button type="button" title="새창"><i class="ico-facebook"></i>페이스북</button>
                                            <button type="button" title="새창"><i class="ico-blog"></i>네이버 블로그</button>
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
                                <h4>여행지</h4>
                                <div class="date-time">2023.11.25(토) - 2023.11.26(일)</div>
                            </div>
                            <div class="schedule-wrap-all">
                                <div class="scroll">
                                    <div>
                                        <div class="day-date">
                                            <h5>1일차</h5>
                                            <div>11.26(일)</div>
                                        </div>
                                        <ul>
                                            <li><span class="number">1</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                            <li><span class="number">2</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                            <li><span class="number">3</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                            <li><span class="number">4</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                        </ul>
                                    </div>
                                    <div>
                                        <div class="day-date">
                                            <h5>2일차</h5>
                                            <div>11.26(일)</div>
                                        </div>
                                        <ul>
                                            <li><span class="number">1</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                            <li><span class="number">2</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                            <li><span class="number">3</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                            <li><span class="number">4</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                        </ul>
                                    </div>
                                    <div>
                                        <div class="day-date">
                                            <h5>3일차</h5>
                                            <div>11.26(일)</div>
                                        </div>
                                        <ul>
                                            <li><span class="number">1</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                            <li><span class="number">2</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                            <li><span class="number">3</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                            <li><span class="number">4</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                        </ul>
                                    </div>
                                    <div>
                                        <div class="day-date">
                                            <h5>1일차</h5>
                                            <div>11.26(일)</div>
                                        </div>
                                        <ul>
                                            <li><span class="number">1</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                            <li><span class="number">2</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                            <li><span class="number">3</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                            <li><span class="number">4</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                        </ul>
                                    </div>
                                    <div>
                                        <div class="day-date">
                                            <h5>1일차</h5>
                                            <div>11.26(일)</div>
                                        </div>
                                        <ul>
                                            <li><span class="number">1</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                            <li><span class="number">2</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                            <li><span class="number">3</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                            <li><span class="number">4</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="map-wrap">
    
            </div>
        </div>
    </div>
    
    <script>
        let inputTag = document.querySelector(".copy-value");
                
        function copyText(){ 
        
            navigator.clipboard.writeText(inputTag.value).then(res=>{
                alert("url이 복사되었습니다.");
            })
        }
    </script>
</body>
</html>