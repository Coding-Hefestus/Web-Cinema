package servlets;


import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import model.User;


public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			User user = UserDAO.get(userName, password);
			if (user == null) { response.sendRedirect("./Login.html"); return;}
			else {
				request.getSession().setAttribute("loggedInUser", user);
								
				setSession(userName, request);

				response.sendRedirect("./MainPageAppServlet");
				
			}
				

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static void setSession(String userName, HttpServletRequest request) {
				
		ServletContext context = request.getSession( ).getServletContext();
		
		@SuppressWarnings("unchecked")
		HashMap<String,HttpSession> mapa = 
				(HashMap<String, HttpSession>) context.getAttribute("usersSessions");
		
		mapa.put(userName, request.getSession());
	}

}
