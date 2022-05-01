package listener;

import javax.servlet.ServletContextEvent;
import java.sql.*;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class driveListener
 *
 */
@WebListener
public class BootListener implements ServletContextListener {
	@Override
    public void contextInitialized(ServletContextEvent sce)  { 
		// �쁽�옱 �젒�냽�옄 移댁슫�듃 �냽�꽦
		sce.getServletContext().setAttribute("currentCount", 0);
		System.out.println("db드라이브 로딩중....");
        try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
}
