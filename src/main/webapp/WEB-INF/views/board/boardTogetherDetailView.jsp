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
    <link rel="stylesheet" href="./resources/css/boardTogetherDetailView.css">
    <script src="./resources/js/togetherEnrollForm.js"></script>
    <script src="./resources/js/board-api.js"></script>
    <script src="./resources/js/boardTogetherNotice.js"></script>
    <script src="./resources/js/boardTogetherDetailView.js"></script>
</head>
<body onload="selectReply()">

            <jsp:include page="../common/header.jsp" />

            <div class="schedule-container">
                <c:if test="${loginUser.memberNo eq list[0].memberNo}">
                <div class="dropdown">
                    <i class="bi bi-pencil-square dropdown-toggle" type="button" data-bs-toggle="dropdown"
                        aria-expanded="false" id="editBtn"></i>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="updateTogetherForm.bo?boardNo=${list[0].boardNo}">일정수정</a></li>
                        <li><a class="dropdown-item" href="#">취소</a></li>
                    </ul>

                </div>
                </c:if>
           
                <div id="map" ><img src="${list[0].tripPlanThumbnail}" alt="" style="width: 100%; height: 100%;"></div>
                
                <div id="schedule-area">
    
                    <div class="nDay-content">
    
                    <c:forEach var="i" begin = "1" end = "${list[list.size() - 1].tripNday}" step="1">
    
                        <div class="nDay">${i}일차</div>
                    
    
                            <div class="location">
                                <c:forEach var="p" items="${list }">
                                    <c:choose>
                                         <c:when test ="${i eq p.tripNday}">
                                            <div class="location-img"><img src="${p.attractionChangeNameImg}" alt="전주"></div>
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
                                <li><a class="dropdown-item" href="updateTogetherForm.bo?boardNo=${list[0].boardNo}">수정</a></li>
                                <li><a class="dropdown-item" href="togetherDelete.bo?boardNo=${list[0].boardNo}">삭제</a></li>
                            </ul>
                        </div>
                        </c:if>
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
                            <p class="bd-content">
                                <span>${list[0].boardContent}</span>
                            </p>
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
                            <thead>
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
                                        </form>
                                    </c:otherwise>
                                </c:choose>  

                                <tr>
                                    <th style="border: none; vertical-align:middle;"
                                     colspan="3">댓글(<span id="rcount">${rlist.size()}</span>)</th>
                                </tr>   

                               <table align="center"  class="reply-area" style="vertical-align:middle;">
                                
                                       
                                        
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


                                    


                               </table> 

                              
                            </thead>

                            


                            
                        </table>

                     </div>

                     
    


                </section>

                
                <script>

                function selectReply(){
                    $.ajax({
                        url : "togetherRlist.bo",
                        data : {
                            boardNo : '${list[0].boardNo}'
                        },
                        success : function(rlist){
                            console.log(rlist);
                            let str = '';

                            if(rlist.length === 0){
                                str = `<div style="margin-bottom: 200px; padding-top: 100px; display: flex; justify-content: center; align-items: center;">댓글 리스트가 존재하지 않습니다.</div>`
                            } 
                            for(reply of rlist){
                               str += `
                                    <tr id="reply-profile">    
                                        <th id="rp-profile-img" style="width: 80px; "> 
                                            <div>
                                            <img src="` + reply.memberProfileImg + `" 
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
                                        <th>`+reply.replyWriter+`</th>
                                        <th style="width: 100px;">`+reply.replyModifyDate+`</th>
                                    </tr>

                                    <tr>
                                        <td id="reply-content" colspan="2">`+reply.replyContent+`</td>
                                          
                                        <td colspan="1" >
                                           

                                            <button type="button" data-bs-toggle="modal" data-bs-target="#reportModal" id="reply-warn">
                                                <i style="font-size: 1.5rem; color: #000;" class="bi bi-exclamation-triangle"></i>
                                            </button>
                                        </td>
                                </tr>

    

                            <tr>
                                <th  colspan="3" id="re-reply" >
                                    <button class="btn btn-secondary" style="background: #b2d8b5; border: #b2d8b5;">
                                            답글
                                    </button>
                                </th>
                            </tr>
                            `
                            }
                            $(".reply-area").html(str);
                    
                        },
                        error:function(){
                            console.log("togetherRlist.bo ajax 통신 실패");
                        }
                    })
                }
                

                //댓글을 추가해주는 메서드
                function addReply(){
                    $.ajax({
                        url : "togetherRinsert.bo",
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

              
        

          


        </body>

        </html>