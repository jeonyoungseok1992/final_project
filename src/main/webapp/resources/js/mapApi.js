// var positions = [];

// $(document).ready(function () {



		


//         init();

//         function init(){

        
//                         $.ajax({
//                         url: "location.api",
//                         success: function(data){
//                             console.log(data);

//                             for (let i in data) {   
//                                 let item = data[i];
                                
//                                 // data의 각 항목에서 필요한 정보를 추출하여 positions에 추가
//                                 var position = {
//                                     title: item.title,
//                                     latlng: new kakao.maps.LatLng(item.mapY, item.mapX) // 예시로 위도와 경도를 가져와서 LatLng 객체 생성
//                                     // 위도와 경도가 데이터 안에 있는지 확인하여 올바르게 수정해야 합니다.
//                                 };
//                                 positions.push(position); // positions 배열에 추가


            
//                             }
//                             console.log(data[0].mapX)
//                             console.log(positions);

//                         },
//                         error: function(){
//                             console.log("air.do ajax 실패");
//                         }
//                     })

//             }

//         // <맵 생성>
// 		var container = document.getElementById('map'); 
// 		var options = { 
// 			center: new kakao.maps.LatLng(37.402707, 126.922044), 
// 			level: 4
// 		};
	
// 		var map = new kakao.maps.Map(container, options); 
// 		// </맵 생성>



//             for (var a = 0; a < positions.length; a ++) {
		   
//                 var marker = new kakao.maps.Marker({  // 마커 생성
//                     map: map, // 마커를 표시할 지도
//                     position: positions[a].latlng, // 마커를 표시할 위치
//                     title : positions[a].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시 됨
//                 });
//                 marker.setMap(map)
//             }


// });


var positions = [];

$(document).ready(function () {
    initMap();

    function initMap() {
        $.ajax({
            url: "kakaoMap.api",
            success: function (data) {
                console.log(data);

                for (let i in data) {
                    let item = data[i];

                    var position = {
                        title: item.title,
                        latlng: new kakao.maps.LatLng(item.mapy, item.mapx)
                    };
                    positions.push(position);
                }
                console.log(positions);

                // 마커 추가는 AJAX 요청이 완료된 후에 실행되도록 이동
                //addMarkersToMap();
            },
            error: function () {
                console.log("location.api ajax 실패");
            }
        });
    }


});
    
    


function addMarkersToMap() {
    var container = document.getElementById('map');
    var options = {
        center: new kakao.maps.LatLng(37.5607522667, 126.9855149899),
        level: 4
    };

    var map = new kakao.maps.Map(container, options);
    var markers = [];
    var linePath = [];
    console.log(container);
    console.log(options);
    for (var a = 0; a < positions.length; a++) {
        var marker = new kakao.maps.Marker({
            map: map,
            position: positions[a].latlng,
            title: positions[a].title,
            
        });
        markers.push(marker);
        linePath.push(positions[a].latlng);
    }






var polyline = new kakao.maps.Polyline({
    path: linePath,
    strokeWeight: 2,
    strokeColor: '#FF0000',

});

//lines.push(polyline);
polyline.setMap(map);



}





// function addMarker(mY, mX) {
//     console.log('마커 성공')
//     console.log(mY)
//     console.log(mX)
//     var container = document.getElementById('map');
//     var options = {
//         center: new kakao.maps.LatLng(mY, mX), // 받아온 좌표로 중심 설정
//         level: 4
//     };

//     var map = new kakao.maps.Map(container, options);

//     var markerPosition = new kakao.maps.LatLng(mY, mX); // 받아온 좌표로 마커 생성
//     var marker = new kakao.maps.Marker({
//         position: markerPosition
//     });

//     marker.setMap(map); // 마커 지도에 표시
// }


