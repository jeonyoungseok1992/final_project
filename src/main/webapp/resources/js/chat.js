let socket;
let youNo;
let sendNo
$(document).ready(function () {

    youNo = document.getElementById("plz").value;
    sendNo = document.getElementById("plz2").value;




    window.scrollTo(0, 99999);






    


    socket = new WebSocket("ws://localhost:8009/mapping/chat");

    //socket 연결 성공시
    socket.onopen = function(){
        console.log("웹소켓 연결 ok...");
        console.log(typeof youNo);
        console.log(typeof sendNo);
        chatList();
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
        let str ='';
        let profileImg = receive.memberProfileImg ? receive.memberProfileImg : "/mapping/resources/images/profile.png";
        if (parseInt(receive.myNo) !== parseInt(sendNo)) {
            str += '<div class="left" align="left">';
            str += '<div>';
            str += '<img class="title-img2" src="' + profileImg + '" >';
            str += '<span style="font-size: 20px; margin-left: 10px;">' + receive.message + '</span>';
            str += '<span style="font-size: 10px; margin-left: 10px;">' + receive.time + '</span>';
            str += '</div>';
            str += '</div>';
        } else {
            str += '<div class="right" align="right">';
            str += '<div>';
            str += '<span style="font-size: 10px; margin-left: 10px;">' + receive.time + '</span>'; 
            str += '<span style="font-size: 20px; margin-left: 10px;">' + receive.message + '</span>';
            str += '<img class="title-img2" src="' + profileImg + '" >';
            str += '</div>';
            str += '</div>';
        }

        // let str = '';

        // str += '<div class="right" align="right">';
        // str += `<span style="font-size: 10px; margin-left: 10px;">${receive.time}</span>`;
        // str += '<c:choose>';
        // str += '<c:when test="${!empty receive.memberProfileImg}">';
        // str += `<img class="title-img2" src="${receive.memberProfileImg}" >`;
        // str += '</c:when>';
        // str += '<c:otherwise>';
        // str += '<img src="resources/images/profile.png" class="title-img"  alt="나의프로필">';
        // str += '</c:otherwise>';
        // str += '</c:choose>';
        // str += `<span style="font-size: 20px; margin-left: 10px;">${receive.message}</span>`;
        // str += '</div>';
    
        msgContainer.innerHTML += str;
        scrollChatToBottom();
    //     str ='';
    //     str = `
    //     <div class="left" align="left">
    //     <c:choose>
    //         <c:when test="${!empty receive.memberProfileImg}">
    //             <img class="title-img2" src="${receive.memberProfileImg}" >
    //         </c:when>
    //         <c:otherwise>
    //             <img src="resources/images/profile.png" class="title-img"  alt="나의프로필">
    //         </c:otherwise>
    //     </c:choose>

    //     <span style="font-size: 20px; margin-left: 10px;">${receive.message}</span>
    //     <span style="font-size: 10px; margin-left: 10px;">${receive.time}</span>
            
    //     </div>
    // `
        
    //     msgContainer.innerHTML += str;
    
  
    
    
    }



    setInterval(function(){

        chatList();

    },5000);
    







});




    function sendMsg(youNo){
        const msgData = {
            message : document.querySelector("input[name=chatting]").value,
            target : youNo
        }
        console.log(msgData);
        socket.send(JSON.stringify(msgData));
        $('#chat-msg-input').val('');

       
       
    }


    function leftChat(){
        $.ajax({
            url: "leftChat.ch",
            success: function (list) {
                
          
                console.log(list);
                
    
                let str = "";
                let str2 = "";
                str2 = `
                    <div id="chat-msg-inner">
                        <input name="chatting" id="chat-msg-input" type="text" placeholder="메세지를 입력해주세요." required>
                        </input>                          
                    </div>
                    <button id="send-button" onclick="sendMsg('${youNo}')" disabled >전송</button>
                `;
                $(document).on('input', '#chat-msg-input', function () {
                    let inputValue = $(this).val();
                    let sendButton = $('#send-button');
    
                    // Enable or disable the button based on input value
                    if (inputValue.trim() !== "") {
                        sendButton.prop('disabled', false);
                    } else {
                        sendButton.prop('disabled', true);
                    }
                });

 
                for (c of list) {
                    
                    console.log(typeof c.myNo);
                    console.log(youNo);
                    console.log(sendNo);
                    console.log(typeof sendNo);

                    let profileImg = c.memberProfileImg ? c.memberProfileImg : "/mapping/resources/images/profile.png";
                    if (parseInt(c.myNo) !== parseInt(sendNo)) {
                        str += `
                            <div class="left" align="left">
                                <div>
                                    <img class="title-img2" src="${profileImg}" >
                                    <span style="font-size: 20px; margin-left: 10px;">${c.message}</span>
                                    <span style="font-size: 10px; margin-left: 10px;">${c.time}</span>
                                </div>
                            </div>
                        `;
                    } else {
                        str += `
                            <div class="right" align="right">
                                <div> 
                                <span style="font-size: 10px; margin-left: 10px;">${c.time}</span>  
                                    <span style="font-size: 20px; margin-left: 10px;">${c.message}</span>   
                                   
                                    <img class="title-img2" src="${profileImg}" >      
                                </div>
                            </div>
                        `;
                    }


                }
                

                document.querySelector("#chat-main").innerHTML = str;
               document.querySelector("#chat-msg").innerHTML = str2;
            },
            error: function () {
                console.log("채팅 화면 통신 실패");
            }
        })

    }

    function chatList(){
        
        $.ajax({
            url: "chatList.ch",
            success: function (list) {
                
          
                console.log(list);
                
    
                let str = "";
                for (c of list) {
                    console.log('채팅목록시작');
                    console.log(c.youNo);
                    let profileImg = c.memberProfileImg ? c.memberProfileImg : "/mapping/resources/images/profile.png";

                    str +=`
                    <div class="chat-list" onclick="goChat(${c.youNo})">
                        <img src="${profileImg}" alt="sunil">
                        <div class="list-user">
                            <h5>${c.memberNickName} <span class="chat_date">${c.time}</span></h5>
                            <p>${c.message}</p>
                        </div>    
                        <div class="chat-count">new</div> 
                    </div>
                    `
                


                }
                document.querySelector("#section1-list").innerHTML = str;
    
            },
            error: function () {
                console.log("채팅 목록 통신 실패");
            }
        })

    }

    function goChat(youNo) {

        $.ajax({
            url: "goChat.ch",
            data: {
                youNo: youNo
            },
            async: false,
            success: function (list) {
                
                console.log(list);

                let str = "";
                let str2 = "";
                str2 = `
                    <div id="chat-msg-inner">
                        <input name="chatting" id="chat-msg-input" type="text" placeholder="메세지를 입력해주세요." required>
                        </input>                          
                    </div>
                    <button id="send-button" onclick="sendMsg('${youNo}')" disabled >전송</button>
                `;
                $(document).on('input', '#chat-msg-input', function () {
                    let inputValue = $(this).val();
                    let sendButton = $('#send-button');
    
                    // Enable or disable the button based on input value
                    if (inputValue.trim() !== "") {
                        sendButton.prop('disabled', false);
                    } else {
                        sendButton.prop('disabled', true);
                    }
                });
                
                for (c of list) {
                    let profileImg = c.memberProfileImg ? c.memberProfileImg : "/mapping/resources/images/profile.png";
                    if (c.youNo !== youNo) {
                        str += `
                            <div class="left" align="left">
                            <div id="hiddenNo" type="hidden" value="youNo"></div>
                                <div>
                                    <img class="title-img2" src="${profileImg}" >
                                    <span style="font-size: 20px; margin-left: 10px;">${c.message}</span>
                                    <span style="font-size: 10px; margin-left: 10px;">${c.time}</span>
                                </div>
                            </div>
                        `;
                    } else {
                        str += `
                            <div class="right" align="right">
                            <div id="hiddenNo" type="hidden" value="youNo"></div>
                                <div> 
                                <span style="font-size: 10px; margin-left: 10px;">${c.time}</span> 
                                    <span style="font-size: 20px; margin-left: 10px;">${c.message}</span>   
                                 
                                    <img class="title-img2" src="${profileImg}" >       
                                </div>
                            </div>
                        `;
                    }
                }

                document.querySelector("#chat-main").innerHTML = str;
                document.querySelector("#chat-msg").innerHTML = str2;
            },
            error: function () {
                console.log("채팅 화면 통신 실패");
            }
        });
    }



    function scrollChatToBottom() {
        var chatMain = document.getElementById("section2");
        chatMain.scrollTop = chatMain.scrollHeight;
    }