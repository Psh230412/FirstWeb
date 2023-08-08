package myPageModify;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
		Blob profile = dao.getProfile(id);

		if (profile != null) {
			InputStream inputStream;
			try {
				inputStream = profile.getBinaryStream();
				byte[] bytes = new byte[(int) profile.length()];
				inputStream.read(bytes);
				String img = Base64.getEncoder().encodeToString(bytes);
				req.setAttribute("myProfile", img);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		req.setAttribute("nickname", nickname);

		req.getRequestDispatcher("/WEB-INF/mypageModify/mypageModify.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String form = req.getParameter("form_type");
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("loggedUserId");
		boolean isCheck = true;

		if (form != null) {

			if (form.equals("changeNicknameForm")) {
				String changeNickname = req.getParameter("changeNickname");
				String checkPassword = req.getParameter("passwordCheck");
				String nowNickname = modifyDao.getNickname(id);

				if (!modifyDao.duplicateNickname(changeNickname)) {
					req.removeAttribute("changeNicknameError");
				} else if (nowNickname.equals(changeNickname)) {
					req.setAttribute("changeNicknameError", "현재 닉네임과 동일한 닉네입입니다");
					System.out.println("여기로오니?");
					isCheck = false;
				} else {
					req.setAttribute("changeNickname", changeNickname);
					req.setAttribute("changeNicknameError", "같은 닉네임이 존재합니다.");
					isCheck = false;
				}

				if (modifyDao.checkPassword(id, checkPassword)) {
					req.removeAttribute("failCheckPassword");
				} else {
					req.setAttribute("failCheckPassword", "비밀번호를 틀렸습니다. 다시 확인해주세요.");
					isCheck = false;
				}

				if (isCheck) {
					modifyDao.updateUserNickname(nowNickname, changeNickname);
				}
			}

			if (form.equals("changePasswordForm")) {
				String passwordNow = req.getParameter("passwordNow");
				String passwordChange = req.getParameter("passwordChange");
				String passwordChangeRe = req.getParameter("passwordChangeRe");

				if (modifyDao.checkPassword(id, passwordNow)) {
					req.removeAttribute("failCheckPasswordChange");
				} else {
					req.setAttribute("failCheckPasswordChange", "비밀번호를 틀렸습니다. 다시 확인해주세요.");
					isCheck = false;
				}

				if (passwordChange.equals(passwordChangeRe)) {
					req.removeAttribute("passwordInputError");
				} else {
					req.setAttribute("passwordInputError", "두 비밀번호 입력이 다릅니다. 다시 확인해주세요.");
					isCheck = false;
				}

				if (isCheck) {
					modifyDao.updateUserPassword(passwordNow, passwordChange);
				}
			}
		}

		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (isMultipart) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			try {
				List<FileItem> items;
				items = upload.parseRequest(req);
				for (FileItem item : items) {
					if (!item.isFormField()) {
						// 파일 처리
						InputStream fileContent = item.getInputStream();
						dao.uploadImg(id, fileContent);
					}
				}
				resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
			} catch (FileUploadException e) {
				e.printStackTrace();
			} finally {
			}
		}
		req.getRequestDispatcher("/WEB-INF/mypageModify/mypageModify.jsp").forward(req, resp);
	}
}
