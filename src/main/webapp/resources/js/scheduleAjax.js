//명소 ajax
function attractionAjax(){
$.ajax({
    url: "",
    success: function(data){
        console.log(data);
        
             let mX;
             let mY;
            let attLoca ;
            let addTitle; 

                    for(att of data){
                        attLoca   = att.firstimage;
                        addTitle   = att.title;
                        mX = att.mapx
                        mY = att.mapy;
                        addMarker(mY, mX);
                            const selectCard = selectWrapLiUnit({
                                src: attLoca, 
                                title: addTitle,
                                category: "명소",
                                className: "draggable",
                                id: generateShortUUID()            
                            });

                selectWrapUl.appendChild(selectCard);
                }
            console.log(attLoca);
    

    },
    error: function(){
        console.log("attractionList.api ajax 실패");
    }
})
}

function restaurantAjax(){
$.ajax({
    url: "attFoodList.api",
    success: function(data){
        console.log(data);
        

            let attLoca ;
            let addTitle; 

                    for(att of data){
                        attLoca   = att.firstimage;
                        addTitle   = att.title;
                            const selectCard = selectWrapLiUnit({
                                src: attLoca, 
                                title: addTitle,
                                category: "식당",
                                className: "draggable",
                                id: generateShortUUID()            
                            });

                selectWrapUl.appendChild(selectCard);
                }
            console.log(attLoca);
    
    },
    error: function(){
        console.log("attFoodList.api ajax 실패");
    }
})
}

function eventAjax(){
$.ajax({
    url: "attEventList.api",
    success: function(data){
        console.log(data);
        

            let attLoca ;
            let addTitle; 

                    for(att of data){

                        attLoca   = att.firstimage;
                        addTitle   = att.title;
                            const selectCard = selectWrapLiUnit({
                                src: attLoca, 
                                title: addTitle,
                                category: "행사",
                                className: "draggable",
                                id: generateShortUUID()            
                            });

                selectWrapUl.appendChild(selectCard);
                }
            console.log(attLoca);
    
    },
    error: function(){
        console.log("attEventList.api ajax 실패");
    }
})
}

//최종일정 짜는 ajax
function scheduleMakeAjax(scheduleInfo){
    $.ajax({
        url: "totalScheduleMake.bo",
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(scheduleInfo),
        success: function(res){
            if(res ==="success"){
                // moveFinalSchedule();
            }
        
        },
        error: function(){
            console.log("totalScheduleMake.bo ajax 실패");
        }
    })
}

//최종일정 데이터가지고 페이지로 가기
function moveFinalSchedule(){

}