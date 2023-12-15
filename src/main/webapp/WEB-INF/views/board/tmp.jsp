<div class="tab-contents">
    <div tabindex="0" role="tabpanel" id="tabPanel1" aria-labelledby="tab1">
        <div class="step1">
            <h3>여행지</h3>
            <div class="date-time">
                <!-- <button>
                    <input type="date" id="startDate" min="" onclick="updateEndDateMin()" value=""/>
                    - 
                    <input type="date" id="endDate" min="" value=""/>
                </button> -->
                <button>
                    <input type="date" id="startDate" min="" max="" onchange="updateEndDate()" value=""/>
                    - 
                    <input type="date" id="endDate" min="" value="" onchange="updateEndDate()"/>
                </button>
            </div>
        </div>
        <div class="time-details-wrap">
            <div class="ui-dropdown">
                <button type="button" aria-expanded="false" class="dropdown-trigger" aria-controls="dropdownLayer1" id="dropDownBtn1">
                    <div class="time-details">
                        <span>여행시간 상세설정</span><span class="color">총 24시간 00분</span>
                        <span class="arrow">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-up" viewBox="0 0 16 16">
                             <path fill-rule="evenodd" d="M7.646 4.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1-.708.708L8 5.707l-5.646 5.647a.5.5 0 0 1-.708-.708l6-6z"/>
                          </svg>
                        </span>
                    </div>
                </button>
                <div class="dropdown-layer" aria-labelledby="dropDownBtn1" id="dropdownLayer1" style="display: block;">
                    <div>
                        <div class="explanation">
                            입력하신 여행 기간이 시차를 고려한 <b>현지 여행 기간(여행지 도착 날짜, 여행지 출발 날짜)</b>이 맞는지 확인해 주시고 각 날짜의 일정 <b>시작시간</b>과 <b>종료시간</b>을 현지 시간 기준으로 설정해 주세요. 기본 설정 시간은 <b>오전 10시~오후 10시 총 12시간</b>입니다.
                        </div>
                        <div class="select-date">
                            <table id="schedule-view-table">
                                <thead>
                                    <tr>
                                        <th>일자</th>
                                        <th>요일</th>
                                        <th>시작시간</th>
                                        <th>종료시간</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr class="startdate">
                                        <th>11/25</th>
                                        <td>토</td>
                                        <td><input type="time" value="10:00"></td>
                                        <td><input type="time" value="22:00"></td>
                                    </tr>
                                    <tr class="enddate">
                                        <th>11/26</th>
                                        <td>일</td>
                                        <td><input type="time" value="10:00"></td>
                                        <td><input type="time" value="22:00"></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <button>시간 설정 완료</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div tabindex="0" role="tabpane2" id="tabPanel2" aria-labelledby="tab2" hidden="true">
        <div class="step2">
            <h4>여행지</h4>
            <div class="date-time">2023.11.25(토) - 2023.11.26(일)</div>
        </div>
        <div class="ui-tab">
            <div class="tab-menu2 button-group line-type" role="tablist" aria-label="탭 이름">
              <button role="tab2" aria-selected="true" aria-controls="tabPanel5" id="tab5">장소선택</button>
              <button role="tab2" aria-selected="false" aria-controls="tabPanel6" id="tab6">신규장소등록</button>
            </div>
            <div class="tab-contents2">
                <div tabindex="0" role="tabpanel" id="tabPanel5" aria-labelledby="tab5">
                    <div class="input-group">
                        <div class="form-outline">
                          <input type="search" id="form1" class="form-control" placeholder="장소명을 입력하세요."/>
                          <button type="button" class="btn btn-primary">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                                </svg>
                            </button>
                        </div>
                    </div>
                   <div class="category">
                        <button class="active">추천 장소</button>
                        <button>명소</button>
                        <button>식당</button>
                        <button>카페</button>
                   </div>
                   <div class="select-wrap">
                        <ul class="container-map">
                            <li class="draggable" draggable="true"><div><img src="./resources/images/place_sample.jpg" alt=""><div><h5>여행지</h5><span>명소</span></div></div><button>+</button></li>
                            <li class="draggable" draggable="true"><div><img src="./resources/images/place_sample.jpg" alt=""><div><h5>여행지</h5><span>명소</span></div></div><button>+</button></li>
                            <li class="draggable" draggable="true"><div><img src="./resources/images/place_sample.jpg" alt=""><div><h5>여행지</h5><span>명소</span></div></div><button>+</button></li>
                            <li class="draggable" draggable="true"><div><img src="./resources/images/place_sample.jpg" alt=""><div><h5>여행지</h5><span>명소</span></div></div><button>+</button></li>
                            <li class="draggable" draggable="true"><div><img src="./resources/images/place_sample.jpg" alt=""><div><h5>여행지</h5><span>명소</span></div></div><button>+</button></li>
                            <li class="draggable" draggable="true"><div><img src="./resources/images/place_sample.jpg" alt=""><div><h5>여행지</h5><span>명소</span></div></div><button>+</button></li>
                            <li class="draggable" draggable="true"><div><img src="./resources/images/place_sample.jpg" alt=""><div><h5>여행지</h5><span>명소</span></div></div><button>+</button></li>
                            <li class="draggable" draggable="true"><div><img src="./resources/images/place_sample.jpg" alt=""><div><h5>여행지</h5><span>명소</span></div></div><button>+</button></li>
                            <li class="draggable" draggable="true"><div><img src="./resources/images/place_sample.jpg" alt=""><div><h5>여행지</h5><span>명소</span></div></div><button>+</button></li>
                            <li class="draggable" draggable="true"><div><img src="./resources/images/place_sample.jpg" alt=""><div><h5>여행지</h5><span>명소</span></div></div><button>+</button></li>
                            <li class="draggable" draggable="true"><div><img src="./resources/images/place_sample.jpg" alt=""><div><h5>여행지</h5><span>명소</span></div></div><button>+</button></li>
                        </ul>
                   </div>
                </div>
                <div tabindex="0" role="tabpanel" id="tabPanel6" aria-labelledby="tab6" hidden="true">
                    <div class="input-group">
                        <div class="form-outline">
                            <input type="search" id="form1" class="form-control" placeholder="상호명 또는 주소를 입력하세요."/>
                            <button type="button" class="btn btn-primary">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                                </svg>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div tabindex="0" role="tabpane3" id="tabPanel3" aria-labelledby="tab3" hidden="true">
        <div class="step3 step2">
            <h4>여행지</h4>
            <div class="date-time">2023.11.25(토) - 2023.11.26(일)</div>
        </div>
        <div class="ui-tab">
            <div class="tab-menu2 button-group line-type" role="tablist" aria-label="탭 이름">
                <button role="tab2" aria-selected="true" aria-controls="tabPanel5" id="tab5">숙소선택</button>
                <button role="tab2" aria-selected="false" aria-controls="tabPanel6" id="tab6">신규숙소등록</button>
            </div>
            <div class="tab-contents2">
                <div tabindex="0" role="tabpanel" id="tabPanel7" aria-labelledby="tab7">
                    <div class="input-group">
                        <div class="form-outline">
                            <input type="search" id="form1" class="form-control" placeholder="숙소명을 입력하세요."/>
                            <button type="button" class="btn btn-primary">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                                </svg>
                            </button>
                        </div>
                    </div>
                    <div class="category">
                        <button class="active">추천 숙소</button>
                    </div>
                    <div class="select-wrap">
                        <ul>
                            <li><div><img src="./resources/images/place_sample.jpg" alt=""><div><h5>여행지</h5><span>명소</span></div></div><button>+</button></li>
                            <li><div><img src="./resources/images/place_sample.jpg" alt=""><div><h5>여행지</h5><span>명소</span></div></div><button>+</button></li>
                            <li><div><img src="./resources/images/place_sample.jpg" alt=""><div><h5>여행지</h5><span>명소</span></div></div><button>+</button></li>
                            <li><div><img src="./resources/images/place_sample.jpg" alt=""><div><h5>여행지</h5><span>명소</span></div></div><button>+</button></li>
                            <li><div><img src="./resources/images/place_sample.jpg" alt=""><div><h5>여행지</h5><span>명소</span></div></div><button>+</button></li>
                            <li><div><img src="./resources/images/place_sample.jpg" alt=""><div><h5>여행지</h5><span>명소</span></div></div><button>+</button></li>
                            <li><div><img src="./resources/images/place_sample.jpg" alt=""><div><h5>여행지</h5><span>명소</span></div></div><button>+</button></li>
                            <li><div><img src="./resources/images/place_sample.jpg" alt=""><div><h5>여행지</h5><span>명소</span></div></div><button>+</button></li>
                            <li><div><img src="./resources/images/place_sample.jpg" alt=""><div><h5>여행지</h5><span>명소</span></div></div><button>+</button></li>
                            <li><div><img src="./resources/images/place_sample.jpg" alt=""><div><h5>여행지</h5><span>명소</span></div></div><button>+</button></li>
                            <li><div><img src="./resources/images/place_sample.jpg" alt=""><div><h5>여행지</h5><span>명소</span></div></div><button>+</button></li>
                        </ul>
                    </div>
                </div>
                <div tabindex="0" role="tabpanel" id="tabPanel8" aria-labelledby="tab8" hidden="true">
                    <div class="input-group">
                        <div class="form-outline">
                            <input type="search" id="form1" class="form-control" placeholder="상호명 또는 주소를 입력하세요."/>
                            <button type="button" class="btn btn-primary">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                                </svg>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div tabindex="0" role="tabpane4" id="tabPanel4" aria-labelledby="tab4" hidden="true">
        <div class="transportation-wrap">
            <h4>이동수단 선택</h4>
            <p>여행 시 이용하실 이동수단을 선택해주세요.</p>
            <div class="transportation">
                <div class="select">
                    <button id="publicTransportButton" class="clicked">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bus-front" viewBox="0 0 16 16">
                            <path d="M5 11a1 1 0 1 1-2 0 1 1 0 0 1 2 0Zm8 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0Zm-6-1a1 1 0 1 0 0 2h2a1 1 0 1 0 0-2H7Zm1-6c-1.876 0-3.426.109-4.552.226A.5.5 0 0 0 3 4.723v3.554a.5.5 0 0 0 .448.497C4.574 8.891 6.124 9 8 9c1.876 0 3.426-.109 4.552-.226A.5.5 0 0 0 13 8.277V4.723a.5.5 0 0 0-.448-.497A44.303 44.303 0 0 0 8 4Zm0-1c-1.837 0-3.353.107-4.448.22a.5.5 0 1 1-.104-.994A44.304 44.304 0 0 1 8 2c1.876 0 3.426.109 4.552.226a.5.5 0 1 1-.104.994A43.306 43.306 0 0 0 8 3Z"/>
                            <path d="M15 8a1 1 0 0 0 1-1V5a1 1 0 0 0-1-1V2.64c0-1.188-.845-2.232-2.064-2.372A43.61 43.61 0 0 0 8 0C5.9 0 4.208.136 3.064.268 1.845.408 1 1.452 1 2.64V4a1 1 0 0 0-1 1v2a1 1 0 0 0 1 1v3.5c0 .818.393 1.544 1 2v2a.5.5 0 0 0 .5.5h2a.5.5 0 0 0 .5-.5V14h6v1.5a.5.5 0 0 0 .5.5h2a.5.5 0 0 0 .5-.5v-2c.607-.456 1-1.182 1-2V8ZM8 1c2.056 0 3.71.134 4.822.261.676.078 1.178.66 1.178 1.379v8.86a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 11.5V2.64c0-.72.502-1.301 1.178-1.379A42.611 42.611 0 0 1 8 1Z"/>
                        </svg>
                        <p>대중교통</p>
                    </button>
                    <button id="carButton">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-car-front-fill" viewBox="0 0 16 16">
                            <path d="M2.52 3.515A2.5 2.5 0 0 1 4.82 2h6.362c1 0 1.904.596 2.298 1.515l.792 1.848c.075.175.21.319.38.404.5.25.855.715.965 1.262l.335 1.679c.033.161.049.325.049.49v.413c0 .814-.39 1.543-1 1.997V13.5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-1.338c-1.292.048-2.745.088-4 .088s-2.708-.04-4-.088V13.5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-1.892c-.61-.454-1-1.183-1-1.997v-.413a2.5 2.5 0 0 1 .049-.49l.335-1.68c.11-.546.465-1.012.964-1.261a.807.807 0 0 0 .381-.404l.792-1.848ZM3 10a1 1 0 1 0 0-2 1 1 0 0 0 0 2Zm10 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2ZM6 8a1 1 0 0 0 0 2h4a1 1 0 1 0 0-2H6ZM2.906 5.189a.51.51 0 0 0 .497.731c.91-.073 3.35-.17 4.597-.17 1.247 0 3.688.097 4.597.17a.51.51 0 0 0 .497-.731l-.956-1.913A.5.5 0 0 0 11.691 3H4.309a.5.5 0 0 0-.447.276L2.906 5.19Z"/>
                        </svg>
                        <p>승용차</p>
                    </button>
                </div>
                <button>일정생성</button>
            </div>
        </div>
    </div>
</div>