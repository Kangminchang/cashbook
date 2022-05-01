package listener;

import dao.StatsDao;
import vo.Stats;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


@WebListener
public class CountListener implements HttpSessionListener {
	private StatsDao statsDao;
	
    public void sessionCreated(HttpSessionEvent se)  { 
    	// �쁽�옱 �젒�냽�옄 �닔 --> �넱耳볦븞�뿉 �꽭�뀡�쓽 �닔
    	int currentCount = (Integer)(se.getSession().getServletContext().getAttribute("currentCount"));
    	se.getSession().getServletContext().setAttribute("currentCount", currentCount+1);
    	
    	// �궇吏쒕퀎 �젒�냽�옄 �닔 --> DB瑜� �씠�슜
    	this.statsDao = new StatsDao();
    	Stats stats = statsDao.selectStatsOneByNow();
    	if(stats == null) { // �삤�뒛 �궇吏� 移댁슫�듃媛� �뾾�떎
    		statsDao.insertStats(); // �삤�뒛 �궇吏쒕줈 移댁슫�듃 1 異붽�
    	} else { // �삤�뒛 �궇吏� 移댁슫�듃媛� �엳�떎
    		statsDao.updateStatsByNow(); // �삤�뒛 �궇吏� 移댁슫�듃瑜� 1 利앷� 
    	}
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	int currentCount = (Integer)(se.getSession().getServletContext().getAttribute("currentCount"));
    	se.getSession().getServletContext().setAttribute("currentCount", currentCount-1);
    }
}

