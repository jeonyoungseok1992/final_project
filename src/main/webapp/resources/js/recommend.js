$(document).ready(function(){
    /* 박수현 추천 여행지 더보기 클릭시 전체보기 ajax */
    $.ajax({
        url: "recommendList.bo",
        success: function(data){
            let str = '';
            for(list of data){
                str +=` <a a href="recommendDetail.bo?recommendBoardNo=${list.recommendBoardNo}">
                            <div class="recommend-info">
                                <strong class="title">${list.recommendBoardTitle}</strong>
                                <p class="text">
                                    ${list.recommendBoardContent}
                                </p> 
                            </div>
                            <div class="recommend-cover">
                                <img src="${list.recommendTitleImg}" alt="이미지를 표출할 수 없습니다.">
                            </div>
                        </a>`
                    }
                document.querySelector('.post-wrap').innerHTML=str;
            
            },
        error: function(){
            console.log("recommendTripList.bo ajax 실패");
        }
    })

});
