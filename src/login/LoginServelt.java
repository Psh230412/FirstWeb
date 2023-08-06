package login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServelt extends HttpServlet {
	LoginDao dao = new LoginDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/loginPage/login.html").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		boolean remember = false;

		if (req.getParameter("remember") != null) {
			remember = req.getParameter("remember").equals("check");
		}
		if (dao.checkId(id)) {
			if (dao.checkPassword(id, password)){
				System.out.println(remember);
				if (remember) {
					Cookie cookie = new Cookie("remember", id);
					resp.addCookie(cookie);
				}
				System.out.println("다음화면으로");
			} else {
				req.setAttribute("loginError", "아이디 또는 비밀번호를 틀리셨습니다.");
			}
		} else {
			req.setAttribute("loginError", "아이디 또는 비밀번호를 틀리셨습니다.");
		}
		req.getRequestDispatcher("/WEB-INF/loginPage/login.html").forward(req, resp);
	}
}
