package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.HallDAO;
import DAO.MovieDAO;
import model.Projection;
import model.ProjectionType;
import model.User;

/**
 * Servlet implementation class AddProjectionType
 */
public class AddProjectionTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		else {
		
		try {
			
			int idProjectionType = Integer.valueOf(request.getParameter("projectionTypeId"));
			//System.out.println(idProjectionType);
			//System.exit(1);
			Projection newProjection = (Projection) request.getSession().getAttribute(String.valueOf(loggedInUser.getId()));

			for (ProjectionType pt : newProjection.getHall().getDimensions()) {
				if (pt.getId() == idProjectionType) {
					//System.out.println(pt.getId());
					newProjection.setProjectionType(pt); 
					break;
				}
			}
			
			//System.out.println(newProjection.getProjectionType().getName());
			request.setAttribute("movies", MovieDAO.getAll());
			request.setAttribute("halls", HallDAO.getAll());
			request.setAttribute("key", String.valueOf(loggedInUser.getId()));
			
			request.getRequestDispatcher("./AddNewProjection.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
