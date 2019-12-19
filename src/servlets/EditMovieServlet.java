package servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MovieDAO;
import model.Movie;
import model.User;


public class EditMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		
		String movieName = request.getParameter("movieName");
		String movieDuration = request.getParameter("movieDuration");
		String movieProductionYear = request.getParameter("movieProductionYear");
		String movieDescription = request.getParameter("movieDescription");
		Movie editMovie = (Movie) request.getAttribute("editMovie");
		if (movieName != null || movieDuration != null || movieProductionYear != null || movieDescription != null) {
			
			try {
				//System.out.println("asfas");
				//System.out.println(movieName);
				int duration = Integer.valueOf(movieDuration);
				int productioYear = Integer.valueOf(movieProductionYear);
				if (duration < 0 || productioYear < 1950) {
					System.out.println("if");
					request.setAttribute("editMovie", editMovie);
					request.getRequestDispatcher("./EditMovie.jsp").forward(request, response);
					return;
				} else {
					
					editMovie.setName(movieName);
					editMovie.setDuration(duration);
					editMovie.setProductionYear(productioYear);
					editMovie.setDescription(movieDescription);
					System.out.println("asfafsasd");
					MovieDAO.update(editMovie);
					
					response.sendRedirect("./MovieServlet");
					return;
				}
			} catch(Exception e) {
				request.setAttribute("editMovie", editMovie);
				//request.getRequestDispatcher("./EditMovie.jsp").forward(request, response);
				return;
			}
	
		} else {
			try {
				String stringId = request.getParameter("edit");
				int idMovie = Integer.valueOf(stringId);
				
				editMovie = MovieDAO.getById(idMovie);
				System.out.println(editMovie.getName());
				request.setAttribute("editMovie", editMovie);
				request.getRequestDispatcher("./EditMovie.jsp").forward(request, response);
				return;
			  
			} catch(Exception e) {
				response.sendRedirect("./MovieServlet");
				return;
			}
		}
		

		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
		
		
		
		
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
