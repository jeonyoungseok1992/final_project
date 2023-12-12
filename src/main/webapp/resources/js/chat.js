$(document).ready(function () {
 
    const socket = new WebSocket("ws://localhost:8009/mapping/chat");

    //socket 연결 성공시
    socket.onopen = function(){
        console.log("웹소켓 연결 ok...");
    }
    //socket 연결 끊어졌을 시 
    socket.onclose = function(){
        console.log("웹소켓 연결 끊어짐...");
    }

    //socket 연결 실패했을 시 
    socket.onerror = function(){
        console.log("웹소켓 연결 실패...");
        alert("웹소켓 연결 실패");
    }

    //socket연결로부터 데이터가 도착했을때
    //서버로부터 데이터가 도착했을때
    socket.onmessage = function(ev){
        const receive = JSON.parse(ev.data);		//String을 다시 json으로 받은거
        
        
        
        const msgContainer = document.querySelector("#chat-main-left");
        msgContainer.innerHTML += (receive.name + "(" + receive.time + ")<br>" + receive.msg);
    
    
    
    
    }
    







});




function sendMsg(youNick){
    const msgData = {
        message : document.querySelector("input[name=chatting]").value,
        target : youNick
    }

    socket.send(JSON.stringify(msgData));
    document.querySelector("#chat-main-rignt")
    = document.querySelector("input[name=chatting]").value;
    document.querySelector("input[name=chatting]").value = "";
}

