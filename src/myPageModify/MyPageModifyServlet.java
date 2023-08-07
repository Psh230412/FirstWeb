package myPageModify;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import join.JoinDao;
import myPage.MyPageDao;

@WebServlet ("/mypagemodify")
public class MyPageModifyServlet extends HttpServlet {
	MyPageDao dao = new MyPageDao();
	JoinDao joinDao = new JoinDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("loggedUserId");
		String nickname = dao.getNickname(id);
		req.setAttribute("nickname", nickname);
		
		req.getRequestDispatcher("/WEB-INF/mypageModify/mypageModify.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String changeNickname = req.getParameter("changeNickname");
		String changePassword = req.getParameter("changePassword");
		String changePasswordRe = req.getParameter("changePasswordRe");
		
		boolean isJoin = true;
		
		if (!joinDao.duplicateNickname(changeNickname)) {
			req.removeAttribute("joinNicknameError");
		} else {
			req.setAttribute("changeNickname", changeNickname);
			req.setAttribute("changeNicknameError", "같은 닉네임이 존재합니다.");
			isJoin = false;
		}
		
		if (changePassword.equals(changePasswordRe)) {
			req.removeAttribute("joinPasswordError");
		} else {
			req.setAttribute("changePassword", changePassword);
			req.setAttribute("changePasswordRe", changePasswordRe);
			req.setAttribute("changePasswordError", "입력한 비밀번호가 서로 다릅니다.");
			isJoin = false;
		}
		
//		if (isJoin) {
//			dao.updateUserInfo(changeNickname, changePassword);
//		}
	}
}
