const reviewValue = {}

$(document).ready(function () {
    // $("#input_file").on("change", handleImgFileSelect);

    var fontSizes = ['8', '9', '10', '11', '12', '14', '16', '18', '20', '22', '24', '28', '30', '36', '50', '72', '100'];
    var fontNames = ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', '맑은 고딕', '궁서', '굴림체', '굴림', '바탕체'];
    var toolbar = [['fontname', ['fontname']],
    ['fontsize', ['fontsize']],
    ['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
    ['color', ['forecolor', 'color']],
    ['insert', ['picture']],
    ['table', ['table']],
    ['para', ['ul', 'ol', 'paragraph']],
    ['height', ['height']],
    ['view', ['codeview']]
    ];

    var setting = {
        height: 500,
        minHeight: null,
        maxHeight: null,
        lang: 'ko-KR',
        toolbar: toolbar,
        fontSizes: fontSizes,
        fontNames: fontNames,
        disableResizeEditor: true,
        callbacks: { //여기 부분이 이미지를 첨부하는 부분
            onImageUpload: function (files, editor, welEditable) {
                for (var i = files.length - 1; i >= 0; i--) {
                    console.log(files[i], this);
                    sendFile(files[i], this);
                }
            },
            onMediaDelete: function ($target, editor, $editable) {
                var deletedImageUrl = $target
                    .attr('src')
                    .split('/')
                    .pop()
                // ajax 함수 호출
                deleteFile(deletedImageUrl);

            }
        }
    };

    // value 를 먼저 넣고 초기화 한다.  
    //$('#summernote').val('${fn:replace(productDetail.product_content,"\'","\\'")}');
    $('#summernote').summernote(setting);

    reviewValue.reviewStar = {};
    // 별점주기
    let $ratings = $('.ui-grade');
    $ratings.each(function () {
        if ($(this).data('init')) return;
        $(this).data('init', true);

        let $star = $(this).find('button');

        //default
        let $checkedStar = $(this).find('[aria-checked="true"]');
        if ($checkedStar) {
            $checkedStar.addClass('checked');
            $checkedStar.prevAll().addClass('checked');
        }

        //mouseover
        $star.on('mouseover', function () {
            $star.not($(this).addClass('hover')).removeClass('hover');
            $(this).prevAll().addClass('hover');
        });
        $star.on('mouseleave', function () {
            $star.removeClass('hover');
        });

        //click
        $star.on('click', function () {
            let $this = $(this);
            let txt = $this.text();
            let $result = $this.parent().find('.result-text');

            $star.not($this.addClass('checked')).removeClass('checked');
            $this.prevAll().addClass('checked');
            $star.not($this.attr('aria-checked', true)).attr('aria-checked', false);

            
            console.log($this.parent().attr('attractionNo'))
            reviewValue.reviewStar[$this.parent().attr('attractionNo')] = txt;
            if (txt) {
                $result.text(txt + "점");
            }
        });
    });

});
//별점 주면 모든 명소에 입력
function insertStars(){
    let sendData = [];
    for(let key in reviewValue.reviewStar) {
        sendData.push({
            attractionNo: key,
            starGrade: reviewValue.reviewStar[key],
        })
    }


    $.ajax({
        url: "insertStars.bo",
        data: {
            stars: JSON.stringify(sendData),
        },
        success: function (res) {
            if(res === "success"){

                for(let i in sendData){
                    console.log(sendData[i].attractionNo)
                    let attDiv =document.getElementById('att'+sendData[i].attractionNo);
                    console.log(attDiv)
                    attDiv.innerText = sendData[i].starGrade +"점";
                }
              
            }   
            
        },
        error: function () {
            console.log("insertStars.bo ajax 통신 실패");
        }
    });
}

/* summernote에서 이미지 업로드시 실행할 함수 */
function sendFile(file, editor) {
    // 파일 전송을 위한 폼생성
    data = new FormData();
    data.append("file", file);
    $.ajax({ // ajax를 통해 파일 업로드 처리
        data: data,
        type: "POST",
        url: "uploadSummernoteImageFile",
        enctype: 'multipart/form-data',
        cache: false,
        contentType: false,
        processData: false,
        success: function (data) { // 처리가 성공할 경우
            // 에디터에 이미지 출력
            $(editor).summernote('editor.insertImage', data);
        }
    });
}
/* summernote에서 이미지 삭제시 실행할 함수 */
function deleteFile(file) {
    data = new FormData()
    data.append('file', file)
    $.ajax({
        data: data,
        type: 'POST',
        url: 'deleteSummernoteImageFile',
        contentType: false,
        enctype: 'multipart/form-data',
        processData: false,
    })
}

function sumReset() {
    // 서머노트 리셋
    $('#summernote').summernote('reset');
}
var boardValue = {};


// ajax로 넘겨주는 함수
function pickupSchedule(num) {
    reviewBoardApi.getScheduleReList({
        memberNo: num,
    }, function (list) {
        const tansList = transScheduleList(list);
        drawPlan(tansList);
    })
}
//db에서 가져온 데이터 가공하는 함수
function transScheduleList(list) {
    let tmpList = []
    // console.log(list);
    for (plan of list) {
        if (tmpList.filter(t => t.tripPlanNo === plan.tripPlanNo).length > 0) {
            tmpList = tmpList.map(t => {
                if (t.tripPlanNo === plan.tripPlanNo) {
                    if (t.attractionList[plan.tripNday]) {
                        t.attractionList[plan.tripNday].push({
                            attractionName: plan.attractionName,
                            attractionChangeNameImg: plan.attractionChangeNameImg
                        });
                    } else {
                        t.attractionList[plan.tripNday] = [{
                            attractionName: plan.attractionName,
                            attractionChangeNameImg: plan.attractionChangeNameImg
                        }];
                    }
                }
                return t;
            })
        } else {
            tmpList.push({
                tripPlanNo: plan.tripPlanNo,
                tripPlanThumbnail: plan.tripPlanThumbnail,
                tripStartDate: plan.tripStartDate,
                tripEndDate: plan.tripEndDate,
                regionName: plan.regionName,
                attractionList: {
                    [plan.tripNday]: [
                        {
                            attractionName: plan.attractionName,
                            attractionChangeNameImg: plan.attractionChangeNameImg
                        }]
                },
            })
        }
    }


    return tmpList;





}

//큰 플랜 버튼 그리는 함수
function drawPlan(tmpList) {
    let str1 = "";
    for (let i in tmpList) {
        str1 += `<button index="` + i + `" role="tab" aria-selected="true" aria-controls="tabPanel1" id="tab1" class="active"><div class="travel-list-text" >
      <p>${tmpList[i].tripStartDate}-${tmpList[i].tripEndDate}</p><h3>${tmpList[i].regionName}</h3></div><img src="${tmpList[i].tripPlanThumbnail}" alt=""></button>`
    }

    $(".tab-menu.button-group.button-type").html(str1);


    $(".active").on("click", function () {
        boardValue.selectPlan = tmpList[this.getAttribute("index")];
        drawPlanChild(tmpList[this.getAttribute("index")])
    });
    $(".active:first-child").trigger("click");
}

//큰 플랜버튼 누르면 밑에 각 장소들 나오게 하는 함수
function drawPlanChild(tripPlan) {
    let str2 = "";
    const attractionList = tripPlan.attractionList;
    // console.log(attractionList);
    for (let i in attractionList) {
        str2 += `
          <div class="travel-location">
              <h4>${i}일차</h4>
              <div>
              <div class="menu">`;



        for (let p of attractionList[i]) {
            str2 +=
                `<div class="item-wrap">
                              <div class="img-area">
                                  <img src="${p.attractionChangeNameImg}" alt="">
                              </div>
                              <h5>${p.attractionName}</h5>
                  </div>`;

        }
        str2 += `</div>
              </div>
          </div>`;

    }
    $("#tabPanel1").html(str2);




}




function reviewReDraw() {
    $.ajax({
        url: "reviewDraw.bo",
        data: {
            tripPlanNo: boardValue.selectPlan.tripPlanNo,
        },
        success: function (list) {
            console.log(list)
            const tansList = transScheduleList(list);
            console.log(tansList)
            reviewReDrawPlan(tansList);
        },
        error: function () {
            console.log("reviewDraw.bo ajax 통신 실패");
        }
    });
}

function reviewReDrawPlan(tmpList) {
    let str3 = "";
    let str4 = "";
    let map = document.getElementById('map');
    let ndayContent = document.getElementById('ndayContent');
    const attractions = tmpList[0].attractionList; 
    console.log(attractions);
    map.innerHTML = str3;
    ndayContent.innerHTML = str4;
    str3 += `<img src="` + tmpList[0].tripPlanThumbnail + `" alt="" style="width: 100%; height: 100%;">`;

    for (let a in attractions) {
        str4 += `
                 <div class="nDay">${a}일차
                 <div class="location">`;

        for (b of attractions[a]) {
            str4 += `
                    <div class="location-img"><img src="`+ b.attractionChangeNameImg + `" alt="장소"></div>
                    
                     `;

        }
        str4 += `</div>
                    </div>`;
    }
    map.innerHTML = str3;
    ndayContent.innerHTML = str4;
}

