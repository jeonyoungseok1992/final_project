const bsValue = {
    regionData: [], /* 페이지 로드시 지역정보 가져온거 담겨있는곳*/
}

function init(regionList){
    bsValue.regionData = JSON.parse(regionList);
    drawScheduleMake({
        startDate: new Date(),
        endDate: null,
        placeInfo : [],
        lodgingInfo : [],
        transportation: "대중교통",
        regionName: bsValue.regionData.regionName,
        regionX: bsValue.regionData.regionX,
        regionY: bsValue.regionData.regionY
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
    console.log(scheduleInfo)
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
    title.innerText = scheduleInfo.regionName;
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
        console.log(scheduleInfo.startDate)

        //endTime
        let endData = new Date(scheduleInfo.endDate);
        endData.setHours(document.querySelector("#endTime").value.split(":")[0]);
        endData.setMinutes(document.querySelector("#endTime").value.split(":")[1]);
        scheduleInfo.endDate = new Date(endData);
        console.log(scheduleInfo.endDate)
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

}

//******************************************step1**************************************************

//******************************************step2**************************************************
function selectLocation(scheduleInfo) {
    console.log(scheduleInfo)
    activeButton('step2');
    document.getElementById("side-modal").style.display = "block";
    

    const contentZone = document.getElementById("content-zone");
    $(contentZone).empty();

    const locationTitleWrap = document.createElement("div");
    locationTitleWrap.className = "location-title-wrap";
    contentZone.appendChild(locationTitleWrap);

    const title = document.createElement("h4");
    title.innerText = scheduleInfo.regionName;
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

    
    $.ajax({
        url: "attractionList.api",
        async:false,
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
            console.log("recommendTrip.bo ajax 실패");
        }
    })



    $.ajax({
        url: "attFoodList.api",
        async:false,
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
            console.log("recommendTrip.bo ajax 실패");
        }
    })

    $.ajax({
        url: "attEventList.api",
        async:false,
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
            console.log("recommendTrip.bo ajax 실패");
        }
    })











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

            info.removeCallback = function(callbackData){
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
    console.log(scheduleInfo)
    activeButton('step3');
    document.getElementById("side-modal").style.display = "block";

    const contentZone = document.getElementById("content-zone");
    $(contentZone).empty();

    const locationTitleWrap = document.createElement("div");
    locationTitleWrap.className = "location-title-wrap";
    contentZone.appendChild(locationTitleWrap);

    const title = document.createElement("h4");
    title.innerText = scheduleInfo.regionName;
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

    for (let i = 0; i < 3; i++) {
        const selectCard = selectWrapLiUnit({
            src: "./resources/images/place_sample.jpg", 
            title: "숙소"+i,
            category: "명소"+i,
            className: "draggable",
            id: generateShortUUID()            
        });
        selectWrapUl.appendChild(selectCard);
    }

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
        }
    }
    

    const ScheduleCreationBtn  = document.createElement('button');
    ScheduleCreationBtn.className = "time-set-btn";
    ScheduleCreationBtn.innerText = "일정생성";
    transportationWrap.appendChild(ScheduleCreationBtn);  


    ScheduleCreationBtn.onclick = function () {
        console.log(scheduleInfo);
    };

    setStepBtn({
        prev:{
            display: "block",
             clickEvent: function(){
              
                console.log(scheduleInfo);
            }
        },
        next: {
            display: "none"
        }
    })
}
//******************************************step4**************************************************
