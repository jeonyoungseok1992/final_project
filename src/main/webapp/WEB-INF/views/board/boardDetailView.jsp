<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

     

    <!DOCTYPE html>
    <html lang="en">


    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>



        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" href="./resources/css/common.css">
        <link rel="stylesheet" href="./resources/css/grade.css">
        <link rel="stylesheet" href="./resources/css/font.css">

        <link rel="stylesheet" href="./resources/css/detailView.css">
    </head>

        <body>
            
        <div class="container" >

            <jsp:include page="../common/header.jsp" />

            <!--지도 그려주는 곳-->
           
                <section class="map-area">

                    <div id="map-point" align="center">
    
    
                        <div id="map-board">
                            <div id="map-map">

                              
                            </div>
    
                            <div id="map-list">

                                <!-- 수정하기, 삭제하기 버튼은 본인만-->
                                <c:if test="${ loginUser.memberId eq b.boardWriter }">
                                    <div id="map-modify">

                                        <img src="https://cdn-icons-png.flaticon.com/512/8054/8054391.png" type="button" 
                                        class="btn btn-primary dropdown-toggle" 
                                        data-bs-toggle="dropdown"
                                        style="background: none; border: none;
                                                width: 70px; height: 55px;">
                                    
                                        </img>
                                        <ul class="dropdown-menu" style="text-align: center;" align="center">
                                        <li><a class="dropdown-item" href="#">일정수정</a></li>
                                        <li><a class="dropdown-item" href="#">삭제</a></li>
                                        </ul>

                                    </div>
                                </c:if>

                                <form action="" method="post" id="postForm">
                                    <input type="hidden" name="bno" value="${b.boardNo}">
                                </form>


                                <script>
                                    function postFormSubmit(num){
                                        if(num === 1){
                                            $("#postForm").attr('action', 'updateForm.bo');
                                            //document.querySelector("#postForm").setAttribute('action','updateForm.bo');
                                        } else {
                                            $("#postForm").attr('action', 'delete.bo');
                                            //document.querySelector("#postForm").setAttribute('action','delete.bo');
                                        }
                                        $("#postForm").submit();
                                        //document.querySelector("#postForm").submit();
                                    }
                                </script>
                                
                                
                            </div>

                        </div>
                    </div>

            </section>
    
                
            
                    <!--제목-->        
            <div id="board-title" style="height: 100%;">

                <!-- 수정하기, 삭제하기 버튼은 본인만-->
                <c:if test="${ loginUser.memberId eq b.boardWriter }">
                    <div id="board-modify">
                        <img src="resources/images/점점점.png" 
                            type="button" class="btn btn-primary dropdown-toggle" 
                            data-bs-toggle="dropdown"
                            style="background: none; border: none;
                                    width: 60px; height: 45px;">
                        </img>

                        <ul class="dropdown-menu" style="text-align: center;" align="center">
                            <li><a class="dropdown-item" onclick="postFormBoardSubmit(1)" >수정</a></li>
                            <li><a class="dropdown-item" onclick="postFormBoardSubmit(2)">삭제</a></li>
                        </ul>
                    </div>
                </c:if>
                
                    <form action="" method="post" id="postFormBoard">
                    <input type="hidden" name="bno" value="${b.boardNo}">
                    </form>
                    
                <script>
                    function postFormBoardSubmit(num){
                        if(num === 1){
                            $("#postFormBoard").attr('action', 'updateForm.bo');
                        } else {
                            $("#postFormBoard").attr('action', 'delete.bo');
                        }
                        $("#postFormBoard").submit();
                    }
                </script>


                <table class="detail-content">
                    <tr id="detail-category">
                        <th id="main-category">여행 후기 게시판</th>
                        
                    </tr>

                    <tr id="detail-title">
                        <th id="main-title">${b.boardTitle}</th>

                    </tr>

                    <tr>
                        <!--프로필 수정-->
                        <th id="profile-modify" ">

                                <div>
                                    <img  src="resources/images/profile.png" 
                                    type="button" class="btn btn-primary dropdown-toggle" 
                                    data-bs-toggle="dropdown"
                                    style="background: none; border: none;
                                            width: 80px; height: 80px;
                                            border-radius: 50%;">
                                    </img>
                                    <span id="board-writer">${b.boardWriter}</span>

                                    <ul class="dropdown-menu" style="text-align: center;" align="center">
                                        <li><a class="dropdown-item" href="#">프로필</a></li>
                                        <li><a class="dropdown-item" href="#">친구신청</a></li>
                                        <li><a class="dropdown-item" href="#">대화화기</a></li>
                                    </ul>
                                </div>
                                    
                                
                                    
                        </th>

                        

                        <tr>
                            <th><span class="board-createDate">${b.boardCreateDate}</span></th>
                        </tr>
                    </tr>

                </table>



                <!--내용-->
            <div id="main-content">
                <p class="bd-content">
                    <span>${b.boardContent} </span>
                </p>
            </div>

                <div id="like-warn">
                    <tr>
                        <th style="vertical-align:middle; width: 45px ;height: 45px;">
                            <button id="like">
                                <img src="resources/images/like.png" style="width: 45px; height: 45px;" alt="좋아요" onclick="like()"> 27
                            </button>
                        </th>

                        <th style="vertical-align:middle; width: 50px ;height: 50px;">
                            <button type="button" data-bs-toggle="modal" data-bs-target="#reportModal" id="warn">
                                <i style="font-size: 1.5rem; color: #000;" class="bi bi-exclamation-triangle"></i>
                            </button>
                        </th>
                    </tr>
                    

                </div>

                <!--댓글 -->
                <table id="replyArea" class="table" align="center">
                    <thead>
                        <c:choose>
                            <c:when test="${ empty loginUser }">
                                <tr>
                                    <th>
                                        <textarea class="form-control" name="reply-text" id="reply-text" readonly cols="55" rows="2" style="resize:none; width:100%;">로그인 후 이용 가능합니다.</textarea>
                                    </th>
                                </tr>

                            </c:when>

                            <c:otherwise>
                                <tr> 
                                    <th>
                                        <textarea class="form-control"  name="content" id="content" cols="55" rows="2" style="resize:none; width:100%;"></textarea>
                                    </th>

                                    <th style="vertical-align:middle; width: 80px;" >
                                        <button class="btn btn-secondary" style="background: #b2d8b5; border: #b2d8b5;"  onclick="addReply();">
                                                등록
                                        </button>
                                    </th>
                                </tr>
                            </c:otherwise>
                        </c:choose>  

                        <tr>
                            <th style="border: none; vertical-align:middle;"
                                colspan="3">댓글(<span id="rcount"></span>)</th>
                        </tr> 
                        
                        
                    </thead>
                   

                </table>
                <div id="reply-main" >
                    
                    


                </div>
                        
         </div>

        <script>
            $(function(){
                selectReplyList();
            })

            function selectReplyList(){
                const memberId = "${ loginUser.memberId}"
                console.log(memberId);

                $.ajax({
                    url:"reviewRlist.bo",
                    data: {
                        bno: "${b.boardNo}"
                    },
                    success:function(list){
                        let str="";
                        for (reply of list){
                            str += 
                                `<table align="center"  class="reply-area" style="vertical-align:middle;">`+
                                ` <tr id="reply-profile">
                                        <th id="rp-profile-img" style="width: 80px; "> 
                                            <div>
                                            <img  src="resources/images/profile.png" 
                                            type="button" class="btn btn-primary dropdown-toggle" 
                                            data-bs-toggle="dropdown"
                                            style="background: none; border: none;
                                                    width: 80px; height: 80px;
                                                    border-radius: 50%;">
                                            </img>
                                        

                                            <ul class="dropdown-menu" style="text-align: center;" align="center">
                                                <li><a class="dropdown-item" href="#">프로필</a></li>
                                                <li><a class="dropdown-item" href="#">친구신청</a></li>
                                                <li><a class="dropdown-item" href="#">대화화기</a></li>
                                            </ul>
                                        </div></th>

                                        <th>` +reply.replyWriter+ '</th>';
                                        if (reply.replyWriter === memberId) {
                                        str +=
                                    '<th colspan="3">'+
                                        '<div class="reply-modify" >'+
                                        '<img  src="resources/images/점점점.png"'+
                                           'type="button" class="btn btn-primary dropdown-toggle"'+ 
                                           'data-bs-toggle="dropdown"'+
                                           'style="background: none; border: none; width: 60px; height: 45px;">'+
                                            '</img>'+
                                             `<ul class="dropdown-menu" style="text-align: center;" align="center">
                                                <li><a class="dropdown-item"  data-bs-toggle='modal' data-bs-target='#replyUpdateModal'  onclick="numinit(`+reply.replyNo+`)" >수정</a></li>
                                                <li><a class="dropdown-item" onclick="postFormBoardSubmit(2)">삭제</a></li>
                                             </ul>` +      
                                      
                                            
                                        
                                            '</div>'+
                                            '</th>';
                                                
                                            }
                                    '</tr>';
                             str+= 

                                    `<tr>`+
                                        `<td id="reply-content" colspan="2">` + reply.replyContent+ `</td>` +
                                            
                                    ` <td colspan="1"  id="reply-warn-area">` +
                                            

                                         `<button type="button" data-bs-toggle="modal" data-bs-target="#reportModal" id="reply-warn">
                                                <i style="font-size: 1.5rem; color: #000;" class="bi bi-exclamation-triangle"></i>
                                            </button>




                                            
                                        </td>` +

                                ` </tr>` ;
                                
                             
                                    if(reply.replyWriter !== memberId && memberId !==null &&memberId !=="" ){
                                    str += `<tr>
                                        <th  colspan="2" id="re-reply" >
                                            <button class="btn btn-secondary" style="background: #b2d8b5; border: #b2d8b5;">
                                                    답글
                                            </button>
                                        </th>
                                    </tr>` ;
                                }
                            str += '<tr>'+
                                '<th class="createDate" colspan="3">'+ reply.replyCreateDate + `</th>`
                                '</tr>'

                      
                        					

                           + ` </table> `





                        }

                        document.querySelector("#reply-main").innerHTML = str;
                        document.querySelector("#rcount").innerHTML = list.length;    
                    },
                    error:function(){
                        console.log("reviewRlist.bo ajax통신 실패");
                    }
                })
            }


        

            //댓글을 추가해주는 메서드
            function addReply(){
                    $.ajax({
                        url: "rinsert.bo",
                        data: {
                            replyBno: '${b.boardNo}',
                            replyWriter: '${loginUser.memberNo}',
                            replyContent: $("#content").val()

                            
                        },
                        success: function(res){
                                //성공시 다시 그려주기
                            if (res === "success"){
                                selectReplyList();
                                $("#content").val("");
                            }
                        },
                        error: function(){
                            console.log(" ajax통신 실패");
                        }
                    })
                }


               
                
        </script>



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
                                    <dl>${reply.replyNo} </dl>
                                    <dl>
                                        <dt><strong>아이디</strong></dt>
                                        <dd>${reply.replyWriter} </dd>
                                    </dl>
                                    <dl>
                                        <dt><strong>내용</strong></dt>
                                        <dd><p>${reply.replyNo}</p></dd>
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



               <!-- 댓글 수정용 Modal -->
				    <div class="modal fade" id="replyUpdateModal">
				        <div class="modal-dialog modal-dialog-centered">
			               <div class="modal-content">
			           				
				                   <!-- Modal Header -->
				                   <div class="modal-header" style="border-bottom: none; padding: 24px;">
				                   		<h4 class="modal-title updatest" style="display: block; text-align: center; width: 100%;">댓글 수정</h4>
						                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
						           </div>
						        
					                <!-- Modal body -->
					                <div class="modal-body delete" align="center" style="padding: 24px;">
                                            <input type="hidden" name="replyNo" class="updateReplyNo">
					                    	 <textarea name="replyContent" rows="10" style="resize: none;width: 350px;" placeholder="수정할 댓글 내용을 입력하세요" required></textarea>
					                    	<button type="submit" data-bs-dismiss="modal" class="btn btn-sm btn-danger updatest" onclick="reviewUpdateReply(document.querySelector('.updateReplyNo').value)">수정하기</button>
					                </div>
					            </div>
				        </div>
				    </div> 

                    <script>
                        function reviewUpdateReply (replyNo){
                            $.ajax({
                                url : "reviewReplyUpdate.bo",
                                data : {
                                    replyBoardNo : '${list[0].boardNo}',
                                    replyNo : replyNo,
                                    replyContent : document.querySelector('[name="replyContent"]').value,
                                    
                                },
                                success : function(res){
                                    if(res === "success")
                                    selectReply();
                                    document.querySelector('[name="replyContent"]').value = '';

                                    console.log(res)
                                },
                                error:function(){
                                    console.log("reviewReplyUpdate.bo ajax 통신 실패");
                                }
                            })
                        }
                        function numinit(num){
                            document.querySelector('.updateReplyNo').value = num;
                        }
                      </script>          



                    <!-- 댓글 삭제용 Modal -->
				    <div class="modal fade" id="replyDeleteModal">
				        <div class="modal-dialog modal-dialog-centered">
		               <div class="modal-content">
		           
		                   <!-- Modal Header -->
		                   <div class="modal-header" style="border-bottom: none; padding: 24px;">
		                   <h4 class="modal-title updatest" style="display: block; text-align: center; width: 100%;">댓글 삭제</h4>
				                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				                </div>
				        
				                <!-- Modal body -->
				                <div class="modal-body delete" align="center" style="padding: 24px;">
									<input type="hidden" class="body-replydel" id="replydelete">
				                    	댓글을 삭제하시겠습니까?
				                    	<br>
				                    	<br>
				                    	<br>
				                    	<button type="button" data-bs-dismiss="modal" class="btn btn-sm btn-danger updatest" onclick="deleteReply(document.getElementById('replydelete').value)">삭제하기</button>
				                </div>
				            </div>
				        </div>
				    </div>
            
    </div>

</body>
</html>             
        

          


   
