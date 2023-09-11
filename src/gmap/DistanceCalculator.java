package gmap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import object.Distance;
import object.Location;

/*
영화 5개

가보고싶은 여행지 4개

경로 3개 

30개
4개 
경로 1번 (가보고싶은 여행지 4개를 포함한 촬영지4개로 구성된 경로)
경로 2번 (전체에서 가보고싶은 여행지 4개를 제외한 것 중에서 기준이 랜덤으로 잡힌다 기준에서 가까운곳 4개로 구성된경로)
경로 3번 (전체에서 1번+2번 제외한 것기준이 랜덤으로 잡힌다 기준에서 가까운곳 4개로 구성된경로)


*/

public class DistanceCalculator implements Runnable {
	private Location destination;
	private Location originLocation;
	private List<Distance> distances = new ArrayList();

	private static final double EARTH_RADIUS = 6371.0;

	public DistanceCalculator(Location originLocation, Location destination) {
		this.destination = destination;
		this.originLocation = originLocation;
	}

	public List<Distance> getDistances() {
		return distances;
	}

	@Override
	public void run() {


		if (!destination.equals(originLocation)) {
			
			double deltaLat = Math.toRadians(destination.getLatitude() - originLocation.getLatitude());
	        double deltaLon = Math.toRadians(destination.getLongitude() - originLocation.getLongitude());

			double a = Math.pow(Math.sin(deltaLat / 2), 2) + Math.cos(Math.toRadians(originLocation.getLatitude()))
					* Math.cos(Math.toRadians(destination.getLatitude())) * Math.pow(Math.sin(deltaLon / 2), 2);

			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

			
			int distanceValue = (int) (EARTH_RADIUS * c);
			if (distanceValue >= 100) {

				distances.add(new Distance(distanceValue, destination, originLocation));
			}
			

		}

	}

	public static List<Distance> distanceCalculate(Location originLocation, List<Location> entireSelectedList) {

		int cores = Runtime.getRuntime().availableProcessors();
		ExecutorService executor = Executors.newFixedThreadPool(cores * 2);

		List<Distance> allDistances = new ArrayList<>();
		List<DistanceCalculator> workers = new ArrayList<>();

		for (Location destination : entireSelectedList) {
			DistanceCalculator worker = new DistanceCalculator(originLocation, destination);
			workers.add(worker);
			executor.execute(worker);
		}

		executor.shutdown();

		while (!executor.isTerminated()) {
		}

		for (DistanceCalculator worker : workers) {
			allDistances.addAll(worker.getDistances());
		}

		System.out.println("allDistances의 크기:	" + allDistances.size());

//      거리 오름차순으로 정렬
		Collections.sort(allDistances, new Comparator<Distance>() {
			@Override
			public int compare(Distance D1, Distance D2) {
				return Integer.compare(D1.getDistance(), D2.getDistance());
			}
		});

		return allDistances;

	}

	public static List<Location> getFirstLocation(List<Location> firstLocation) {
//      가고싶은 여행지 4곳!!
//      List<gmap_Location> firstLocation = new ArrayList<gmap_Location>();

		return firstLocation;
	}

	public static List<Location> getSecondLocation(List<Location> firstLocation, List<Location> entireSelectedList) {
//      전체 관광지 몇십개
		entireSelectedList.removeAll(firstLocation);
		Random random = new Random();
		// 0부터 entireSelectedList.size() - 1 사이의 랜덤한 정수를 얻습니다.
		int randomIndex = random.nextInt(entireSelectedList.size() - 1);
		Location randomLocation = entireSelectedList.get(randomIndex);
		List<Distance> resultList = distanceCalculate(randomLocation, entireSelectedList);
		System.out.println("두번째 resultList의 크기: " + resultList.size());
//		int count = 0;
//		for(int i=0;i<resultList.size();i++) {
//			if(resultList.get(i).getDistance()>=100) {
//				count++;
//			}
//		}
//		System.out.println("거리가 100이상인 경우의 수: "+count);

		List<Location> secondLocation = new ArrayList<Location>();
		secondLocation.add(randomLocation);
		for (int i = 0; i < 3; i++) {
			Distance distance = resultList.get(i);
			for (int j = 0; j < entireSelectedList.size(); j++) {
				if (entireSelectedList.get(j).equals(distance.getDestination())) {
					secondLocation.add(entireSelectedList.get(j));
				}
			}
		}
		return secondLocation;
	}

	public static List<Location> getThirdLocation(List<Location> firstLocation, List<Location> secondLocation,
			List<Location> entireSelectedList) {

//      전체 관광지-가고싶은곳 4곳-랜덤 4곳

		entireSelectedList.removeAll(firstLocation);
		entireSelectedList.removeAll(secondLocation);

		Random random = new Random();

		int randomIndex = random.nextInt(entireSelectedList.size() - 1);

		Location randomLocation = entireSelectedList.get(randomIndex);

		List<Distance> resultList = distanceCalculate(randomLocation, entireSelectedList);
		System.out.println("세번째 resultList의 크기: " + resultList.size());

//		int count = 0;
//		for(int i=0;i<resultList.size();i++) {
//			if(resultList.get(i).getDistance()>=100) {
//				count++;
//			}
//		}
//		System.out.println("거리가 100이상인 경우의 수: "+count);

		List<Location> thirdLocation = new ArrayList<Location>();

		thirdLocation.add(randomLocation);

		for (int i = 0; i < 3 && i < resultList.size(); i++) {
			Distance distance = resultList.get(i);

			for (int j = 0; j < entireSelectedList.size(); j++) {

				if (entireSelectedList.get(j).equals(distance.getDestination())) {
					thirdLocation.add(entireSelectedList.get(j));

				}

			}

		}

		return thirdLocation;
	}

}