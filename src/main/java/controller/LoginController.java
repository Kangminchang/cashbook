package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

import java.sql.*;
import java.util.*;
import dao.CashbookDao;
import vo.Member;
import dao.MemberDao;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	//doGet-> 로그인 폼]
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인이 되어있는 멤버라면 리다이렉트
		HttpSession session = request.getSession();
		String sessionMemberId=(String)session.getAttribute("sessionMemberId");
		if(sessionMemberId!=null) {
			//이미 로그인이 되어 있는 상태
			response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
		}
		request.getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(request, response);
		
	}
	//doPost->로그인 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인이 되어있는 멤버라면 리다이렉트
		String memberId=request.getParameter("memberId");
		String memberPw=request.getParameter("memberPw");
		Member member=new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		MemberDao memberdao=new MemberDao();
		
		String returnMemberId=memberdao.selectMemberByIdPw(member);
		if(returnMemberId==null) {
			//로그인 실패
			System.out.println("로그인 실패");
			response.sendRedirect(request.getContextPath()+"/LoginController");
		
			return;
		}
		//로그인 성공
		HttpSession session = request.getSession(); //현재 연결한 클라이언트
		session.setAttribute("sessionMemberId", returnMemberId);
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
		
	}

}
