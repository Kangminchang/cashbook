package dao;

import java.sql.*;
import vo.*;
public class StatsDao {
	public void insertStats() { // <-- Listener
		 // INSERT INTO stats(day, cnt) VALUES(CURDATE(), 1)
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); //자동으로 커밋되는 것을 막는 코드 
			String sql="INSERT INTO stats(day, cnt) VALUES(CURDATE(), 1)";
			stmt=conn.prepareStatement(sql);
			int row=stmt.executeUpdate();
			if(row==1) {
				System.out.println("입력 성공");
			}else {
				System.out.println("입력 실패");
			}
			conn.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public Stats selectStatsOneByNow() { // <-- Listner, Controller
		// SELECT day,cnt FROM stats WHERE DAY = CURDATE()
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Stats stats=null;
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); //자동으로 커밋되는 것을 막는 코드 
			String sql="SELECT day,cnt FROM stats WHERE DAY = CURDATE()";
			stmt=conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			while(rs.next()) {
				stats=new Stats();
				stats.setDay(rs.getString("day"));
				stats.setCnt(rs.getInt("cnt"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();rs.close();stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return stats;
	}
	
	public void updateStatsByNow() { // <-- Listener
		// UPDATE stats SET cnt = cnt+1 WHERE DAY = CURDATE()
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); //자동으로 커밋되는 것을 막는 코드 
			String sql="UPDATE stats SET cnt = cnt+1 WHERE DAY = CURDATE()";
			stmt=conn.prepareStatement(sql);
			int row=stmt.executeUpdate();
			if(row==1) {
				System.out.println("수정 성공");
			}else {
				System.out.println("수정 실패");
			}
			conn.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public int selectStatsTotalCount() { // <-- Controller
		// SELECT SUM(cnt) from stats 
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		int totalCount=0;
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); //자동으로 커밋되는 것을 막는 코드 
			String sql="SELECT SUM(cnt) from stats ";
			stmt=conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			while(rs.next()) {
				totalCount=rs.getInt("SUM(cnt)");
			}
			conn.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();rs.close();stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return totalCount;
	}
}
