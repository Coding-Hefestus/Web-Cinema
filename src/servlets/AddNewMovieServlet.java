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
		else {
		
		String movieName = request.getParameter("movieName");
		String movieDuration = request.getParameter("movieDuration");
		String movieProductionYear = request.getParameter("movieProductionYear");
		String movieDescription = request.getParameter("movieDescription");
		String countryOfOrigin = request.getParameter("countryOfOrigin");
		String movieDistributor = request.getParameter("distributor");
		
		//prvi put stizemo na servlet tj trazimo da se doda novi film
		if (movieName == null || movieDuration == null || movieProductionYear == null || movieDescription == null || countryOfOrigin == null || movieDistributor == null ) {

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
			Movie movie = (Movie) request.getSession().getAttribute(String.valueOf(loggedInUser.getId()));
			//if (movie.getName().equals("") || movie.getDuration() < 0)
		
			//prvo pokusavamo da konvertujemo te podatke konvertujemo, ako prodje konvertujemo onda ih odma setujemo
			//za svaki podatak try/catch,
			boolean success = true; //pretpostavljamo da ce svi podaci/parametri biti u redu, ako samo jedan pukne ovaj boolean se manja na false
			
			if (!movieName.equals("")) movie.setName(movieName); //NAME
			else success = false; 
			
			try {
				int duration = Integer.valueOf(movieDuration);
				if (duration > 0) movie.setDuration(duration);
				else success = false; 
			} catch(Exception e) {success = false;};
			
			
			try {
				int productionY = Integer.valueOf(movieProductionYear);
				if (productionY > 1950) movie.setProductionYear(productionY);
				else success = false; 
			}catch(Exception e) {success = false;};
			
			
			movie.setDescription(movieDescription);
			
			
			if (!countryOfOrigin.equals("")) movie.setCountryOfOrigin(countryOfOrigin);
			else success = false;
			
			if (!movieDistributor.equals("")) movie.setDistributor(movieDistributor);
			else success = false;

			try {
				
				if (success) {
					request.getSession().removeAttribute(String.valueOf(loggedInUser.getId()));
					MovieDAO.add(movie);
					response.sendRedirect("./MovieServlet");
				} else {
					request.setAttribute("key", String.valueOf(loggedInUser.getId()));
					request.setAttribute("directors", DirectorDAO.getAllDirectors());
					request.setAttribute("actors", ActorDAO.getAllActors());
					request.setAttribute("genres", GenreDAO.getAllGenres());
					
					request.getRequestDispatcher("./AddNewMovie.jsp").forward(request, response);
				}
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
		} //od else
		
		
		}
		

	}

}
