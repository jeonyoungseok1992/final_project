$(document).ready(function(){
    /*여행지 ajax*/
    $.ajax({
        url: "regionList.bo",
        success: function(data){
            console.log(data)
            drawFunk(data);
        },
        error: function(){
            console.log("regionList.bo ajax 실패");
        }
    })

/* 박수현 추천 여행지 ajax */
$.ajax({
url: "recommendMain.bo",
success: function(data){
let recommend = "";
for(rec of data){
recommend +=`<div class="photo_recommend">
    <img class="rc-photo" src="${rec.recommendTitleImg}">
    <div class="rc-region">${rec.recommendBoardRegion}</div>
    <div class="rc-memo">${rec.recommendBoardTitle}</div>
    </div>`;
}
document.querySelector('.recommend-content').innerHTML = recommend;
},
error: function(){
console.log("recommendMain.bo ajax 실패");
}
})

   

const drawFunk = function(data){

let str = "";
for (let i in data) {   
    let item = data[i];
    
    str += `
    <div class="section3-lc-inner" onclick="redirectToPlan(${item.regionNo})">
        <img class="lc-photo" src="${item.regionImg}">
        <div class="lc-eng">${item.regionName}</div>
        <div class="lc-han">${item.regionEng}</div>
    </div>
    `
}

document.querySelector("#section3-lc").innerHTML = str;

}


});

function redirectToPlan(num) {
window.location.href = `goPlan.bo?regionNo=`+num;
}


function searchWithKeyword(){
let keyword = document.getElementById('section1-input').value;
console.log(keyword);
$.ajax({
url: "regionSearch.bo",
data : {keyword : keyword },
success: function(data){
   
   let str = '';
   for (let i in data) {   
    let item = data[i];
    
    str += `
    <div class="section3-lc-inner" onclick="redirectToPlan(${item.regionNo})">
        <img class="lc-photo" src="${item.regionImg}">
        <div class="lc-eng">${item.regionName}</div>
        <div class="lc-han">${item.regionEng}</div>
    </div>
    `
}
   document.querySelector("#section3-lc").innerHTML = str;

   let section3_lc = document.getElementById('section3-lc').offsetTop;
    console.log(section3_lc);
   // 스크롤 이동하기
   $('html, body').animate({
    scrollTop: section3_lc
}, 200);
},
error: function(){
    console.log("recommendTrip.bo ajax 실패");
}
})
}




function searchWithKeyword2(){
let keyword = document.getElementById('section1-input2').value;
console.log(keyword);
$.ajax({
url: "regionSearch.bo",
data : {keyword : keyword },
success: function(data){
   
   let str = '';
   for (let i in data) {   
    let item = data[i];
    
    str += `
    <div class="section3-lc-inner" onclick="redirectToPlan(${item.regionNo})">
        <img class="lc-photo" src="${item.regionImg}">
        <div class="lc-eng">${item.regionName}</div>
        <div class="lc-han">${item.regionEng}</div>
    </div>
    `
}
   document.querySelector("#section3-lc").innerHTML = str;
   scrollChatToBottom();

},
error: function(){
    console.log("recommendTrip.bo ajax 실패");
}
})
}
