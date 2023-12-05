$(document).ready(function(){



		
		$(document).ready(function () {
            let $tab2 = $('.ui-tab');

            $tab2.each(function(){
                if( $(this).data('init') ) return;
                $(this).data('init' , true);

                let $this2 = $(this);
                let $tabMenu2 = $this2.find('.tab-menu2');
                let $tabBtn2 = $('[role="tab2"]', $tabMenu2);
                let $tabContents2 = $this2.find('.tab-contents2>div');
                
                $tabBtn2.on('click.default' , function(e){
                    let _this2 = $(this);
                    let indexNum2 = _this2.index();
                    let _tabContent2 = $tabContents2.eq( indexNum2 );
                    
                    $tabBtn2.not(_this2).removeClass('active');
                    $tabBtn2.not(_this2).attr('aria-selected', false);
                    _this2.addClass('active');
                    _this2.attr('aria-selected', true);
                    $tabContents2.not(_tabContent2).attr('hidden', true);
                    _tabContent2.removeAttr('hidden');
                    
                    if($(this).is('a')){
                        e.preventDefault();
                    }
                });
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


    //프로필 사진누를때 function
    function loadImg(inputFile, memberNo){
    
    const formData = new FormData();
    	
        formData.append('upfile', inputFile.files[0]);
        formData.append('memberNo',memberNo);
        
        $.ajax({  
            type: "POST",
            enctype: 'multipart/form-data',	// 필수
            url: "updateImg.me",
            data: formData,	// 필수
            processData: false,	// 필수
            contentType: false,	// 필수
            cache: false,
            success: function(mem){
                
                //ajax로 이미지변경 요청
            if(mem !== null){
                    document.getElementById('title-img').src = mem.memberProfileImg; 
					document.getElementById('title-img2').src = mem.memberProfileImg;
			
                    
            } else {   
                document.getElementById('title-img').src = null;
                }        

            },
            error: function(){
            console.log("loadImg.me ajax통신 실패");
            }
        })
    }
   
   function chooseFile(){
       document.getElementById("file").click();
   }


   function together(memberNo){
        console.log(1);
        $.ajax({
            url: "myTogetherList.bo",
            data: {
                memberNo: memberNo
            },
            success: function(list){
                
                let str = "";
                for (b of list) {
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

                document.querySelector(".myTogetherSchedule").innerHTML = str;  

            },
            error: function(){
                console.log("myTogetherList.bo ajax통신 실패");
            }
        })
    }



    function review(memberNo){
        console.log(1);
        $.ajax({
            url: "myReviewList.bo",
            data: {
                memberNo: memberNo
            },
            success: function(list){
                
                let str = "";
                for (b of list) {
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

                document.querySelector(".myReviewSchedule").innerHTML = str;  

            },
            error: function(){
                console.log("myReviewList.bo ajax통신 실패");
            }
        })
    }


   







