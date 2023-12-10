$(document).ready(function() 
{
    // $("#input_file").on("change", handleImgFileSelect);

    var fontSizes = [ '8', '9', '10', '11', '12', '14','16', '18', '20', '22', '24', '28', '30', '36', '50', '72','100' ];
    var fontNames = [ 'Arial', 'Arial Black','Comic Sans MS', 'Courier New', '맑은 고딕', '궁서', '굴림체', '굴림', '바탕체' ];
    var toolbar = [[ 'fontname', [ 'fontname' ] ],
    [ 'fontsize', [ 'fontsize' ] ],
    [ 'style',    [ 'bold', 'italic', 'underline','strikethrough', 'clear' ] ],
    [ 'color', [ 'forecolor', 'color' ] ],
    ['insert',['picture']],
    [ 'table', [ 'table' ] ],
    [ 'para', [ 'ul', 'ol', 'paragraph' ] ],
    [ 'height', [ 'height' ] ],
    [ 'view', [ 'codeview' ] ]
    ];
    
  var setting = {
      height : 500,
      minHeight : null,
      maxHeight : null,
      lang : 'ko-KR',
      toolbar : toolbar,
      fontSizes : fontSizes,
      fontNames : fontNames,
      disableResizeEditor: true,
      callbacks : { //여기 부분이 이미지를 첨부하는 부분
          onImageUpload : function(files, editor,	welEditable) 
          {
              for (var i = files.length - 1; i >= 0; i--) 
              {
                console.log(files[i],this);
                sendFile(files[i],this);
              }
          },
          onMediaDelete : function($target, editor, $editable) 
            {
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
    //   $('#summernote').val('${fn:replace(productDetail.product_content,"\'","\\'")}');
      $('#summernote').summernote(setting);
    });

     /* summernote에서 이미지 업로드시 실행할 함수 */
	 	function sendFile(file, editor) {
            // 파일 전송을 위한 폼생성
	 		data = new FormData();
	 	    data.append("file", file);
	 	    $.ajax({ // ajax를 통해 파일 업로드 처리
	 	        data : data,
	 	        type : "POST",
	 	        url : "uploadSummernoteImageFile",
                enctype : 'multipart/form-data',
	 	        cache : false,
	 	        contentType : false,
	 	        processData : false,
	 	        success : function(data) { // 처리가 성공할 경우
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

        function sumReset(){
          // 서머노트 리셋
          $('#summernote').summernote('reset');
        }
var boardValue = {};


// ajax로 넘겨주는 함수
function pickupSchedule(num){
  boardApi.getScheduleList({
      memberNo : num,
  }, function(list){
      const tansList = transScheduleList(list);
      drawPlan(tansList);
  })
}
//db에서 가져온 데이터 가공하는 함수
function transScheduleList(list){
  let tmpList = []
  // console.log(list);
  for(plan of list){
      if (tmpList.filter(t => t.tripPlanNo === plan.tripPlanNo).length > 0){
          tmpList = tmpList.map( t => {
              if(t.tripPlanNo === plan.tripPlanNo ){
                  if (t.attractionList[plan.tripNday]) {
                      t.attractionList[plan.tripNday].push({
                          attractionName: plan.attractionName,
                          attractionChangeNameImg: plan.attractionChangeNameImg
                          });
                  } else {
                      t.attractionList[plan.tripNday]= [{
                          attractionName: plan.attractionName,
                          attractionChangeNameImg: plan.attractionChangeNameImg
                          }];
                  }
              }
              return t;
          })
      } else {
          tmpList.push({
              tripPlanNo : plan.tripPlanNo,
              tripPlanThumbnail : plan.tripPlanThumbnail,
              tripStartDate : plan.tripStartDate,
              tripEndDate : plan.tripEndDate,
              regionName : plan.regionName,
              attractionList : {
                  [plan.tripNday] : [
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
function drawPlan(tmpList){
  let str1 ="";
  for(let i in tmpList){
      str1 += `<button index="`+i+`" role="tab" aria-selected="true" aria-controls="tabPanel1" id="tab1" class="active"><div class="travel-list-text" >
      <p>${tmpList[i].tripStartDate}-${tmpList[i].tripEndDate}</p><h3>${tmpList[i].regionName}</h3></div><img src="${tmpList[i].tripPlanThumbnail}" alt=""></button>`
  }
  
  $(".tab-menu.button-group.button-type").html(str1);
  
  
  $(".active").on("click",function(){
      boardValue.selectPlan = tmpList[this.getAttribute("index")];
      drawPlanChild(tmpList[this.getAttribute("index")])
  });
  $(".active:first-child").trigger("click");
}

//큰 플랜버튼 누르면 밑에 각 장소들 나오게 하는 함수
function drawPlanChild(tripPlan){
  let str2 = "";
  const attractionList = tripPlan.attractionList;
  // console.log(attractionList);
  for(let i in attractionList){
          str2 += `
          <div class="travel-location">
              <h4>${i}일차</h4>
              <div>
              <div class="menu">`;
             
              

          for(let p of attractionList[i]){
              str2 +=  
                  `<div class="item-wrap">
                              <div class="img-area">
                                  <img src="${p.attractionChangeNameImg}" alt="">
                              </div>
                              <h5>${p.attractionName}</h5>
                  </div>`;
      
          }
          str2 +=`</div>
              </div>
          </div>`;
      
      } 
      $("#tabPanel1").html(str2);
   
      
    

  }




  function reDraw() {
    $.ajax({
        url: "reDraw.bo",
        data : {"tripPlanNo" : boardValue.selectPlan.tripPlanNo,
        },
        anyne: true,
        type: "POST",
        contentType: 'application/json',
        dataType: "json",
        success: function (list) {
          console.log(list)
            const tansList = transScheduleList(list);
            console.log(tansList)
            reDrawPlan(tansList);
        },
        error: function () {
            console.log("reDraw.bo ajax 통신 실패");
        }
    });
}

function reDrawPlan(tmpList) {
    let str3 = "";
    let str4 = "";
    let map = document.getElementById('map');
    let ndayContent = document.getElementById('ndayContent');
    map.empty();
    ndayContent.empty();
    str3 += `<img src="${tmpList[0].tripPlanThumbnail}" alt="" style="width: 100%; height: 100%;">`;
    for (let i in tmpList) {
        str4 +=
            `<c:forEach var="i" begin="1" end="${tmpList[tmpList.size() - 1].tripNday}" step="1">
                <div class="nDay">i일차</div>
                <div class="location">
                    <c:forEach var="p" items="${tmpList}">
                        <c:choose>
                            <c:when test ="i eq p.tripNday}">
                                <div class="location-img"><img src="${p.attractionChangeNameImg}" alt="전주"></div>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </div>
            </c:forEach>`;
    }
    map.innerHTML = str3;
    ndayContent.innerHTML = str4;
}