package servlets;

import java.io.IOException;
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
import model.Projection;
import model.Seat;
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
			//System.out.println(request.getParameter("seats"));
			String uri = request.getQueryString();
			int idProjection = Integer.valueOf(request.getParameter("projection"));
			Projection projection = ProjectionDAO.getById(idProjection);
			//System.out.println(uri);
			
			Stream<String> st = Stream.of(uri.split("&"));
			//Stream<String> st1 = st.of(uri.split("seats="));
			
			//st1.forEach(System.out::println);
			
			ArrayList<Ticket> newTicketsForSeats = Stream.of(uri.split("&"))
					.map(s -> s.split("seats="))
					.flatMap(Arrays::stream)
					.mapToInt(Integer::valueOf)
					//.map(s -> new Seat(s))
					.map(s -> new Ticket(-1, true, projection, SeatDAO.getById(s), LocalDateTime.now(), loggedInUser))
					.collect(Collectors.toList());
			//seats=2&seats=3
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
