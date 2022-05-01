package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StatsDao;
import vo.Stats;


@WebServlet("/IndexController")
public class IndexController extends HttpServlet {
	private StatsDao statsDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.statsDao = new StatsDao();
		Stats stats = statsDao.selectStatsOneByNow();
		int totalCount = statsDao.selectStatsTotalCount();
		
		request.setAttribute("stats", stats);
		request.setAttribute("totalCount", totalCount);
		
		request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
	}
}
