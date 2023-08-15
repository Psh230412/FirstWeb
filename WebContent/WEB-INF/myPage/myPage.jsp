<%@page import="com.google.gson.Gson"%>
<%@page import="object.MyPath"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link rel="stylesheet" type="text/css" href="myPage/myPage.css" />
<title>MyPage</title>
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
<script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA0e22ys-P8tLqDUwqH0tcu-OKfeLUm8GQ"></script>
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</head>
<body>
	<header class="header">
		<nav class="navbar">
			<div class="navbar-item">
				<img src="/login/Untitled-1_0000_Group-3-copy.png" class="logo" alt=""> <a href="#" class="navbar-menu"><span>영화선택</span></a> <a href="#" class="navbar-menu"><span>경로보기</span></a>

				<button class="btn btn-primary" onclick="redirectToLogout()">로그아웃</button>
				<div class="social-icon">
					<a href="#"><i class='bx bxl-facebook'></i></a> <a href="#"><i class='bx bxl-twitter'></i></a> <a href="#"><i class='bx bxl-youtube'></i></a> <a href="#"><i class='bx bxl-instagram'></i></a> <a href="#"><i class='bx bxl-linkedin'></i></a>
				</div>
			</div>

			<button class="menu-open-btn" data-menu-open-btn>
				<ion-icon name="reorder-two"></ion-icon>
			</button>

			<nav class="navbar" data-navbar>
				<div class="navbar-top">
					<a href="./" class="logo"> </a>

					<button class="menu-close-btn" data-menu-close-btn>
						<ion-icon name="close-outline"></ion-icon>
					</button>
				</div>

				<ul class="navbar-list">
					<li><a href="./movie" class="navbar-link">영화선택</a></li>
					<li><a href="./mypage" class="navbar-link">경로확인</a></li>
				</ul>

				<ul class="navbar-social-list">
					<li><a href="#" class="navbar-social-link"> <ion-icon
								name="logo-twitter"></ion-icon>
					</a></li>
					<li><a href="#" class="navbar-social-link"> <ion-icon
								name="logo-facebook"></ion-icon>
					</a></li>
					<li><a href="#" class="navbar-social-link"> <ion-icon
								name="logo-pinterest"></ion-icon>
					</a></li>
					<li><a href="#" class="navbar-social-link"> <ion-icon
								name="logo-instagram"></ion-icon>
					</a></li>
					<li><a href="#" class="navbar-social-link"> <ion-icon
								name="logo-youtube"></ion-icon>
					</a></li>
				</ul>
			</nav>
		</div>
	</header>
	<main>
		<div class="container">

			<section>
				<div class="profile">
					<img class="fixed-size-image" src="data:image/jpeg;base64,${ loggedUserProfileImg }" />
					<div id="nicknameDiv">
						<div id="nickname">
							<p>${ loggedUserNickname }</p>
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
							<form method="post">
								<div class="cancelBtn">
									<input type="hidden" value="${ path.pathNo }" name="pathPk"> 
									<input type="hidden" value="cancel" name="input_type">
									<button class="button-x-image"></button>
								</div>
							</form>
							<div class="detailRoot" >
								<form action="./mypath" method="post">
									 <input type="hidden" name="pathNo" value="${path.pathNo}" >
            							<button class="rootImg" onclick="setPathValue(${path.pathNo})"></button>
								</form>
								<div class="detailRootLeft">
									<div class="detailRootTitle">
										<p class="detailRootBigText">
											<span id="pathText${path.pathNo}">${path.rootName}</span>
										<form id="pathForm${path.pathNo}" method="post">
											<input type="hidden" value="${path.pathNo}" name="pathPk"> 
											<input type="hidden" value="inputName" name="input_type"> 
											<input type="text" name="pathName" id="pathNameInput${path.pathNo}" style="display: none;" value="${path.rootName}">
											<button class="bx bxs-pencil" data-pathno="${path.pathNo}"></button>
										</form>
										</p>
									</div>
									<div class="detailRootAdressDiv">
										<div class="searchDiv">
											<p class="detailRootAdress">${ path.locationAddress1.address }</p>
											<form action="./hotelFood">
												<input type="hidden" name="locationName" value="${ path.locationAddress1.address }">
											<div class="markDiv">
												<button type="submit" class="hotel" name="search" value="lodging"><img class="markImg" src="img/호텔.png"></button>
												<button type="submit" class="food" name="search" value="restaurant"><img class="markImg" src="img/레스토랑.png"></button>
											</div>
											</form>
										</div>

										<div class="searchDiv">
											<p class="detailRootAdress">${ path.locationAddress2.address }</p>
											<form action="./hotelFood">
												<input type="hidden" name="locationName" value="${ path.locationAddress2.address }">
											<div class="markDiv">
												<button type="submit" class="hotel" name="search" value="lodging"><img class="markImg" src="img/호텔.png"></button>
												<button type="submit" class="food" name="search" value="restaurant"><img class="markImg" src="img/레스토랑.png"></button>
											</div>
											</form>
										</div>
										<div class="searchDiv">
											<p class="detailRootAdress">${ path.locationAddress3.address }</p>
											<form action="./hotelFood">
												<input type="hidden" name="locationName" value="${ path.locationAddress3.address }">
											<div class="markDiv">
												<button type="submit" class="hotel" name="search" value="lodging"><img class="markImg" src="img/호텔.png"></button>
												<button type="submit" class="food" name="search" value="restaurant"><img class="markImg" src="img/레스토랑.png"></button>
											</div>
											</form>
										</div>
										<div class="searchDiv">
											<p class="detailRootAdress">${ path.locationAddress4.address }</p>
											<form action="./hotelFood">
												<input type="hidden" name="locationName" value="${ path.locationAddress4.address }">
											<div class="markDiv">
												<button type="submit" class="hotel" name="search" value="lodging"><img class="markImg" src="img/호텔.png"></button>
												<button type="submit" class="food" name="search" value="restaurant"><img class="markImg" src="img/레스토랑.png"></button>
											</div>
											</form>
										</div>
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
	<script src="myPage/myPage.js">
	</script>
</body>
</html>