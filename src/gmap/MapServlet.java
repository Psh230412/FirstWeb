package gmap;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/Map")
public class MapServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Location_DAO m = new Location_DAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	List<Location> mapList = m.getLatLongList();

	ObjectMapper objectMapper = new ObjectMapper();
	String json = objectMapper.writeValueAsString(mapList);
	response.setContentType("application/json");
	response.setCharacterEncoding("UTF-8");
	
	request.setAttribute("mapList", json);
	request.getRequestDispatcher("NewFile.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
    }

}
