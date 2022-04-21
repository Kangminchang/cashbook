package controller;
import java.util.*;
import dao.CashbookDao;
import vo.Cashbook;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class CashbookOneController
 */
@WebServlet("/CashbookOneController")
public class CashbookOneController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Cashbook> list= new ArrayList<Cashbook>();
		int cashbookNo =Integer.parseInt(request.getParameter("cashbookNo"));
		System.out.println(cashbookNo+"One");
		Cashbook cashbook= new Cashbook();
		CashbookDao cashbookdao = new CashbookDao();
		
		cashbook.setCashbookNo(cashbookNo);
		
		list =cashbookdao.selectCashbookOne(cashbook);	
		request.setAttribute("cashbookNo", cashbookNo);
		request.setAttribute("list", list);
		request.getRequestDispatcher("WEB-INF/view/CashbookOne.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
