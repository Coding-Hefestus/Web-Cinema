package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Movie;
import model.Moviefiable;
import model.Role;
import model.User;


public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser.getRole().equals(Role.ADMIN)) {
			Moviefiable m = (Moviefiable) request.getSession().getAttribute(String.valueOf(loggedInUser.getId()));
			if ( m != null) request.getSession().removeAttribute(String.valueOf(loggedInUser.getId()));
		}
		
		request.getSession().invalidate();
		
		response.sendRedirect("./Login.html");
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
