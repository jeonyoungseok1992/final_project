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