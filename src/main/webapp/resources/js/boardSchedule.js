function init(){

    drawScheduleMake();
    // selectLocation({
    //     startDate: new Date(),
    //     endDate: new Date(new Date().setDate(new Date().getDate() + 5)),
    //     placeInfo : []
    // });
    // selectLodging(({
    //     startDate: new Date(),
    //     endDate: new Date(new Date().setDate(new Date().getDate() + 5)),
    //     placeInfo : []
    // }));
    // chooseTransportation();






    // //step 버튼 tab menu
    // let $tabBtn = $('.tab-menu button[role="tab"]');
    // let $tabContents = $('.schedule .tab-contents > div');

    // $tabBtn.on('click', function (e) {
    //     e.preventDefault();

    //     let indexNum = $tabBtn.index(this);
    //     let $tabContent = $tabContents.eq(indexNum);

    //     $tabBtn.removeClass('active').attr('aria-selected', false);
    //     $(this).addClass('active').attr('aria-selected', true);
    //     $tabContents.attr('hidden', true);
    //     $tabContent.removeAttr('hidden');
    // });

    // //step2, step3 안에 contents tab
    // let $tab2 = $('.ui-tab');

    // $tab2.each(function () {
    //     if ($(this).data('init')) return;
    //     $(this).data('init', true);

    //     let $this2 = $(this);
    //     let $tabMenu2 = $this2.find('.tab-menu2');
    //     let $tabBtn2 = $('[role="tab2"]', $tabMenu2);
    //     let $tabContents2 = $this2.find('.tab-contents2>div');

    //     $tabBtn2.on('click.default', function (e) {
    //         let _this2 = $(this);
    //         let indexNum2 = _this2.index();
    //         let _tabContent2 = $tabContents2.eq(indexNum2);

    //         $tabBtn2.not(_this2).removeClass('active');
    //         $tabBtn2.not(_this2).attr('aria-selected', false);
    //         _this2.addClass('active');
    //         _this2.attr('aria-selected', true);
    //         $tabContents2.not(_tabContent2).attr('hidden', true);
    //         _tabContent2.removeAttr('hidden');

    //         if ($(this).is('a')) {
    //             e.preventDefault();
    //         }
    //     });
    // });

    // //step1 dropdown
    // let $dropdown = $('.ui-dropdown');

    // $dropdown.each(function () {
    //     initializeDropdown($(this));
    // });

    //시작시간을 오늘보다 이후로만 가능하게 설정
    document.getElementById('startDate').min = new Date().toISOString().substring(0, 10);
    //끝나는 시간을 오늘보다 이후로만 가능하게 설정
    document.getElementById('endDate').min = new Date().toISOString().substring(0, 10);

    // 기본시작시간을 오늘로 설정
    document.getElementById('startDate').value = new Date().toISOString().substring(0, 10);


};


function initializeDropdown($dropdown) {
    if ($dropdown.data('init')) return;
    $dropdown.data('init', false);

    let $trigger = $dropdown.find('.dropdown-trigger');
    let $target = $dropdown.find('.dropdown-layer');

    $trigger.on('click', function () {
        let $wrap = $(this).parent();
        let isExpanded = $(this).attr('aria-expanded') === 'false';

        if (!isExpanded) {
            // open
            $(this).attr('aria-expanded', false);
            $wrap.css('z-index', 1);
            $target.slideDown(300);
        } else {
            // close
            $(this).attr('aria-expanded', true);
            $target.slideUp(300);
            UI._afterRun(function () {
                $wrap.removeAttr('style');
            }, 300);
        }
    });
}

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

//일정 시작시간과 종료시간이 변했을 때 실행하는 함수
function updateEndDate(){
    const startInput = document.getElementById('startDate');
    const endInput = document.getElementById('endDate');

    const startDate = startInput.value ? new Date(startInput.value) : null;
    const endDate = endInput.value ? new Date(endInput.value) : null;

    if (startDate) {
        endInput.min = toStringDate(startDate);
    }

    if (endDate) {
        startInput.max = toStringDate(endDate);       
    }
    
    if(startDate && endDate) {
        drawFixSchedule(startDate, endDate);
    }
}

//시작시간과 끝시간을 넘겨받아
//일정확정날짜를 그려주는 함수
function drawFixSchedule(start, end){
    const fixScheduleTB = document.querySelector("#schedule-view-table > tbody");
    $(fixScheduleTB).empty();

    for(let i = 0; i <= dateToDay(start, end); i++) {
        const tmpDate = new Date(start);
        const nowDate = new Date(tmpDate.setDate(tmpDate.getDate() + i));
        
        let str = `<tr>
                    <th>${dateToMMDD(nowDate)}</th>
                    <td>${getKoreanDayOfWeek(nowDate)}</td>`;

        if (i === 0 || i === dateToDay(start, end)) {
            str += `<td><input type="time" value=""></td>
            <td><input type="time" value=""></td>`
        }

        str += `</tr>`;
        fixScheduleTB.innerHTML += str;
    }
}


//******************************************step1**************************************************
function drawScheduleMake() {
    const contentZone = document.getElementById("content-zone");
    $(contentZone).empty();

    const title = document.createElement("h3");
    title.innerText = "여행지";
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

    const scheduleInfo = document.createElement("p");
    scheduleInfo.className = "explanation";
    scheduleInfo.innerHTML = `입력하신 여행 기간이 시차를 고려한 <b>현지 여행 기간(여행지 도착 날짜, 여행지 출발 날짜)</b>이 맞는지 확인해 주시고 각 날짜의 일정 <b>시작시간</b>과 <b>종료시간</b>을 현지 시간 기준으로 설정해 주세요. 기본 설정 시간은 <b>오전 10시~오후 10시 총 12시간</b>입니다.`
    scheduleZone.appendChild(scheduleInfo);

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
    
    const TimeSettingBtn  = document.createElement('button');
    TimeSettingBtn.className = "time-set-btn";
    TimeSettingBtn.innerText = "시간 설정 완료";
    scheduleZone.appendChild(TimeSettingBtn);


    TimeSettingBtn.addEventListener("click", function() {
        const startDate = document.getElementById("startDate").value;
        const endDate = document.getElementById("endDate").value;
    
        selectLocation({
            startDate: new Date(startDate),
            endDate: new Date(endDate),
            placeInfo : [],
            lodgingInfo: [],
        });
        // selectLocation({
        //     startDate: startDate,
        //     endDate: endDate,
        // });
        

        // sideModalFunk();
    });

    document.getElementById("next-btn").onclick = function(){
        const startDate = document.getElementById("startDate").value;
        const endDate = document.getElementById("endDate").value;
    
        selectLocation({
            startDate: new Date(startDate),
            endDate: new Date(endDate),
            placeInfo : [],

        });
    }
    

}
//******************************************step1**************************************************

//******************************************step2**************************************************
function selectLocation(scheduleInfo) {
    console.log(scheduleInfo);
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
    category.id = 'categoryRec';
    tabContent1.appendChild(category);
    const recommendedPlace = toggleButtonUnit({
        className:"toggle",
        innerText:"추천 장소",
    });
    category.appendChild(recommendedPlace);
    const sights = toggleButtonUnit({
        innerText:"명소",
    });
    category.appendChild(sights);
    const restaurant = toggleButtonUnit({
        innerText:"식당",
    });
    category.appendChild(restaurant);
    const cafe = toggleButtonUnit({
        innerText:"카페",
    });
    category.appendChild(cafe);
    
    const selectWrap = document.createElement('div');
    selectWrap.className = "save-wrap";
    tabContent1.appendChild(selectWrap);

    const selectWrapUl = document.createElement('ul');
    selectWrap.appendChild(selectWrapUl);

    
    for (let i = 0; i < 3; i++) {
        const selectCard = selectWrapLiUnit({
            src: "./resources/images/place_sample.jpg", 
            title: "여행지"+i,
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


    document.getElementById("next-btn").onclick = function(){
    
        selectLodging(scheduleInfo);
    }










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
    
    for (let i = 0; i < dateToDay(scheduleInfo.startDate, scheduleInfo.endDate); i++) {
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
   
     // const containers = document.querySelectorAll(".container-map");

 
     draggables.forEach(draggable => {
        draggable.ondragstart = () => {

            draggable.classList.add("dragging");
        }
 
         draggable.ondragend = (ev) => {
            draggable.classList.remove("dragging");

            const info = document.querySelector(".select-dragging").info;
            info.id = generateShortUUID();

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
 
    /*현수*/

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
    
    const selectLocation = getSearchInputUnit({
        inputPlaceholder: "장소명을 입력하세요.",
    });
    tabContent1.appendChild(selectLocation);

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
    selectWrap.appendChild(selectWrapUl);

    for (let i = 0; i < 3; i++) {
        const selectCard = selectWrapLiUnit({
            src: "./resources/images/place_sample.jpg", 
            title: "여행지"+i,
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
    
    for (let i = 0; i < dateToDay(scheduleInfo.startDate, scheduleInfo.endDate); i++) {
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
   
     // const containers = document.querySelectorAll(".container-map");

 
     draggables.forEach(draggable => {
        draggable.ondragstart = () => {

            draggable.classList.add("dragging");
        }
 
         draggable.ondragend = (ev) => {
            draggable.classList.remove("dragging");

            const info = document.querySelector(".select-dragging").info;
            info.id = generateShortUUID();

            const filterList = scheduleInfo.placeInfo.filter(place => 
                {return place.title === info.title && areDatesEqual(info.date, place.date)}
            )
            
            if (filterList.length === 0)
                scheduleInfo.placeInfo = [...scheduleInfo.placeInfo,
                    info
                ]
  
    
            sideModalLodging(scheduleInfo);
        }
     });
 
    /*현수*/

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
function chooseTransportation() {
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
        btnToggle.isToggle = !btnToggle.isToggle;
        btnToggle2.isToggle = false;
        btnToggle.classList[btnToggle.isToggle ? 'add' : 'remove']('clicked');
        btnToggle2.classList.remove("clicked");
    };

    const btnToggle2 = document.createElement('button');
    btnToggle2.isToggle = false;
    btnToggle2.innerHTML = " <i class='bi bi-car-front-fill'></i><p>승용차</p>";
    transportationBtnWrap.appendChild(btnToggle2);
    btnToggle2.onclick = function () {
        btnToggle2.isToggle = !btnToggle2.isToggle;
        btnToggle.isToggle = false;
        btnToggle2.classList[btnToggle2.isToggle ? 'add' : 'remove']('clicked');
        btnToggle.classList.remove("clicked");
    };

    const ScheduleCreationBtn  = document.createElement('button');
    ScheduleCreationBtn.className = "time-set-btn";
    ScheduleCreationBtn.innerText = "일정생성";
    transportationWrap.appendChild(ScheduleCreationBtn);    
}
//******************************************step4**************************************************














