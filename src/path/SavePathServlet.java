package path;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import object.SelectPath;

@WebServlet("/savePath")
public class SavePathServlet extends HttpServlet {
	SelectPathDAO dao = new SelectPathDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("서블릿 연결 성공");

	    String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

	    // Gson을 사용하여 requestBody 객체를 SelectPath 객체로 변환
	    Gson gson = new Gson();
	    SelectPath selectPath = gson.fromJson(requestBody, SelectPath.class); // JSON 객체를 문자열로 변환

	    System.out.println(selectPath);

	    int result = dao.insertSelectedPath(selectPath);
	    System.out.println(result);
	    resp.sendRedirect("http://localhost:8080/ScreenSceneP/main/index.html");
//	    if (result == 1) {
//	    	
//	    } else {
//	    	System.out.println("경로 저장 실패");
//	    }
	}

}
