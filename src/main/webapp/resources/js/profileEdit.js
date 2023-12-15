$(document).ready(function () {


    //핸드폰 번호 스크립트
    $('.tel').keydown(function(event) {
        var key = event.charCode || event.keyCode || 0;
        $text = $(this);
        if (key !== 8 && key !== 9) {
            if ($text.val().length === 3) {
                $text.val($text.val() + '-');
            }
            if ($text.val().length === 8) {
                $text.val($text.val() + '-');
            }
        }

        return (key == 8 || key == 9 || key == 46 || (key >= 48 && key <= 57) || (key >= 96 && key <= 105));          
    });









});


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