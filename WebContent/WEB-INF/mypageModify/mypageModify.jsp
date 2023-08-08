<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
<meta charset="utf-8" />
<link rel="stylesheet" type="text/css"
	href="mypage modify/mypage modify.css" />
<title>MyPage</title>
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
</head>
<body>
	<header class="header">
		<nav class="navbar">
			<div class="navbar-item">
				<img src="/login/Untitled-1_0000_Group-3-copy.png" class="logo"
					alt=""> <a href="#" class="navbar-menu"><span>영화선택</span></a>
				<a href="#" class="navbar-menu"><span>경로보기</span></a>

				<button class="btn btn-primary">로그아웃</button>
				<div class="social-icon">
					<a href="#"><i class='bx bxl-facebook'></i></a> <a href="#"><i
						class='bx bxl-twitter'></i></a> <a href="#"><i
						class='bx bxl-youtube'></i></a> <a href="#"><i
						class='bx bxl-instagram'></i></a> <a href="#"><i
						class='bx bxl-linkedin'></i></a>
				</div>
			</div>
		</nav>
	</header>
	<main>
		<div class="container">
			<section>
				<div class="profile">
					<form method="post">
						<button id="photoset-Button">
							<i class='bx bxs-cog' style='color: #ffffff'></i>
						</button>
					</form>
					<p class="profileimg">사진</p>
					<div class="textitemDiv">
						<div id="nickname">
							<p>닉네임변경</p>
						</div>
						<button id="NicknameButton">
							<i class='bx bxs-pencil' style='color: #ffffff'></i>
						</button>
					</div>
					<div class="textitemDiv">
						<div id="password">
							<p>비밀번호변경</p>
						</div>
						<button id="passwordButton">
							<i class='bx bxs-pencil' style='color: #ffffff'></i>
						</button>
					</div>
					<div id="inputChangeNickname" style="display: none;">
						<form method="post">
							<input type="hidden" name="form_type" value="changeNicknameForm">
							<p>닉네임변경</p>
							<input type="text" id="changeNickname" name="changeNickname">
							<p>비밀번호확인</p>
							<input type="password" id="passwordCheck" name="passwordCheck">
							<input type="submit" id="ChangeNicknameBtn">
						</form>
					</div>

					<div id="inputChangePassword" style="display: none;">
						<form method="post">
							<input type="hidden" name="form_type" value="changePasswordForm">
							<p>현재 비밀번호 입력</p>
							<input type="password" id="passwordNow" name="changeNickname">
							<p>변경할 비밀번호 입력</p>
							<input type="password" id="passwordChange" name="passwordCheck">
							<p>변경할 비밀번호 입력 확인</p>
							<input type="password" id="passwordChangeRe" name="passwordCheck">
							<input type="submit" id="ChangePasswordBtn">
						</form>
					</div>
				</div>
			</section>
		</div>
	</main>
	<footer></footer>
	<script src="mypage modify/mypage modify.js"></script>
</body>
</html>