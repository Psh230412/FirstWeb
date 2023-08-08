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

@WebServlet("/mypagemodify")
public class MyPageModifyServlet extends HttpServlet {
	MyPageDao dao = new MyPageDao();
	MyPageModifyDao modifyDao = new MyPageModifyDao();

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
		String form = req.getParameter("form_type");
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("loggedUserId");
		boolean isJoin = true;

		if (form.equals("changeNicknameForm")) {
			String changeNickname = req.getParameter("changeNickname");
			String checkPassword = req.getParameter("passwordCheck");
			String nowNickname = modifyDao.getNickname(id);

			if (!modifyDao.duplicateNickname(changeNickname)) {
				req.removeAttribute("changeNicknameError");
			} else if (nowNickname.equals(changeNickname)) {
				req.setAttribute("changeNicknameError", "현재 닉네임과 동일한 닉네입입니다");
				isJoin = false;
			} else {
				req.setAttribute("changeNickname", changeNickname);
				req.setAttribute("changeNicknameError", "같은 닉네임이 존재합니다.");
				isJoin = false;
			}

			if (modifyDao.checkPassword(id, checkPassword)) {
				req.removeAttribute("failCheckPassword");
			} else {
				req.setAttribute("failCheckPassword", "비밀번호를 틀렸습니다. 다시 확인해주세요.");
				isJoin = false;
			}

			if (isJoin) {
				modifyDao.updateUserNickname(nowNickname, changeNickname);
			}
		}

		req.getRequestDispatcher("/WEB-INF/mypageModify/mypageModify.jsp").forward(req, resp);

//		String changePassword = req.getParameter("changePassword");
//		String changePasswordRe = req.getParameter("changePasswordRe");
//		
//		
//		
//		
//		if (changePassword.equals(changePasswordRe)) {
//			req.removeAttribute("joinPasswordError");
//		} else {
//			req.setAttribute("changePassword", changePassword);
//			req.setAttribute("changePasswordRe", changePasswordRe);
//			req.setAttribute("changePasswordError", "입력한 비밀번호가 서로 다릅니다.");
//			isJoin = false;
//		}

//		if (isJoin) {
//			dao.updateUserInfo(changeNickname, changePassword);
//		}
	}
}
