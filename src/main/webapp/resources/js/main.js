const myPageValue = {}



// $(document).ready(function(){

//     myPageValue.memberNo = document.getElementById("hiddenNo").value; 

//             $.ajax({
//                 url: "location.api",
//                 success: function(data){
//                     console.log(data)
//                     drawFunk(data);
//                 },
//                 error: function(){
//                     console.log("air.do ajax 실패");
//                 }
//             })
        
    
    
//     const drawFunk = function(data){
        
//         let str = "";
//         for (let i in data) {   
//             let item = data[i];
            
//             str += `
//             <div class="section3-lc-inner" onclick="redirectToPlan(${item})">
//             <img class="lc-photo" src="${item.img}">
//             <div class="lc-eng">${item.title}</div>
//             <div class="lc-han">${item.address}</div>
//             </div>
//             `
//         }
        
//         document.querySelector("#section3-lc").innerHTML = str;
//     }








// });

function redirectToPlan() {
    window.location.href = `goPlan.bo`;
}