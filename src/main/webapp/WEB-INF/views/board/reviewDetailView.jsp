<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

     

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
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <!-- jquery 3.7.1 -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>


    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
    
    <link rel="stylesheet" href="./resources/css/grade.css">
    <link rel="stylesheet" href="./resources/css/scheduleListModal.css">
    <link rel="stylesheet" href="./resources/css/board-card.css">
    <link rel="stylesheet" href="./resources/css/boardReviewDetailView.css">

    <script src="./resources/js/reviewEnrollForm.js"></script>
    <script src="./resources/js/board-api.js"></script>
    <script src="./resources/js/reviewDetailView.js"></script>

</head>
<body onload="selectReply()">

            <jsp:include page="../common/header.jsp" />

            <div class="schedule-container">
                
                <div id="map" ><img src="${list[0].tripPlanThumbnail}" alt="" style="width: 100%; height: 100%;"></div>
                
                <div id="schedule-area">
    
                    <div class="nDay-content">
    
                    <c:forEach var="i" begin = "1" end = "${list[list.size() - 1].tripNday}" step="1">
    
                        <div class="nDay">${i}일차</div>
                    
    
                              <div class="location">
                            <c:forEach var="p" items="${list}">
                                <c:choose>
                                    <c:when test="${i eq p.tripNday}">
                                        <div data-bs-toggle="modal" data-bs-target="#gradeModal${i}${p.attractionNo}"
                                            class="location-img"><img src="${p.attractionChangeNameImg}"
                                                alt="장소""> 
                                        </div>
                                        <!-- The Modal -->
                                        <div class="modal fade" id="gradeModal${i}${p.attractionNo}">
                                            <div class="modal-dialog modal-dialog-centered">
                                                <div class="modal-content style grade">
                                                    <!-- Modal Header -->
                                                    <div class="modal-header">
                                                        <button type="button" class="btn-close"
                                                            data-bs-dismiss="modal"></button>
                                                    </div>

                                                    <div class="modal-body">

                                                        <h4 class="modal-title">${p.attractionName}<span>${p.categoryName}</span>
                                                        </h4>



                                                        <div class="img-area">
                                                            <img src="${p.attractionChangeNameImg}"
                                                                alt="">
                                                        </div>


                                                        <div class="text-area" style="height: 100%;">
                                                            <p>${p.attractionAddress}</p>
                                                        </div>
                                                        

                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </c:when>
                                </c:choose>
                            </c:forEach>


                        </div>
                    </c:forEach> 
                    
                    
                        
    
                        
    
                    </div>
    
                </div>
    
            </div>
                 
    
                
            <section id="title-area">
                     <!--제목-->
                <div id="board-title">
                        <c:if test="${loginUser.memberNo eq list[0].memberNo}">
                        <div class="dropdown">
                            <i class="bi bi-pencil-square dropdown-toggle" type="button" data-bs-toggle="dropdown"
                                aria-expanded="false" id="editBtn2"></i>
                            <ul class="dropdown-menu haha">
                                <li><a class="dropdown-item" href="updateReviewForm.bo?boardNo=${list[0].boardNo}">수정</a></li>
                                <li><a class="dropdown-item" href="reviewDelete.bo?boardNo=${list[0].boardNo}">삭제</a></li>
                            </ul>
                        </div>
                        </c:if>

                        <!--board제목부분 컨텐츠 영역-->
                        <table class="detail-content">
                            <tr id="detail-category">
                                <th id="main-category">같이가요 게시판</th>
                                
                            </tr>

                            <tr id="detail-title">
                                <th id="main-title">${list[0].boardTitle}</th>
    
                            </tr>

                            <tr>
                                <!--프로필 수정-->
                                <th id="profile-modify">

                                        <div>
                                            <img src="resources/images/profile.png" 
                                            type="button" class="btn btn-primary dropdown-toggle" 
                                            data-bs-toggle="dropdown" id="profileClickBtn">
                                            </img>
                                            <span id="board-writer">${list[0].boardWriter}</span>

                                            <ul class="dropdown-menu hoho" style="text-align: center;" align="center">
                                                <li><a class="dropdown-item" href="#">프로필</a></li>
                                                <li><a class="dropdown-item" href="#">친구신청</a></li>
                                                <li><a class="dropdown-item" href="#">대화화기</a></li>
                                            </ul>
                                        </div>
                                            
                                      
                                         
                                </th>

                                

                                <tr>
                                    <th><span class="board-createDate">${list[0].boardModifyDate}</span></th>
                                </tr>
                            </tr>
                        </table>

                    <!--내용-->
                    <div id="main-content">
                        <div class="bd-content" >
                            <span>${list[0].boardContent}</span>
                        </div>
                    

                        <div id="like-warn">
                            <tr>
                                <th style="vertical-align:middle; width: 45px ;height: 45px;">
                                    <button id="like">
                                        <img src="resources/images/like.png" style="width: 45px; height: 45px;" alt="좋아요"> 27
                                    </button>
                                </th>

                                <th style="vertical-align:middle; width: 50px ;height: 50px;">
                                    <button type="button" data-bs-toggle="modal" data-bs-target="#reportModal" id="warn">
                                        <i style="font-size: 1.5rem; color: #000;" class="bi bi-exclamation-triangle"></i>
                                    </button>
                                </th>
                            </tr>
                        </div>

                     
                        <table id="replyArea" class="table" align="center">
                                <c:choose>
                                    <c:when test="${ empty loginUser }">
                                        <tr>
                                            <th>
                                                <textarea class="form-control" readonly cols="55" rows="2" style="resize:none; width:100%;">로그인 후 이용 가능합니다.</textarea>
                                            </th>
                                        </tr>

                                    </c:when>

                                    <c:otherwise>
                                        
                                        <tr> 
                                            <th>
                                                <textarea class="form-control" id="content"  cols="55" rows="2" style="resize:none; width:100%;"></textarea>
                                            </th>

                                            <th style="vertical-align:middle; width: 80px;" >
                                                <button class="btn btn-secondary" style="background: #b2d8b5; border: #b2d8b5;" onclick="addReply()">
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
                            </table>
                               
                                <!--댓글영역-->
                                <table align="center"  class="reply-area" style="vertical-align:middle;">

                               </table> 

                    
                    </div>      
                    <!--메인 영역 끝-->



             
    

                    </div>
                    <!--board-title 영역 끝-->
                </section>

                
                <script>
                    var listlength ='';
                function selectReply(){
                    $.ajax({
                        url : "reviewRlist.bo",
                        data : {
                        boardNo : '${list[0].boardNo}'
                        },
                        success : function(rlist){
                            $('#rcount').text(rlist.length);
                            let str = '';

                            if(rlist.length === 0){
                                str = `<div style="margin-bottom: 200px; padding-top: 100px; display: flex; justify-content: center; align-items: center;">댓글 리스트가 존재하지 않습니다.</div>`
                            } 
                            for(reply of rlist){
                               str += `
                                        <tr class="reply-profile">
                                        <th class="rp-profile-img" style="width: 80px; "> 
                                            <div>
                                            <img  src="` + reply.memberProfileImg + `" alt="프로필없음" 
                                            type="button" class="btn btn-primary dropdown-toggle" 
                                            data-bs-toggle="dropdown"
                                            style="background: none; border: none;
                                                    width: 50px; height: 50px;
                                                    border-radius: 50%;">
                                            </img>
                                        

                                            <ul class="dropdown-menu" style="text-align: center;" align="center">
                                                <li><a class="dropdown-item" href="#">프로필</a></li>
                                                <li><a class="dropdown-item" href="#">친구신청</a></li>
                                                <li><a class="dropdown-item" href="#">대화화기</a></li>
                                            </ul>
                                        </div></th>

                                        <th>` +reply.replyWriter+ `</th>
                                        <th style="width: 150px;">`+ reply.replyModifyDate + `</th>
                                </tr>
                               
                                    <tr>`+
                                        `<td class="reply-content" colspan="2">` + reply.replyContent+ `</td>`;
                                   
                                   
                                 if(!(reply.replyWriter === "${loginUser.memberId}")) {         
                                   str += ` <td colspan="1" id="reply-warn-area">
                                         <button type="button" data-bs-toggle="modal" data-bs-target="#reportModal" id="reply-warn">
                                                <i style="font-size: 1.5rem; color: #000;" class="bi bi-exclamation-triangle"></i>
                                            </button>




                                            
                                        </td>`;
                                    }
                                str += ` </tr>

    

                            <tr>
                                <th  colspan="3" id="re-reply" >
                                    <button class="btn btn-secondary" style="background: #b2d8b5; border: #b2d8b5;">
                                            답글
                                    </button>
                                    `;


                            if(reply.replyWriter === "${loginUser.memberId}") {
                                str +=`
                                <button class="btn btn-secondary" data-bs-toggle='modal' data-bs-target='#replyUpdateModal' style="background: #b2d8b5; border: #b2d8b5;" onclick="numinit(`+reply.replyNo+`)">수정</button>
                                <button class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#myModal" style="background: #b2d8b5; border: #b2d8b5;" onclick="numinit(`+reply.replyNo+`)">
                                            삭제
                                        </button>`;
                            }
                            
                                    
                            str +=`
                                </th>
                            </tr>
                            `;
                            }
                         
                            $(".reply-area").html(str);
                    
                        },
                        error:function(){
                            console.log("reviewRlist.bo ajax 통신 실패");
                        }
                    })
                }

                
                

                //댓글을 추가해주는 메서드
                function addReply(){
                    $.ajax({
                        url : "reviewReplyInsert.bo",
                        data:{
                            replyBoardNo : '${list[0].boardNo}',
                            replyWriter : '${loginUser.memberNo}',
                            replyContent : $("#content").val()
                        },
                        success : function(res){
                            //성공시 다시 그려주기
                            if(res === "success")
                            selectReply();
                            $("#content").val("");
                            
                        },
                        error:function(){
                            console.log("reply 댓글추가 통신 실패");
                        }
                    })  
                }
                </script>
                <script>
                    function reviewReplyDelete(num) {
                    $.ajax({
                        url : "reviewReplyDelete.bo",
                        data : {
                            replyBoardNo : '${list[0].boardNo}',
                            replyNo : num,
                        },
                        success : function(res){
                            if(res === "success")
                            selectReply();
                        },
                        error:function(){
                            console.log("reviewReplyDelete.bo ajax 통신 실패");
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
        
            <!-- 댓글 삭제 모달 Modal -->
            <div class="modal fade" id="myModal">
                <div class="modal-dialog">
                    <div class="modal-content" style="margin-top: 300px;">
                
                        <!-- Modal body -->
                        <div class="modal-body" style="font-size: 18px; font-weight: 500; text-align: center;">
                            댓글을 정말 삭제하시겠습니까?
                        </div>
                
                        <!-- Modal footer -->
                        <div class="modal-footer" style="display: flex; justify-content: center;">
                            <input type="hidden" name="replyNo" class="updateReplyNo">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="background: lightgray; width: 200px; height: 50px; border: none; ">Cancle</button>
                            <button id="footerBtn" type="button" onclick="reviewReplyDelete(document.querySelector('.updateReplyNo').value)" class="btn btn-secondary" style="background: #b2d8b5; width: 200px; height: 50px; border: none;" data-bs-dismiss="modal">Ok</button>
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
					                    	<button type="submit" data-bs-dismiss="modal" class="btn btn-sm btn-danger updatest" onclick="updateTogetherReply(document.querySelector('.updateReplyNo').value)">수정하기</button>
					                </div>
					            </div>
				        </div>
				    </div> 
                    
                  <script>
                    function updateReviewReply (replyNo){
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
                            },
                            error:function(){
                                console.log("reviewRlist.bo ajax 통신 실패");
                            }
                        })
                    }
                    function numinit(num){
                        document.querySelector('.updateReplyNo').value = num;
                    }
                    function contentsInit1(cont){
                        document.querySelector('.rptContent').value = cont;
                        document.getElementById('rcontent').innerText =cont;
                    }
                    function contentsInit(num){
                        const val = document.getElementById('reply' + num).innerText;
                        document.querySelector('.rptContent').value = val;
                        document.getElementById('rcontent').innerText =val;
                    }

                    function drawInput(ev,replyNo){
                       $(".reply-tr").remove();
                    
                        const box = document.createElement("tr");
                        box.className = 'reply-tr';
                        const th = document.createElement("th");
                        const input = document.createElement("input");
                        const btn = document.createElement("button");
                        btn.className = 'btn btn-secondary';
                        btn.style = "background: #b2d8b5; border: #b2d8b5; margin-left:10px;"
                        btn.innerText = "등록";
                        th.colSpan = "3";
                        th.appendChild(input);
                        th.appendChild(btn);
                        box.appendChild(th);
                        ev.target.parentNode.parentNode.after(box);
                        btn.addEventListener("click", function(){
                            $.ajax({
                            url : "togetherRRinsert.bo",
                            data : {
                                replyBoardNo : '${list[0].boardNo}',
                                replyWriter : '${loginUser.memberNo}',
                                replyNo : replyNo,
                                replyContent : input.value,
                            },
                            success : function(res){
                                console.log(res)
                                if(res === "success"){
                                    selectReply();
                                }
                            
                            },
                            error:function(){
                                console.log("togetherRRinsert.bo ajax 통신 실패");
                            }
                        })
                            
                            
                        });
                    }
                  </script>  
        </body>

        </html>