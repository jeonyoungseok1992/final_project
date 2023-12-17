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

            /* 추천 여행지 ajax */
            $.ajax({
                url: "recommendTrip.bo",
                success: function(data){
                   let recommend = '';
                   for(rec of data){
                    let arr = rec.addr1.split(" ");
                    recommend +=`<div class="photo_recommend">
                        <img class="rc-photo" src="${rec.firstimage}">
                        <div class="rc-region">${arr[0]}</div>
                        <div class="rc-memo">${rec.title}</div>
                        </div>`;
                   }
                   document.querySelector('.recommend-content').innerHTML = recommend;
                },
                error: function(){
                    console.log("recommendTrip.bo ajax 실패");
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
           scrollChatToBottom();
        },
        error: function(){
            console.log("recommendTrip.bo ajax 실패");
        }
    })
}

function scrollChatToBottom() {
    var chatMain = document.getElementById("section3-lc");
    chatMain.scrollTop = chatMain.scrollHeight;
}

$('#section1-btn').click(function(){			
    var offset = $('#section1-btn').offset();  //animate()메서드를 이용해서 선택한 태그의 스크롤 위치를 지정해서 0.4초 동안 부드럽게 해당 위치로 이동함 	        
    $('body').animate({scrollTop : offset.top}, 400);		
});

const btn = document.getElementById('section1-btn')
btn.addEventListener('click',function(){
    console.log("Button clicked!");
	//window.scrollTo(0, 100) //or
	window.scrollTo({left:0, top:500})
})

$('#section1-btn').click(function() {		
    console.log("Button clicked!");	
    var offset = $('#section1-btn').offset();
    $('body, html').animate({ scrollTop: offset.top }, 400);
});


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