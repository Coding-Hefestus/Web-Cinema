package servlets;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;

import DAO.ConnectionManager;



public class InitListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }


    public void contextInitialized(ServletContextEvent event)  { 
    	System.out.println("inicijalizacija...");
    	
    	ServletContext context = event.getServletContext();
    	
    	context.setAttribute("usersSessions", new HashMap<String,HttpSession>() );
    	
    	ConnectionManager.open();
    }
	
}
