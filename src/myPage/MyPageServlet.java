package myPage;

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

import object.MyPath;

@WebServlet("/mypage")
public class MyPageServlet extends HttpServlet {
	MyPageDao dao = new MyPageDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("loggedUserId");
		String nickname = (String) session.getAttribute("loggedUserNickname");
		String profileImg = (String) session.getAttribute("loggedUserProfileImg");
		
		req.setAttribute("nickname", nickname);
		req.setAttribute("porfileImg", profileImg);
		
		int userno = dao.getUserNo(id);
		System.out.println(userno);
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
			return;
		}
		resp.sendRedirect("/ScreenSceneP/mypagemodify");
	}
}
