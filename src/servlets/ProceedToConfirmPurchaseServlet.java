package servlets;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProjectionDAO;
import model.Projection;
import model.User;

public class ProceedToConfirmPurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		final User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		else {
		
		try {
		
			String uri = request.getQueryString();
			//projection=1&seats=2&seats=3

			int idProjection = Integer.valueOf(request.getParameter("projection"));
			Projection projection = ProjectionDAO.getById(idProjection);
			
			String[] seatsIdsArray = Arrays.copyOfRange(uri.split("&"), 1, uri.split("&").length);
			
			int seatsCount = seatsIdsArray.length; //seatsCount = number of ticket;
			
			double totalPrice = projection.getTicketPrice() * seatsCount;
			
			request.setAttribute("projection", projection);
			request.setAttribute("seatsIds", String.join("|", seatsIdsArray));
			request.setAttribute("totalPrice", totalPrice);
			
			request.getRequestDispatcher("./ConfirmPurchase.jsp").forward(request, response);


			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
