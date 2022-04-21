package controller;

import java.io.IOException;
import dao.*;
import vo.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteCashBookController
 */
@WebServlet("/DeleteCashBookController")
public class DeleteCashBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cashbookNo=Integer.parseInt(request.getParameter("cashbookNo"));
		System.out.println(cashbookNo+"Delete");
		
		Cashbook cashbook = new Cashbook();
		cashbook.setCashbookNo(cashbookNo);
		CashbookDao cashbookdao=new CashbookDao();
		
		int row=cashbookdao.deleteCashbook(cashbook);

		
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
