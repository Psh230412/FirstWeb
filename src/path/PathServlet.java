package path;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CRUD.MovieDAO;
import dbutil.DBUtil;
import gmap.DistanceCalculator;
import object.Location;

@WebServlet("/selectpath")
public class PathServlet extends HttpServlet {
    SelectPathDAO selectPathDao = new SelectPathDAO();
    MovieDAO movieDao = new MovieDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	Connection conn = null;
	System.out.println(req.getParameter("selectedLocationNos"));
//      SelectPath path1 = (SelectPath) req.getAttribute("path1");
//      SelectPath path2 = (SelectPath) req.getAttribute("path2");
//      SelectPath path3 = (SelectPath) req.getAttribute("path3");
//      SelectPath path1 = new SelectPath(1, 10, 36, 54, 70, null);
//      SelectPath path2 = new SelectPath(1, 84, 93, 110, 136, null);
//      SelectPath path3 = new SelectPath(1, 156, 164, 197, 221, null);
//      SelectPath[] paths = { path1, path2, path3 };

	// 선택된 촬영지 번호

	int[] selectedNos;

	String selectedLocationNos = req.getParameter("selectedLocationNos");

	if (selectedLocationNos != null && !selectedLocationNos.isEmpty()) {
	    String[] numbersString = selectedLocationNos.split(",");
	    selectedNos = new int[numbersString.length];

	    for (int i = 0; i < numbersString.length; i++) {
		selectedNos[i] = Integer.parseInt(numbersString[i].trim());
	    }

//          req.setAttribute("selectedNos", selectedNos);

	}

	// 선택된 영화 번호

	int[] selectedMovies;

	String selectedMoviesString = req.getParameter("selectedMovies");

	if (selectedMoviesString != null && !selectedMoviesString.isEmpty()) {
	    String[] numbersString = selectedMoviesString.split(",");
	    selectedMovies = new int[numbersString.length];

	    for (int i = 0; i < numbersString.length; i++) {
		selectedMovies[i] = Integer.parseInt(numbersString[i].trim());

	    }

	}

	try {
	    conn = DBUtil.getConnection();

	    List<Location> selectedLocation = selectPathDao.getLocationList(conn, selectedNos);
	    List<Location> entireSelectedList = new ArrayList<Location>();

	    for (int i = 0; i < selectedMovies.length; i++) {

		List<Location> entireSelectedListPerMovie = movieDao.selectLocationList(selectedMovies[i]);
		entireSelectedList.addAll(entireSelectedListPerMovie);

	    }

	    List<Location> firstLocationList = DistanceCalculator.getFirstLocation(selectedLocation);
	    List<Location> secondLocationList = DistanceCalculator.getSecondLocation(firstLocationList,
		    entireSelectedList);
	    List<Location> thirdLocationList = DistanceCalculator.getThirdLocation(firstLocationList,
		    secondLocationList, entireSelectedList);

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    DBUtil.close(conn);
	}

//      List<ViewPath> viewPaths = selectPathDao.getViewPathArr(paths);

//      req.setAttribute("viewPaths", viewPaths);
//      req.getRequestDispatcher("/WEB-INF/selectpathpage/selectpath.jsp").forward(req, resp);

    }

}