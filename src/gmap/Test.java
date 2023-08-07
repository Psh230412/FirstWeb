package gmap;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.mysql.cj.protocol.x.ReusableOutputStream;

import dbutil.DBUtil;

public class Test {

//	public static AtomicInteger counter = new AtomicInteger(0);

	public static void main(String[] args) {
//		List<Distance> list = DistanceCalculator.distanceCalculate( "34.4656639099121", "-118.405334472656");
//		
//		int avail = Runtime.getRuntime().availableProcessors();
//		System.out.println("코어의 갯수"+avail);
//		System.out.println("count of distances: "+list.size());
////		System.out.println(list.contains(0));
//		
//		for(int i=0;i<list.size();i++) {
//			
//			System.out.println(list.get(i));
//		}
//		System.out.println(list.get(0));
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT count(*) as cnt FROM movie.location where movie_no=5;");
			
			if (conn != null) {
		        if (rs.next()) {
		            System.out.println("The connection is active.");
		            System.out.println(rs.getInt("cnt"));
		        }
		    } else {
		        System.out.println("The connection is null.");
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
