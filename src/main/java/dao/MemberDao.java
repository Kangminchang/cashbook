package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.*;
import vo.Member;

public class MemberDao {

	//회원 가입
	public void insertMemberId(Member m) {
		int row=0;
		String memberId=null;
		Connection conn=null;
		PreparedStatement stmt =null;
		ResultSet rs=null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); //자동으로 커밋되는 것을 막는 코드 
			String sql="INSERT INTO member(member_id,member_pw,create_date) VALUES(?,PASSWORD(?),NOW())";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1,m.getMemberId());
			stmt.setString(2, m.getMemberPw());
		    row=stmt.executeUpdate();
		    conn.commit();
		    if(row==1) {
		    	System.out.println("회원가입 성공");
		    }else {
		    	System.out.println("회원가입 실패");
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
			conn.setAutoCommit(false); //자동으로 커밋되는 것을 막는 코드 
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
	
	//회원 수정
	public void updateMember(Member member) {
		Connection conn=null;
		PreparedStatement stmt =null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); //자동으로 커밋되는 것을 막는 코드 
			String sql="UPDATE member SET member_pw=PASSWORD(?) WHERE member_id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1,member.getMemberPw());
			stmt.setString(2, member.getMemberId());
			System.out.println(member.getMemberId());
			System.out.println(member.getMemberPw());
			int row =stmt.executeUpdate();
			if(row==1) {
				System.out.println("수정 성공");
			}else {
				System.out.println("수정 실패");
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
	//회원 탈퇴
	public void deleteMember(Member member) {
		Connection conn=null;
		PreparedStatement stmt =null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); //자동으로 커밋되는 것을 막는 코드 
			String sql="DELETE FROM member WHERE member_pw=PASSWORD(?) AND member_id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1,member.getMemberPw());
			stmt.setString(2, member.getMemberId());
			System.out.println(member.getMemberId());
			System.out.println(member.getMemberPw());
			int row =stmt.executeUpdate();
			if(row==1) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
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
	//회원 정보 보기
	public Member selectMemberOne(String memberId) {
		Connection conn=null;
		PreparedStatement stmt =null;
		ResultSet rs=null;
		Member member=null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); //자동으로 커밋되는 것을 막는 코드 
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
