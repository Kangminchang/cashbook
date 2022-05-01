package controller;

import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.*;
import vo.*;

/**
 * Servlet implementation class UpdateMemberPwController
 */
@WebServlet("/UpdateMemberPwController")
public class UpdateMemberPwController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId==null) {
			//로그인이 안되어 있다면
			response.sendRedirect(request.getContextPath()+"/LogOutController");
			return;
		}

		request.setAttribute(sessionMemberId,"sessionMemberId");
		request.getRequestDispatcher("WEB-INF/view/UpdateMemberPw.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId=request.getParameter("memberId");
		String memberPw=request.getParameter("memberPw");
		
		System.out.println(memberId+" "+ memberPw);
		
		Member member =new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		MemberDao memberdao = new MemberDao();
		
		memberdao.updateMember(member);
		response.sendRedirect(request.getContextPath()+"/LogOutController");
		
	}

}
