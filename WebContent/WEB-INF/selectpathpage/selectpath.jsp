<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@ page import="object.LatAndLng"%>
<%@ page import="com.google.gson.Gson" %>
<%@page import="object.SelectPath"%>

<!DOCTYPE html>

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="selectpath/selectpath.css" />
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <script
	src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js"></script>
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA0e22ys-P8tLqDUwqH0tcu-OKfeLUm8GQ&callback=initMap"
	async defer></script>
	
	<script>
	
	<% 
    LatAndLng[] firstlatAndLngs = (LatAndLng[]) request.getAttribute("firstlatAndLngs");
    LatAndLng[] secondlatAndLngs = (LatAndLng[]) request.getAttribute("secondlatAndLngs");
    LatAndLng[] thirdlatAndLngs = (LatAndLng[]) request.getAttribute("thirdlatAndLngs");
    %>

    var firstLocationListJson = '<%= new ObjectMapper().writeValueAsString(firstlatAndLngs) %>';
    var secondLocationListJson = '<%= new ObjectMapper().writeValueAsString(secondlatAndLngs) %>';
    var thirdLocationListJson = '<%= new ObjectMapper().writeValueAsString(thirdlatAndLngs) %>';

    var firstLocationList = JSON.parse(firstLocationListJson);
    var secondLocationList = JSON.parse(secondLocationListJson);
    var thirdLocationList = JSON.parse(thirdLocationListJson);
	
	
	function addMarkersToMap(locations, map) {
	    locations.forEach(location => {
	        var marker = new google.maps.Marker({
	            position: {lat: location.latitude, lng: location.longitude},
	            map: map
	        });
	    });
	}

	// 지도에 선을 그리는 함수
	function addPathToMap(locations, map) {
	    var pathCoordinates = locations.map(location => {
	        return {lat: location.latitude, lng: location.longitude};
	    });
	    
	    var pathLine = new google.maps.Polyline({
	        path: pathCoordinates,
	        geodesic: true,
	        strokeColor: '#FF0000',
	        strokeOpacity: 1.0,
	        strokeWeight: 2
	    });
	    pathLine.setMap(map);
	}
	function initMap() {
	    // 첫 번째 지도
	    var map1 = new google.maps.Map(document.getElementById('map1'), {
	        zoom: 10,
	        center: {lat: firstLocationList[0].latitude, lng: firstLocationList[0].longitude}
	    });
	    addMarkersToMap(firstLocationList, map1);
	    addPathToMap(firstLocationList, map1);
	
	    // 두 번째 지도
	    var map2 = new google.maps.Map(document.getElementById('map2'), {
	        zoom: 10,
	        center: {lat: secondLocationList[0].latitude, lng: secondLocationList[0].longitude}
	    });
	    addMarkersToMap(secondLocationList, map2);
	    addPathToMap(secondLocationList, map2);
	
	    // 세 번째 지도
	    var map3 = new google.maps.Map(document.getElementById('map3'), {
	        zoom: 10,
	        center: {lat: thirdLocationList[0].latitude, lng: thirdLocationList[0].longitude}
	    });
	    addMarkersToMap(thirdLocationList, map3);
	    addPathToMap(thirdLocationList, map3);
	}
	
	// 지도 로딩 비동기 함수
	async function loadMap(routeNumber) {
	    switch (routeNumber) {
	        case 1:
	            await initMap(firstLocationList, 'map1');
	            break;
	        case 2:
	            await initMap(secondLocationList, 'map2');
	            break;
	        case 3:
	            await initMap(thirdLocationList, 'map3');
	            break;
	    }
	}
	

	// 지도 초기화 비동기 함수
	async function initMap(locationList, mapId) {
	    var map = new google.maps.Map(document.getElementById(mapId), {
	        zoom: 10,
	        center: { lat: locationList[0].latitude, lng: locationList[0].longitude }
	    });
	    addMarkersToMap(locationList, map);
	    addPathToMap(locationList, map);
	}
	
	// 특정 인덱스 활성화 함수
	function setActiveIndex(indexNumber) {
	    document.getElementById(`index${indexNumber}`).classList.add('active');
	}
	
	window.onload = function() {
	    loadMap(1);
	    setActiveIndex(1);
	};

	</script>
</head>

<body background="img/대지1 1.png" style="overflow-x: hidden">
    <header class="header">
        <nav class="navbar">
            <div class="navbar-item">
                <img src="img/Untitled-1_0000_Group-3-copy.png" class="logo" alt="">
                <a href="http://localhost:8080/ScreenSceneP/movie" class="navbar-menu"><span>영화선택</span></a>
                <a href="http://localhost:8080/ScreenSceneP/mypage" class="navbar-menu"><span>경로보기</span></a>
                
<!--                 <div id="profileImgDiv"> -->
<%--                 <img src="data:image/jpeg;base64,${ porfileImg }" alt="대체이미지" /> --%>
<!--                 </div> -->
<!--                 <div id="nickname"> -->
<%-- 					<p>${ nickname }</p> --%>
<!-- 				</div> -->
				
				<form action="logout" method="get">
                	<button class="btn btn-primary">로그아웃</button>
                </form>
                <div class="social-icon">


                    <a href="#"><i class='bx bxl-facebook'></i></a>
                    <a href="#"><i class='bx bxl-twitter'></i></a>
                    <a href="#"><i class='bx bxl-youtube'></i></a>
                    <a href="#"><i class='bx bxl-instagram'></i></a>
                    <a href="#"><i class='bx bxl-linkedin'></i></a>
                </div>
            </div>
        </nav>
    </header>
    <div class="background">
        <div class="container">
            <div class="picitem">
                <p><img src="img/타입트립 1.png" /></p>
            </div>
            <div class="button-group">
                <a href="#" class="group-link clicked" id="group-1" onclick="changeColor('group-1'); loadMap(1);"><span><i
                            class='bx bxs-circle'></i></span></a>
                <a href="#" class="group-link" id="group-2" onclick="changeColor('group-2'); loadMap(2);"><span><i
                            class='bx bxs-circle'></i></span></a>
                <a href="#" class="group-link" id="group-3" onclick="changeColor('group-3'); loadMap(3);"><span><i
                            class='bx bxs-circle'></i></span></a>

            </div>

            <div class="index1">
                <div class="slider-section">
                    <div class="item">
                        <div class="slidernumberitem">
                            <p>경로 1</p>
                        </div>
                        <div class="slidermap">
                            <div id="map1" style="height: 840px; width: 1200px"></div>
                        </div>
                        <div class="left-section" id="section-1">
                            <div class="left-section-poster">
                                <img src="data:image/jpeg;base64,${viewPath1.location[0].posterImgStr}" alt="Image">
                            </div>
                            <div class="left-section-content">
                                <div class="photoTop">
                                    <div class="location-mark" id="mark1">
                                        <img src="img/피커 1.png" />
                                    </div>
                                    <div class="location_photo" id="photo1-1">
                                        <img src="data:image/jpeg;base64,${viewPath1.location[0].locationImgStr}" alt="Image">
                                    </div>
                                    <div class="location-title" id="location-title1-1">
                                        <p>${viewPath1.location[0].locationName}</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="right-section" id="section-2">
                            <div class="right-section-poster">
                                <img src="data:image/jpeg;base64,${viewPath1.location[1].posterImgStr}" alt="Image">
                            </div>
                            <div class="right-section-content">
                                <div class="photoTop">
                                    <div class="location-mark" id="mark1">
                                        <img src="img/피커 1.png" />
                                    </div>
                                    <div class="location_photo" id="photo1-2">
                                        <img src="data:image/jpeg;base64,${viewPath1.location[1].locationImgStr}" alt="Image">
                                    </div>
                                    <div class="location-title" id="location-title1-2">
                                        <p>${viewPath1.location[1].locationName}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="left-section" id="section-3">
                            <div class="left-section-poster">
                                <img src="data:image/jpeg;base64,${viewPath1.location[2].posterImgStr}" alt="Image">
                            </div>
                            <div class="left-section-content">
                                <div class="photoTop">
                                    <div class="location-mark" id="mark1">
                                        <img src="img/피커 1.png" />
                                    </div>
                                    <div class="location_photo" id="photo1-1">
                                        <img src="data:image/jpeg;base64,${viewPath1.location[2].locationImgStr}" alt="Image">
                                    </div>
                                    <div class="location-title" id="location-title1-1">
                                        <p>${viewPath1.location[2].locationName}</p>
                                    </div>
                                    
                                </div>
                            </div>
                        </div>

                        <div class="right-section" id="section-4">
                            <div class="right-section-poster">
                                <img src="data:image/jpeg;base64,${viewPath2.location[3].posterImgStr}" alt="Image">
                            </div>
                            <div class="right-section-content">
                                <div class="photoTop">
                                    <div class="location-mark" id="mark1">
                                        <img src="img/피커 1.png" />
                                    </div>
                                    <div class="location_photo" id="photo1-2">
                                        <img src="data:image/jpeg;base64,${viewPath2.location[3].locationImgStr}" alt="Image">
                                    </div>
                                    <div class="location-title" id="location-title1-2">
                                        <p>${viewPath2.location[3].locationName}</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <div class="index2">
                <div class="slider-section">
                    <div class="item">
                        <div class="slidernumberitem">
                            <p>경로 2</p>
                        </div>
                        <div class="slidermap">
                            <div id="map2" style="height: 840px; width: 1200px"></div>
                        </div>
                        <div class="left-section" id="section-1">
                            <div class="left-section-poster">
                                <img src="data:image/jpeg;base64,${viewPath2.location[0].posterImgStr}" alt="Image">
                            </div>
                            <div class="left-section-content">
                                <div class="photoTop">
                                    <div class="location-mark" id="mark1">
                                        <img src="img/피커 1.png" />
                                    </div>
                                    <div class="location_photo" id="photo1-1">
                                        <img src="data:image/jpeg;base64,${viewPath2.location[0].locationImgStr}" alt="Image">
                                    </div>
                                    <div class="location-title" id="location-title1-1">
                                        <p>${viewPath2.location[0].locationName}</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="right-section" id="section-2">
                            <div class="right-section-poster">
                                <img src="data:image/jpeg;base64,${viewPath2.location[1].posterImgStr}" alt="Image">
                            </div>
                            <div class="right-section-content">
                                <div class="photoTop">
                                    <div class="location-mark" id="mark1">
                                        <img src="img/피커 1.png" />
                                    </div>
                                    <div class="location_photo" id="photo1-2">
                                        <img src="data:image/jpeg;base64,${viewPath2.location[1].locationImgStr}" alt="Image">
                                    </div>
                                    <div class="location-title" id="location-title1-2">
                                        <p>${viewPath2.location[1].locationName}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="left-section" id="section-3">
                            <div class="left-section-poster">
                                <img src="data:image/jpeg;base64,${viewPath2.location[2].posterImgStr}" alt="Image">
                            </div>
                            <div class="left-section-content">
                                <div class="photoTop">
                                    <div class="location-mark" id="mark1">
                                        <img src="img/피커 1.png" />
                                    </div>
                                    <div class="location_photo" id="photo1-1">
                                        <img src="data:image/jpeg;base64,${viewPath2.location[2].locationImgStr}" alt="Image">
                                    </div>
                                    <div class="location-title" id="location-title1-1">
                                        <p>${viewPath2.location[2].locationName}</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="right-section" id="section-4">
                            <div class="right-section-poster">
                                <img src="data:image/jpeg;base64,${viewPath2.location[3].posterImgStr}" alt="Image">
                            </div>
                            <div class="right-section-content">
                                <div class="photoTop">
                                    <div class="location-mark" id="mark1">
                                        <img src="img/피커 1.png" />
                                    </div>
                                    <div class="location_photo" id="photo1-2">
                                        <img src="data:image/jpeg;base64,${viewPath2.location[3].locationImgStr}" alt="Image">
                                    </div>
                                    <div class="location-title" id="location-title1-2">
                                        <p>${viewPath2.location[3].locationName}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="index3">
                <div class="slider-section">
                    <div class="item">
                        <div class="slidernumberitem">
                            <p>경로 3</p>
                        </div>
                        <div class="slidermap">
                            <div id="map3" style="height: 840px; width: 1200px"></div>
                        </div>
                        <div class="left-section" id="section-1">
                            <div class="left-section-poster">
                                <img src="data:image/jpeg;base64,${viewPath3.location[0].posterImgStr}" alt="Image">
                            </div>
                            <div class="left-section-content">
                                <div class="photoTop">
                                    <div class="location-mark" id="mark1">
                                        <img src="img/피커 1.png" />
                                    </div>
                                    <div class="location_photo" id="photo1-1">
                                        <img src="data:image/jpeg;base64,${viewPath3.location[0].locationImgStr}" alt="Image">
                                    </div>
                                    <div class="location-title" id="location-title1-1">
                                        <p>${viewPath3.location[0].locationName}</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="right-section" id="section-2">
                            <div class="right-section-poster">
                                <img src="data:image/jpeg;base64,${viewPath3.location[1].posterImgStr}" alt="Image">
                            </div>
                            <div class="right-section-content">
                                <div class="photoTop">
                                    <div class="location-mark" id="mark1">
                                        <img src="img/피커 1.png" />
                                    </div>
                                    <div class="location_photo" id="photo1-2">
                                        <img src="data:image/jpeg;base64,${viewPath3.location[1].locationImgStr}" alt="Image">
                                    </div>
                                    <div class="location-title" id="location-title1-2">
                                        <p>${viewPath3.location[1].locationName}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="left-section" id="section-3">
                            <div class="left-section-poster">
                                <img src="data:image/jpeg;base64,${viewPath3.location[2].posterImgStr}" alt="Image">
                            </div>
                            <div class="left-section-content">
                                <div class="photoTop">
                                    <div class="location-mark" id="mark1">
                                        <img src="img/피커 1.png" />
                                    </div>
                                    <div class="location_photo" id="photo1-1">
                                        <img src="data:image/jpeg;base64,${viewPath3.location[2].locationImgStr}" alt="Image">
                                    </div>
                                    <div class="location-title" id="location-title1-1">
                                        <p>${viewPath3.location[2].locationName}</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="right-section" id="section-4">
                            <div class="right-section-poster">
                                <img src="data:image/jpeg;base64,${viewPath3.location[3].posterImgStr}" alt="Image">
                            </div>
                            <div class="right-section-content">
                                <div class="photoTop">
                                    <div class="location-mark" id="mark1">
                                        <img src="img/피커 1.png" />
                                    </div>
                                    <div class="location_photo" id="photo1-2">
                                        <img src="data:image/jpeg;base64,${viewPath3.location[3].locationImgStr}" alt="Image">
                                    </div>
                                    <div class="location-title" id="location-title1-2">
                                        <p>${viewPath3.location[3].locationName}</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <div class="guide-text">
                <h1>추천 받은 경로가 마음에 들지 않으세요?</h1>
                <p>재 테스트를 통해, 새로 경로를 추천 받을 수 있습니다.</p>
                <p>원하는 항목을 선택후 새로운 경로를 추천받으세요.</p>
				
				<form method="post" action="/ScreenSceneP/flow">
                	<button class="btn btn-primary" id="reselect-location">위치다시선택하기</button>
				</form>
				<form method="get" action="/ScreenSceneP/movie">
                <button class="btn btn-primary" id="reselect-movie">영화다시선택하기</button>
                </form>
				<button class="btn btn-primary" id="select-path">경로 선택</button>
            </div>

        </div>
    </div>
    <div class="footer">
        <p>푸터내용~</p>

    </div>
    <form>
    <button id="topbutton" type="button" onclick="window.scrollTo({ top: 0, behavior: 'smooth' })">▲</button>
	</form>
</body>

<script src="selectpath/selectpath.js"></script>
<script>

document.addEventListener('DOMContentLoaded', (event) => {
	let selectpath = document.getElementById("select-path");
	if (!selectpath) return; // select-path가 없는 경우 종료
	
	let group1 = document.getElementById("group-1");
	
	const indexsec1 = document.querySelector('.index1');
	const indexsec2 = document.querySelector('.index2');
	const indexsec3 = document.querySelector('.index3');
	
	<%
		SelectPath path1 = (SelectPath)request.getAttribute("path1");
		SelectPath path2 = (SelectPath)request.getAttribute("path2");
		SelectPath path3 = (SelectPath)request.getAttribute("path3");
	%>

	selectpath.addEventListener("click", function(e) {
		console.log("경로 선택 버튼 누름");
		alert('경로 선택이 완료되었습니다.');
		if (indexsec1.classList.contains('active')) {
			console.log("인덱스 1 활성화");
			var usernoParse = <%= path1.getUserno() %>;
			 var location1Parse = '<%= path1.getLocation1() %>';
			 var location2Parse = '<%= path1.getLocation2() %>';
			 var location3Parse = '<%= path1.getLocation3() %>';
			 var location4Parse = '<%= path1.getLocation4() %>';
			 var pathMapImageParse = '<%= path1.getPathMapImage() %>';
			
		} else if (indexsec2.classList.contains('active')) {
			console.log("인덱스 2 활성화");
			var usernoParse = <%= path2.getUserno() %>;
			var location1Parse = '<%= path2.getLocation1() %>';
			var location2Parse = '<%= path2.getLocation2() %>';
			var location3Parse = '<%= path2.getLocation3() %>';
			var location4Parse = '<%= path2.getLocation4() %>';
			var pathMapImageParse = '<%= path2.getPathMapImage() %>';
			 
		} else if (indexsec3.classList.contains('active')) {
			console.log("인덱스 3 활성화");
			var usernoParse = <%= path3.getUserno() %>;
			var location1Parse = '<%= path3.getLocation1() %>';
			var location2Parse = '<%= path3.getLocation2() %>';
			var location3Parse = '<%= path3.getLocation3() %>';
			var location4Parse = '<%= path3.getLocation4() %>';
			var pathMapImageParse = '<%= path3.getPathMapImage() %>';
		}
		
		var path = {
				// 올바른 값을 넣어야 함
				userno: usernoParse,
				location1: location1Parse,
				location2: location2Parse,
				location3: location3Parse,
				location4: location4Parse,
				pathMapImage: pathMapImageParse
		};
		var pathJson = JSON.stringify(path);

        // 서버에 path 값을 전송
        fetch('http://localhost:8080/ScreenSceneP/savePath', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: pathJson
        }).then(response => response.json())
        .then(data => {
        	console.log(data);
        })
        .catch(error => console.error('An error occurred:', error));
    });
});


</script>
</html>