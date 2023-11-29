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
<link rel="stylesheet" href="/mapping/resources/css/chat.css">
</head>
<body>
    <jsp:include page="../common/header.jsp" />
    <div class="container">  
        <div class="chat-page">Chatting</div>
        <div class="chat-inner">
            <div class="chat-inner2">
                <div class="section1"> 
                    <div class="section1-list">
                        <div class="chat-list chat-list-first">
                            <img src="/mapping/resources/images/profile.png" alt="sunil">
                            <div class="list-user">
                                <h5>전영석 <span class="chat_date">Dec 25</span></h5>
                                <p>대화 내용 몇글자 제한</p>
                            </div>    
                            <div class="chat-count">2</div> 
                        </div>
                        <div class="chat-list">
                            <img src="/mapping/resources/images/profile.png" alt="sunil">
                            <div class="list-user">
                                <h5>전영석 <span class="chat_date">Dec 25</span></h5>
                                <p>대화 내용 몇글자 제한</p>
                            </div>    
                            <div class="chat-count">2</div> 
                        </div>
                        <div class="chat-list">
                            <img src="/mapping/resources/images/profile.png" alt="sunil">
                            <div class="list-user">
                                <h5>전영석 <span class="chat_date">Dec 25</span></h5>
                                <p>대화 내용 몇글자 제한</p>
                            </div>    
                            <div class="chat-count">2</div> 
                        </div>
                        <div class="chat-list">
                            <img src="/mapping/resources/images/profile.png" alt="sunil">
                            <div class="list-user">
                                <h5>전영석 <span class="chat_date">Dec 25</span></h5>
                                <p>대화 내용 몇글자 제한</p>
                            </div>    
                            <div class="chat-count">2</div> 
                        </div>
                    </div>
                    <button class="report">
                        <div>신고하기</div>
                        <img class="report-img" src="/mapping/resources/images/report.PNG" alt="">
                    </button>
                </div>  
                <div class="section2-outer">
                    <div class="section2">
                        <div class="chat-main">
                            <div class="in-msg">           
                                <img src="/mapping/resources/images/profile.png" alt="sunil">
                                <div class="received_msg">
                                    <div>여행 갈려 말려</div>
                                    <div class="time_date"> 11:01 AM    |    June 9</div>
                                </div>
                            </div>
                            <div class="out-msg">
                                <div class="sent_msg">
                                    <span class="time_date2"> 11:01 AM    |    June 9</span>
                                    <p>
                                        언제?
                                    </p>
                                </div>
                            </div>
                        </div> 
                        <div class="chat-main">
                            <div class="in-msg">           
                                <img src="/mapping/resources/images/profile.png" alt="sunil">
                                <div class="received_msg">
                                    <div>여행 갈려 말려</div>
                                    <div class="time_date"> 11:01 AM    |    June 9</div>
                                </div>
                            </div>
                            <div class="out-msg">
                                <div class="sent_msg">
                                    <span class="time_date2"> 11:01 AM    |    June 9</span>
                                    <p>
                                        언제?
                                    </p>
                                </div>
                            </div>
                        </div> 
                        <div class="chat-main">
                            <div class="in-msg">           
                                <img src="/mapping/resources/images/profile.png" alt="sunil">
                                <div class="received_msg">
                                    <div>여행 갈려 말려</div>
                                    <div class="time_date"> 11:01 AM    |    June 9</div>
                                </div>
                            </div>
                            <div class="out-msg">
                                <div class="sent_msg">
                                    <span class="time_date2"> 11:01 AM    |    June 9</span>
                                    <p>
                                        언제?
                                    </p>
                                </div>
                            </div>
                        </div> 
                        <div class="chat-main">
                            <div class="in-msg">           
                                <img src="/mapping/resources/images/profile.png" alt="sunil">
                                <div class="received_msg">
                                    <div>여행 갈려 말려</div>
                                    <div class="time_date"> 11:01 AM    |    June 9</div>
                                </div>
                            </div>
                            <div class="out-msg">
                                <div class="sent_msg">
                                    <span class="time_date2"> 11:01 AM    |    June 9</span>
                                    <p>
                                        언제?
                                    </p>
                                </div>
                            </div>
                        </div> 
                        <div class="chat-main">
                            <div class="in-msg">           
                                <img src="/mapping/resources/images/profile.png" alt="sunil">
                                <div class="received_msg">
                                    <div>여행 갈려 말려</div>
                                    <div class="time_date"> 11:01 AM    |    June 9</div>
                                </div>
                            </div>
                            <div class="out-msg">
                                <div class="sent_msg">
                                    <span class="time_date2"> 11:01 AM    |    June 9</span>
                                    <p>
                                        언제?
                                    </p>
                                </div>
                            </div>
                        </div> 
                        <div class="chat-main">
                            <div class="in-msg">           
                                <img src="/mapping/resources/images/profile.png" alt="sunil">
                                <div class="received_msg">
                                    <div>여행 갈려 말려</div>
                                    <div class="time_date"> 11:01 AM    |    June 9</div>
                                </div>
                            </div>
                            <div class="out-msg">
                                <div class="sent_msg">
                                    <span class="time_date2"> 11:01 AM    |    June 9</span>
                                    <p>
                                        언제?
                                    </p>
                                </div>
                            </div>
                        </div> 
                        <div class="chat-main">
                            <div class="in-msg">           
                                <img src="/mapping/resources/images/profile.png" alt="sunil">
                                <div class="received_msg">
                                    <div>여행 갈려 말려</div>
                                    <div class="time_date"> 11:01 AM    |    June 9</div>
                                </div>
                            </div>
                            <div class="out-msg">
                                <div class="sent_msg">
                                    <span class="time_date2"> 11:01 AM    |    June 9</span>
                                    <p>
                                        언제?
                                    </p>
                                </div>
                            </div>
                        </div>                 
                    </div>
                    <form>
                        <div class="chat-msg">             
                            <div class="chat-msg-inner">
                                <textarea name="chatting" id="summernote" type="text" class="chat-msg-input" placeholder="메세지를 입력해주세요.">
                                </textarea>                          
                            </div>
                            <button class="send-button" type="button">Button</button>
                        </div>
                    </form>
                </div>               

            </div>
 
        </div>


    </div>

    <script>
        //서머노트 그려주는 함수
        $(document).ready(function() {
        $('#summernote').summernote({
            lang: 'ko-KR',
            tabsize: 2,
            width: 870,
            height: 100,
            toolbar: [
                ['style', ['style']],
                ['font', ['bold', 'underline', 'clear']],
                ['color', ['color']],
                ['insert', ['link', 'picture', 'video']],
   
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