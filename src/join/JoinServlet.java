package join;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JoinDao joinDao = new JoinDao();
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String nickname = req.getParameter("nickname");

		if (joinDao.duplicateId(id)) {
			
		} else {
		}

		if (joinDao.duplicateNickname(nickname)) {
			System.out.println("닉네임 통과");
		} else {
		}

		req.getRequestDispatcher("/WEB-INF/join.jsp").forward(req, resp);
	}
}