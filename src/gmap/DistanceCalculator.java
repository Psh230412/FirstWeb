package gmap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DistanceCalculator implements Runnable {
	private String originLatitude;
	private String originLongitude;
	private Location destination;
	private String API_KEY;
	private List<Distance> distances = new ArrayList();

	public DistanceCalculator(Location destination, String originLatitude, String originLongitude, String API_KEY) {
		this.originLatitude = originLatitude;
		this.originLongitude = originLongitude;
		this.destination = destination;
		this.API_KEY = API_KEY;
	}

	public List<Distance> getDistances() {
		return distances;
	}

	@Override
	public void run() {
		try {

			ObjectMapper mapper = new ObjectMapper();

			String destinationLatitude = String.valueOf(destination.getLat());
			String destinationLongitude = String.valueOf(destination.getLng());

			if (!(destinationLatitude.equals(originLatitude) && destinationLongitude.equals(originLongitude))) {

				URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + originLatitude
						+ "%2C" + originLongitude + "&destinations=" + destinationLatitude + "%2C"
						+ destinationLongitude + "&key=" + API_KEY);

				HttpURLConnection Httpconn = (HttpURLConnection) url.openConnection();
				Httpconn.setRequestMethod("GET");

				BufferedReader in = new BufferedReader(new InputStreamReader(Httpconn.getInputStream()));
				String inputLine;
				StringBuilder content = new StringBuilder();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				String jsonString = content.toString();
				JsonNode rootNode = mapper.readTree(jsonString);

				JsonNode rowsNode = rootNode.path("rows");

				for (JsonNode row : rowsNode) {
					JsonNode elementsNode = row.path("elements");
					for (JsonNode element : elementsNode) {
						String elementStatus = element.path("status").asText();

						Test.counter.incrementAndGet();
						if (elementStatus.equals("OK")) {

							JsonNode distanceNode = element.path("distance");
							int distanceValue = distanceNode.path("value").asInt(); // distance의 value 값을 가져옵니다.
							System.out.println("Response Content: " + content.toString());
							System.out.println(distanceValue);
							
							distances.add(new Distance(destination.getLat(), destination.getLng(), distanceValue));

						}
					}
				}
				in.close();
				Httpconn.disconnect();
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Distance> distanceCalculate(AtomicInteger counter, String originLatitude,
			String originLongitude) {
		Location_DAO location_DAO = new Location_DAO();
		ExecutorService executor = Executors.newFixedThreadPool(16);
		String API_KEY = "AIzaSyA0e22ys-P8tLqDUwqH0tcu-OKfeLUm8GQ";
		
		
		List<Location> entireDestinationList = location_DAO.getLatLongList();

		
		List<Distance> allDistances = new ArrayList<>();
		List<DistanceCalculator> workers = new ArrayList<>();

		
		for (Location destination : entireDestinationList) {
			DistanceCalculator worker = new DistanceCalculator(destination, originLatitude, originLongitude, API_KEY);
			workers.add(worker);
			executor.execute(worker);
		}

		executor.shutdown();

		while (!executor.isTerminated()) {
		}

		for (DistanceCalculator worker : workers) {
			allDistances.addAll(worker.getDistances());
		}

		System.out.println("\nFinished all threads");
		System.out.println("Number of successful executions: " + counter.get());

		
//		거리 오름차순으로 정렬
		Collections.sort(allDistances, new Comparator<Distance>() {
            @Override
            public int compare(Distance D1, Distance D2) {
                return Integer.compare(D1.getDistance(), D2.getDistance());  
            }
        });
		
		
		return allDistances;

	}

}
