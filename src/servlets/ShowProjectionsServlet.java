package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProjectionDAO;
import model.Projection;
import model.User;


public class ShowProjectionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		
		try {
			int idMovie = Integer.valueOf(request.getParameter("movie"));
			ArrayList<Projection> projectionsForMovie = (ArrayList<Projection>) 
					ProjectionDAO.getProjectionsForMovie(idMovie)
					.stream()
					.filter(Projection.hasAvaliableProjections())
					.sorted(Projection.sortByStartDate().reversed())
					.collect(Collectors.toList());
			
			request.setAttribute("projections", projectionsForMovie);
			request.getRequestDispatcher("./AllProjectionsForMovie.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
