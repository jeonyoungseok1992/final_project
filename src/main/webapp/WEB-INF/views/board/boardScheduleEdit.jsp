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
                        <button type="button" class="edit-btn">취소</button>
                        <button type="button">수정</button>
                    </div>
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
                                            <h5>장소 보관함</h5>
                                        </div>
                                        <ul class="container-map">
                                            
                                        </ul>
                                    </div>
                                    <div>
                                        <div class="day-date">
                                            <h5>1일차</h5>
                                            <div>11.25(토)</div>
                                        </div>
                                        <ul class="container-map">
                                            <li class="draggable" draggable="true"><span class="number">1</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                            <li class="draggable" draggable="true"><span class="number">2</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                            <li class="draggable" draggable="true"><span class="number">3</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                            <li class="draggable" draggable="true"><span class="number">4</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                        </ul>
                                    </div>
                                    <div>
                                        <div class="day-date">
                                            <h5>2일차</h5>
                                            <div>11.26(일)</div>
                                        </div>
                                        <ul class="container-map">
                                            <li class="draggable" draggable="true"><span class="number">1</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                            <li class="draggable" draggable="true"><span class="number">2</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                            <li class="draggable" draggable="true"><span class="number">3</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
                                            <li class="draggable" draggable="true"><span class="number">4</span><div><div><span>명소</span><h5>여행지</h5></div><img src="./resources/images/place_sample.jpg" alt=""></div></li>
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
        // 일정 드래그
        const draggables = document.querySelectorAll(".draggable");
        const containers = document.querySelectorAll(".container-map");

        draggables.forEach(draggable => {
        draggable.addEventListener("dragstart", () => {
            draggable.classList.add("dragging");
        });

        draggable.addEventListener("dragend", () => {
            draggable.classList.remove("dragging");
        });
        });

        containers.forEach(container => {
        container.addEventListener("dragover", e => {
            e.preventDefault();
            const afterElement = getDragAfterElement(container, e.clientX);
            const draggable = document.querySelector(".dragging");
            if (afterElement === undefined) {
            container.appendChild(draggable);
            } else {
            container.insertBefore(draggable, afterElement);
            }
        });
        });

        function getDragAfterElement(container, x) {
        const draggableElements = [
            ...container.querySelectorAll(".draggable:not(.dragging)"),
        ];

        return draggableElements.reduce(
            (closest, child) => {
            const box = child.getBoundingClientRect();
            const offset = x - box.left - box.width / 2;
            // console.log(offset);
            if (offset < 0 && offset > closest.offset) {
                return { offset: offset, element: child };
            } else {
                return closest;
            }
            },
            { offset: Number.NEGATIVE_INFINITY },
        ).element;
        }
    </script>
</body>
</html>