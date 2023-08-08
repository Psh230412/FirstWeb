<<<<<<< HEAD
package selectLocation;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import CRUD.MovieDAO;
import object.Location;
import object.imgLocationObject;

@WebServlet("/flow")
public class test extends HttpServlet {
    MovieDAO movieDao = new MovieDAO(); // movieDao 객체 생성

    private void MoviesPoster(HttpServletRequest req, List<Integer> movieNumbers) {
	List<String> encodedPosters = new ArrayList<>();
	Map<Integer, String> postersMap = new HashMap<>();

	for (Integer movieNumber : movieNumbers) {
	    Blob posterBlob = movieDao.selectOneMovie(movieNumber);
	    if (posterBlob != null) {
		try {
		    byte[] posterBytes = posterBlob.getBytes(1, (int) posterBlob.length());
		    String encodedPoster = Base64.getEncoder().encodeToString(posterBytes);
		    postersMap.put(movieNumber, encodedPoster);
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}

	req.setAttribute("postersMap", postersMap);
    }

    // 각 영화당 촬영지,이미지
    private void MoviesAddress(HttpServletRequest req, List<Integer> movieNumbers) throws SQLException, IOException {
	Map<String, List<imgLocationObject>> movieLocationsMap = new HashMap<>();

	for (Integer movieNumber : movieNumbers) {
	    List<imgLocationObject> locationsList = new ArrayList<>();
	    List<Location> locations = movieDao.selectLocationList(movieNumber);

	    for (Location location : locations) {
		List<String> singleImageData = new ArrayList<>();
		List<String> singleAddressData = new ArrayList<>();

		Blob blob = location.getImage();
		InputStream inputStream = blob.getBinaryStream();
		byte[] bytes = new byte[(int) blob.length()];
		inputStream.read(bytes);

		singleImageData.add(Base64.getEncoder().encodeToString(bytes));
		singleAddressData.add(location.getAddress());

		locationsList.add(new imgLocationObject(singleImageData, singleAddressData));
	    }
	    System.out.println("Movie Number: " + movieNumber + ", Locations: " + locationsList);
	    movieLocationsMap.put(String.valueOf(movieNumber), locationsList);
	}

	req.setAttribute("movieLocations", movieLocationsMap);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String movieNumbersJson = req.getParameter("movieNumbers");
	ObjectMapper mapper = new ObjectMapper();

	try {
	    JsonNode rootNode = mapper.readTree(movieNumbersJson);
	    JsonNode movieNumbersNode = rootNode.get("movieNumbers");
	    List<Integer> movieNumbers = new ArrayList<>();

	    if (movieNumbersNode.isArray()) {
		for (JsonNode movieNumberNode : movieNumbersNode) {
		    movieNumbers.add(movieNumberNode.asInt());
		}
	    }
	    System.out.println(movieNumbers);
	    MoviesPoster(req, movieNumbers); // 이 부분을 추가합니다.
	    MoviesAddress(req, movieNumbers);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	req.getRequestDispatcher("/WEB-INF/selectMovie.jsp").forward(req, resp);
    }
=======
package selectLocation;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import CRUD.MovieDAO;
import object.Location;
import object.imgLocationObject;

@WebServlet("/flow")
public class test extends HttpServlet {
	MovieDAO movieDao = new MovieDAO(); // movieDao 객체 생성

	// 영화 3개의 title
	private void MoviesTitle(HttpServletRequest req, List<Integer> movieNumbers) {
		List<String> titles = new ArrayList<>(); // 영화 제목을 담을 리스트

		int[] arr = { 2, 8, 9, 15, 36 };

		for (Integer movieNumber : movieNumbers) {
			String title = movieDao.selectOneMovie(movieNumber); // 영화 제목 가져오기
			titles.add(title); // 리스트에 추가
		}

		req.setAttribute("titles", titles); // 영화 제목 리스트를 request에 저장
	}

	private void MoviesAddress(HttpServletRequest req, List<Integer> movieNumbers) throws SQLException, IOException {
		Map<String, List<imgLocationObject>> movieLocationsMap = new HashMap<>();

		int[] arr = { 2, 8, 9, 15, 36 };

		for (Integer movieNumber : movieNumbers) {
			List<imgLocationObject> locationsList = new ArrayList<>();
			List<Location> locations = movieDao.selectLocationList(movieNumber);

			for (Location location : locations) {
				List<String> singleImageData = new ArrayList<>();
				List<String> singleAddressData = new ArrayList<>();
				List<Integer> location_no = new ArrayList<>();

				Blob blob = location.getImage();
				InputStream inputStream = blob.getBinaryStream();
				byte[] bytes = new byte[(int) blob.length()];
				inputStream.read(bytes);

				singleImageData.add(Base64.getEncoder().encodeToString(bytes));
				singleAddressData.add(location.getAddress());
				location_no.add(location.getLocation_no());

				locationsList.add(new imgLocationObject(singleImageData, singleAddressData, location_no));
			}

			movieLocationsMap.put(movieDao.selectOneMovie(movieNumber), locationsList);
		}

		req.setAttribute("movieLocations", movieLocationsMap);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String movieNumbersJson = req.getParameter("movieNumbers");
		ObjectMapper mapper = new ObjectMapper();

		try {
			JsonNode rootNode = mapper.readTree(movieNumbersJson);
			JsonNode movieNumbersNode = rootNode.get("movieNumbers");
			List<Integer> movieNumbers = new ArrayList<>();

			if (movieNumbersNode.isArray()) {
				for (JsonNode movieNumberNode : movieNumbersNode) {
					movieNumbers.add(movieNumberNode.asInt());
				}
			}
			System.out.println(movieNumbers);
			MoviesTitle(req, movieNumbers);
			MoviesAddress(req, movieNumbers);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("/WEB-INF/selectMovie.jsp").forward(req, resp);
	}
>>>>>>> branch 'master' of https://github.com/Psh230412/FirstWeb.git
}