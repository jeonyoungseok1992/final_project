<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>같이가요 게시판</title>
<link rel="stylesheet" href="./resources/css/common.css">
<link rel="stylesheet" href="./resources/css/board-card.css">
</head>
<body>
	<jsp:include page="../common/header.jsp" />
    <main class="contents board">
        <div class="cont-header">
            <h2>같이가요</h2>
            <p>함께면 더 좋은여행, 같이가요 우리</p>
        </div>
        <div class="search-area">
            <div class="sel-search">
                <select class="form-select">
                    <option>제목+내용</option>
                    <option>제목</option>
                    <option>내용</option>
                    <option>작성자</option>
                </select>
            </div>
            <div class="inp-search">
                <input type="text" placeholder="검색어를 입력하세요">
                <button>검색</button>
            </div>
        </div>
        <div class="borad-btn-top">
            <div><span>총</span><span>157</span><span>건</span></div>
            <button>글쓰기</button>
        </div>
        <div class="cards-wrap">
            <div class="cards">
               <a>
                  <div class="card-photo ">
                    <img src="./resources/images/card_sample_02.jpg">
                </div>
               <div class="card-desc">
                  <h2 class="card-title">제목입니다. 제목은 한줄만 가능합니다.</h2>
                  <div class="card-text">
                    내용입니다. 내용은 세줄을 허용합니다.내용입니다. 내용은 세줄을 허용합니다.
                    내용입니다. 내용은 세줄을 허용합니다.내용입니다. 내용은 세줄을 허용합니다.
                  </div>
                  <div class="card-counts">
                      <span>
                        작성자
                      </span>
                      <span style="padding: 0 1px;">|</span>
                      <span>
                        2023.11.13  
                      </span>
                  </div>
                </div>
               </a>
            </div>
            <div class="cards">
                <a>
                   <div class="card-photo ">
                     <img src="./resources/images/card_sample_02.jpg">
                 </div>
                <div class="card-desc">
                   <h2 class="card-title">제목입니다. 제목은 한줄만 가능합니다.</h2>
                   <div class="card-text">
                     내용입니다. 내용은 세줄을 허용합니다.내용입니다. 내용은 세줄을 허용합니다.
                     내용입니다. 내용은 세줄을 허용합니다.내용입니다. 내용은 세줄을 허용합니다.
                   </div>
                   <div class="card-counts">
                       <span>
                         작성자
                       </span>
                       <span style="padding: 0 1px;">|</span>
                       <span>
                         2023.11.13  
                       </span>
                   </div>
                 </div>
                </a>
             </div>
             <div class="cards">
                <a>
                   <div class="card-photo ">
                     <img src="./resources/images/card_sample_02.jpg">
                 </div>
                <div class="card-desc">
                   <h2 class="card-title">제목입니다. 제목은 한줄만 가능합니다.</h2>
                   <div class="card-text">
                     내용입니다. 내용은 세줄을 허용합니다.내용입니다. 내용은 세줄을 허용합니다.
                     내용입니다. 내용은 세줄을 허용합니다.내용입니다. 내용은 세줄을 허용합니다.
                   </div>
                   <div class="card-counts">
                       <span>
                         작성자
                       </span>
                       <span style="padding: 0 1px;">|</span>
                       <span>
                         2023.11.13  
                       </span>
                   </div>
                 </div>
                </a>
             </div>
             <div class="cards">
                <a>
                   <div class="card-photo ">
                     <img src="./resources/images/card_sample_02.jpg">
                 </div>
                <div class="card-desc">
                   <h2 class="card-title">제목입니다. 제목은 한줄만 가능합니다.</h2>
                   <div class="card-text">
                     내용입니다. 내용은 세줄을 허용합니다.내용입니다. 내용은 세줄을 허용합니다.
                     내용입니다. 내용은 세줄을 허용합니다.내용입니다. 내용은 세줄을 허용합니다.
                   </div>
                   <div class="card-counts">
                       <span>
                         작성자
                       </span>
                       <span style="padding: 0 1px;">|</span>
                       <span>
                         2023.11.13  
                       </span>
                   </div>
                 </div>
                </a>
             </div>
             <div class="cards">
                <a>
                   <div class="card-photo ">
                     <img src="./resources/images/card_sample_02.jpg">
                 </div>
                <div class="card-desc">
                   <h2 class="card-title">제목입니다. 제목은 한줄만 가능합니다.</h2>
                   <div class="card-text">
                     내용입니다. 내용은 세줄을 허용합니다.내용입니다. 내용은 세줄을 허용합니다.
                     내용입니다. 내용은 세줄을 허용합니다.내용입니다. 내용은 세줄을 허용합니다.
                   </div>
                   <div class="card-counts">
                       <span>
                         작성자
                       </span>
                       <span style="padding: 0 1px;">|</span>
                       <span>
                         2023.11.13  
                       </span>
                   </div>
                 </div>
                </a>
             </div>
             <div class="cards">
                <a>
                   <div class="card-photo ">
                     <img src="./resources/images/card_sample_02.jpg">
                 </div>
                <div class="card-desc">
                   <h2 class="card-title">제목입니다. 제목은 한줄만 가능합니다.</h2>
                   <div class="card-text">
                     내용입니다. 내용은 세줄을 허용합니다.내용입니다. 내용은 세줄을 허용합니다.
                     내용입니다. 내용은 세줄을 허용합니다.내용입니다. 내용은 세줄을 허용합니다.
                   </div>
                   <div class="card-counts">
                       <span>
                         작성자
                       </span>
                       <span style="padding: 0 1px;">|</span>
                       <span>
                         2023.11.13  
                       </span>
                   </div>
                 </div>
                </a>
             </div>
             <div class="cards">
                <a>
                   <div class="card-photo ">
                     <img src="./resources/images/card_sample_02.jpg">
                 </div>
                <div class="card-desc">
                   <h2 class="card-title">제목입니다. 제목은 한줄만 가능합니다.</h2>
                   <div class="card-text">
                     내용입니다. 내용은 세줄을 허용합니다.내용입니다. 내용은 세줄을 허용합니다.
                     내용입니다. 내용은 세줄을 허용합니다.내용입니다. 내용은 세줄을 허용합니다.
                   </div>
                   <div class="card-counts">
                       <span>
                         작성자
                       </span>
                       <span style="padding: 0 1px;">|</span>
                       <span>
                         2023.11.13  
                       </span>
                   </div>
                 </div>
                </a>
             </div>
             <div class="cards">
                <a>
                   <div class="card-photo ">
                     <img src="./resources/images/card_sample_02.jpg">
                 </div>
                <div class="card-desc">
                   <h2 class="card-title">제목입니다. 제목은 한줄만 가능합니다.</h2>
                   <div class="card-text">
                     내용입니다. 내용은 세줄을 허용합니다.내용입니다. 내용은 세줄을 허용합니다.
                     내용입니다. 내용은 세줄을 허용합니다.내용입니다. 내용은 세줄을 허용합니다.
                   </div>
                   <div class="card-counts">
                       <span>
                         작성자
                       </span>
                       <span style="padding: 0 1px;">|</span>
                       <span>
                         2023.11.13  
                       </span>
                   </div>
                 </div>
                </a>
             </div>
             <div class="cards">
                <a>
                   <div class="card-photo ">
                     <img src="./resources/images/card_sample_02.jpg">
                 </div>
                <div class="card-desc">
                   <h2 class="card-title">제목입니다. 제목은 한줄만 가능합니다.</h2>
                   <div class="card-text">
                     내용입니다. 내용은 세줄을 허용합니다.내용입니다. 내용은 세줄을 허용합니다.
                     내용입니다. 내용은 세줄을 허용합니다.내용입니다. 내용은 세줄을 허용합니다.
                   </div>
                   <div class="card-counts">
                       <span>
                         작성자
                       </span>
                       <span style="padding: 0 1px;">|</span>
                       <span>
                         2023.11.13  
                       </span>
                   </div>
                 </div>
                </a>
             </div>
             <div class="cards">
                <a>
                   <div class="card-photo ">
                     <img src="./resources/images/card_sample_02.jpg">
                 </div>
                <div class="card-desc">
                   <h2 class="card-title">제목입니다. 제목은 한줄만 가능합니다.</h2>
                   <div class="card-text">
                     내용입니다. 내용은 세줄을 허용합니다.내용입니다. 내용은 세줄을 허용합니다.
                     내용입니다. 내용은 세줄을 허용합니다.내용입니다. 내용은 세줄을 허용합니다.
                   </div>
                   <div class="card-counts">
                       <span>
                         작성자
                       </span>
                       <span style="padding: 0 1px;">|</span>
                       <span>
                         2023.11.13  
                       </span>
                   </div>
                 </div>
                </a>
             </div>
        </div>    
        <ul class="tr-pagination">
            <li class="tr-page-prev"><a href="#none">이전</a></li>
            <li class="tr-current"><a href="#none">1<span class="sr-only">현재 페이지</span></a></li>
            <li><a href="#none">2</a></li>
            <li><a href="#none">3</a></li>
            <li><a href="#none">4</a></li>
            <li><a href="#none">5</a></li>
            <li><a href="#none">6</a></li>
            <li><a href="#none">7</a></li>
            <li><a href="#none">8</a></li>
            <li><a href="#none">9</a></li>
            <li><a href="#none">10</a></li>
            <li class="tr-page-next"><a href="#none">다음</a></li>
        </ul>
    </main>
</body>
</html>