<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Page Title</title>
<link rel="stylesheet" type="text/css" href="Movie.css" />
<script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script>
</head>
<header>
	<div id="top_container">
		<img src="img/Untitled-1_0000_Group-3-copy.png" id="logo" alt="대체 텍스트" />
		<div id="navigation">
			<div class="navi">
				<p>영화선택</p>
			</div>
			<div class="navi">
				<p>내경로보기</p>
			</div>
			<div class="navi">
				<p>회원정보수정</p>
			</div>
		</div>
	</div>
</header>
<body>

<c:forEach var="entry" items="${postersMap}">
    <div class="movie-container">
        <div class="poster-container">
            <img src="data:image/jpeg;base64,${entry.value}" class="moviePoster"/>
        </div>
        <div class="locations-container">
            <!-- 문자열로 키를 변환하여 사용 -->
            <c:forEach var="locationObject" items="${movieLocations[String.valueOf(entry.key)]}"> 
                <c:forEach var="imgData" items="${locationObject.imageData}" varStatus="status">
                    <div class="item">
                        <img src="data:image/jpeg;base64,${imgData}" class="movieimg"/>
                        <div class="description">
                            <p class="address">${locationObject.addressData[status.index]}</p>
                        </div>
                    </div>
                </c:forEach>
            </c:forEach>
        </div>
    </div>
</c:forEach>
    <script>        
        const items = document.querySelectorAll(".item");
        let selectedCount = 0;

        items.forEach((item) => {
            item.addEventListener("click", () => {
                if (selectedCount < 3 || item.classList.contains("selected")) {
                    if (item.classList.contains("selected")) {
                        item.classList.remove("selected");
                        selectedCount--;
                    } else {
                        item.classList.add("selected");
                        selectedCount++;
                    }
                }
            });
        });
    </script>
</body>
</html>
