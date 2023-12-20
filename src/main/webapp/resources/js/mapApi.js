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

    function attractionMap(attPosition) {
        console.log(attPosition)
          


                for (let i in attPosition) {
                    let item = attPosition[i];

                    var position = {
                        title: item.title,
                        latlng: new kakao.maps.LatLng(item.mapy, item.mapx)
                    };
                    positions.push(position);
                }
                console.log(positions);

                // 마커 추가는 AJAX 요청이 완료된 후에 실행되도록 이동

                //addMarkersToMap();
            }
        });
    


function addMarkersToMap() {

    var container = document.getElementById('map');
    var options = {
        center: new kakao.maps.LatLng(positions[7].latlng.Ma, positions[7].latlng.La),
        level: 9
    };

    var map = new kakao.maps.Map(container, options);


    var markers = [];
    var linePath = [];
    // 마커 이미지의 이미지 주소입니다
    var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
    for (var a = 0; a < positions.length; a++) {
    // 마커 이미지의 이미지 크기 입니다
    var imageSize = new kakao.maps.Size(24, 35); 
    
    // 마커 이미지를 생성합니다    
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 


        var marker = new kakao.maps.Marker({
            map: map,
            position: positions[a].latlng,
            title: positions[a].title,
            image : markerImage // 마커 이미지 
            
        });
        markers.push(marker);
        linePath.push(positions[a].latlng);
    }






// 지도에 표시할 선을 생성합니다
var polyline = new kakao.maps.Polyline({
    path: linePath, // 선을 구성하는 좌표배열 입니다
    strokeWeight: 5, // 선의 두께 입니다
    strokeColor: '#FFAE00', // 선의 색깔입니다
    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
    strokeStyle: 'solid' // 선의 스타일입니다
});

// 지도에 선을 표시합니다 
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


