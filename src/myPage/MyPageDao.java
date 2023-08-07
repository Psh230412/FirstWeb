package myPage;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutil.DBUtil;

public class MyPageDao {
	
	public String getNickname(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String nickname = "";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM user WHERE id = ?");
			stmt.setString(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				nickname = rs.getString("nickname");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return nickname;
	}
	
	public List<Path> getMyPath(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT location1\r\n" + 
					"					FROM path\r\n" + 
					"					WHERE user_choice_no = (SELECT user_choice_no\r\n" + 
					"					FROM user_choice\r\n" + 
					"					WHERE userno = 1);");
			stmt.setString(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				String location1 = rs.getString("location1");
				String location2 = rs.getString("location2");
				String location3 = rs.getString("location3");
				String location4 = rs.getString("location4");
				Blob pathMapImage = rs.getBlob("pathMapImage");
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}
}