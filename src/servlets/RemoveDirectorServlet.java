package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ActorDAO;
import DAO.DirectorDAO;
import DAO.GenreDAO;
import model.Director;
import model.Movie;
import model.User;


public class RemoveDirectorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		else {
		//doGet(request, response);

		
		String directorString = request.getParameter("director");
		try {
			
			int idDirector = Integer.valueOf(directorString);			
			Director director = DirectorDAO.getDirectorById(idDirector);
			Movie movie = (Movie) request.getSession().getAttribute(String.valueOf(loggedInUser.getId()));
			movie.getDirectors().remove(director);
			
			//if (DirectorDAO.removeDirectorFromMovie(movie.getId(), director.getId())) {
				
				request.setAttribute("key", String.valueOf(loggedInUser.getId()));
				request.setAttribute("directors", DirectorDAO.getAllDirectors());
				request.setAttribute("actors", ActorDAO.getAllActors());
				request.setAttribute("genres", GenreDAO.getAllGenres());
			//} 			

		} catch (Exception e) {
			e.printStackTrace();
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("./EditMovie.jsp").forward(request, response);
		}
	}

}
