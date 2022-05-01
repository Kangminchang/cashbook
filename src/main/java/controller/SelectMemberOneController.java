package controller;

import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.Member;
import dao.MemberDao;
import java.util.*;

/**
 * Servlet implementation class SelectMemberOneController
 */
@WebServlet("/SelectMemberOneController")
public class SelectMemberOneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession();
		String sessionMemberId=(String)session.getAttribute("sessionMemberId");
		if(sessionMemberId==null) {
			//로그인이 안되어있는 상태라면
			response.sendRedirect(request.getContextPath()+"/LoginController");
		}
		System.out.println(sessionMemberId);
		
		
		MemberDao memberdao =new MemberDao();
		Member member = memberdao.selectMemberOne(sessionMemberId);
		String memberId =member.getMemberId();
		String createDate =member.getCreateDate();
		System.out.println(memberId+"  "+createDate);
		request.setAttribute("memberId", memberId);
		request.setAttribute("createDate",createDate);
		request.getRequestDispatcher("WEB-INF/view/memberOne.jsp").forward(request, response);
		
	}



}
