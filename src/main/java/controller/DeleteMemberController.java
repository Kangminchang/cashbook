package controller;

import java.io.IOException;
import vo.Member;
import dao.MemberDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DeleteMemberController
 */
@WebServlet("/DeleteMemberController")
public class DeleteMemberController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sessionMemberId=(String)session.getAttribute("sessionMemberId");
		if(sessionMemberId==null) {
			//로그인이 안되잇다면 
			response.sendRedirect(request.getContextPath()+"/LogOutController");
			return;
		}
		String memberId=request.getParameter("memberId");
		request.setAttribute(memberId,"memberId");
		request.getRequestDispatcher("WEB-INF/view/DeleteMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId =request.getParameter("memberId");
		String memberPw =request.getParameter("memberPw");
		
		Member member=new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		MemberDao memberdao =new MemberDao();
		memberdao.deleteMember(member);
		
		response.sendRedirect(request.getContextPath()+"/LogOutController");
		
	}

}
