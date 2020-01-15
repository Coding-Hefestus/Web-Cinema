package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProjectionDAO;
import DAO.TicketDAO;
import model.Projection;
import model.Ticket;
import model.User;


public class SingleProjectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		else {
		
		
		String idP = request.getParameter("id");
		int idProjection = Integer.valueOf(idP);
		//System.out.println(idProjection);
		
		String fromDate = request.getParameter("fromDate");
		String fromTime = request.getParameter("fromTime");
		
		String toDate = request.getParameter("toDate");
		String toTime = request.getParameter("toTime");
		
		String userF = request.getParameter("userFilter"); 
		userF = (userF != null ? userF : "");		
		final String userFilter = new String(userF);
		
		LocalDateTime from = LocalDateTime.MIN;
		LocalDateTime to = LocalDateTime.MAX;
		
		if (fromDate != null && fromTime != null && toDate != null && toTime != null) {			
			from = LocalDateTime.parse(fromDate + " " + fromTime, formatter);
			to = LocalDateTime.parse(toDate + " " + toTime, formatter);
		}

		
		
		
		
		try {
			Projection projection = ProjectionDAO.getById(idProjection);
			
			ArrayList<Ticket> filteredTicketsForProjection = (ArrayList<Ticket>) TicketDAO.getTicketsForProjection(idProjection)
					.stream()
					.filter(Ticket.dateFilter(from, to)
					.and(Ticket.userFilter(userFilter)))
					.collect(Collectors.toList());
			
			if (from == LocalDateTime.MIN) {
				
				request.setAttribute("fromDate", "1950-01-01" );
				request.setAttribute("fromTime", "12:00" );
			} else {
				request.setAttribute("fromDate", fromDate);
				request.setAttribute("fromTime", fromTime);
			}
			
			if (to == LocalDateTime.MAX) {
				request.setAttribute("toDate", "2025-01-01" );
				request.setAttribute("toTime", "12:00" );
			} else {
				request.setAttribute("toDate", toDate);
				request.setAttribute("toTime", toTime);
			}
			
			if (avaliable(projection)) request.setAttribute("avaliable", true);
			else request.setAttribute("avaliable", false);
			
			request.setAttribute("userFilter", userFilter);
			
			request.setAttribute("projection", projection);
			
			request.setAttribute("ticketsForProjection", filteredTicketsForProjection);
			
			request.getRequestDispatcher("./SingleProjection.jsp").forward(request, response);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		}
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		
		doGet(request, response);
	}
	
	private boolean avaliable(Projection projection) {
		return ((projection.getHall().getCapacity() - projection.getTicketsSold()) != 0 && projection.getPeriod().getStart().isAfter(LocalDateTime.now()));
	}

}
