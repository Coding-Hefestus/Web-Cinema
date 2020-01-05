package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProjectionDAO;
import DAO.SeatDAO;
import DAO.TicketDAO;
import model.Projection;
import model.Ticket;
import model.User;


public class ConfirmPurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		final User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		
		try {
			
			int idProjection = Integer.valueOf(request.getParameter("projection"));

			Projection projection = ProjectionDAO.getById(idProjection);
			
			String seatsIds = request.getParameter("seatsIds");
	
			ArrayList<Ticket> newTicketsForSeats = 
		    Stream.of(seatsIds.split("\\|"))
				  .map(s -> s.split("seats="))
                  .flatMap(Arrays::stream)
                  .filter(x -> !x.contentEquals(""))
                  .mapToInt(Integer::valueOf)
                  .mapToObj(s -> {
                   try {
                        return new Ticket(-1, true, projection, SeatDAO.getById(s), 
                    				 LocalDateTime.now(), loggedInUser);
                       } catch (SQLException e) { e.printStackTrace(); return null;}
                   
                       })
                  .collect(Collectors.toCollection(ArrayList::new));
			
			boolean success = false;
			
			for (Ticket t : newTicketsForSeats) {
				success = false;
				if(TicketDAO.add(t)) success = true; 
			}
			
			if (success) response.sendRedirect("./MainPageAppServlet");
				

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
