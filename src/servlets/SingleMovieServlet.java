package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MovieDAO;
import model.Movie;
import model.User;


public class SingleMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		else {
		
		
		String idM = request.getParameter("id");
		int idMovie = Integer.valueOf(idM);
		
		try {
			Movie movie = MovieDAO.getById(idMovie);
			request.setAttribute("movie", movie);
			
			request.getRequestDispatcher("./SingleMovie.jsp").forward(request, response);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		//response.getWriter().append("Served at: ").append(request.getContextPath());
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
