package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.TicketDAO;
import DAO.UserDAO;
import model.Moviefiable;
import model.Role;
import model.Ticket;
import model.User;

public class MyProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		else {
		
		
		if (loggedInUser.getRole().equals(Role.ADMIN)) {
			Moviefiable m = (Moviefiable) request.getSession().getAttribute(String.valueOf(loggedInUser.getId()));
			if ( m != null) request.getSession().removeAttribute(String.valueOf(loggedInUser.getId()));
			
//			Projection p = (Projection) request.getSession().getAttribute(String.valueOf(loggedInUser.getId()));
//			if ( p != null) request.getSession().removeAttribute(String.valueOf(loggedInUser.getId()));
//		
		} 
		
		String newPassword = request.getParameter("newPassword");
		
		try {
			if ( newPassword != null && !newPassword.equals("")) {
				if (!UserDAO.alreadyExists(loggedInUser.getUsername(), newPassword)) {
					loggedInUser.setPassword(newPassword);
					UserDAO.update(loggedInUser);
					request.getSession().invalidate();
					response.sendRedirect("./Login.html");
					//response.sendRedirect("./UsersManagementServlet");			
				}
			} else if (newPassword == null){
				ArrayList<Ticket> ticketsForUser = TicketDAO.getTicketsForUser(loggedInUser);
				
				request.setAttribute("tickets", ticketsForUser);
				request.getRequestDispatcher("./MyProfile.jsp").forward(request, response);
				//response.sendRedirect("./MyProfile.jsp");
			}else {
			
				response.sendRedirect("./MainPageAppServlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
