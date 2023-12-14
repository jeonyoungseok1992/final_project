var boardValue = {};

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

// ajax로 넘겨주는 함수
function pickupReSchedule(num){
    reviewBoardApi.getScheduleReList({
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
function moveWritePage(){
    location.href="reviewEdit.bo?ppage=" + boardValue.selectPlan.tripPlanNo;
}