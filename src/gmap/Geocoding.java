package gmap;



import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Geocoding {

    public gmap_Location addressINTOLatLng() {

        URL url;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			
			String address = "1600 Amphitheatre Parkway, Mountain View, CA"; // 변환하고자 하는 주소
			String apiKey = "AIzaSyA0e22ys-P8tLqDUwqH0tcu-OKfeLUm8GQ"; // 실제 API 키로 교체해야 함
			String urlString = "https://maps.googleapis.com/maps/api/geocode/json?address=" + 
					java.net.URLEncoder.encode(address, "UTF-8") + "&key=" + apiKey;
			url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			
			int responseCode = conn.getResponseCode();
			if(responseCode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responseCode);
			}
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			JsonNode jsonNode = objectMapper.readTree(response.toString());
			double lat = jsonNode.path("results").get(0).path("geometry").path("location").path("lat").asDouble();
			double lng = jsonNode.path("results").get(0).path("geometry").path("location").path("lng").asDouble();
			
//			System.out.println("Latitude: " + lat);
//			System.out.println("Longitude: " + lng);
			
			return new gmap_Location(lat, lng);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
}
