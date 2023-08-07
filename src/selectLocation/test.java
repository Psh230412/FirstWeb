package selectLocation;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/flow")
public class test extends HttpServlet {

	// 영화 3개의 title
	private void MoviesTitle(HttpServletRequest req) {
		movieDao movieDao = new movieDao(); // movieDao 객체 생성

		List<String> titles = new ArrayList<>(); // 영화 제목을 담을 리스트

		for (int i = 1; i <= 5; i++) {
			String title = movieDao.selectOneMovie(i); // 영화 제목 가져오기
			titles.add(title); // 리스트에 추가
		}

		req.setAttribute("titles", titles); // 영화 제목 리스트를 request에 저장
	}

	// 각 영화당 촬영지,이미지
	private void MoviesAddress(HttpServletRequest req) throws SQLException, IOException {
		movieDao movieDao = new movieDao();
		List<imgLocationObject> list = new ArrayList<>();

		for (int i = 1; i <= 5; i++) {
			List<String> imageData = new ArrayList<>();
			List<String> addressData = new ArrayList<>();
			List<Location> location = movieDao.selectLocationList(i);
			System.out.println(location.size());
			for (int j = 0; j < location.size(); j++) {

				Blob blob = location.get(j).getImage();
				InputStream inputStream = blob.getBinaryStream();
				byte[] bytes = new byte[(int) blob.length()];
				inputStream.read(bytes);

				imageData.add(Base64.getEncoder().encodeToString(bytes));
			}
			list.add(new imgLocationObject(imageData, addressData));
		}
		req.setAttribute("locations", list);
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