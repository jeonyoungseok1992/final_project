function toStringDate(date){
    return new Date(date).toISOString().substring(0, 10)
}

//일단위 날짜 차이 구하기
function dateToDay(startDate, endDate){
    // 날짜 차이 계산 (밀리초 단위)
    let timeDifference = endDate - startDate;

    // 밀리초를 일로 변환
    let daysDifference = timeDifference / (1000 * 60 * 60 * 24);
    return daysDifference;
}

//yyyy.mm.dd(day)
function formatDate(date) {
    // month는 0부터 시작하므로 1을 빼줍니다.
    const formattedDate = date ? new Date(date) : new Date();

    // 날짜를 원하는 형식으로 출력하기 위해 필요한 부분들을 추출합니다.
    const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
    const formattedString = formattedDate.toLocaleDateString('ko-KR', options);

    return formattedString;
}

//mm/dd
function dateToMMDD(date){
    // 현재 날짜 가져오기
    let currentDate = new Date(date);

    // 월과 일을 가져오기
    let month = currentDate.getMonth() + 1; // 월은 0부터 시작하므로 1을 더합니다.
    let day = currentDate.getDate();

    // 날짜를 'MM/DD' 형식으로 조합
    let formattedDate = (month < 10 ? '0' : '') + month + '/' + (day < 10 ? '0' : '') + day;

    return formattedDate;
}

//yyyy-mm-dd
function dateToYYYYMMDD(date){
    // 현재 날짜 가져오기
    let currentDate = new Date(date);

    // 월과 일을 가져오기
    let month = currentDate.getMonth() + 1; // 월은 0부터 시작하므로 1을 더합니다.
    let day = currentDate.getDate();
    let year = currentDate.getFullYear();

    // 날짜를 'MM/DD' 형식으로 조합
    let formattedDate = year + "-" + (month < 10 ? '0' : '') + month + '-' + (day < 10 ? '0' : '') + day;

    return formattedDate;
}

//date객체 두개 년/월/일 비교
function areDatesEqual(date1, date2) {
    return (
        date1.getFullYear() === date2.getFullYear() &&
        date1.getMonth() === date2.getMonth() &&
        date1.getDate() === date2.getDate()
    );
}

function getKoreanDayOfWeek(date) {
    // 요일에 해당하는 문자열 배열
    var daysOfWeek = ['일', '월', '화', '수', '목', '금', '토'];
  
    // Date 객체의 getDay 메서드를 사용하여 요일을 가져옴 (0: 일요일, 1: 월요일, ..., 6: 토요일)
    var dayIndex = date.getDay();
  
    // 요일에 해당하는 문자열 반환
    return daysOfWeek[dayIndex];
}

// date type input
function getInputUnit(info){
    const inputUnit = document.createElement("input");
    switch(info.type){
        case "normal-input-date":{
            inputUnit.type = "date";

            if (info.min) inputUnit.min = info.min;
            if (info.onchange) {
                inputUnit.onchange = info.onchange;
            }       
        }

    }
    if (info.id) inputUnit.id = info.id;
    if (info.value) inputUnit.value = info.value + "-";

    return inputUnit;

}

// 검색 아이콘 input
function getSearchInputUnit(info) {
    const formOutline = document.createElement("div");
    formOutline.className = "form-outline";
  
    const inputSearch = document.createElement("input");
    inputSearch.type = "text";
    inputSearch.className = "form-control";
    if (info.id) inputSearch.id = info.id;
    if (info.value) inputSearch.value = info.value;
    if (info.inputPlaceholder) inputSearch.placeholder = info.inputPlaceholder;
    formOutline.appendChild(inputSearch);
  
    const searchButton = document.createElement("button");
    searchButton.type = "button";
    searchButton.className = "btn btn-primary";
    const icon = document.createElement("i");
    icon.className = "bi bi-search";
    searchButton.appendChild(icon);
  
    formOutline.appendChild(searchButton);
  
    return formOutline;
  }

  // 토글버튼
  function toggleButtonUnit(info) {
    const toggleButton = document.createElement("button");
    toggleButton.type = "button";
    if (info.className) toggleButton.className = info.className;
    if (info.innerHTML) toggleButton.innerHTML = info.innerHTML;
    if (info.innerText) toggleButton.innerText = info.innerText;
  
    toggleButton.isToggle = false;
  
    toggleButton.addEventListener("click", function () {
      const parent = toggleButton.parentElement;
      const buttons = parent.querySelectorAll("button");
      buttons.forEach((button) => {
        button.classList.remove("toggle");
        button.isToggle = false;
      });
  
      toggleButton.isToggle = !toggleButton.isToggle;
      toggleButton.classList.toggle("toggle", toggleButton.isToggle);
    });
  
    return toggleButton;
  }

  // selectWrap li
    function selectWrapLiUnit(info) {
   
        const selectWrapLi = document.createElement('li');
        selectWrapLi.info = info;
        selectWrapLi.className = info.className ? info.className : "";
        selectWrapLi.draggable = "true";

        const liDiv = document.createElement('div');
        selectWrapLi.appendChild(liDiv);

        const img = document.createElement("img");
        if (info.src){
            img.src = info.src;
        } else {
            img.src = "https://img.freepik.com/free-vector/festive-city-night-concept_74855-7363.jpg";

        }
        liDiv.appendChild(img);

        const divDiv = document.createElement('div');
        liDiv.appendChild(divDiv);

        const divDiv2 = document.createElement('div');
        divDiv2.type = "hidden";
        divDiv2.name = "mapX";
        divDiv2.value = info.mapX;
        liDiv.appendChild(divDiv2);

        const h5 = document.createElement('h5');
        if (info.title) h5.innerText = info.title;
        divDiv.appendChild(h5);

        const span = document.createElement('span');
        if (info.category) span.innerText = info.category;
        span.dataset.filter = info.category;
        divDiv.appendChild(span);

        const selectWrapUlDiv = document.createElement('div');
        liDiv.appendChild(selectWrapUlDiv);

        const selectWrapBtn = document.createElement('button');
        if (info.removeCallback) {
            selectWrapBtn.innerHTML = '<i class="bi bi-x"></i>';
            selectWrapBtn.onclick = function() {
                info.removeCallback(info);
            };
        } else {
            selectWrapBtn.innerText = "+";
        }
        selectWrapLi.appendChild(selectWrapBtn);

        return selectWrapLi;

    }



    // sidemodal content
    function sideModalContentUnit(info, placeList) {
        let formattedStartDate = dateToMMDD(info.date) + "(" + getKoreanDayOfWeek(info.date) + ")";
        
        const contentWrap = document.createElement('div');
        contentWrap.className = "content-wrap";

        const sideModalDayDate = document.createElement('div');
        sideModalDayDate.className = "day-date side";
        contentWrap.appendChild(sideModalDayDate);

        const sideModalDay = document.createElement('h5');
        if (info.day) sideModalDay.innerHTML = info.day;
        sideModalDayDate.appendChild(sideModalDay);

        const sideModalDate = document.createElement('div');
        sideModalDate.innerText = formattedStartDate;
        sideModalDayDate.appendChild(sideModalDate);

        const sideModalSelectWrap = document.createElement('div');
        sideModalSelectWrap.className = "select-wrap";
        contentWrap.appendChild(sideModalSelectWrap);

        const sideModalUl = document.createElement('ul');
        sideModalUl.startDate = info.date;
        sideModalSelectWrap.appendChild(sideModalUl);


        for (let place of placeList) {
            sideModalUl.appendChild(selectWrapLiUnit(place));
        }

        return contentWrap;
    }

    //id 4자리 랜덤 생성
    function generateShortUUID() {
        var uuid = '';
        var characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
      
        for (var i = 0; i < 4; i++) {
          var randomIndex = Math.floor(Math.random() * characters.length);
          uuid += characters.charAt(randomIndex);
        }
      
        return uuid;
      }

      function requestFriend(memberNo){
        console.log('도착');
        $.ajax({
            url : "requestFriend.me",
            data:{
                friendNo : memberNo
            },
            success : function(res){
                //성공시 다시 그려주기
                if(res === "success"){
                    console.log("친구신청 성공");
                    document.getElementById('drop-fr').classList.add('ddd');
            }
            },
            error:function(){
                console.log("reply 댓글추가 통신 실패");
            }
        })
    
    }


    //AJAX 통신 시작
$( document ).ajaxStart(function() {
    //마우스 커서를 로딩 중 커서로 변경
    $('html').css("cursor", "wait"); 
});
//AJAX 통신 종료
$( document ).ajaxStop(function() {
    //마우스 커서를 원래대로 돌린다
    $('html').css("cursor", "auto"); 
});

