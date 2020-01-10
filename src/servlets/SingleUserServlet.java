package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.TicketDAO;
import DAO.UserDAO;
import model.Ticket;
import model.User;


public class SingleUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		else {
		
		String idU = request.getParameter("id");
		int idUser = Integer.valueOf(idU);
		
		try {
			User user = UserDAO.getById(idUser);
			
			ArrayList<Ticket> ticketsForUser = TicketDAO.getTicketsForUser(user);
			
			request.setAttribute("tickets", ticketsForUser);
			request.setAttribute("user", user);
			request.getRequestDispatcher("./SingleUser.jsp").forward(request, response);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
