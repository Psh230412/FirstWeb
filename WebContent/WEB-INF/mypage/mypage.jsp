<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link rel="stylesheet" type="text/css" href="mypage/mypage.css" />
<title>MyPage</title>
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
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
					<p id="profil">사진</p>
					<div id="nicknameDiv">
						<div id="nickname">
							<p>${ nickname }</p>
						</div>
						<form method="post">
							<button id="NicknameButton">
								<i class='bx bxs-cog' style='color: #ffffff'></i>
							</button>
						</form>
					</div>
				</div>
			</section>
			<section>
				<div class="barDiv">
					<div id="barTextDiv">
						<p>나의경로보기</p>
					</div>
				</div>
				<c:forEach var="path" items="${ list }">
					<div class="myRoot-sc">
						<div class="myRoot">
							<div class="cancelBtn">
								<p>ⓧ</p>
							</div>
							<div class="detailRoot">
								<img src="img/춘식15.png" class="rootImg" />
								<div class="detailRootLeft">
									<div class="detailRootTitle">
										<p class="detailRootBigText">
											나의경로1 <i class='bx bxs-pencil' style='color: #ffffff'></i>
										</p>
									</div>
									<p class="detailRootDate">00/00/00 (저장된 날짜)</p>
									<div class="detailRootAdressDiv">
										<p class="detailRootAdress">${ path.locationAddress1 }</p>
										<p class="detailRootAdress">${ path.locationAddress2 }</p>
										<p class="detailRootAdress">${ path.locationAddress3 }</p>
										<p class="detailRootAdress">${ path.locationAddress4 }</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</section>

		</div>
	</main>
	<footer></footer>
	<script src="mypage/mypage.js"></script>
</body>
</html>