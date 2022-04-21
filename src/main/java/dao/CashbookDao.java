package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vo.Cashbook;

public class CashbookDao {
	//�닔�젙1
	public int updateCashBook(Cashbook c) {
		List<Cashbook> list =new ArrayList<>();
		
		
		int row=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); //�옄�룞�쑝濡� 而ㅻ컠�릺�뒗 寃껋쓣 留됰뒗 肄붾뱶 
			//�궘�젣 �떆 �쇅�옒�궎 �삤瑜섎줈 �씤�빐 hashtag �뀒�씠釉� 癒쇱� �궘�젣
			String sql="UPDATE cashbook SET cash_date=?,kind=?,cash=?,memo=? WHERE cashbook_no=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, c.getCashDate());
			stmt.setString(2, c.getKind());
			stmt.setInt(3, c.getCash());
			stmt.setString(4, c.getMemo());
			stmt.setInt(5, c.getCashbookNo());
			row=stmt.executeUpdate();
			conn.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(SQLException e) {
			}
		}
		return row;
	}
	//�궘�젣
	public int deleteCashbook(Cashbook c) {
		int row=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); //�옄�룞�쑝濡� 而ㅻ컠�릺�뒗 寃껋쓣 留됰뒗 肄붾뱶 
			//�궘�젣 �떆 �쇅�옒�궎 �삤瑜섎줈 �씤�빐 hashtag �뀒�씠釉� 癒쇱� �궘�젣
			String sql="DELETE FROM hashtag WHERE cashbook_no=?";
			stmt=conn.prepareStatement(sql);
			stmt.setInt(1, c.getCashbookNo());
			row=stmt.executeUpdate();
			conn.commit();
			//cashbook �뜲�씠�꽣 �궘�젣
			String sql2="DELETE FROM cashbook WHERE cashbook_no=?";
			stmt=conn.prepareStatement(sql2);
			stmt.setInt(1, c.getCashbookNo());
			row=stmt.executeUpdate();
			conn.commit();
			if(row==1) {
				System.out.println("�궘�젣 �꽦怨�");
				
			}else {
				System.out.println("�궘�젣 �떎�뙣");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(SQLException e) {
				
			}
		}
		return row;					
		
	}
	//�긽�꽭蹂닿린
	public List<Cashbook> selectCashbookOne(Cashbook c){
		List<Cashbook> list= new ArrayList<Cashbook>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); //�옄�룞�쑝濡� 而ㅻ컠�릺�뒗 寃껋쓣 留됰뒗 肄붾뱶 
			String sql="SELECT cashbook_no cashbookNo,cash_date cashDate,kind,cash,memo,update_date updateDate,create_date createDate FROM cashbook WHERE cashbook_no=?";
			stmt=conn.prepareStatement(sql);
			stmt.setInt(1, c.getCashbookNo());
			rs=stmt.executeQuery();
			while(rs.next()) {
				Cashbook cashbook = new Cashbook();
				cashbook.setCashbookNo(rs.getInt("cashbookNo"));
				cashbook.setCashDate(rs.getString("cashDate"));
				cashbook.setKind(rs.getString("kind"));
				cashbook.setCash(rs.getInt("cash"));
				cashbook.setMemo(rs.getString("memo"));
				cashbook.setUpdateDate(rs.getString("updateDate"));
				cashbook.setCreateDate(rs.getString("createDate"));
				list.add(cashbook);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	//�뜲�씠�꽣 �엯�젰
	public void insertCashbook(Cashbook c,List<String> hashtag) {
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO cashbook(cash_date,kind,cash,memo,update_date,create_date) VALUES(?,?,?,?,NOW(),NOW())";
		try {
			Class.forName("o-rg.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); //�옄�룞�쑝濡� 而ㅻ컠�릺�뒗 寃껋쓣 留됰뒗 肄붾뱶 
			//諛⑷툑 �깮�꽦�맂 �뻾�쓽 �궎媛믪쓣 select ex)select 諛⑷툑�엯�젰�븳 cashbook_no from cashbook
			stmt = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);//�몢踰덉㎏ 留ㅺ컻蹂��닔 �엯�젰�떆 insert臾� 肉먯씠�븘�땲�씪 selec臾몃룄 寃��깋�븳�떎.
			stmt.setString(1,c.getCashDate());
			stmt.setString(2, c.getKind());
			stmt.setInt(3,c.getCash());
			stmt.setString(4,c.getMemo());
			stmt.executeUpdate(); //Insert
			rs =stmt.getGeneratedKeys(); //Select 諛⑷툑�엯�젰�븳 cashbook_no
			int cashbookNo=0;
			if(rs.next()) {
				cashbookNo=rs.getInt(1);
			}
		
			//hashtag瑜� �엯�젰�븯�뒗 肄붾뱶
			for(String h: hashtag) { // hashtag媛믪씠 議댁옱�븷�븣留�
				PreparedStatement stmt2 =null;
				String sql2="INSERT INTO hashtag(cashbook_no,tag,create_date) VALUES(?,?,NOW())";
				stmt2=conn.prepareStatement(sql2);
				stmt2.setInt(1, cashbookNo);
				stmt2.setString(2, h); //hashtag (List)
				stmt2.executeUpdate();
			}
			conn.commit();
		}catch (Exception e) {
			try {
				conn.rollback();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public List<Map<String, Object>> selectCashbookListByMonth(int y, int m) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		/*
		 SELECT 
		 	cashbook_no cashbookNo
		 	,DAY(cash_date) day
		 	,kind
		 	,cash
		 	,LEFT(memo, 5) memo
		 FROM cashbook
		 WHERE YEAR(cash_date) = ? AND MONTH(cash_date) = ?
		 ORDER BY DAY(cash_date) ASC, kind ASC
		 */
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT"
				+ "		 	cashbook_no cashbookNo"
				+ "		 	,DAY(cash_date) day"
				+ "		 	,kind"
				+ "		 	,cash"
				+ "			,LEFT(memo, 5) memo"
				+ "		 FROM cashbook"
				+ "		 WHERE YEAR(cash_date) = ? AND MONTH(cash_date) = ?"
				+ "		 ORDER BY DAY(cash_date) ASC, kind ASC";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, y);
			stmt.setInt(2, m);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cashbookNo", rs.getInt("cashbookNo"));
				map.put("day", rs.getInt("day"));
				map.put("kind", rs.getString("kind"));
				map.put("cash", rs.getInt("cash"));
				map.put("memo", rs.getString("memo"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
