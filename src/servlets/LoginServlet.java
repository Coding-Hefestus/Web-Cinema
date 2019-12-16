package servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;
import model.User;


public class LoginServlet extends HttpServlet {
	
       

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			User user = UserDAO.get(userName, password);
			if (user == null) { response.sendRedirect("./Login.html"); return;}
			else {
				request.getSession().setAttribute("loggedInUser", user);
				response.sendRedirect("./MainPageApp.html");
			}
				

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
