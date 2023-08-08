package path;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import dbutil.DBUtil;
import object.Location;
import object.SelectPath;

// 경로에 해당하는 영화 포스터, 장소 사진, 상세주소 추출
public class SelectPathDAO {

	public List<ViewPath> getViewPathArr(SelectPath[] paths) {
		Connection conn = null;
		List<ViewPath> viewPathList = new ArrayList<>();
		
		try {
			conn = DBUtil.getConnection();
			for (int i = 0; i < paths.length; i++) {
				List<ViewLocation> viewLocList = new ArrayList<>();
				SelectPath path = paths[i];
				int user_choice_no = path.getUser_choice_no();
				List<Location> locationList = candidatePathLocation(conn, path);

				for (int j = 0; j < locationList.size(); j++) {
					Location location = locationList.get(j);
					
					int locationNo = location.getLocation_no();
					String locationName = location.getAddress();
					String locationImgStr = encodeBlobToStr(location.getImage());
					String posterImgStr = candidateMoviePoster(conn, location.getMovie_no());
					
					ViewLocation viewLoc1 = new ViewLocation(locationNo, locationName, locationImgStr, posterImgStr);
					viewLocList.add(viewLoc1);
				}
				viewPathList.add(new ViewPath(i + 1, user_choice_no, viewLocList));
			}
			return viewPathList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return null;

	}

	public List<Location> candidatePathLocation(Connection conn, SelectPath path) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int location1 = path.getLocation1();
		int location2 = path.getLocation2();
		int location3 = path.getLocation3();
		int location4 = path.getLocation4();
		List<Location> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM Location WHERE location_no IN (?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, location1);
			stmt.setInt(2, location2);
			stmt.setInt(3, location3);
			stmt.setInt(4, location4);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int location_no = rs.getInt("location_no");
				int movie_no = rs.getInt("movie_no");
				String address = rs.getString("address");
				double latitude = rs.getDouble("latitude");
				double longitude = rs.getDouble("longitude");
				Blob image = rs.getBlob("image");
				list.add(new Location(location_no, movie_no, address, latitude, longitude, image));
			}
			return list;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
		}
	}

	// Location에 해당하는 Movie의 포스터를 가져옴
	public String candidateMoviePoster(Connection conn, int movie_no) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT poster FROM movie WHERE movie_no = ?";
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, movie_no);

			rs = stmt.executeQuery();

			while (rs.next()) {
				Blob poster = rs.getBlob("poster");
				return encodeBlobToStr(poster);
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
		}
		return null;
	}

	// 이진 자료(바이트)를 텍스트로 변환(인코딩)
	private String encodeBlobToStr(Blob blob) {
		byte[] imageData = null;
		try {
			int blobLength = (int) blob.length();
			imageData = blob.getBytes(1, blobLength); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String encoded = Base64.getMimeEncoder().encodeToString(imageData);
		return encoded;
	}

	// 결정한 경로를 db에 입력
	public int insertSelectedPath(SelectPath path) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO path (user_choice_no, location1, location2, location3, location4) VALUES (?, ?, ?, ?, ?)";
			stmt= conn.prepareStatement(sql);
			stmt.setInt(1, path.getUser_choice_no());
			stmt.setInt(2, path.getLocation1());
			stmt.setInt(3, path.getLocation2());
			stmt.setInt(4, path.getLocation3());
			stmt.setInt(5, path.getLocation4());
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return -1;
	}

}
