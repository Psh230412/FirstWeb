package myPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import object.Location;
import object.MyPath;
import object.SelectPath;

@WebServlet("/mypath")
public class MyPathServlet extends HttpServlet{


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("요청왔음");
		
		String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

	    // Gson을 사용하여 requestBody 객체를 SelectPath 객체로 변환
	    Gson gson = new Gson();
	    MyPath path = gson.fromJson(requestBody, MyPath.class); // JSON 객체를 문자열로 변환
	    System.out.println(path);
		
		List<Location> locationList = new ArrayList<>();
		
		locationList.add(path.getLocationAddress1());
		locationList.add(path.getLocationAddress2());
		locationList.add(path.getLocationAddress3());
		locationList.add(path.getLocationAddress4());
		
		req.setAttribute("locationList", locationList);
		req.getRequestDispatcher("/WEB-INF/mypage/onePathMap.jsp").forward(req, resp);
	}
}
