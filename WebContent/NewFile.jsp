<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
  <head>
    <title>Simple Map</title>
<script src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBodLVKsbfm_E9LyDU8PxrWsinKkUNUbgY&callback=initMap" async defer></script>
    <script>
    
    
      var map;
      function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: -34.397, lng: 150.644},
          zoom: 8
        });
        var mapDataElement = document.getElementById("mapData");

        var locations = JSON.parse(mapDataElement.value);
        
        
          var markers = locations.map(function(location) {
            return new google.maps.Marker({
              position: location
              
            });
          });

          var markerCluster = new MarkerClusterer(map, markers,
            {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
        	gridSize : 30
      }
    </script>
  </head>
  <body>
    <div id="map" style="height: 1000px; width: 1000px"></div>
	<input type = "hidden" id = "mapData" value='${ mapList }'/>  
  </body>

</html>
