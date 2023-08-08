package myPage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import object.MyPath;

@WebServlet("/mypage")
public class MyPageServlet extends HttpServlet {
	MyPageDao dao = new MyPageDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("loggedUserId");
		String nickname = dao.getNickname(id);
		req.setAttribute("nickname", nickname);
		int userno = dao.getUserNo(id);

		List<MyPath> list = dao.getMyPath(userno);

		req.setAttribute("list", list);
		req.getRequestDispatcher("./WEB-INF/mypage/mypage.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathNo = req.getParameter("pathPk");
		if (pathNo != null) {
			dao.deletePath(pathNo);
			resp.sendRedirect("/ScreenSceneP/mypage");
		}
		resp.sendRedirect("/ScreenSceneP/mypagemodify");
	}
}
