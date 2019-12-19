package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MovieDAO;
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
		String movieDescription = request.getParameter("movieDescription");
		
		try {
			int duration = Integer.valueOf(movieDuration);
			int productionYear = Integer.valueOf(movieProductionYear);
			
			if (duration < 0 || productionYear < 1950 || movieName.equals("") || movieDescription.equals("")) response.sendRedirect("./AddNewMovie.html");
			else {
				//Movie newMovie = new Movie(true, movieName, duration, productionYear, movieDescription);
				Movie newMovie = new Movie();
				if (MovieDAO.add(newMovie)) response.sendRedirect("./MovieServlet");
				else response.sendRedirect("./AddNewMovie.html");
				
			}
		} catch(Exception e) {response.sendRedirect("./AddNewMovie.html");}
		

	}

}
