<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행후기 게시판</title>
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
</head>
<body>
	<jsp:include page="../common/header.jsp" />
    <main class="contents board">
        <div class="cont-header">
            <h2>Review</h2>
            <p>소중한 고객님의 후기를 기다립니다.</p>
        </div>


        <form action="reviewSearch.bo" method="get">
            <div class="search-area" >
                <div class="sel-search">
                    <select class="form-select" name="condition">
                        <option value="region">지역</option>
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                        <option value="writer">작성자</option>
                    </select>
                </div>
                <div class="inp-search">
                    <input type="text" placeholder="검색어를 입력하세요" name="keyword" value="${keyword}">
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
            
            <div><span>총</span>
                ${fn:length(list)}
                <span>건</span>
            </div>

            <c:if test="${not empty loginUser}">
                <button type="button" data-bs-toggle="modal" data-bs-target="#myschedulelistModal" id="writeBtn">글쓰기</button>
                <br>
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
                                <div class="tab-menu button-group button-type" role="tablist" aria-label="여행지 클릭 버튼">
                                    <button role="tab" aria-selected="true" aria-controls="tabPanel1" id="tab1" class="active"><div class="travel-list-text"><p>2023.11.30-2023.12.01</p><h3>여행지 1</h3></div><img src="./resources/images/vancouver_sample.jpg" alt=""></button>
                                    <button role="tab" aria-selected="false" aria-controls="tabPanel2" id="tab2" class=""><div class="travel-list-text"><p>2023.11.30-2023.12.01</p><h3>여행지 2</h3></div><img src="./resources/images/vancouver_sample.jpg" alt=""></button>
                                    <button role="tab" aria-selected="false" aria-controls="tabPanel3" id="tab3" class=""><div class="travel-list-text"><p>2023.11.30-2023.12.01</p><h3>여행지 3</h3></div><img src="./resources/images/vancouver_sample.jpg" alt=""></button>
                                </div>
                              </div>
                            </div>


                            <div class="travel-detail">
                                <div class="tab-contents">
                                    <!-- tab1에 대한 contents -->
                                    <div tabindex="0" role="tabpanel" id="tabPanel1" aria-labelledby="tab1">
                                        <!-- tab1에 대한 선택한 일정 -->
                                        <div class="travel-location">
                                            <h4>선택한 장소</h4>
                                            <div>
                                                <div class="menu">
                                                    <div class="item-wrap">
                                                        <div class="img-area">
                                                            <img src="./resources/images/place_sample.jpg" alt="">
                                                        </div>
                                                        <h5>장소명입니다.</h5>
                                                    </div>
                                                    <div class="item-wrap">
                                                        <div class="img-area">
                                                            <img src="./resources/images/place_sample.jpg" alt="">
                                                        </div>
                                                        <h5>장소명입니다.</h5>
                                                    </div>
                                                    <div class="item-wrap">
                                                        <div class="img-area">
                                                            <img src="./resources/images/place_sample.jpg" alt="">
                                                        </div>
                                                        <h5>장소명입니다.</h5>
                                                    </div>
                                                    <div class="item-wrap">
                                                        <div class="img-area">
                                                            <img src="./resources/images/place_sample.jpg" alt="">
                                                        </div>
                                                        <h5>장소명입니다.</h5>
                                                    </div>
                                              </div>
                                            </div>
                                        </div>


                                        <!-- tab1에 대한 선택한 숙소 -->
                                        <div class="travel-location" style="padding : 24px 0 32px 0;">
                                            <h4>선택한 숙소</h4>
                                            <div>
                                                <div class="menu">
                                                    <div class="item-wrap">
                                                        <div class="img-area">
                                                            <img src="./resources/images/modal_sample.jpg" alt="">
                                                        </div>
                                                        <h5>숙소명입니다.</h5>
                                                    </div>
                                                    <div class="item-wrap">
                                                        <div class="img-area">
                                                            <img src="./resources/images/modal_sample.jpg" alt="">
                                                        </div>
                                                        <h5>숙소명입니다.</h5>
                                                    </div>
                                              </div>
                                            </div>
                                          </div>
                                        <button class="btn">일정선택</button>
                                    </div>

                                    <!-- tab2에 대한 contents -->
                                    <div tabindex="0" role="tabpanel" id="tabPanel2" aria-labelledby="tab2" hidden="hidden">
                                        <div>
                                            <!-- tab2에 대한 선택한 일정 -->
                                            <div class="travel-location">
                                                <h4>선택한 장소</h4>
                                                <div>
                                                    <div class="menu">
                                                        <div class="item-wrap">
                                                            <div class="img-area">
                                                                <img src="./resources/images/place_sample.jpg" alt="">
                                                            </div>
                                                            <h5>장소명입니다.</h5>
                                                        </div>
                                                        <div class="item-wrap">
                                                            <div class="img-area">
                                                                <img src="./resources/images/place_sample.jpg" alt="">
                                                            </div>
                                                            <h5>장소명입니다.</h5>
                                                        </div>
                                                        <div class="item-wrap">
                                                            <div class="img-area">
                                                                <img src="./resources/images/place_sample.jpg" alt="">
                                                            </div>
                                                            <h5>장소명입니다.</h5>
                                                        </div>
                                                        <div class="item-wrap">
                                                            <div class="img-area">
                                                                <img src="./resources/images/place_sample.jpg" alt="">
                                                            </div>
                                                            <h5>장소명입니다.</h5>
                                                        </div>
                                                        <div class="item-wrap">
                                                            <div class="img-area">
                                                                <img src="./resources/images/place_sample.jpg" alt="">
                                                            </div>
                                                            <h5>장소명입니다.</h5>
                                                        </div>
                                                        <div class="item-wrap">
                                                            <div class="img-area">
                                                                <img src="./resources/images/place_sample.jpg" alt="">
                                                            </div>
                                                            <h5>장소명입니다.</h5>
                                                        </div>
                                                        <div class="item-wrap">
                                                            <div class="img-area">
                                                                <img src="./resources/images/place_sample.jpg" alt="">
                                                            </div>
                                                            <h5>장소명입니다.</h5>
                                                        </div>
                                                        <div class="item-wrap">
                                                            <div class="img-area">
                                                                <img src="./resources/images/place_sample.jpg" alt="">
                                                            </div>
                                                            <h5>장소명입니다.</h5>
                                                        </div>
                                                  </div>
                                                </div>
                                            </div>

                                            <!-- tab2에 대한 선택한 숙소 -->
                                            <div class="travel-location" style="padding : 24px 0 32px 0;">
                                                <h4>선택한 숙소</h4>
                                                <div>
                                                    <div class="menu">
                                                        <div class="item-wrap">
                                                            <div class="img-area">
                                                                <img src="./resources/images/modal_sample.jpg" alt="">
                                                            </div>
                                                            <h5>숙소명입니다.</h5>
                                                        </div>
                                                        <div class="item-wrap">
                                                            <div class="img-area">
                                                                <img src="./resources/images/modal_sample.jpg" alt="">
                                                            </div>
                                                            <h5>숙소명입니다.</h5>
                                                        </div>
                                                        <div class="item-wrap">
                                                            <div class="img-area">
                                                                <img src="./resources/images/modal_sample.jpg" alt="">
                                                            </div>
                                                            <h5>숙소명입니다.</h5>
                                                        </div>
                                                        <div class="item-wrap">
                                                            <div class="img-area">
                                                                <img src="./resources/images/modal_sample.jpg" alt="">
                                                            </div>
                                                            <h5>숙소명입니다.</h5>
                                                        </div>
                                                  </div>
                                                </div>
                                              </div>
                                            <button class="btn">일정선택</button>
                                        </div>
                                    </div>


                                    <!-- tab3에 대한 contents -->
                                    <div tabindex="0" role="tabpanel" id="tabPanel3" aria-labelledby="tab3" hidden="hidden">
                                        <div>
                                            <!-- tab3에 대한 선택한 일정 -->
                                            <div class="travel-location">
                                                <h4>선택한 장소</h4>
                                                <div>
                                                    <div class="menu">
                                                        <div class="item-wrap">
                                                            <div class="img-area">
                                                                <img src="./resources/images/place_sample.jpg" alt="">
                                                            </div>
                                                            <h5>장소명입니다.</h5>
                                                        </div>
                                                  </div>
                                                </div>
                                            </div>
                                            <!-- tab3에 대한 선택한 숙소 -->
                                            <div class="travel-location" style="padding : 24px 0 32px 0;">
                                                <h4>선택한 숙소</h4>
                                                <div>
                                                    <div class="menu">
                                                        <div class="item-wrap">
                                                            <div class="img-area">
                                                                <img src="./resources/images/modal_sample.jpg" alt="">
                                                            </div>
                                                            <h5>숙소명입니다.</h5>
                                                        </div>
                                                  </div>
                                                </div>
                                              </div>
                                            <button class="btn">일정선택</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                          </div>
                      </div>

                  </div>
              </div>
            </div>
                    <!-- 글쓰기버튼 클릭시 나의 모든일정 보여주는 modal -->
            </div>
        	
        	
	
		   
        <div class="cards-wrap">
            <c:forEach var="b" items="${list}">
                <div class="cards" onclick="location.href='detail.bo?bno=${b.boardNo}'">
                <a>
                    
                    <div class="card-photo ">
                        <img src="resources/images/card_samplex`_01.jpg">
                    </div>
                <div class="card-desc">
                    <h2 class="card-title">${b.boardTitle}</h2>
                    <div class="card-text">
                        ${b.boardContent}
                    </div>
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
		
		
		
     
        
        
 
        <div>
       		<ul class="tr-pagination">
              
                 <c:choose>
                    <c:when test="${pi.currentPage eq 1 }">
                   <li class="tr-page-prev disabled"><a class="page-link" href="#">이전</a></li>
                       </c:when>
                       <c:otherwise>
                          <li class="tr-page-prev"><a class="page-link" href="review.bo?cpage=${pi.currentPage - 1}">이전</a></li>
                       </c:otherwise>
             </c:choose>
             <c:forEach var="p" begin="${pi.startPage }" end="${pi.endPage }">
                       <li class="tr-current"><a class="page-link" href="review.bo?cpage=${p}">${p}</a></li>
                  </c:forEach>
                  
                  <c:choose>
                     <c:when test="${pi.currentPage eq pi.maxPage}">
                         <li class="tr-page-next disabled"><a class="page-link" href="#">다음</a></li>
                    </c:when>
                    <c:otherwise>
                          <li class="tr-page-next"><a class="page-link" href="review.bo?cpage=${pi.currentPage + 1}">다음</a></li>
                       </c:otherwise>
                 </c:choose>
              </ul>
        </div>
        
    </main>
    <script>
      $(document).ready(function () {
          var $tabBtn = $('.tab-menu button[role="tab"]');
          var $tabContents = $('.travel-detail .tab-contents > div');

          $tabBtn.on('click', function (e) {
              e.preventDefault();

              var indexNum = $tabBtn.index(this);
              var $tabContent = $tabContents.eq(indexNum);

              $tabBtn.removeClass('active').attr('aria-selected', false);
              $(this).addClass('active').attr('aria-selected', true);
              $tabContents.attr('hidden', true);
              $tabContent.removeAttr('hidden');
          });
      });
  </script>
</body>
</html>