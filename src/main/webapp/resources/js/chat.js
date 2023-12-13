let socket;

$(document).ready(function () {


    socket = new WebSocket("ws://localhost:8009/mapping/chat");

    //socket 연결 성공시
    socket.onopen = function(){
        console.log("웹소켓 연결 ok...");

        leftChat();
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
        console.log(receive)
       
        
        const msgContainer =  document.querySelector("#chat-main");
        msgContainer.innerHTML += ("(" + receive.time + ")<br>" + receive.message);
    
    
    
    
    }
    







});




    function sendMsg(youNo){
        const msgData = {
            message : document.querySelector("textarea[name=chatting]").value,
            target : youNo
        }
        console.log(msgData);
        socket.send(JSON.stringify(msgData));
        $('#summernote').summernote('code', '');

       
    }


    function leftChat(){
        $.ajax({
            url: "leftChat.ch",
            success: function (list) {
                
          
                console.log(list);
                
    
                let str = "";
                for (c of list) {
                    console.log(typeof c.myNo);
                    console.log(typeof c.youNo);

                    let profileImg = c.memberProfileImg ? c.memberProfileImg : "/mapping/resources/images/profile.png";
                    if(c.myNo !== c.youNo){
                        str += `
                        <div class="left" align="left">
                            <div>
                                <img class="title-img2" src="${profileImg}" >
        
                                <span style="font-size: 20px; margin-left: 10px;">${c.message}</span>
                            </div>	
                        </div>
                    `
                    }else{
                        str += `
                        <div class="right" align="right">
                            <div>
                                <img class="title-img2" src="${profileImg}" >
        
                                <span style="font-size: 20px; margin-left: 10px;">${c.message}</span>
                            </div>	
                        </div>
                    `
                    }


                }
    
                document.querySelector("#chat-main").innerHTML = str;
    
            },
            error: function () {
                console.log("friendList ajax통신 실패");
            }
        })

    }