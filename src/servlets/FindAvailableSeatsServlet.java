package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProjectionDAO;
import DAO.SeatDAO;
import model.Projection;
import model.Seat;
import model.User;


public class FindAvailableSeatsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		else {
		
		
		try {
			Projection projection = ProjectionDAO.getById(Integer.valueOf(request.getParameter("projection")));
			ArrayList<Seat> availableSeatsForProjection = (ArrayList<Seat>) SeatDAO.availableSeatsForProjection(projection.getId(), projection.getHall().getId());
			request.setAttribute("projection", projection);
			request.setAttribute("availableSeatsForProjection", availableSeatsForProjection);
			request.getRequestDispatcher("./ChooseSeats.jsp").forward(request, response);
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
