<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>같이가요 게시판</title>
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
<link rel="stylesheet" href="./resources/css/scheduleListModal.css">
<link rel="stylesheet" href="./resources/css/board-card.css">
<script src="./resources/js/board-api.js"></script>
<script src="./resources/js/boardTogetherNotice.js"></script>

</head>
<body>
	<jsp:include page="../common/header.jsp" />
    <main class="contents board">
        <div class="cont-header">
            <h2>같이가요</h2>
            <p>함께면 더 좋은여행, 같이가요 우리</p>
        </div>
        
        <form action="togetherSearch.bo" method="get">
       		 <div class="search-area">
	            <div class="sel-search">
	                <select class="form-select" name="condition">
	                    <option value="region">지역</option>
	                    <option value="title">제목</option>
	                    <option value="content">내용</option>
	                    <option value="writer">작성자</option>
	                </select>
	            </div>
	            <div class="inp-search">
	                <input type="text" name="keyword" value="${keyword}" placeholder="검색어를 입력하세요">
	                <button type="submit">검색</button>
	            </div>
	           </div>
            </form>
        
        <c:if test="${not empty condition}">
	        <script type="text/javascript">
	        	window.onload = function(){
	        		const opt = document.querySelector(".search-area[value=${condition}]");
	        		opt.setAttribute("selected", true);
	        	}
	        </script>
        </c:if>
        <div class="borad-btn-top">
            <div><span>총</span><span>${fn:length(list)}</span><span>건</span></div>
            <c:if test="${!(empty loginUser) }">
                <button type="button" data-bs-toggle="modal" data-bs-target="#myschedulelistModal" onclick="pickupSchedule(${loginUser.memberNo})">글쓰기</button>
            </c:if>
            <!-- 글쓰기버튼 클릭시 나의 모든일정 보여주는 modal -->
            <div class="modal fade" id="myschedulelistModal">
              <div class="modal-dialog modal-dialog-centered">
                  <div class="modal-content style list">
              
                      <!-- Modal Header -->
                      <div class="modal-header">
                          <h4 class="modal-title">나의 여행</h4>
                          <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                      </div>
                

                      <!-- Modal body -->
                      <div class="modal-body" style="max-height: 700px; overflow-x: hidden;">
                          <div class="contents-wrap">
                            <div class="travel-list">
                              <div>
                                <!-- 여행지 클릭 버튼-->
                                <div class="tab-menu button-group button-type" role="tablist" aria-label="여행지 클릭 버튼" >
                                    
                                </div>
                              </div>
                            </div>
                            <div class="travel-detail">
                                <div class="tab-contents">
                                    <!-- tab1에 대한 contents -->
                                    <div tabindex="0" role="tabpanel" id="tabPanel1" aria-labelledby="tab1">
                                        
                                        
                                    </div>
                                    
                                    
                                    </div>
                                </div>
                                <button class="btn" id="writeBtn" onclick="moveWritePage()">일정선택</button>
                            </div>
                          </div>
                      </div>

                  </div>
              </div>
          </div>
          <!-- 글쓰기버튼 클릭시 나의 모든일정 보여주는 modal -->
        </div>
        
        <div class="cards-wrap">
        <c:forEach var="b" items="${list }">
        
            <div class="cards">
               <a href="togetherDetail.bo?boardNo=${b.boardNo}">
                  <div class="card-photo ">
                    <img src="${b.tripPlanThumbnail}">
                </div>
               <div class="card-desc">
                  <h2 class="card-title">${b.boardTitle}</h2>
                
                    <div class="card-text" >
                        ${b.boardContent}
                    </div>
        <script>
            $('.card-text > p > img').css("display", "none" );
        </script>
                  <div class="card-counts">
                      <span>
                        ${b.boardWriter}
                      </span>
                      <span style="padding: 0 1px;">|</span>
                      <span>
                        ${b.boardCreateDate}  
                      </span>
                  </div>
                </div>
               </a>
            </div>
            
        </c:forEach> 
        </div>  
            <c:if test="${empty list}" >
                <div style="margin-bottom: 200px; padding-top: 200px; display: flex; justify-content: center; align-items: center;">리스트가 존재하지 않습니다.</div>
            </c:if> 
            
        <ul class="tr-pagination">
	        <c:choose>
	        	<c:when test="${pi.currentPage eq 1}">
	            	<li class="tr-page-prev disabled" ><a>이전</a></li>
	            </c:when>
	            <c:otherwise>
	            	<li class="tr-page-prev"><a href="together.bo?cpage=${pi.currentPage -1 }">이전</a></li>
	            </c:otherwise>
	        </c:choose>
	        
	        <c:forEach var="p" begin="${pi.startPage }" end="${pi.endPage }">
	        	<li class="tr-current"><a href="together.bo?cpage=${p }">${p }<span class="sr-only"></span></a></li>
	        </c:forEach>
	        
	        <c:choose>
	        	<c:when test="${pi.currentPage eq pi.maxPage}">
	            	<li class="tr-page-next disabled"><a >다음</a></li>
	            </c:when>
	            <c:otherwise>
	            	<li class="tr-page-next"><a href="together.bo?cpage=${pi.currentPage +1 }">다음</a></li>
	            </c:otherwise>
	        </c:choose>    
        </ul>
    </main>
    
</body>
</html>