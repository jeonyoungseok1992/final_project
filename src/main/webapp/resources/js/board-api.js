const boardApi = {
    getScheduleList : function(data, callback){
        $.ajax({
            url : "scheduleList.bo",
            data: data,
            success : function(list){
                callback(list);
            },
            error:function(){
                console.log("scheduleList.bo ajax 통신 실패");
            }
        })
    } 
}

const reviewBoardApi = {
    getScheduleReList : function(data, callback){
        $.ajax({
            url : "scheduleListRe.bo",
            data: data,
            success : function(list){
                callback(list);
            },
            error:function(){
                console.log("scheduleListRe.bo ajax 통신 실패");
            }
        })
    } 
}


