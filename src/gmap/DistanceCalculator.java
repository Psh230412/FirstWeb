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
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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

         String destinationLatitude = String.valueOf(destination.getLatitude());
         String destinationLongitude = String.valueOf(destination.getLongitude());

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

//                  Test.counter.incrementAndGet();
                  if (elementStatus.equals("OK")) {

                     JsonNode distanceNode = element.path("distance");
                    
                     int distanceValue = distanceNode.path("value").asInt(); // distance의 value 값을 가져옵니다.
                    
                     
//                     System.out.println("Response Content: " + content.toString());
//                     System.out.println(distanceValue);
                     
                     distances.add(new Distance(destination.getLatitude(), destination.getLongitude(), distanceValue));

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

   public static List<Distance> distanceCalculate( String originLatitude,
         String originLongitude,List<Location> entireSelectedList) {
//      Location_DAO location_DAO = new Location_DAO();
      ExecutorService executor = Executors.newFixedThreadPool(16);
      String API_KEY = "AIzaSyA0e22ys-P8tLqDUwqH0tcu-OKfeLUm8GQ";
      
      
//      List<Location> entireDestinationList = location_DAO.getLatLongList();
      

      
      List<Distance> allDistances = new ArrayList<>();
      List<DistanceCalculator> workers = new ArrayList<>();

      
      for (Location destination : entireSelectedList) {
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

//      System.out.println("\nFinished all threads");
//      System.out.println("Number of successful executions: " + counter.get());

      
//      거리 오름차순으로 정렬
      Collections.sort(allDistances, new Comparator<Distance>() {
            @Override
            public int compare(Distance D1, Distance D2) {
                return Integer.compare(D1.getDistance(), D2.getDistance());  
            }
        });
      
      
      return allDistances;

   }
   public static List<Location> getFirstLocation(List<Location> firstLocation){
//      가고싶은 여행지 4곳!!
//      List<gmap_Location> firstLocation = new ArrayList<gmap_Location>();
      
      
      
      
      
      return firstLocation;
   }
   
   public static List<Location> getSecondLocation(List<Location> firstLocation,List<Location> entireSelectedList){
//      전체 관광지 몇십개
      
      entireSelectedList.removeAll(getFirstLocation(firstLocation));
      
      Random random = new Random();

      // 0부터 entireSelectedList.size() - 1 사이의 랜덤한 정수를 얻습니다.
      int randomIndex = random.nextInt(entireSelectedList.size()-1);

      Location randomLocation = entireSelectedList.get(randomIndex);
      
      Double originLatitude= randomLocation.getLatitude();
      Double originLongitude = randomLocation.getLongitude();
      
      
      List<Distance> resultList= distanceCalculate(originLatitude.toString(),originLongitude.toString(),entireSelectedList);
      
      List<Location> secondLocation = new ArrayList<Location>();
      for (int i =0 ;i<entireSelectedList.size();i++) {
    	  if(entireSelectedList.get(i).getLatitude()== originLatitude &&  entireSelectedList.get(i).getLongitude() == originLongitude) {
    		  secondLocation.add(entireSelectedList.get(i));
    	  }
      }
      
      for(int i=0;i<3;i++) {
         Distance distance = resultList.get(i);
         
         for(int j=0;j<entireSelectedList.size();j++) {
            if(entireSelectedList.get(j).getLatitude()== distance.getLatitude() &&  entireSelectedList.get(j).getLongitude() == distance.getLongitude() ) {
               secondLocation.add(entireSelectedList.get(j));
            }
            
         }
         
         
//         secondLocation.add(new Location(distance.getLatitude(), distance.getLongitude()));
         
      }

      return secondLocation;
   }
   
   public static List<Location> getThirdLocation(List<Location> firstLocation,List<Location> secondLocation,List<Location> entireSelectedList){
      
//      전체 관광지-가고싶은곳 4곳-랜덤 4곳
      
      entireSelectedList.removeAll(getFirstLocation(firstLocation));
      entireSelectedList.removeAll(secondLocation);
      
      Random random = new Random();

      int randomIndex = random.nextInt(entireSelectedList.size()-1);

      Location randomLocation = entireSelectedList.get(randomIndex);
      
      
      Double originLatitude= randomLocation.getLatitude();
      Double originLongitude = randomLocation.getLongitude();
      
      
      List<Distance> resultList= distanceCalculate(originLatitude.toString(),originLongitude.toString(),entireSelectedList);
      
      List<Location> thirdLocation = new ArrayList<Location>();
      for (int i =0 ;i<entireSelectedList.size();i++) {
    	  if(entireSelectedList.get(i).getLatitude()== originLatitude &&  entireSelectedList.get(i).getLongitude() == originLongitude) {
    		  thirdLocation.add(entireSelectedList.get(i));
    	  }
      }
      
      for(int i=0;i<3;i++) {
         Distance distance = resultList.get(i);
         
         for(int j=0;j<entireSelectedList.size();j++) {
            if(entireSelectedList.get(j).getLatitude()== distance.getLatitude() &&  entireSelectedList.get(j).getLongitude() == distance.getLongitude() ) {
               thirdLocation.add(entireSelectedList.get(j));
            }
            
         }
         
         
//         secondLocation.add(new Location(distance.getLatitude(), distance.getLongitude()));
         
      }
      
      
      return thirdLocation;
   }
   
   

}