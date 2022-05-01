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
import vo.Stats;
import dao.MemberDao;
import dao.StatsDao;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	//doGet-> get방식으로 요청
	private StatsDao statsDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//생성된 session에서 값을 받아옴
		HttpSession session = request.getSession();
		String sessionMemberId=(String)session.getAttribute("sessionMemberId");
		this.statsDao = new StatsDao();
		Stats stats = statsDao.selectStatsOneByNow();
		int totalCount = statsDao.selectStatsTotalCount();
		
		request.setAttribute("stats", stats);
		request.setAttribute("totalCount", totalCount);
		
		request.getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(request, response);
		if(sessionMemberId!=null) {
			//�씠誘� 濡쒓렇�씤�씠 �릺�뼱 �엳�뒗 �긽�깭
			response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
		}
		request.getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(request, response);
		
	}
	//doPost->濡쒓렇�씤 �븸�뀡
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//濡쒓렇�씤�씠 �릺�뼱�엳�뒗 硫ㅻ쾭�씪硫� 由щ떎�씠�젆�듃
		String memberId=request.getParameter("memberId");
		String memberPw=request.getParameter("memberPw");
		Member member=new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		MemberDao memberdao=new MemberDao();
		
		String returnMemberId=memberdao.selectMemberByIdPw(member);
		if(returnMemberId==null) {
			//로그인이 되어있지 않다면
			System.out.println("로그인 중");
			response.sendRedirect(request.getContextPath()+"/LoginController");
		
			return;
		}
		//session
		HttpSession session = request.getSession(); //session
		session.setAttribute("sessionMemberId", returnMemberId);
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
		
	}

}
