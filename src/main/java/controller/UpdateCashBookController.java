package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.*;
import dao.*;

/**
 * Servlet implementation class UpdateCashBookController
 */
@WebServlet("/UpdateCashBookController")
public class UpdateCashBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cashbookNo=Integer.parseInt(request.getParameter("cashbookNo"));
		
		request.setAttribute("cashbookNo", cashbookNo);
		
		request.getRequestDispatcher("WEB-INF/view/updateCashbookForm.jsp").forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int cashbookNo=Integer.parseInt(request.getParameter("cashbookNo"));
		String cashDate=request.getParameter("cashDate");
		String kind=request.getParameter("kind");
		int cash=Integer.parseInt(request.getParameter("cash"));
		String memo= request.getParameter("memo");
		
		System.out.println(cashbookNo+"Update");
		System.out.println(cashDate+"Update");
		System.out.println(kind+"Update");
		System.out.println(cash+"Update");
		System.out.println(memo+"Update");
		
		Cashbook cashbook = new Cashbook();
		cashbook.setCashbookNo(cashbookNo);
		cashbook.setCashDate(cashDate);
		cashbook.setKind(kind);
		cashbook.setCash(cash);
		cashbook.setMemo(memo);
		
		CashbookDao cashbookdao=new CashbookDao();
		int row=cashbookdao.updateCashBook(cashbook);
		if(row==1) {
			System.out.println("수정 성공");
		}else {
			System.out.println("수정 실패");
		}
		
		
		
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
	}

}
