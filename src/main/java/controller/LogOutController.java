package controller;

import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogOut
 */
@WebServlet("/LogOutController")
public class LogOutController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("로그아웃 성공");
		request.getSession().invalidate(); //session갱신 메서드 : 기존 세션을 지우고, 새로운 세션 공간을 부여
		/*
		 HttpSession =request.getSession();
		 session.invalidate()
		 */
		response.sendRedirect(request.getContextPath()+"/LoginController");
	}



}
