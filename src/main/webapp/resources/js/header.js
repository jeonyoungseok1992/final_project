$(document).ready(function(){

//휴대폰 번호 인증
var code2 = "";
$("#phoneChk").click(function(event){
	
	var phone = $("#phone").val();

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



