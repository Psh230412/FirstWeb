<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link rel="stylesheet" type="text/css"
	href="mypage modify/mypage modify.css" />
<title>Page Title</title>
</head>
<body>
	<header></header>
	<main>
		<div id="middle_container">
			<div id="profilDiv">
				<p id="profil">사진</p>
				<img src="img/톱니바퀴 단추.png" id="profilBtn" alt="대체 텍스트" />
			</div>
			<form method="post">
				<div class="inputLongDiv">
					<div class="inputDiv">
						<input type="text" class="inputText" name="changeNickname" value="${ nickname }" />
					</div>
					<img src="img/연필모양img.png" id="pancilBtn" alt="대체 텍스트" />
				</div>

				<div class="inputLongDiv">
					<div class="inputDiv">
						<input type="password" class="inputText" name="changePassword" />
					</div>
					<img src="img/연필모양img.png" id="pancilBtn" alt="대체 텍스트" />
				</div>

				<div class="inputLongDiv">
					<div class="inputDiv">
						<input type="password" class="inputText" name="changePasswordRe" />
					</div>
					<img src="img/연필모양img.png" id="pancilBtn" alt="대체 텍스트" />
					<button></button>
				</div>
			</form>
		</div>
	</main>
</body>
</html>
