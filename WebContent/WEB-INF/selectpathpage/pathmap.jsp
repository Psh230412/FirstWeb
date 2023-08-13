
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@ page import="object.LatAndLng"%>

<!DOCTYPE html>
<html>
<head>
<title>Simple Map</title>
<script
	src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js"></script>
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA0e22ys-P8tLqDUwqH0tcu-OKfeLUm8GQ&callback=initMap"
	async defer></script>

</head>

<body>
	<div id="map1" style="height: 330px; width: 300px"></div>
	<div id="map2" style="height: 330px; width: 300px"></div>
	<div id="map3" style="height: 330px; width: 300px"></div>
</body>


<script>
	console.log("pathmap.jsp로 이동 완료");
	
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
	
	</script>

</html>
