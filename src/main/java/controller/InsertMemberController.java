package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.Member;
import dao.MemberDao;

/**
 * Servlet implementation class InsertMemberController
 */
@WebServlet("/InsertMemberController")
public class InsertMemberController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sessionMemberId=(String)session.getAttribute("sessionMemberId");
		if(sessionMemberId!=null) {
			//이미 로그인이 되어 있는 상태
			response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
		}
		request.getRequestDispatcher("WEB-INF/view/InsertMember.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId =request.getParameter("memberId");
		String memberPw=request.getParameter("memberPw");
		
		Member member =new Member();
	
		if(memberId!=null && memberPw!=null) {
			member.setMemberId(memberId);
			member.setMemberPw(memberPw);
		}else {
			response.sendRedirect(request.getContextPath()+"/InsertMemberController");
		}
		MemberDao memberdao =new MemberDao();
		memberdao.insertMemberId(member);
		
		
		response.sendRedirect(request.getContextPath()+"/LoginController");

		
		
		
	}
}
