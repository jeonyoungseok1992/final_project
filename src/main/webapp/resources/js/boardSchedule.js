const bsValue = {
    regionData: [], /* 페이지 로드시 지역정보 가져온거 담겨있는곳*/
    attData: [],
    motelData: []
}

function init(regionList, memberNo){
    bsValue.regionData = JSON.parse(regionList);
    drawScheduleMake({
        startDate: new Date(),
        endDate: null,
        placeInfo : [],
        lodgingInfo : [],
        transportation: "대중교통",
        regionName: bsValue.regionData.regionName,
        regionX: bsValue.regionData.regionX,
        regionY: bsValue.regionData.regionY,
        regionNo: bsValue.regionData.regionNo,
        memberNo : memberNo,
    });


};  

function getDragAfterElement(container, x) {
    const tdraggable = [
        ...container.querySelectorAll(".draggable:not(.dragging)"),
    ];

    return tdraggable.reduce(
        (closest, child) => {
            const box = child.getBoundingClientRect();
            const offset = x - box.left - box.width / 2;
      
            if (offset < 0 && offset > closest.offset) {
                return { offset: offset, element: child };
            } else {
                return closest;
            }
        },
        { offset: Number.NEGATIVE_INFINITY },
    ).element;
}

function checkPlaceAndLodgingInfo(type, scheduleInfo){
    const start = scheduleInfo.startDate;
    const end = scheduleInfo.endDate;
    let dateList = {};
    
    for (let i = 0; i <= dateToDay(start, end); i++) {
        const tmpDate = new Date(start);
        const nowDate = new Date(tmpDate.setDate(tmpDate.getDate() + i));

        dateList[dateToYYYYMMDD(nowDate)] = true;
    }
    
    for (let unit of scheduleInfo[type]){
        dateList[dateToYYYYMMDD(unit.date)] = false;
    }
    
    for (let date in dateList){
        if(dateList[date])
            return true;
    }

    return false;
}

function setStepBtn(btnsInfo){

    const prevBtn = document.getElementById("prev-btn");
    const nextBtn = document.getElementById("next-btn");

    if (btnsInfo?.prev?.display)
        prevBtn.style.display = btnsInfo.prev.display;

    if (btnsInfo?.prev?.clickEvent)
        prevBtn.onclick = btnsInfo?.prev?.clickEvent;

    if (btnsInfo?.next?.display)
        nextBtn.style.display = btnsInfo.next.display;

    if (btnsInfo?.next?.clickEvent)
        nextBtn.onclick = btnsInfo?.next?.clickEvent;
}

// Step button active
function activeButton(activeStep) {
    document.querySelectorAll('.tab-menu button').forEach(button => {
        button.classList.remove('active');
    });

    document.getElementById(activeStep).classList.add('active');
}

//******************************************step1**************************************************
function drawScheduleMake(scheduleInfo) {

    //맵 그리기
    var container = document.getElementById('map'); 
    var options = { 
        center: new kakao.maps.LatLng( scheduleInfo.regionY, scheduleInfo.regionX), 
        level: 4
    };

    var map = new kakao.maps.Map(container, options);



   
    activeButton('step1');
    document.getElementById("side-modal").style.display = "none";

    //시작시간과 끝시간을 넘겨받아
    //일정확정날짜를 그려주는 함수
    function drawFixSchedule(start, end) {
        const fixScheduleTB = document.querySelector("#schedule-view-table > tbody");
        $(fixScheduleTB).empty();

        for (let i = 0; i <= dateToDay(start, end); i++) {
            const tmpDate = new Date(start);
            const nowDate = new Date(tmpDate.setDate(tmpDate.getDate() + i));

            if (i === 0 || i === dateToDay(start, end)) {
                let str = `<tr>
                            <th>${dateToMMDD(nowDate)}</th>
                            <td>${getKoreanDayOfWeek(nowDate)}</td>`;

                str +=  i === 0 ? `<td><input id="startTime" type="time" value="10:00"></td><td></td></tr>` : `<td></td><td><input id="endTime" type="time" value="22:00"></td></tr>`;
                        
                fixScheduleTB.innerHTML += str;
            }
        }
    }

    //일정 시작시간과 종료시간이 변했을 때 실행하는 함수
    function updateEndDate(){
        const startInput = document.getElementById('startDate');
        const endInput = document.getElementById('endDate');

        const startDate = startInput.value ? new Date(startInput.value) : null;
        const endDate = endInput.value ? new Date(endInput.value) : null;

        if (startDate) {
            endInput.min = toStringDate(startDate);
            scheduleInfo.startDate = startDate;
        }

        if (endDate) {
            startInput.max = toStringDate(endDate);  
            scheduleInfo.endDate = endDate;     
        }
        
        if(startDate && endDate) {
            drawFixSchedule(startDate, endDate);
        } 
    }

    const contentZone = document.getElementById("content-zone");
    $(contentZone).empty();

  
    const title = document.createElement("h3");
    title.innerText = bsValue.regionData.regionName;
    contentZone.appendChild(title);

    const timeZone = document.createElement("div");
    timeZone.className = "date-time";
    contentZone.appendChild(timeZone);

    const startInput = getInputUnit({
        type: "normal-input-date",
        id: "startDate",
        onchange: updateEndDate
    });
    timeZone.appendChild(startInput)

    const dash = document.createTextNode(" - ");
    timeZone.appendChild(dash)

    const endInput = getInputUnit({
        type: "normal-input-date",
        id: "endDate",
        onchange: updateEndDate
    });
    timeZone.appendChild(endInput);

    const scheduleZone = document.createElement('div');
    scheduleZone.className = "time-details";
    contentZone.appendChild(scheduleZone);
    
    const scheduleZoneToggle = document.createElement('p');
    scheduleZoneToggle.className = "time";
    scheduleZoneToggle.innerHTML = "여행시간 상세설정 <span class='color'>총 24시간 00분</span>";
    scheduleZoneToggle.isToggle = true;
    scheduleZone.appendChild(scheduleZoneToggle);
    scheduleZoneToggle.onclick = function(){
        scheduleZoneToggle.isToggle = !scheduleZoneToggle.isToggle;
        scheduleZone.classList.toggle("toggle", !scheduleZoneToggle.isToggle);
    }

    const ToggleArrow  = document.createElement('span');
    ToggleArrow.className = "arrow";
    ToggleArrow.innerHTML = "<i class='bi bi-chevron-down'></i>";
    scheduleZoneToggle.appendChild(ToggleArrow);

    const scheduleInfoP = document.createElement("p");
    scheduleInfoP.className = "explanation";
    scheduleInfoP.innerHTML = `입력하신 여행 기간이 시차를 고려한 <b>현지 여행 기간(여행지 도착 날짜, 여행지 출발 날짜)</b>이 맞는지 확인해 주시고 각 날짜의 일정 <b>시작시간</b>과 <b>종료시간</b>을 현지 시간 기준으로 설정해 주세요. 기본 설정 시간은 <b>오전 10시~오후 10시 총 12시간</b>입니다.`
    scheduleZone.appendChild(scheduleInfoP);

    const scheduleZoneTable = document.createElement('div');
    scheduleZoneTable.className = "select-date";
    scheduleZoneTable.innerHTML = `
                                    <table id='schedule-view-table'>
                                        <thead>
                                            <tr>
                                                <th>일자</th>
                                                <th>요일</th>
                                                <th>시작시간</th>
                                                <th>종료시간</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>

                                            </tr>
                                        </tbody>
                                    </table>
                                `;
    scheduleZone.appendChild(scheduleZoneTable);
    //시간 설정 완료 버튼
    const TimeSettingBtn  = document.createElement('button');
    TimeSettingBtn.className = "time-set-btn";
    TimeSettingBtn.innerText = "시간 설정 완료";
    scheduleZone.appendChild(TimeSettingBtn);

    function nextStep(){
        if (!scheduleInfo.startDate || !scheduleInfo.endDate){
            alert("일정을 선택해 주세요.");
            return;
        }


        //startTime
        let startData = new Date(scheduleInfo.startDate);
        startData.setHours(document.querySelector("#startTime").value.split(":")[0]);
        startData.setMinutes(document.querySelector("#startTime").value.split(":")[1]);
        scheduleInfo.startDate = new Date(startData);
      

        //endTime
        let endData = new Date(scheduleInfo.endDate);
        endData.setHours(document.querySelector("#endTime").value.split(":")[0]);
        endData.setMinutes(document.querySelector("#endTime").value.split(":")[1]);
        scheduleInfo.endDate = new Date(endData);
      
        selectLocation(scheduleInfo);
    }
    TimeSettingBtn.onclick = nextStep;

    //기본 시간 세팅
    //시작시간을 오늘보다 이후로만 가능하게 설정
    document.getElementById('startDate').min = scheduleInfo.startDate.toISOString().substring(0, 10);
    //끝나는 시간을 오늘보다 이후로만 가능하게 설정
    document.getElementById('endDate').min = scheduleInfo.startDate.toISOString().substring(0, 10);
    // 기본시작시간을 오늘로 설정
    document.getElementById('startDate').value = scheduleInfo.startDate.toISOString().substring(0, 10);

    if (scheduleInfo.endDate) {
        document.getElementById('endDate').value = scheduleInfo.endDate.toISOString().substring(0, 10);

        document.getElementById('startDate').max = scheduleInfo.endDate.toISOString().substring(0, 10);

        updateEndDate();
    }
    
    setStepBtn({
        prev:{
            display: "none"
        },
        //다음
        next:{
            clickEvent: nextStep
        }
    })



    $.ajax({
        url: "attractionList.api",
        data : {
            regionNo : bsValue.regionData.regionNo 
        },
        success: function(data){
            
                //  let mX;
                //  let mY;
                let attLoca ;
                let addTitle; 

                        for(att of data){
                            attLoca   = att.firstimage;
                            addTitle   = att.title;
                            // mX = att.mapx
                            // mY = att.mapy;
                            // addMarker(mY, mX);
                            let selectCard = selectWrapLiUnit({
                                    src: attLoca, 
                                    title: addTitle,
                                    category: "명소",
                                    className: "draggable",
                                    id: generateShortUUID()            
                                });
                                bsValue.attData.push(selectCard);
                                
                    //selectWrapUl.appendChild(selectCard);
                    }


        },
        error: function(){
            console.log("recommendTrip.bo ajax 실패");
        }
    })



    $.ajax({
        url: "attFoodList.api",
        data : {
            regionNo : bsValue.regionData.regionNo 
        },
        success: function(data){
         
            

                let attLoca ;
                let addTitle; 

                        for(att of data){
                            attLoca   = att.firstimage;
                            addTitle   = att.title;
                            let selectCard = selectWrapLiUnit({
                                    src: attLoca, 
                                    title: addTitle,
                                    category: "식당",
                                    className: "draggable",
                                    id: generateShortUUID()            
                                });
                                bsValue.attData.push(selectCard);
                    //selectWrapUl.appendChild(selectCard);
                    }
        
        },
        error: function(){
            console.log("recommendTrip.bo ajax 실패");
        }
    })

    $.ajax({
        url: "attEventList.api",
        data : {
            regionNo : bsValue.regionData.regionNo 
        },
        success: function(data){
        
            

                let attLoca ;
                let addTitle; 

                        for(att of data){

                            attLoca   = att.firstimage;
                            addTitle   = att.title;
                            let selectCard = selectWrapLiUnit({
                                    src: attLoca, 
                                    title: addTitle,
                                    category: "행사",
                                    className: "draggable",
                                    id: generateShortUUID()            
                                });
                                bsValue.attData.push(selectCard);
                    //selectWrapUl.appendChild(selectCard);
                    }
        
        },
        error: function(){
            console.log("recommendTrip.bo ajax 실패");
        }
    })



    $.ajax({
        url: "attMotelList.api",
        data : {
            regionNo : bsValue.regionData.regionNo 
        },
        success: function(data){

            

                let attLoca ;
                let addTitle; 

                        for(att of data){

                            attLoca   = att.firstimage;
                            addTitle   = att.title;
                            let selectCard = selectWrapLiUnit({
                                    src: attLoca, 
                                    title: addTitle,
                                    category: "추천 숙소",
                                    className: "draggable",
                                    id: generateShortUUID()            
                                });
                                bsValue.motelData.push(selectCard);
                    //selectWrapUl.appendChild(selectCard);
                    }
        
        },
        error: function(){
            console.log("recommendTrip.bo ajax 실패");
        }
    })






}

//******************************************step1**************************************************

//******************************************step2**************************************************
function selectLocation(scheduleInfo) {
    activeButton('step2');
    document.getElementById("side-modal").style.display = "block";
    

    const contentZone = document.getElementById("content-zone");
    $(contentZone).empty();

    const locationTitleWrap = document.createElement("div");
    locationTitleWrap.className = "location-title-wrap";
    contentZone.appendChild(locationTitleWrap);

    const title = document.createElement("h4");
    title.innerText = bsValue.regionData.regionName;
    locationTitleWrap.appendChild(title);

    const timeZone = document.createElement("div");
    timeZone.className = "trave-date";
    timeZone.innerText = formatDate(scheduleInfo.startDate) + " - " + formatDate(scheduleInfo.endDate);
    locationTitleWrap.appendChild(timeZone);

    const locationContentWrap = document.createElement("div");
    locationContentWrap.className = "location-content-wrap";
    contentZone.appendChild(locationContentWrap);

    const btnWrap = document.createElement("div");
    btnWrap.className = "button-group";
    locationContentWrap.appendChild(btnWrap);

    const btnToggle = document.createElement('button');
    btnToggle.id = 'btnToggle';
    btnToggle.className = 'clicked';
    btnToggle.innerText = '장소선택';
    btnWrap.appendChild(btnToggle);

    const btnToggle2 = document.createElement('button');
    btnToggle2.id = 'btnToggle2';
    btnToggle2.innerText = '신규장소등록';
    btnWrap.appendChild(btnToggle2);

    const tabContent1 = document.createElement('div');
    tabContent1.id = 'tabContent1';
    tabContent1.className = 'tab-content2 active';
    locationContentWrap.appendChild(tabContent1);
    
    const selectLocation = getSearchInputUnit({
        inputPlaceholder: "장소명을 입력하세요.",
    });
    tabContent1.appendChild(selectLocation);

    const category = document.createElement('div');
    category.className = 'category';
    tabContent1.appendChild(category);
    const recommendedPlace = toggleButtonUnit({
        className:"toggle",
        innerText:"추천 장소",
    });
    recommendedPlace.setAttribute('data-filter', 'all');
    category.appendChild(recommendedPlace);

    const sights = toggleButtonUnit({
        innerText:"명소", //명소
    });
    sights.setAttribute('data-filter', '명소');
    category.appendChild(sights);

    const restaurant = toggleButtonUnit({
        innerText:"식당", //식당
    });
    restaurant.setAttribute('data-filter', '식당');
    category.appendChild(restaurant);

    const cafe = toggleButtonUnit({
        innerText:"행사", //행사
    });
    cafe.setAttribute('data-filter', '행사');
    category.appendChild(cafe);

    const selectWrap = document.createElement('div');
    selectWrap.className = "save-wrap";
    tabContent1.appendChild(selectWrap);

    const selectWrapUl = document.createElement('ul');
    selectWrap.appendChild(selectWrapUl);

    


    
    for(item of bsValue.attData){
        selectWrapUl.appendChild(item);
    }








    // 카테고리 선택
    const categoryfilter = document.querySelector('.category');
    const saveWraps = document.querySelectorAll('.save-wrap > ul > li');

    categoryfilter.addEventListener('click', (ev) => {
        const filter = ev.target.dataset.filter || ev.target.parentNode.dataset.filter;
        












        if (filter == null) {
            return;
        }

        saveWraps.forEach((saveWrap) => {
            const categoryValue = saveWrap.querySelector('span').dataset.filter;
     
            if (filter === 'all' || filter === categoryValue) {
                saveWrap.classList.remove('invisible');
            } else {
                saveWrap.classList.add('invisible');
            }
        });
    });

    const tabContent2 = document.createElement('div');
    tabContent2.id = 'tabContent2';
    tabContent2.className = 'tab-content2';
    locationContentWrap.appendChild(tabContent2);

    const newLocation = getSearchInputUnit({
        inputPlaceholder: "상호명 또는 주소를 입력하세요.",
    });
    tabContent2.appendChild(newLocation);

    const mapArea = document.createElement('div');
    mapArea.className = 'map-area';
    tabContent2.appendChild(mapArea);


    btnToggle.onclick = function () {
        btnToggle.isToggle = !btnToggle.isToggle;
        btnToggle2.isToggle = false;
        btnToggle.classList[btnToggle.isToggle ? 'add' : 'remove']('clicked');
        btnToggle2.classList.remove('clicked');
        toggleTabContent();
    };

    btnToggle2.onclick = function () {
        btnToggle2.isToggle = !btnToggle2.isToggle;
        btnToggle.isToggle = false;
        btnToggle2.classList[btnToggle2.isToggle ? 'add' : 'remove']('clicked');
        btnToggle.classList.remove('clicked');
        toggleTabContent();

         // 마커를 담을 배열입니다
         var markers = [];
         const ulEliment = document.createElement('ul');
         ulEliment.id = 'placesList';
         const divEliment = document.createElement('div');
         divEliment.id = 'pagination';
 
         var mapContainer = document.querySelector('.map-area'), // 지도를 표시할 div 
             mapOption = {
                 center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
                 level: 3 // 지도의 확대 레벨
             };
         tabContent2.appendChild(ulEliment);
         tabContent2.appendChild(divEliment);
         // 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
         var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
 
         // 지도를 생성합니다    
         var map = new kakao.maps.Map(mapContainer, mapOption);
 
         // 장소 검색 객체를 생성합니다
         var ps = new kakao.maps.services.Places();
         var keywordInput = document.querySelector('#tabContent2 .form-outline .form-control');
         var keyword = '';
         var searchBtnn = document.querySelector('#tabContent2 .form-outline .btn.btn-primary');
         keywordInput.addEventListener('keyup', (ev) => {
             if (ev.key === "Enter") {
                 keyword = keywordInput.value;
                 // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
                 ps.keywordSearch(keyword, placesSearchCB);
             }
 
         })
         searchBtnn.addEventListener('click', (ev) => {
             //'장소명을 입력하세요 인풋창옆에 돋보기모양 클릭하는 순간 검색 ajax'
             if (ev.type === "click") {
                 keyword = keywordInput.value;
                 // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
                 ps.keywordSearch(keyword, placesSearchCB);
             }
         })
 
         // 키워드 검색 완료 시 호출되는 콜백함수 입니다
         function placesSearchCB(data, status, pagination) {
             if (status === kakao.maps.services.Status.OK) {
 
                 // 정상적으로 검색이 완료됐으면
                 // 검색 목록과 마커를 표출합니다
                 displayPlaces(data);
                 // 페이지 번호를 표출합니다
                 displayPagination(pagination);
 
             } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
 
                 alert('검색 결과가 존재하지 않습니다.');
                 return;
 
             } else if (status === kakao.maps.services.Status.ERROR) {
 
                 alert('검색 결과 중 오류가 발생했습니다.');
                 return;
 
             }
         }
 
         // 검색 결과 목록과 마커를 표출하는 함수입니다
         function displayPlaces(places) {
 
             var listEl = document.getElementById('placesList'),
                 menuEl = document.getElementById('menu_wrap'),
                 fragment = document.createDocumentFragment(),
                 bounds = new kakao.maps.LatLngBounds(),
                 listStr = '';
 
             // 검색 결과 목록에 추가된 항목들을 제거합니다
             removeAllChildNods(listEl);
 
             // 지도에 표시되고 있는 마커를 제거합니다
             removeMarker();
 
             for (var i = 0; i < places.length; i++) {
 
                 // 마커를 생성하고 지도에 표시합니다
                 var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
                     marker = addMarker(placePosition, i),
                     itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다
 
                 // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
                 // LatLngBounds 객체에 좌표를 추가합니다
                 bounds.extend(placePosition);
 
                 // 마커와 검색결과 항목에 mouseover 했을때
                 // 해당 장소에 인포윈도우에 장소명을 표시합니다
                 // mouseout 했을 때는 인포윈도우를 닫습니다
                 (function (marker, title) {
                     kakao.maps.event.addListener(marker, 'mouseover', function () {
                         displayInfowindow(marker, title);
                     });
 
                     kakao.maps.event.addListener(marker, 'mouseout', function () {
                         infowindow.close();
                     });
 
                     itemEl.onmouseover = function () {
                         displayInfowindow(marker, title);
                     };
 
                     itemEl.onmouseout = function () {
                         infowindow.close();
                     };
                 })(marker, places[i].place_name);
 
                 fragment.appendChild(itemEl);
             }
 
             // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
             listEl.appendChild(fragment);
             // menuEl.scrollTop = 0;
 
             // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
             map.setBounds(bounds);
         }
         // 검색결과 항목을 Element로 반환하는 함수입니다
         function getListItem(index, places) {
             var el = document.createElement('li'),
                 itemStr = '<span class="markerbg marker_' + (index + 1) + '"></span>' +
                     '<div class="info">' +
                     '   <h5>' + places.place_name + '</h5>';
 
             if (places.road_address_name) {
                 itemStr += '    <span>' + places.road_address_name + '</span>' +
                     '   <span class="jibun gray">' + places.address_name + '</span>';
             } else {
                 itemStr += '    <span>' + places.address_name + '</span>';
             }
 
             itemStr += '  <span class="tel">' + places.phone + '</span>' +
                 '</div>';
 
             el.innerHTML = itemStr;
             el.className = 'item';
        
            //장소 추가하기위해 검색후 나온결과값 넘기는 함수 
           
            el.onclick = function () {
                places.id = generateShortUUID();
                places.category ="내장소";
                places.src="./resources/images/logo_01.png";
                places.title=places.place_name;
                places.date= new Date();
                places.className = "t-draggable";
        
                places.removeCallback = function (callbackData) {
                    scheduleInfo.placeInfo = scheduleInfo.placeInfo.filter(place => !(place.title === callbackData.title && areDatesEqual(callbackData.date, place.date)))
                    sideModalFunk(scheduleInfo);
                }
    
    
                const filterList = scheduleInfo.placeInfo.filter(place => { return place.title === places.title && areDatesEqual(places.date, place.date) }
                )
    
                if (filterList.length === 0){
                    scheduleInfo.placeInfo = [...scheduleInfo.placeInfo,
                        places
                    ]
                }
    
                sideModalFunk(scheduleInfo);

                
    
                // addMarker(mY, mX);
            };
  

             return el;
         }
         
         
         // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
         function addMarker(position, idx, title) {
             var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
                 imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
                 imgOptions = {
                     spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
                     spriteOrigin: new kakao.maps.Point(0, (idx * 46) + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
                     offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
                 },
                 markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
                 marker = new kakao.maps.Marker({
                     position: position, // 마커의 위치
                     image: markerImage
                 });
 
             marker.setMap(map); // 지도 위에 마커를 표출합니다
             markers.push(marker);  // 배열에 생성된 마커를 추가합니다
 
             return marker;
         }
 
         // 지도 위에 표시되고 있는 마커를 모두 제거합니다
         function removeMarker() {
             for (var i = 0; i < markers.length; i++) {
                 markers[i].setMap(null);
             }
             markers = [];
         }
 
         // 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
         function displayPagination(pagination) {
             var paginationEl = document.getElementById('pagination'),
                 fragment = document.createDocumentFragment(),
                 i;
 
             // 기존에 추가된 페이지번호를 삭제합니다
             while (paginationEl.hasChildNodes()) {
                 paginationEl.removeChild(paginationEl.lastChild);
             }
 
             for (i = 1; i <= pagination.last; i++) {
                 var el = document.createElement('a');
                 el.href = "#";
                 el.innerHTML = i;
 
                 if (i === pagination.current) {
                     el.className = 'on';
                 } else {
                     el.onclick = (function (i) {
                         return function () {
                             pagination.gotoPage(i);
                         }
                     })(i);
                 }
 
                 fragment.appendChild(el);
             }
             paginationEl.appendChild(fragment);
         }
 
         // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
         // 인포윈도우에 장소명을 표시합니다
         function displayInfowindow(marker, title) {
             var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';
 
             infowindow.setContent(content);
             infowindow.open(map, marker);
         }
 
         // 검색결과 목록의 자식 Element를 제거하는 함수입니다
         function removeAllChildNods(el) {
             while (el.hasChildNodes()) {
                 el.removeChild(el.lastChild);
             }
         }
    };

    function toggleTabContent() {
        tabContent1.classList.toggle('active', btnToggle.isToggle);
        tabContent2.classList.toggle('active', btnToggle2.isToggle);
    }

    sideModalFunk(scheduleInfo);
    
    setStepBtn({
        prev:{
            display: "block",
             clickEvent: function(){
              
                drawScheduleMake(scheduleInfo);
            }
        },
        next: {
            clickEvent: function () {
                if (checkPlaceAndLodgingInfo("placeInfo", scheduleInfo)) {
                    alert("여행지를 일자별로 적어도 한 곳을 선택해주세요.");
                    return;
                }
                selectLodging(scheduleInfo);
            }
        }
    })


 
}
//******************************************step2**************************************************
//****************************************side modal**********************************************
function sideModalFunk(scheduleInfo){
    const sideModal = document.getElementById("side-modal");
    $(sideModal).empty();
    
    const noScroll = document.createElement('div');
    noScroll.className = "no_scroll";
    sideModal.appendChild(noScroll); 

    const sideModalBtn = document.createElement('button');
    sideModalBtn.innerHTML = "<i class='bi bi-chevron-right'></i>";
    sideModal.appendChild(sideModalBtn);
    sideModal.isToggle = true;
    sideModalBtn.onclick = function(){
        sideModal.isToggle = !sideModal.isToggle;
        sideModal.classList.toggle("active", !sideModal.isToggle);
    }
    
    for (let i = 0; i <= dateToDay(scheduleInfo.startDate, scheduleInfo.endDate); i++) {

        // 일차 하나를 그려줄 때마다
        // 해당 일차에 맞는 명소들을 같이 보내줌
        let startDate = new Date(scheduleInfo.startDate);
        startDate = new Date(startDate.setDate(startDate.getDate() + i));
    

        const sideModalContent = sideModalContentUnit({
            day: "<span>" + (i + 1) + "<span>일차", 
            date: startDate,
        }, scheduleInfo.placeInfo.filter(s => {
            return areDatesEqual(startDate, s.date);
        }));
        noScroll.appendChild(sideModalContent);
    }


     // // 일정 드래그
     const draggables = document.querySelectorAll(".draggable");
     const containers = document.querySelectorAll(".select-wrap > ul");
     const fixDraggable = document.querySelectorAll(".select-wrap > ul > .t-draggable");

     draggables.forEach(draggable => {
        draggable.ondragstart = () => {

            draggable.classList.add("dragging");
        }
 /*맵 마커 여기다!!!!!*/
         draggable.ondragend = (ev) => {
            draggable.classList.remove("dragging");

            const info = document.querySelector(".select-dragging").info;
            info.id = generateShortUUID();
            info.removeCallback = function (callbackData) {
                console.log(callbackData)
                scheduleInfo.placeInfo = scheduleInfo.placeInfo.filter(place => !(place.title === callbackData.title && areDatesEqual(callbackData.date, place.date)))
                sideModalFunk(scheduleInfo);
            }
            

            const filterList = scheduleInfo.placeInfo.filter(place => 
                {return place.title === info.title && areDatesEqual(info.date, place.date)}
            )
            
            if (filterList.length === 0)
                scheduleInfo.placeInfo = [...scheduleInfo.placeInfo,
                    info
                ]

            sideModalFunk(scheduleInfo);   
    
            
        }
     });
 

    fixDraggable.forEach(draggable => {
        draggable.ondragstart = () => {

            draggable.classList.add("change-dragging");
 
        }
    
        draggable.ondragend = (ev) => {
            
            const info = document.querySelector(".change-dragging").info;

            const filterList = scheduleInfo.placeInfo.filter(place => 
                {return place.title === info.title && areDatesEqual(info.date, place.date)}
            )
            
            if (filterList.length === 0)
                scheduleInfo.placeInfo = scheduleInfo.placeInfo.map(p => (p.id === info.id) ? info : p);

            sideModalFunk(scheduleInfo);
            draggable.classList.remove("change-dragging");
            
        }
    });

    containers.forEach(container => {
        container.ondragover = (e) => {
            e.preventDefault();
            const afterElement = getDragAfterElement(container, e.clientX);

            if (document.querySelector(".dragging") || document.querySelector(".select-dragging")) {
                let draggable = document.querySelector(".select-dragging") || document.querySelector(".dragging").cloneNode(true);
                
                
                if (!draggable.classList.contains('select-dragging')) {
                    draggable.classList.add("select-dragging");
                    draggable.info = document.querySelector(".dragging").info;
                }
    
                draggable.info = {
                    ...draggable.info,
                    className: "t-draggable",
                    date: new Date(container.startDate)
                };


                if (afterElement === undefined) {
                    container.appendChild(draggable);
                } else {
                    container.insertBefore(draggable, afterElement);
                }
            } 

            
            if (document.querySelector(".change-dragging")) {
                let draggable2 = document.querySelector(".change-dragging");

                draggable2.info = {
                    ...draggable2.info,
                    className: "t-draggable",
                    date: new Date(container.startDate)
                };

                if (afterElement === undefined) {
                    container.appendChild(draggable2);
                } else {
                    container.insertBefore(draggable2, afterElement);
                }

            }
            
        }
    });
}
//****************************************side modal**********************************************

//******************************************step3**************************************************
function selectLodging(scheduleInfo) {

    activeButton('step3');
    document.getElementById("side-modal").style.display = "block";

    const contentZone = document.getElementById("content-zone");
    $(contentZone).empty();

    const locationTitleWrap = document.createElement("div");
    locationTitleWrap.className = "location-title-wrap";
    contentZone.appendChild(locationTitleWrap);

    const title = document.createElement("h4");
    title.innerText = "여행지";
    locationTitleWrap.appendChild(title);

    const timeZone = document.createElement("div");
    timeZone.className = "trave-date";
    timeZone.innerText = formatDate(scheduleInfo.startDate) + " - " + formatDate(scheduleInfo.endDate);
    locationTitleWrap.appendChild(timeZone);

    const locationContentWrap = document.createElement("div");
    locationContentWrap.className = "location-content-wrap";
    contentZone.appendChild(locationContentWrap);

    const btnWrap = document.createElement("div");
    btnWrap.className = "button-group";
    locationContentWrap.appendChild(btnWrap);

    const btnToggle = document.createElement('button');
    btnToggle.id = 'btnToggle';
    btnToggle.className = 'clicked';
    btnToggle.innerText = '숙소선택';
    btnWrap.appendChild(btnToggle);

    const btnToggle2 = document.createElement('button');
    btnToggle2.id = 'btnToggle2';
    btnToggle2.innerText = '신규숙소등록';
    btnWrap.appendChild(btnToggle2);

    const tabContent1 = document.createElement('div');
    tabContent1.id = 'tabContent1';
    tabContent1.className = 'tab-content2 active';
    locationContentWrap.appendChild(tabContent1);
    
    const searchInputLocation = getSearchInputUnit({
        inputPlaceholder: "장소명을 입력하세요.",
    });
    tabContent1.appendChild(searchInputLocation);

    const category = document.createElement('div');
    category.className = 'category';
    tabContent1.appendChild(category);
    const recommendedPlace = toggleButtonUnit({
        className:"toggle",
        innerText:"추천 숙소",
    });
    category.appendChild(recommendedPlace);
    
    const selectWrap = document.createElement('div');
    selectWrap.className = "save-wrap";
    tabContent1.appendChild(selectWrap);

    const selectWrapUl = document.createElement('ul');
    selectWrapUl.id = 'ulajax'
    selectWrap.appendChild(selectWrapUl);




    for(item of bsValue.motelData){
        selectWrapUl.appendChild(item);
    }

    // for (let i = 0; i < 3; i++) {
    //     const selectCard = selectWrapLiUnit({
    //         src: "./resources/images/place_sample.jpg", 
    //         title: "숙소"+i,
    //         category: "명소"+i,
    //         className: "draggable",
    //         id: generateShortUUID()            
    //     });
    //     selectWrapUl.appendChild(selectCard);
    // }

    const tabContent2 = document.createElement('div');
    tabContent2.id = 'tabContent2';
    tabContent2.className = 'tab-content2';
    locationContentWrap.appendChild(tabContent2);

    const newLocation = getSearchInputUnit({
        inputPlaceholder: "상호명 또는 주소를 입력하세요.",
    });
    tabContent2.appendChild(newLocation);

    const mapArea = document.createElement('div');
    mapArea.className = 'map-area';
    tabContent2.appendChild(mapArea);

    btnToggle.onclick = function () {
        btnToggle.isToggle = !btnToggle.isToggle;
        btnToggle2.isToggle = false;
        btnToggle.classList[btnToggle.isToggle ? 'add' : 'remove']('clicked');
        btnToggle2.classList.remove('clicked');
        toggleTabContent();
    };

    btnToggle2.onclick = function () {
        btnToggle2.isToggle = !btnToggle2.isToggle;
        btnToggle.isToggle = false;
        btnToggle2.classList[btnToggle2.isToggle ? 'add' : 'remove']('clicked');
        btnToggle.classList.remove('clicked');
        toggleTabContent();

        // 마커를 담을 배열입니다
        var markers = [];
        const ulEliment = document.createElement('ul');
        ulEliment.id = 'placesList';
        const divEliment = document.createElement('div');
        divEliment.id = 'pagination';

        var mapContainer = document.querySelector('.map-area'), // 지도를 표시할 div 
            mapOption = {
                center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
                level: 3 // 지도의 확대 레벨
            };
        tabContent2.appendChild(ulEliment);
        tabContent2.appendChild(divEliment);
        // 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
        var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

        // 지도를 생성합니다    
        var map = new kakao.maps.Map(mapContainer, mapOption);

        // 장소 검색 객체를 생성합니다
        var ps = new kakao.maps.services.Places();
        var keywordInput = document.querySelector('#tabContent2 .form-outline .form-control');
        var keyword = '';
        var searchBtnn = document.querySelector('#tabContent2 .form-outline .btn.btn-primary');
        keywordInput.addEventListener('keyup', (ev) => {
            if (ev.key === "Enter") {
                keyword = keywordInput.value;
                // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
                ps.keywordSearch(keyword, placesSearchCB);
            }

        })
        searchBtnn.addEventListener('click', (ev) => {
            //'장소명을 입력하세요 인풋창옆에 돋보기모양 클릭하는 순간 검색 ajax'
            if (ev.type === "click") {
                keyword = keywordInput.value;
                // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
                ps.keywordSearch(keyword, placesSearchCB);
            }
        })

        // 키워드 검색 완료 시 호출되는 콜백함수 입니다
        function placesSearchCB(data, status, pagination) {
            if (status === kakao.maps.services.Status.OK) {

                // 정상적으로 검색이 완료됐으면
                // 검색 목록과 마커를 표출합니다
                displayPlaces(data);
                // 페이지 번호를 표출합니다
                displayPagination(pagination);

            } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

                alert('검색 결과가 존재하지 않습니다.');
                return;

            } else if (status === kakao.maps.services.Status.ERROR) {

                alert('검색 결과 중 오류가 발생했습니다.');
                return;

            }
        }

        // 검색 결과 목록과 마커를 표출하는 함수입니다
        function displayPlaces(places) {

            var listEl = document.getElementById('placesList'),
                menuEl = document.getElementById('menu_wrap'),
                fragment = document.createDocumentFragment(),
                bounds = new kakao.maps.LatLngBounds(),
                listStr = '';

            // 검색 결과 목록에 추가된 항목들을 제거합니다
            removeAllChildNods(listEl);

            // 지도에 표시되고 있는 마커를 제거합니다
            removeMarker();

            for (var i = 0; i < places.length; i++) {

                // 마커를 생성하고 지도에 표시합니다
                var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
                    marker = addMarker(placePosition, i),
                    itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

                // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
                // LatLngBounds 객체에 좌표를 추가합니다
                bounds.extend(placePosition);

                // 마커와 검색결과 항목에 mouseover 했을때
                // 해당 장소에 인포윈도우에 장소명을 표시합니다
                // mouseout 했을 때는 인포윈도우를 닫습니다
                (function (marker, title) {
                    kakao.maps.event.addListener(marker, 'mouseover', function () {
                        displayInfowindow(marker, title);
                    });

                    kakao.maps.event.addListener(marker, 'mouseout', function () {
                        infowindow.close();
                    });

                    itemEl.onmouseover = function () {
                        displayInfowindow(marker, title);
                    };

                    itemEl.onmouseout = function () {
                        infowindow.close();
                    };
                })(marker, places[i].place_name);

                fragment.appendChild(itemEl);
            }

            // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
            listEl.appendChild(fragment);
            // menuEl.scrollTop = 0;

            // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
            map.setBounds(bounds);
        }
        // 검색결과 항목을 Element로 반환하는 함수입니다
        function getListItem(index, places) {
            console.log(places)
            var el = document.createElement('li'),
                itemStr = '<span class="markerbg marker_' + (index + 1) + '"></span>' +
                    '<div class="info">' +
                    '   <h5>' + places.place_name + '</h5>';

            if (places.road_address_name) {
                itemStr += '    <span>' + places.road_address_name + '</span>' +
                    '   <span class="jibun gray">' + places.address_name + '</span>';
            } else {
                itemStr += '    <span>' + places.address_name + '</span>';
            }

            itemStr += '  <span class="tel">' + places.phone + '</span>' +
                '</div>';

            el.innerHTML = itemStr;
            el.className = 'item';
       
           //장소 추가하기위해 검색후 나온결과값 넘기는 함수 
          
           el.onclick = function () {
       
               places.id = generateShortUUID();
               places.category ="내숙소";
               places.src="./resources/images/logo_01.png";
               places.title=places.place_name;
               places.date= new Date();
               places.className = "t-draggable";
       
               places.removeCallback = function(callbackData){
                    scheduleInfo.lodgingInfo = scheduleInfo.lodgingInfo.filter(place => !(place.title === callbackData.title && areDatesEqual(callbackData.date, place.date)))
                    sideModalLodging(scheduleInfo);
                }

                const filterList = scheduleInfo.lodgingInfo.filter(place => 
                    {return place.title === places.title && areDatesEqual(places.date, place.date)}
                )
                
                if (filterList.length === 0)
                    scheduleInfo.lodgingInfo = [...scheduleInfo.lodgingInfo,
                        places
                    ]
    
        
                sideModalLodging(scheduleInfo);

               
   
               // addMarker(mY, mX);
           };
 

            return el;
        }
        
        
        // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
        function addMarker(position, idx, title) {
            var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
                imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
                imgOptions = {
                    spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
                    spriteOrigin: new kakao.maps.Point(0, (idx * 46) + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
                    offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
                },
                markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
                marker = new kakao.maps.Marker({
                    position: position, // 마커의 위치
                    image: markerImage
                });

            marker.setMap(map); // 지도 위에 마커를 표출합니다
            markers.push(marker);  // 배열에 생성된 마커를 추가합니다

            return marker;
        }

        // 지도 위에 표시되고 있는 마커를 모두 제거합니다
        function removeMarker() {
            for (var i = 0; i < markers.length; i++) {
                markers[i].setMap(null);
            }
            markers = [];
        }

        // 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
        function displayPagination(pagination) {
            var paginationEl = document.getElementById('pagination'),
                fragment = document.createDocumentFragment(),
                i;

            // 기존에 추가된 페이지번호를 삭제합니다
            while (paginationEl.hasChildNodes()) {
                paginationEl.removeChild(paginationEl.lastChild);
            }

            for (i = 1; i <= pagination.last; i++) {
                var el = document.createElement('a');
                el.href = "#";
                el.innerHTML = i;

                if (i === pagination.current) {
                    el.className = 'on';
                } else {
                    el.onclick = (function (i) {
                        return function () {
                            pagination.gotoPage(i);
                        }
                    })(i);
                }

                fragment.appendChild(el);
            }
            paginationEl.appendChild(fragment);
        }

        // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
        // 인포윈도우에 장소명을 표시합니다
        function displayInfowindow(marker, title) {
            var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

            infowindow.setContent(content);
            infowindow.open(map, marker);
        }

        // 검색결과 목록의 자식 Element를 제거하는 함수입니다
        function removeAllChildNods(el) {
            while (el.hasChildNodes()) {
                el.removeChild(el.lastChild);
            }
        }
    };

    function toggleTabContent() {
        tabContent1.classList.toggle('active', btnToggle.isToggle);
        tabContent2.classList.toggle('active', btnToggle2.isToggle);
    }
    sideModalLodging(scheduleInfo);
    setStepBtn({
        prev:{
            display: "block",
             clickEvent: function(){
              
                selectLocation(scheduleInfo);
            }
        },
        next: {
            display: "block",
            clickEvent: function () {
                if (checkPlaceAndLodgingInfo("lodgingInfo", scheduleInfo)) {
                    alert("숙소를 일자별로 적어도 한 곳을 선택해주세요.");
                    return;
                }
                chooseTransportation(scheduleInfo);
            }
        }
    })
}
//******************************************step3**************************************************
//****************************************side modal**********************************************
function sideModalLodging(scheduleInfo){
    const sideModal = document.getElementById("side-modal");
    $(sideModal).empty();
    
    const noScroll = document.createElement('div');
    noScroll.className = "no_scroll";
    sideModal.appendChild(noScroll); 

    const sideModalBtn = document.createElement('button');
    sideModalBtn.innerHTML = "<i class='bi bi-chevron-right'></i>";
    sideModal.appendChild(sideModalBtn);
    sideModal.isToggle = true;
    sideModalBtn.onclick = function(){
        sideModal.isToggle = !sideModal.isToggle;
        sideModal.classList.toggle("active", !sideModal.isToggle);
    }
    
    for (let i = 0; i <= dateToDay(scheduleInfo.startDate, scheduleInfo.endDate); i++) {
        // 일차 하나를 그려줄 때마다
        // 해당 일차에 맞는 명소들을 같이 보내줌
        let startDate = new Date(scheduleInfo.startDate);
        startDate = new Date(startDate.setDate(startDate.getDate() + i));
    

        const sideModalContent = sideModalContentUnit({
            day: "<span>" + (i + 1) + "<span>일차", 
            date: startDate,
        }, scheduleInfo.lodgingInfo.filter(s => {
            return areDatesEqual(startDate, s.date);
        }));
        noScroll.appendChild(sideModalContent);
    }


     // // 일정 드래그
     const draggables = document.querySelectorAll(".draggable");
     const containers = document.querySelectorAll(".select-wrap > ul");
     const fixDraggable = document.querySelectorAll(".select-wrap > ul > .t-draggable");
   
     // const containers = document.querySelectorAll(".container-map");

 
     draggables.forEach(draggable => {
        draggable.ondragstart = () => {

            draggable.classList.add("dragging");
        }
 
         draggable.ondragend = (ev) => {
            draggable.classList.remove("dragging");

            const info = document.querySelector(".select-dragging").info;
            info.id = generateShortUUID();

            info.removeCallback = function(callbackData){
                scheduleInfo.lodgingInfo = scheduleInfo.lodgingInfo.filter(place => !(place.title === callbackData.title && areDatesEqual(callbackData.date, place.date)))
                sideModalLodging(scheduleInfo);
            }

            const filterList = scheduleInfo.lodgingInfo.filter(place => 
                {return place.title === info.title && areDatesEqual(info.date, place.date)}
            )
            
            if (filterList.length === 0)
                scheduleInfo.lodgingInfo = [...scheduleInfo.lodgingInfo,
                    info
                ]
  
    
            sideModalLodging(scheduleInfo);
        }
     });
 


    fixDraggable.forEach(draggable => {
        draggable.ondragstart = () => {

            draggable.classList.add("change-dragging");
 
        }
    
        draggable.ondragend = (ev) => {
            
            const info = document.querySelector(".change-dragging").info;
            

            const filterList = scheduleInfo.lodgingInfo.filter(place => 
                {return place.title === info.title && areDatesEqual(info.date, place.date)}
            )

            
            if (filterList.length === 0)
                scheduleInfo.lodgingInfo = scheduleInfo.lodgingInfo.map(p => (p.id === info.id) ? info : p);
            
            
                sideModalLodging(scheduleInfo);
            draggable.classList.remove("change-dragging");
        }
    });

    containers.forEach(container => {
        container.ondragover = (e) => {
            e.preventDefault();
            const afterElement = getDragAfterElement(container, e.clientX);

            if (document.querySelector(".dragging") || document.querySelector(".select-dragging")) {
                let draggable = document.querySelector(".select-dragging") || document.querySelector(".dragging").cloneNode(true);
                
                
                if (!draggable.classList.contains('select-dragging')) {
                    draggable.classList.add("select-dragging");
                    draggable.info = document.querySelector(".dragging").info;
                }
    
                draggable.info = {
                    ...draggable.info,
                    className: "t-draggable",
                    date: new Date(container.startDate)
                };

                if (afterElement === undefined) {
                    container.appendChild(draggable);
                } else {
                    container.insertBefore(draggable, afterElement);
                }
            } 

            
            if (document.querySelector(".change-dragging")) {
                let draggable2 = document.querySelector(".change-dragging");

                draggable2.info = {
                    ...draggable2.info,
                    className: "t-draggable",
                    date: new Date(container.startDate)
                };

                if (afterElement === undefined) {
                    container.appendChild(draggable2);
                } else {
                    container.insertBefore(draggable2, afterElement);
                }

            }
        }
    });
}
//****************************************side modal**********************************************

//******************************************step4**************************************************
function chooseTransportation(scheduleInfo) {
    activeButton('step4');
    document.getElementById("side-modal").style.display = "none";
    const contentZone = document.getElementById("content-zone");
    $(contentZone).empty();

    const transportationWrap = document.createElement("div");
    transportationWrap.className = "transportation-wrap";
    transportationWrap.innerHTML = `
                                    <h4>이동수단 선택</h4>
                                    <p>여행 시 이용하실 이동수단을 선택해주세요.</p>
                                `;
    contentZone.appendChild(transportationWrap);

    const transportationBtnWrap = document.createElement("div");
    transportationBtnWrap.className = "select";
    transportationWrap.appendChild(transportationBtnWrap);

    const btnToggle = document.createElement('button');
    btnToggle.isToggle = true;
    btnToggle.className = "clicked";
    btnToggle.innerHTML = " <i class='bi bi-bus-front-fill'></i><p>대중교통</p>";
    transportationBtnWrap.appendChild(btnToggle);
    btnToggle.onclick = function () {
        if (!btnToggle.classList.contains('clicked')) {
            btnToggle.isToggle = !btnToggle.isToggle;
            btnToggle2.isToggle = false;
            btnToggle.classList[btnToggle.isToggle ? 'add' : 'remove']('clicked');
            btnToggle2.classList.remove("clicked");


            TransportationInfo(btnToggle);
            
        }
    };

    const btnToggle2 = document.createElement('button');
    btnToggle2.isToggle = false;
    btnToggle2.innerHTML = " <i class='bi bi-car-front-fill'></i><p>승용차</p>";
    transportationBtnWrap.appendChild(btnToggle2);
    btnToggle2.onclick = function () {
        if (!btnToggle2.classList.contains('clicked')) {
            btnToggle2.isToggle = !btnToggle2.isToggle;
            btnToggle.isToggle = false;
            btnToggle2.classList[btnToggle2.isToggle ? 'add' : 'remove']('clicked');
            btnToggle.classList.remove("clicked");

            TransportationInfo(btnToggle2);
        }
    };

   
    function TransportationInfo() {
        if (btnToggle.isToggle) {
            scheduleInfo.transportation = "대중교통";
        } else if (btnToggle2.isToggle) {
            scheduleInfo.transportation = "승용차";
            console.log(scheduleInfo)
        }
    }
    

    const ScheduleCreationBtn  = document.createElement('button');
    ScheduleCreationBtn.className = "time-set-btn";
    ScheduleCreationBtn.innerText = "일정생성";
    transportationWrap.appendChild(ScheduleCreationBtn);  


    ScheduleCreationBtn.onclick = function () {
        // html2canvas(document.getElementById("map"), {useCORS: true}).then(function(canvas) {
       
        //     const myImg = canvas.toDataURL("image/jpeg");
        //     const src = myImg.replace("image/jpeg", "image/octet-stream");
			  
        //     document.body.innerHTML = "<img src = '"+src+"'/>";

                 //     var formData = new FormData();

            //      formData.append("imgSrc", myImg);

                // $.ajax({
                //     type : "POST",
                //     data : formData,
                //     async:false,
                //     processData: false,
                //     contentType: false,
                //     dataType : "text",
                //     url : "captureImgSave.bo",
                //     success : function(data) {
                //         console.log(data);
                //     },
                //     error : function() {
                //         alert("error");
                //     }
                // });

            // })

        scheduleMakeAjax(scheduleInfo);
        
        
    };

    setStepBtn({
        prev:{
            display: "block",
             clickEvent: function(){
                selectLodging(scheduleInfo);
             
            }
        },
        next: {
            display: "none"
        }
    })
}
//******************************************step4**************************************************
