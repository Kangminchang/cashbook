package controller;

import java.io.IOException;
import dao.*;
import vo.*;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CashbookDao;

@WebServlet("/CashbookListByMonthController")
public class CashbookListByMonthController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//濡쒓렇�씤 �뙋蹂� 肄붾뱶媛� �뾾�떎硫� 濡쒓렇�씤�쓣 �븞�빐�룄 �젒洹쇱씠 媛��뒫�븯�떎
		//�븯吏�留� 濡쒓렇�씤 �뙋蹂꾩퐫�뱶媛� �깮湲� �썑濡� 濡쒓렇�씤�씠 �븞�릺�뼱�엳�떎硫� 濡쒓렇�씤 李쎌쑝濡� 媛꾨떎.
		HttpSession session = request.getSession();
		String sessionMemberId=(String)session.getAttribute("sessionMemberId");
		if(sessionMemberId==null) {
			//�씠誘� 濡쒓렇�씤�씠 �릺�뼱 �엳�뒗 �긽�깭
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return; 
		}
		
		// 1) �썡蹂� 媛�怨꾨� 由ъ뒪�듃 �슂泥� 遺꾩꽍
		Calendar now = Calendar.getInstance(); // ex) 2022.04.19
		int y = now.get(Calendar.YEAR);
		int m = now.get(Calendar.MONTH) + 1; // 0 - 1�썡, 1 - 2�썡, ... 11 - 12�썡
		
		if(request.getParameter("y") != null) {
			y = Integer.parseInt(request.getParameter("y"));
		}
		if(request.getParameter("m") != null) {
			m = Integer.parseInt(request.getParameter("m"));
		}
		if(m==0) {
			m = 12;
			y = y-1;
		}
		if(m==13) {
			m = 1;
			y = y+1;
		}
		
		System.out.println(y+" <-- y");
		System.out.println(m+" <-- m");
		
		/*
		 
		 1) startBlank
		 2) endDay
		 3) endBlank
		 4) totalBlank
		 
		 */
		
		// �떆�옉�떆 �븘�슂�븳 怨듬갚 <TD>�쓽 媛��닔瑜� 援ы븯�뒗 �븣怨좊━利� -> startBlank 
		// firstDay�뒗 �삤�뒛�궇吏쒕�� 癒쇱�援ы븯�뿬 �궇吏쒕쭔 1�씪濡� 蹂�寃쏀빐�꽌 援ы븯�옄
		Calendar firstDay = Calendar.getInstance(); // ex) 2022.04.19
		firstDay.set(Calendar.YEAR, y);
		firstDay.set(Calendar.MONTH, m-1); // �옄諛� �떖�젰API�뒗 1�썡�쓣 0�쑝濡�, 2�썡�쓣 1濡�, ... 12�썡�쓣 11濡� �꽕�젙�릺�뼱�엳�쓬
		firstDay.set(Calendar.DATE, 1); // ex) 2022.04.01
		int dayOfWeek = firstDay.get(Calendar.DAY_OF_WEEK);
		// dayOfWeek 	�씪1, �썡2, ... �넗7
		// startBlank 	�씪0, �썡1, ... �넗6
		// 1)
		int startBlank = dayOfWeek - 1;
		// 留덉�留� �궇吏쒕뒗 �옄諛� �떖�젰api瑜� �씠�슜�븯�뿬 援ы븯�옄
		// 2)
		int endDay = firstDay.getActualMaximum(Calendar.DATE);// firstDay�떖�쓽 �젣�씪 �겙�닔�옄 -> 留덉�留됰궇吏�
		// strartBlank�� endDay瑜� �빀�쓽 寃곌낵�뿉 endBlank瑜� �뜑�빐�꽌 7�쓽 諛곗닔媛� �릺�룄濡�
		// 3)
		int endBlank = 0;
		if((startBlank+endDay)%7 != 0) {
			// 7�뿉�꽌 startBlank+endDay�쓽 7濡� �굹�늿 �굹癒몄�媛믪쓣 鍮쇰㈃
			endBlank = 7-((startBlank+endDay)%7);
		}
		// 4)
		int totalTd = startBlank + endDay + endBlank;
		
		
		// 2) 紐⑤뜽媛�(�썡蹂� 媛�怨꾨� 由ъ뒪�듃)�쓣 諛섑솚�븯�뒗 鍮꾩��땲�뒪濡쒖쭅(紐⑤뜽) �샇異�
		CashbookDao cashbookDao = new CashbookDao();
		List<Map<String, Object>> list = cashbookDao.selectCashbookListByMonth(y, m);
		/*
		 �떖�젰異쒕젰�뿉 �븘�슂�븳 紐⑤뜽媛�(1), 2), 3), 4)) + �뜲�씠�꽣踰좎씠�뒪�뿉�꽌 諛섑솚�맂 紐⑤뜽媛�(list, y異쒕젰�뀈�룄, m異쒕젰�썡) + �삤�뒛�궇吏�(today)
		 */
		request.setAttribute("startBlank", startBlank);
		request.setAttribute("endDay", endDay);
		request.setAttribute("endBlank", endBlank);
		request.setAttribute("totalTd", totalTd);
		
		request.setAttribute("list", list);
		request.setAttribute("y", y);
		request.setAttribute("m", m);
		
		StatsDao statsDao=new StatsDao();
		Stats stats = statsDao.selectStatsOneByNow();
		int totalCount = statsDao.selectStatsTotalCount();
		request.setAttribute("stats", stats);
		request.setAttribute("totalCount", totalCount);
		// 3) 酉� �룷�썙�뵫
		request.getRequestDispatcher("/WEB-INF/view/CashbookListByMonth.jsp").forward(request, response) ;
	}

}
