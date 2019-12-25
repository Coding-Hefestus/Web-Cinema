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
import model.Actor;
import model.Director;
import model.Movie;
import model.User;

public class AddActorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//doGet(request, response);
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		
		String actor = request.getParameter("actor");
		String origin = request.getParameter("origin");
		int actorId = Integer.valueOf(actor);
		
		try {
			Actor a = (Actor) ActorDAO.getActorById(actorId);

			Movie movie = (Movie) request.getSession().getAttribute(String.valueOf(loggedInUser.getId()));
			
			movie.getActors().add(a);
			request.setAttribute("key", String.valueOf(loggedInUser.getId()));
			request.setAttribute("directors", DirectorDAO.getAllDirectors());
			request.setAttribute("actors", ActorDAO.getAllActors());
			request.setAttribute("genres", GenreDAO.getAllGenres());
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if (origin.equals("EditMovie")) {
			request.getRequestDispatcher("./EditMovie.jsp").forward(request, response);
		} else if (origin.equals("AddNewMovie")) {
			request.getRequestDispatcher("./AddNewMovie.jsp").forward(request, response);
		}
		
		
	}

}
