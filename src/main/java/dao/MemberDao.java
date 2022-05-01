package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.*;
import vo.Member;

public class MemberDao {

	//회원 목록
	public void insertMemberId(Member m) {
		int row=0;
		String memberId=null;
		Connection conn=null;
		PreparedStatement stmt =null;
		ResultSet rs=null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); //자동으로 커밋 방지
			String sql="INSERT INTO member(member_id,member_pw,create_date) VALUES(?,PASSWORD(?),NOW())";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1,m.getMemberId());
			stmt.setString(2, m.getMemberPw());
		    row=stmt.executeUpdate();
		    conn.commit();
		    if(row==1) {
		    	System.out.println("입력 성공");
		    }else {
		    	System.out.println("입력 실패");
		    }
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			}catch(SQLException e) {
			}
		}
		
	}
	public String selectMemberByIdPw(Member member) {
		String memberId=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); //�옄�룞�쑝濡� 而ㅻ컠�릺�뒗 寃껋쓣 留됰뒗 肄붾뱶 
			String sql="SELECT member_id memberId FROM member WHERE member_id=? AND member_pw=PASSWORD(?)";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			rs=stmt.executeQuery();
			if(rs.next()) {
				memberId=rs.getString("memberId");
			}
		} catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return memberId;
	   }
	
	//�쉶�썝 �닔�젙
	public void updateMember(Member member) {
		Connection conn=null;
		PreparedStatement stmt =null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); //�옄�룞�쑝濡� 而ㅻ컠�릺�뒗 寃껋쓣 留됰뒗 肄붾뱶 
			String sql="UPDATE member SET member_pw=PASSWORD(?) WHERE member_id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1,member.getMemberPw());
			stmt.setString(2, member.getMemberId());
			System.out.println(member.getMemberId());
			System.out.println(member.getMemberPw());
			int row =stmt.executeUpdate();
			if(row==1) {
				System.out.println("�닔�젙 �꽦怨�");
			}else {
				System.out.println("�닔�젙 �떎�뙣");
			}
			conn.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				conn.close();
			}catch(SQLException e) {
				
			}
		}
	}
	//�쉶�썝 �깉�눜
	public void deleteMember(Member member) {
		Connection conn=null;
		PreparedStatement stmt =null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); //�옄�룞�쑝濡� 而ㅻ컠�릺�뒗 寃껋쓣 留됰뒗 肄붾뱶 
			String sql="DELETE FROM member WHERE member_pw=PASSWORD(?) AND member_id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1,member.getMemberPw());
			stmt.setString(2, member.getMemberId());
			System.out.println(member.getMemberId());
			System.out.println(member.getMemberPw());
			int row =stmt.executeUpdate();
			if(row==1) {
				System.out.println("�궘�젣 �꽦怨�");
			}else {
				System.out.println("�궘�젣 �떎�뙣");
			}
			conn.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				conn.close();
			}catch(SQLException e) {
				
			}
		}
	}
	//�쉶�썝 �젙蹂� 蹂닿린
	public Member selectMemberOne(String memberId) {
		Connection conn=null;
		PreparedStatement stmt =null;
		ResultSet rs=null;
		Member member=null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); //�옄�룞�쑝濡� 而ㅻ컠�릺�뒗 寃껋쓣 留됰뒗 肄붾뱶 
			String sql="SELECT member_id memberId,create_date createDate FROM member WHERE member_id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			rs=stmt.executeQuery();
			while(rs.next()) {
				member= new Member();
				member.setMemberId(rs.getString("memberId"));
				member.setCreateDate(rs.getString("createDate"));
			}
			conn.commit();
		} catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
		return member;
	}
	
}
