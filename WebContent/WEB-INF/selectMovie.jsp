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
	<c:forEach var="title" items="${titles}">
		<h2 class="movieTitle">${title}</h2>
		<div class="container">
			<c:forEach var="locationObject" items="${movieLocations[title]}">
				<c:forEach var="imgData" items="${locationObject.imageData}" varStatus="status">
					<div class="item" data-location_no="${locationObject.location_no}">
						<img src="data:image/jpeg;base64,${imgData}" class="movieimg"/>
						<div class="description">
							<p class="address">${locationObject.addressData[status.index]}</p>
							<p class="address">${locationObject.location_no[status.index]}</p>
						</div>
					</div>
				</c:forEach>
			</c:forEach>
		</div>
	</c:forEach>

	
	<script>
	const items = document.querySelectorAll(".item");
	let selectedCount = 0;
	const selectedLocationNos = [];

	items.forEach((item) => {
	    item.addEventListener("click", () => {
	    	if (selectedCount < 4 || item.classList.contains("selected")) {
	    	    if (item.classList.contains("selected")) {
	    	        item.classList.remove("selected");
	    	        selectedCount--;
	    	        const locationNo = item.getAttribute("data-location_no");
	    	        const index = selectedLocationNos.indexOf(locationNo);
	    	        console.log("취소한 장소 "+selectedLocationNos);
	    	        if (index !== -1) {
	    	            selectedLocationNos.splice(index, 1);
	    	        }
	    	    } else {
	    	        if (selectedCount < 4) {
	    	            item.classList.add("selected");
	    	            selectedCount++;
	    	            const locationNo = item.getAttribute("data-location_no");
	    	            selectedLocationNos.push(locationNo);
	    	            console.log("선택한 장소 "+selectedLocationNos);
	    	        }
	    	    }
	    	    const selectedLocationsInput = document.getElementById("selectedLocations");
	    	    if (selectedLocationsInput) {
	    	        selectedLocationsInput.value = selectedLocationNos;
	    	    }
	    	}
	    });
	});
    </script>
    
    <form action="/selectpath" post="get">
		<input type="hidden" id="selectedLocationNos" name="selectedLocationNos" />
		<input type="submit" class="nextPage"value="경로 확인하기">
	</form>
</body>
</html>
