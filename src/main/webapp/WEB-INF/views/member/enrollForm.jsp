<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/mapping/resources/css/enrollForm.css">
</head>
<body>
   <%@ include file="../common/header.jsp"%>

    <div class="outer">
        <br>
        <h2 align="center" style="font-size: 24px; font-weight: 700; line-height: 35px;">회원가입</h2>  
             <form action="/insert.me" id="mem-enroll-form" >
            <table style="width:auto;">
                <tr>
                    <td>아이디 *  </td> 
                    <br>
                    <td>
                        <input type="text" name="userId" maxlength="12"  placeholder="아이디를 입력해주세요."required>
                        <button id = "userId" type = "button" onclick="idCheck()">중복확인</button>
                    </td>
                    <td></td>
                    <!-- <td><button type="button" style="background-color: #FF8B3D;  border: 1px solid;
                        border-radius: 5px; 
                        color: white; height: 35px;">중복확인</button></td> -->
                </tr>
                <tr> 
                    <td>비밀번호*</td>
                    <td><input type="password" name="userPwd" maxlength="15" placeholder="비밀번호를 입력해주세요." required></td>
                    <td></td>
                </tr>
                <tr>
                    <td>비밀번호 확인*</td>
                    <td><input type="password" name="userPwdCheck" maxlength="15" placeholder="비밀번호를 한 번 더 입력해주세요." required></td>
                    <td></td>
                </tr>
                <tr>
                    <td>이름  *</td>
                    <td><input type="text" name="userName" maxlength="6" placeholder="이름을 입력해주세요." required></td>
                    <td></td>
                </tr> 
                <tr>
                    <td>닉네임</td>
                    <td>
                        <input type="text" name="userNickName" maxlength="6" placeholder="넥네임을 입력해주세요." required>
                        <button id = "userNickName" type = "button" onclick="nickNameCheck()">중복확인</button>
                    </td>
                    <td></td>
                </tr> 
                <tr>
                    <form name="frm" method="post" action="">
                        <td>핸드폰번호</td>
                        <td><input type="text" class="tel" maxlength="13" placeholder="핸드폰번호를 입력해주세요(-제외)"></td>
                        <td></td>
                    </form>
                </tr>


                <tr>
                    <td scope="row">이메일</td>
                    <td>
                    <input type="text" id="email_id" class="form_w200" value="" title="이메일 아이디" placeholder="이메일" maxlength="18" style="width: 100px;" /> @ 
                    <input type="text" id="email_domain" class="form_w200" value="" title="이메일 도메인" placeholder="이메일 도메인" maxlength="18" style="width: 240px;" /> 
                    <select class="select" title="이메일 도메인 주소 선택" onclick="setEmailDomain(this.value);return false;">
                        <option value="">선택</option>
                        <option value="naver.com">naver.com</option>
                        <option value="gmail.com">gmail.com</option>
                        <option value="hanmail.net">hanmail.net</option>
                        <option value="hotmail.com">hotmail.com</option>
                        <option value="korea.com">korea.com</option>
                        <option value="nate.com">nate.com</option>
                        <option value="yahoo.com">yahoo.com</option>
                    </select>
                    </td>
                </tr>
                    
                



                
               
                
            </table>
            


            <br><br>

            <div align = "center" style="margin-right: 13px;">
                <button type="submit" id="joinMember" onclick="return checkPwd()">회원가입</button>
            	<button type = "reset" id = "reset">초기화</button>
            </div>

            <br><br>

        </form>
		<script>
	       <!--비밀번호 제약-->
            function checkPwd(){
                let pwdInput = document.querySelector("#mem-enroll-form input[name=userPwd]");
                let pwdCheckInput = document.querySelector("#mem-enroll-form input[name=userPwdCheck]");
               
                if(pwdInput.value !== pwdCheckInput.value){
                    alert("비밀번호가 일치하지않습니다.");
                    return false;      
                }
            }
            
            function idCheck(){
            

            	const inInput = document.querySelector("#mem-enroll-form input[name=userId]");

                if(inInput.value.length == 0){
            		alert("아이디를 입력해주세요.");
            		return;
            	}

            	$.ajax({
            		url : "idCheck.me",
            		data : {
            			"checkId" : inInput.value
            		},
            		success : function(res){
            			if(res === "NNNNY"){
            				if(confirm("사용가능한 아이디입니다. 사용하시겠습니까?")){
            					let submitBtn = document.querySelector("#mem-enroll-form button[type=submit]");
            					submitBtn.removeAttribute("disabled");
                                disabled.setAttribute("readonly", true);
                            } else{
                                inInput.focus();
                            }
                        } else{
                            alert("사용하실 수 없는 아이디입니다. 다시 입력해주세요.");
                            inInput.focus();
                        }
                    },
                    error : function(){
                        console.log("아이디 중복체크용 ajax 통신 실패");
                    }	
            		
            	})
            }




            //닉네임 중복확인 
            function nickNameCheck(){
            

            const inInput = document.querySelector("#mem-enroll-form input[name=userNickName]");

            if(inInput.value.length == 0){
                alert("닉네임을 입력해주세요.");
                return;
            }

            $.ajax({
                url : "nickNameCheck.me",
                data : {
                    "checkNickName" : inInput.value
                },
                success : function(res){
                    if(res === "NNNNY"){
                        if(confirm("사용가능한 닉네임입니다. 사용하시겠습니까?")){
                            let submitBtn = document.querySelector("#mem-enroll-form button[type=submit]");
                            submitBtn.removeAttribute("disabled");
                            disabled.setAttribute("readonly", true);
                        } else{
                            inInput.focus();
                        }
                    } else{
                        alert("사용하실 수 없는 닉네임입니다. 다시 입력해주세요.");
                        inInput.focus();
                    }
                },
                error : function(){
                    console.log("닉네임 중복체크용 ajax 통신 실패");
                }	
                
            })
        }
        </script>

        <!--핸드폰 번호 스크립트-->
        <script type="text/javascript">
            $('.tel').keydown(function(event) {
                var key = event.charCode || event.keyCode || 0;
                $text = $(this);
                if (key !== 8 && key !== 9) {
                    if ($text.val().length === 3) {
                        $text.val($text.val() + '-');
                    }
                    if ($text.val().length === 8) {
                        $text.val($text.val() + '-');
                    }
                }
        
                return (key == 8 || key == 9 || key == 46 || (key >= 48 && key <= 57) || (key >= 96 && key <= 105));          
            });
        </script>
    </div>
</body>
</html>