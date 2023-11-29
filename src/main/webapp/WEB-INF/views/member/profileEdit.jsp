<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 수정</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- jquery 3.7.1 -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="./resources/css/profileEdit.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        <!-- 초록색 영역부분-->
		<div class="color-container"></div>

            <!-- 프로필 정보쪽-->
            <div class="profile-container">
                <!--프로필 이미지 클릭시에 프로필 사진 수정가능해야함 !-->
                <img src="resources/images/profile.png" alt="나의프로필" style="border-radius: 45px;">
                <br>
                <span style="font-size: 20px; height: 30px;">로그인유저 프로필이름입력 자리</span>
        
            <div class="edit-container">
                <div id="edit-title">프로필 설정</div>
                <div>
                
                <form action="#" method="" style="min-width: 448px;">
                    <div id="edit-input">
                        <label for="nickname" style="margin-bottom: 15px;">닉네임</label>
                        <input class="form-control" type="text" placeholder="빵빵이" id="nickname" style="margin-bottom: 20px;">
                        <label for="email" style="margin-bottom: 15px;">이메일</label>
                        <input class="form-control" type="email" placeholder="abc123@naver.com" id="email">
                    </div>
                    <button class="btn btn-light" data-bs-toggle="modal" data-bs-target="#myModal" style="width: 448px; margin-bottom: 25px;">회원탈퇴</button>
                    <div class="edit-btns">
                        <input type="button" class="btn btn-light" value="뒤로가기" onclick="history.back(-1);" style="width: 216px;">
                        <button type="submit" class="btn btn-secondary" style="width: 216px; padding-left: 16px; ">저장</button>
                    </div>
                </form>

                </div>

            </div>
        

        
        </div> 

            <!-- The Modal -->
			<div class="modal fade" id="myModal">
				<div class="modal-dialog">
					<div class="modal-content" style="margin-top: 300px;">
				
						<!-- Modal body -->
						<div class="modal-body" style="font-size: 18px; font-weight: 500; text-align: center;">
							탈퇴 후 정보를 복구할 수 없습니다.<br><br> 
							정말 탈퇴하시겠습니까?
						</div>
				
						<!-- Modal footer -->
						<div class="modal-footer" style="display: flex; justify-content: center;">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="background: lightgray; width: 200px; height: 50px; border: none; ">Cancle</button>
							<button type="button" onclick="location.href=''" class="btn btn-secondary" style="background: #b2d8b5; width: 200px; height: 50px; border: none;">Ok</button>
						</div>
				
					</div>
				</div>
			</div>   
</body>
</html>