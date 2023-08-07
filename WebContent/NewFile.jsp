<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
  <head>
    <title>Simple Map</title>
<script src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBodLVKsbfm_E9LyDU8PxrWsinKkUNUbgY&callback=initMap" async defer></script>
    <script>
    
    
    function initMap() {
      
   // 지도를 생성합니다.
      var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 10,
        center: {lat: 37.773972, lng: -122.431297}  // 예시로 샌프란시스코의 좌표를 사용
      });

      // Polyline의 좌표 배열을 정의합니다.
      var pathCoordinates = [
        {lat: 33.7078170776367, lng: -118.286170959473},
        {lat: 37.773, lng: -122.215},
        {lat: 34.4656639099121, lng:-118.405334472656 },
        {lat: 40.7260704040527, lng:-73.952278137207 }
        // ... 여러 좌표들 ...
      ];

      // Polyline 객체를 생성합니다.
      var pathLine = new google.maps.Polyline({
        path: pathCoordinates,
        geodesic: true,
        strokeColor: '#FF0000',
        strokeOpacity: 1.0,
        strokeWeight: 2
      });

      // Polyline을 지도에 추가합니다.
      pathLine.setMap(map);
    }
      
    </script>
  </head>
  <body>
    <div id="map" style="height: 1000px; width: 1000px"></div>
  </body>

</html>
