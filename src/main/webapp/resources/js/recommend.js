let startNum = 0;

function recommendListMore() {
    $.ajax({
        url: "recommendList.bo",
        data: { "startNum": startNum },
        success: function (data) {
            if (data.length < 8) {
                $("#btn-more").remove();
            } else {
                let str = '';
                if (data.length > 0) {
                    for (let list of data) {
                        str += `<a href="recommendDetail.bo?recommendBoardNo=${list.recommendBoardNo}">
                                <div class="recommend-info">
                                    <strong class="title">${list.recommendBoardTitle}</strong>
                                    <p class="text">${list.recommendBoardContent}</p>
                                </div>
                                <div class="recommend-cover">
                                    <img src="${list.recommendTitleImg}" alt="이미지를 표출할 수 없습니다.">
                                </div>
                            </a>`;
                    }
                    document.querySelector('.post-wrap').innerHTML = str;
                }
            }
        },
        error: function () {
            console.log("recommendTripList.bo ajax 실패");
        }
    });
}

$(document).ready(function () {
    recommendListMore(); // Initial call

    // You can keep other document.ready code here if needed
});