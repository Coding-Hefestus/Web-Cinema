package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.HallDAO;
import DAO.MovieDAO;
import model.Hall;
import model.Movie;
import model.Projection;
import model.User;

/**
 * Servlet implementation class AddHallServlet
 */
public class AddHallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		
		
		try {
			int idHall = Integer.valueOf(request.getParameter("hall"));
			Hall hall = HallDAO.getById(idHall);
			
			Projection newProjection = (Projection) request.getSession().getAttribute(String.valueOf(loggedInUser.getId()));
			
			newProjection.setHall(hall);
			newProjection.setProjectionType(null);
			
			request.setAttribute("movies", MovieDAO.getAll());
			request.setAttribute("halls", HallDAO.getAll());
			request.setAttribute("key", String.valueOf(loggedInUser.getId()));
			
			request.getRequestDispatcher("./AddNewProjection.jsp").forward(request, response);

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
