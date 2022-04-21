package controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.Cashbook;
import dao.CashbookDao;

@WebServlet("/InsertCashBookController")
public class InsertCashbookController extends HttpServlet {
	//CshbookListByMonthController.java 로부터 데이터를 get방식으로 받아옴
	//InsertCashBookForm.jsp 로 입력할 날짜를 보냄
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String y=request.getParameter("y"); // 년도
		String m =request.getParameter("m"); // 달
		String d=request.getParameter("d"); // 일자
		String cashDate=y+"-"+m+"-"+d;  //선택한 날짜
		request.setAttribute("cashDate", cashDate);
		
		System.out.println(cashDate+" 입력할 날짜");
		request.getRequestDispatcher("WEB-INF/view/InsertCashBookForm.jsp").forward(request, response);
		
		
	}
	
	// InsertCashBookForm에서 입력한 데이터 받아와 Action 처리 (Post방식)
	// hashtag 생성 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cashDate=request.getParameter("cashDate");
		String kind = request.getParameter("kind");
		int cash=Integer.parseInt(request.getParameter("cash"));
		String memo=request.getParameter("memo");
		
		System.out.println(cashDate+"cashDate Insert");
		System.out.println(kind+"kind Insert");
		System.out.println(cash+"cash Insert");
		System.out.println(memo+"memo Insert");
		
		Cashbook cashbook = new Cashbook();
		cashbook.setCashDate(cashDate);
		cashbook.setKind(kind);
		cashbook.setMemo(memo);
		cashbook.setCash(cash);
		
		//hashtag 부분
		// #으로 시작하는 단어들만 정렬
		// 
		List<String> hashtag =new ArrayList<String>();
		String memo2=memo.replace("#", " #");
		String[] arr=memo2.split(" ");
		for(String s : arr) {
			if(s.startsWith("#")) {
				String temp=s.replace("#","");
				if(temp.length()>0){
					hashtag.add(temp);
				}
			}
		}
		System.out.println(hashtag.size()+"memo Insert");
		for(String h:hashtag) {
			System.out.println(hashtag+"<---h");
		}
		
		CashbookDao cashbookdao=new CashbookDao();
		cashbookdao.insertCashbook(cashbook, hashtag);
		
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
	}

}
