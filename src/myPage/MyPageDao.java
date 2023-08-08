package myPage;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.taglibs.standard.extra.spath.Path;

import dbutil.DBUtil;
import object.MyPath;
import object.SelectPath;

public class MyPageDao {

	public int getUserNo(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		int no = -1;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM user WHERE id = ?");
			stmt.setString(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				no = rs.getInt("userno");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return no;
	}

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

	public List<MyPath> getMyPath(int userno) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<MyPath> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT *\r\n" + "					FROM path\r\n"
					+ "					WHERE user_choice_no = (SELECT user_choice_no\r\n"
					+ "					FROM user_choice\r\n" + "					WHERE userno = ?)");
			stmt.setInt(1, userno);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int location1 = rs.getInt("location1");
				int location2 = rs.getInt("location2");
				int location3 = rs.getInt("location3");
				int location4 = rs.getInt("location4");
				Blob pathMapImage = rs.getBlob("pathMapImage");

				String locationAddress1 = getAddress(location1, conn);
				String locationAddress2 = getAddress(location2, conn);
				String locationAddress3 = getAddress(location3, conn);
				String locationAddress4 = getAddress(location4, conn);

				list.add(new MyPath(locationAddress1, locationAddress2, locationAddress3, locationAddress4,
						pathMapImage));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return list;
	}

	public String getAddress(int location, Connection conn) {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String address = "";

		try {
			stmt = conn.prepareStatement("SELECT location_no, address\r\n" + "						FROM location\r\n"
					+ "						WHERE location_no = ?");
			stmt.setInt(1, location);
			rs = stmt.executeQuery();

			if (rs.next()) {
				address = rs.getString("address");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
		}
		return address;
	}
}