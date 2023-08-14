package movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/movie")
public class MovieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	HttpSession session = req.getSession();
	String nickname = (String) session.getAttribute("loggedUserNickname");
	String profileImg = (String) session.getAttribute("loggedUserProfileImg");

	req.setAttribute("nickname", nickname);
	req.setAttribute("porfileImg", profileImg);
	req.getRequestDispatcher("./WEB-INF/moviePage/movie.jsp").forward(req, resp);
    }
}
