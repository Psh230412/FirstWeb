package path;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import object.SelectPath;

@WebServlet("/selectpath")
public class PathServlet extends HttpServlet {
	SelectPathDAO selectPathDao = new SelectPathDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getParameter("selectedLocationNos"));
//		SelectPath path1 = (SelectPath) req.getAttribute("path1");
//		SelectPath path2 = (SelectPath) req.getAttribute("path2");
//		SelectPath path3 = (SelectPath) req.getAttribute("path3");
		SelectPath path1 = new SelectPath(1, 10, 36, 54, 70, null);
		SelectPath path2 = new SelectPath(1, 84, 93, 110, 136, null);
		SelectPath path3 = new SelectPath(1, 156, 164, 197, 221, null);
		SelectPath[] paths = { path1, path2, path3 };

		List<ViewPath> viewPaths = selectPathDao.getViewPathArr(paths);

		req.setAttribute("viewPaths", viewPaths);
		req.getRequestDispatcher("/WEB-INF/selectpathpage/selectpath.jsp").forward(req, resp);

	}

}
