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

import CRUD.MovieDAO;
import object.Location;
import object.imgLocationObject;

@WebServlet("/flow")
public class test extends HttpServlet {

	// 영화 3개의 title
	private void MoviesTitle(HttpServletRequest req) {
		MovieDAO movieDao = new MovieDAO(); // movieDao 객체 생성

		List<String> titles = new ArrayList<>(); // 영화 제목을 담을 리스트

		int[] arr = { 2, 8, 9, 15, 36 };

		for (int i = 0; i < 5; i++) {
			String title = movieDao.selectOneMovie(arr[i]); // 영화 제목 가져오기
			titles.add(title); // 리스트에 추가
		}

		req.setAttribute("titles", titles); // 영화 제목 리스트를 request에 저장
	}

	private void MoviesAddress(HttpServletRequest req) throws SQLException, IOException {
		MovieDAO movieDao = new MovieDAO();
		Map<String, List<imgLocationObject>> movieLocationsMap = new HashMap<>();

		int[] arr = { 2, 8, 9, 15, 36 };

		for (int i = 0; i < 5; i++) {
			List<imgLocationObject> locationsList = new ArrayList<>();
			List<Location> locations = movieDao.selectLocationList(arr[i]);

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

			movieLocationsMap.put(movieDao.selectOneMovie(i), locationsList);
		}

		req.setAttribute("movieLocations", movieLocationsMap);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MoviesTitle(req);
		try {
			MoviesAddress(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		req.getRequestDispatcher("/WEB-INF/selectMovie.jsp").forward(req, resp);
	}
}