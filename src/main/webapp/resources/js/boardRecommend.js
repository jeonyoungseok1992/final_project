$(document).ready(function(){
    /*추천 여행지 공공데이터 ajax*/
    $.ajax({
        url: "recommendTripList.bo",
        success: function(data){
            console.log(data)
            let str = '';
            for(list of data){
                str +=` <a href="#none">
                            <div class="recommend-info">
                                <strong class="title">${list.title}</strong>
                                <p class="text">
                                    추천 행사 및 여행지를 찾고 계시다면 Mapping이 추천하는 장소들은 어떠실까요? "${list.title}" 라는 축제를 소개해드리겠습니다! ${list.addr2} 행사 운영 기간 : ${list.eventstartdate} - ${list.eventenddate} <br> 주소 : ${list.addr1}<br> 문의 : ${list.tel} 
                                </p> 
                            </div>
                            <div class="recommend-cover">
                                <img src="${list.firstimage}" alt="이미지를 표출할 수 없습니다.">
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