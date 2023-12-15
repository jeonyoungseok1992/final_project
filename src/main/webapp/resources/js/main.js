$(document).ready(function(){
            $.ajax({
                url: "regionList.bo",
                success: function(data){
                    console.log(data)
                    drawFunk(data);
                },
                error: function(){
                    console.log("air.do ajax 실패");
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

// function redirectToPlan() {
//     window.location.href = `goPlan.bo`;
// }