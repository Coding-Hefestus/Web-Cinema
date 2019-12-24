package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ActorDAO;
import DAO.DirectorDAO;
import DAO.GenreDAO;
import model.Movie;
import model.User;


public class AddNewMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		
		String movieName = request.getParameter("movieName");
		String movieDuration = request.getParameter("movieDuration");
		String movieProductionYear = request.getParameter("movieProductionYear");
		String countryOfOrigin = request.getParameter("countryOfOrigin");
		//String movieDescription = request.getParameter("movieDescription");
		
		//prvi put stizemo na servlet tj trazimo da se doda novi film
		if (movieName == null || movieDuration == null || movieProductionYear == null || countryOfOrigin == null) {

			try {
				
				request.getSession().setAttribute(String.valueOf(loggedInUser.getId()), new Movie());
	
				request.setAttribute("directors", DirectorDAO.getAllDirectors());
				request.setAttribute("actors", ActorDAO.getAllActors());
				request.setAttribute("genres", GenreDAO.getAllGenres());
				request.setAttribute("key", String.valueOf(loggedInUser.getId()));
				
				
				request.getRequestDispatcher("./AddNewMovie.jsp").forward(request, response);
				
				
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
			
		}
		else {
			//vec je popunio neke podatke ovde radimo proveru tih (obaveznih) podataka
			//...
			
			
		}
		
		
		
		

	}

}
