$(document).ready(function(){

//휴대폰 번호 인증
var code2 = "";
$("#phoneChk").click(function(event){
	
	var phone = $("#phone").val();
	console.log(phone)
	if (phone.trim() === '') {
        // 전화번호가 입력되지 않은 경우
        alert("전화번호를 입력하세요."); // 또는 다른 작업 수행 가능
       
        // 이벤트 기본 동작 차단 (버튼 클릭 막기)
        event.preventDefault();
		return;
    } else{
		alert("인증번호 발송이 완료되었습니다.\n휴대폰에서 인증번호 확인을 해주십시오.");
	}

	

	$.ajax({
        type:"GET",
        url:"phoneCheck?phone=" + phone,
        cache : false,
        success:function(data){
            console.log('핸드폰인증 성공');
			console.log(data);
        	if(data == "error"){
        		alert("휴대폰 번호가 올바르지 않습니다.")
				$(".successPhoneChk").text("유효한 번호를 입력해주세요.");
				$(".successPhoneChk").css("color","red");
				$("#phone").attr("autofocus",true);
        	}else{	        		
        		$("#phone2").attr("disabled",false);
        		$("#phoneChk2").css("display","inline-block");
        		$(".successPhoneChk").text("인증번호를 입력한 뒤 본인인증을 눌러주십시오.");
        		$(".successPhoneChk").css("color","green");
        		$("#phone").attr("readonly",true);
        		code2 = data;
        	}
        }
    });
});



//휴대폰 인증번호 대조
$("#phoneChk2").click(function(){
	if($("#phone2").val() == code2){
		$(".successPhoneChk").text("인증번호가 일치합니다.");
		$(".successPhoneChk").css("color","green");
		$("#phoneDoubleChk").val("true");
		$("#phone2").attr("disabled",true);
	}else{
		$(".successPhoneChk").text("인증번호가 일치하지 않습니다. 확인해주시기 바랍니다.");
		$(".successPhoneChk").css("color","red");
		$("#phoneDoubleChk").val("false");
		$(this).attr("autofocus",true);
	}



});










});




//핸드폰인증 성공시 동작
function idNext(){
	$.ajax({
        url:"phoneIdCheck.me",
        data: {
            memberPhone: document.getElementById("phone").value
        },
        success:function(m){
			console.log(m);
            console.log('id 성공');
            console.log(m.memberPhone);
			console.log(m.memberId);
		
 
            //$(".mobileNo").empty();
            let str = "";
           document.querySelector("#modal-header").innerHTML = str;
           document.querySelector("#mobileNo2").innerHTML = str;
           document.querySelector("#modal-header").innerHTML = m.memberId;
        },
        error: function(){
            console.log("phoneIdCheck ajax통신 실패");
            }
    });
}




function frList(myNo){
    console.log(myNo);
	$.ajax({
        url:"friendList.me",
        data: {
            memberNo: myNo
        },
        success:function(list){
			console.log(list)
            let str = "";
            if (list.length == 0) {
                str = '<div style="font-size: 20px; margin-bottom: 20px;">등록 된 친구가 없습니다</div>';
                console.log(str);
            } else{
            for (m of list) {

                let profileImg = m.memberProfileImg ? m.memberProfileImg : "/mapping/resources/images/profile.png";
                console.log(m)
                str += `
                <div class="myfriend">
                <div style="display: flex; align-items: center;">
                    <img class="title-img2" src="${profileImg}" >
                    <span style="font-size: 20px; margin-left: 10px;">${m.memberNickName}</span>
                </div>
                <div style="display: flex; align-items: center;">
                    <a href="chat.me?youNo=${m.friendMemberNo}"><img src="resources/images/talkIcons.png" alt="채팅" style="width: 30px; height: 30px;">
                    <span id="chat-count${m.friendMemberNo}" class="chatCount" style="font-size: 30px; margin: 5px; padding-right: 20px; color:red;"></span>
                    </a>
                    
                </div>
            </div>
                `
        }


        





    }
        
		document.querySelector("#modal-body-chat").innerHTML = str;
        chatCheck22(myNo);
    
	},
        error: function(){
            console.log("phoneIdCheck ajax통신 실패");
            }
    });
}



async function chatCheck22(myNo) {
    console.log("chatCheck 도착");
    
    const list = await $.ajax({
        url: "friendList.me",
        data: {
            memberNo: myNo
        },
    });
    console.log(list);
    for (const c of list) {
 

        try {
            const count = await $.ajax({
                url: "chatCheck2.ch",
                data: {
                    youNo: c.friendMemberNo,
                    myNo: myNo,
                }
            });

            let str = "&nbsp&nbsp";
            console.log(count);

            if(count !== 0){
                str = count;
            }

            document.querySelector("#chat-count" + c.friendMemberNo).innerHTML = str;
        } catch (error) {
            console.log("chatCheck 통신 실패");
        }
    }
}