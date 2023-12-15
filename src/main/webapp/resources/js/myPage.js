const myPageValue = {}

$(document).ready(function () {

    let $tab2 = $('.ui-tab');

    $tab2.each(function () {
        if ($(this).data('init')) return;
        $(this).data('init', true);

        let $this2 = $(this);
        let $tabMenu2 = $this2.find('.tab-menu2');
        let $tabBtn2 = $('[role="tab2"]', $tabMenu2);
        let $tabContents2 = $this2.find('.tab-contents2>div');

        $tabBtn2.on('click.default', function (e) {
            let _this2 = $(this);
            let indexNum2 = _this2.index();
            let _tabContent2 = $tabContents2.eq(indexNum2);

            $tabBtn2.not(_this2).removeClass('active');
            $tabBtn2.not(_this2).attr('aria-selected', false);
            _this2.addClass('active');
            _this2.attr('aria-selected', true);
            $tabContents2.not(_tabContent2).attr('hidden', true);
            _tabContent2.removeAttr('hidden');

            if ($(this).is('a')) {
                e.preventDefault();
            }
        });
    });


    //나의일정탭 누르면 버튼색 바꿔주는 함수 , 여기서 ajax같이 부르면 될듯
    var btn = document.getElementsByClassName("scheduleTap1");

    function handleClick(event) {

        if (event.target.classList[1] === "clicked") {
            event.target.classList.remove("clicked");
        } else {
            for (var i = 0; i < btn.length; i++) {
                btn[i].classList.remove("clicked");
            }

            event.target.classList.add("clicked");
        }
    }

    function init() {
        for (var i = 0; i < btn.length; i++) {
            btn[i].addEventListener("click", handleClick);
        }
    }

    init();



});
function friendDelete() {
    console.log(1);
    $('#myModal').modal('hide');
    $.ajax({
        url: "refriendDelete.me",
        data: {
            memberNo: myPageValue.memberNo
        },
        success: function (list) {
            let str = "";
            console.log(list);
            for (m of list) {
                
                let profileImg = m.memberProfileImg ? m.memberProfileImg : "/mapping/resources/images/profile.png";
                console.log(m)
                str += `
                    <div id="myfriend">
                    <div>
                        <img class="title-img2" src="${profileImg}" >

                        <span style="font-size: 20px; margin-left: 10px;">${m.memberNickName}</span>
                    </div>
                    <div>
                        <a href="#"><img src="resources/images/talkIcons.png" alt="채팅" style="width: 30px; height: 30px;"></a>
                        <a name="mmodal" data-bs-toggle="modal" data-bs-target="#myModal" onclick="myPageValue.memberNo=${m.memberNo}"><img src="resources/images/xIcons.png" alt="x" style="width: 30px; height: 30px;"></a>
                        
                        
                        </div>	
                </div>
                `
            }

            document.querySelector("#fdList").innerHTML = str;

        },
        error: function () {
            console.log("friendDelete ajax통신 실패");
        }
    })
}




//프로필 사진누를때 function
function loadImg(inputFile, memberNo) {

    const formData = new FormData();

    formData.append('upfile', inputFile.files[0]);
    formData.append('memberNo', memberNo);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',	// 필수
        url: "updateImg.me",
        data: formData,	// 필수
        processData: false,	// 필수
        contentType: false,	// 필수
        cache: false,
        success: function (mem) {

            //ajax로 이미지변경 요청
            if (mem !== null) {
                let len = document.querySelectorAll('.title-img');
                for (var index = 0; index < len.length; index++) {        
                len[index].src = mem.memberProfileImg;
                }

            } else {
                document.querySelector('.title-img').src = null;
            }

        },
        error: function () {
            console.log("loadImg.me ajax통신 실패");
        }
    })
}

function chooseFile() {
    document.getElementById("file").click();
}

//같이가요 리스트
function together(memberNo) {

    $.ajax({
        url: "myTogetherList.bo",
        data: {
            memberNo: memberNo
        },
        success: function (obb) {
            console.log('ajax성공');
            console.log(obb.list);
            let str3 = "";
            for (b of obb.list) {
                str3 += `
                    <div id="mySchedule">
                    <div style="width: 180px; height: 180px;"><img id="mainImg" src="resources/images/Jeonju.jpeg" alt="전주사진"></div>

                    <div class="mySchedule-content">
                        <span>${b.boardTitle}</span>
                        <!-- <span style="font-size: 14px; font-weight: 400; color: gray;">전주</span> -->
                        <span style="font-size: 18px; font-weight: 600; display: flex; justify-content: space-between;">${b.tripStartDate}~${b.tripEndDate}<span style="font-size: 13px; color: gray;">최근 수정일 ${b.boardModifyDate}</span></span>									
                    </div>

                    <div class="dropdown">
                            <img class="dropdown-toggle" id="dropdownMenuButton" data-bs-toggle="dropdown"  aria-haspopup="true" aria-expanded="false" src="resources/images/moreIcons.png" alt="더보기" style="transform:rotate(90deg) translateY(50%); width: 30px; height: 30px; cursor: pointer;">
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                          <li><a class="dropdown-item" href="#">공유</a></li>
                          <li><a class="dropdown-item" href="#">삭제</a></li>
                          
                        </ul>
                      </div>
                </div>
                `
            }
            let str4 = "";
            str4 += "<ul class='tr-pagination'>"; // 예시로 사용한 pagination 클래스명입니다.

            // '이전' 페이지 링크 추가
            if (obb.pi.currentPage === 1) {
                str4 += "<li class='tr-page-prev disabled' ><a>이전</a></li>";
            } else {
                str4 += `<li class="tr-page-prev"><a href="together.bo?cpage=${obb.pi.currentPage - 1}'>이전</a></li>`;
            }

            // 페이지 숫자 링크 추가
            for (let p = obb.pi.startPage; p <= obb.pi.endPage; p++) {
                str4 += `<li class='tr-current'><a class='page-link' href='together.bo?cpage=${p}'>${p}<span class="sr-only"></span></a></li>`;
            }

            // '다음' 페이지 링크 추가
            if (obb.pi.currentPage === obb.pi.maxPage) {
                str4 += "<li class='tr-page-next disabled'><a>다음</a></li>";
            } else {
                str4 += `<li class='tr-page-next'><a class='page-link' href='together.bo?cpage=${obb.pi.currentPage + 1}'>다음</a></li>`;
            }

            str4 += "</ul>"; // pagination 마감

            document.querySelector(".myTogetherSchedule").innerHTML = str3 + str4;

        },
        error: function () {
            console.log("myTogetherList.bo ajax통신 실패");
        }
    })
}







//후기 리스트
function review(memberNo) {
    $.ajax({
        url: "myReviewList.bo",
        data: {
            memberNo: memberNo
        },
        success: function (obj) {
            console.log('ajax성공');
            let str = "";
            for (b of obj.list) {
                str += `
                    <div id="mySchedule">
                    <div style="width: 180px; height: 180px;"><img id="mainImg" src="resources/images/Jeonju.jpeg" alt="전주사진"></div>

                    <div class="mySchedule-content">
                        <span>${b.boardTitle}</span>
                        <!-- <span style="font-size: 14px; font-weight: 400; color: gray;">전주</span> -->
                        <span style="font-size: 18px; font-weight: 600; display: flex; justify-content: space-between;">${b.tripStartDate}~${b.tripEndDate}<span style="font-size: 13px; color: gray;">최근 수정일 ${b.boardModifyDate}</span></span>									
                    </div>

                    <div class="dropdown">
                            <img class="dropdown-toggle" id="dropdownMenuButton" data-bs-toggle="dropdown"  aria-haspopup="true" aria-expanded="false" src="resources/images/moreIcons.png" alt="더보기" style="transform:rotate(90deg) translateY(50%); width: 30px; height: 30px; cursor: pointer;">
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                          <li><a class="dropdown-item" href="#">공유</a></li>
                          <li><a class="dropdown-item" href="#">삭제</a></li>
                          
                        </ul>
                      </div>
                </div>
                `
            }

            let str2 = "";
            str2 += "<ul class='tr-pagination'>"; // 예시로 사용한 pagination 클래스명입니다.

            // '이전' 페이지 링크 추가
            if (obj.pi.currentPage === 1) {
                str2 += "<li class='tr-page-prev disabled' ><a>이전</a></li>";
            } else {
                str2 += `<li class="tr-page-prev"><a href="together.bo?cpage=${obj.pi.currentPage - 1}'>이전</a></li>`;
            }

            // 페이지 숫자 링크 추가
            for (let p = obj.pi.startPage; p <= obj.pi.endPage; p++) {
                str2 += `<li class='tr-current'><a class='page-link' href='together.bo?cpage=${p}'>${p}<span class="sr-only"></span></a></li>`;
            }

            // '다음' 페이지 링크 추가
            if (obj.pi.currentPage === obj.pi.maxPage) {
                str2 += "<li class='tr-page-next disabled'><a>다음</a></li>";
            } else {
                str2 += `<li class='tr-page-next'><a class='page-link' href='together.bo?cpage=${obj.pi.currentPage + 1}'>다음</a></li>`;
            }

            str2 += "</ul>"; // pagination 마감

            document.querySelector(".myReviewSchedule").innerHTML = str + str2;

        },
        error: function () {
            console.log("myReviewList.bo ajax통신 실패");
        }
    })
}




//친구목록 리스트
function fdList(memberNo) {
    $.ajax({
        url: "friendList.me",
        data: {
            memberNo: memberNo
        },
        success: function (list) {
            let str = "";
            console.log(list);
            for (m of list) {

                let profileImg = m.memberProfileImg ? m.memberProfileImg : "/mapping/resources/images/profile.png";
                console.log(m)
                str += `
                    <div id="myfriend">
                    <div>
                        <img class="title-img2" src="${profileImg}" >

                        <span style="font-size: 20px; margin-left: 10px;">${m.memberNickName}</span>
                    </div>
                    <div>
                        <a href="#"><img src="resources/images/talkIcons.png" alt="채팅" style="width: 30px; height: 30px;"></a>
                        <a name="mmodal" data-bs-toggle="modal" data-bs-target="#myModal" onclick="myPageValue.memberNo=${m.memberNo}"><img src="resources/images/xIcons.png" alt="x" style="width: 30px; height: 30px;"></a>
                        
                        
                        </div>	
                </div>
                `
            }

            document.querySelector("#fdList").innerHTML = str;

        },
        error: function () {
            console.log("friendList ajax통신 실패");
        }
    })
}





//받은 친구요청 리스트
function fdRequest(memberNo) {
    $.ajax({
        url: "friendRequest.me",
        data: {
            memberNo: memberNo
        },
        success: function (list) {

            let profileImg = m.memberProfileImg ? m.memberProfileImg : "/mapping/resources/images/profile.png";

            let str = "";
            for (m of list) {
                str += `
                    <div id="myfriend">
                    <div>
                        <img class="title-img2" src="${profileImg}" >

                        <span style="font-size: 20px; margin-left: 10px;">${m.memberNickName}</span>
                    </div>
                    <div>
                        <a href="#" style="font-size: 18px;">수락</a>
                        <span style="font-size: 18px;">|</span>
                        <a href="#" style="font-size: 18px;">거절</a>
                    </div>	
                </div>
                `
            }

            document.querySelector("#fdList").innerHTML = str;

        },
        error: function () {
            console.log("friendList ajax통신 실패");
        }
    })
}






