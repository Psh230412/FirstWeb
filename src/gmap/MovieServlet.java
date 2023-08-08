package gmap;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dbutil.DBUtil;


@WebServlet("/movie")
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ObjectMapper objectMapper = new ObjectMapper();
	gmap_Movie_DAO choice_DAO = new gmap_Movie_DAO();
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {		
			conn = DBUtil.getConnection();
			
			String body = choice_DAO.getRequestBody(request);
	        JsonNode jsonNode = objectMapper.readTree(body);
	        
	     
	        int moviesListChildrenLength = jsonNode.get("length").asInt();
	        
			
	        
			String sql = "SELECT * FROM movie LIMIT ?, 12";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, moviesListChildrenLength);
			rs = pstmt.executeQuery();
			
			
			choice_DAO.sendResponse(rs, objectMapper, response,conn);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
			
		}
		
	
	}

}
