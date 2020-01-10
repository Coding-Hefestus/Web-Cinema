package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.HallDAO;
import DAO.MovieDAO;
import DAO.ProjectionDAO;
import model.Hall;
import model.Movie;
import model.Period;
import model.Projection;
import model.ProjectionType;
import model.User;

public class AddNewProjectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		else {
		
		String movieName = request.getParameter("movieName");
		String hallName = request.getParameter("hall");
		String projType = request.getParameter("dimension");
		String startDate = request.getParameter("start");
		String price = request.getParameter("ticket");
		
		
		if (price == null && movieName == null && hallName == null && projType == null && startDate == null) {
			try {
				
				request.getSession().setAttribute(String.valueOf(loggedInUser.getId()), new Projection());
	
				
				request.setAttribute("movies", MovieDAO.getAll());
				request.setAttribute("halls", HallDAO.getAll());
				request.setAttribute("key", String.valueOf(loggedInUser.getId()));

				request.getRequestDispatcher("./AddNewProjection.jsp").forward(request, response);

			} catch ( Exception e) {

				e.printStackTrace();
			}
		} else {
			//nije prvi put dosao na servlet
			//vec je popunio neke podatke ovde radimo proveru tih (obaveznih) podataka
			
			Projection newProjection = (Projection) request.getSession().getAttribute(String.valueOf(loggedInUser.getId()));
			
			boolean success = true;
			
			
			if (!(newProjection.getMovie() instanceof Movie)) success = false;
			
			if (!(newProjection.getHall() instanceof Hall)) success = false;
			
			if (!(newProjection.getProjectionType() instanceof ProjectionType)) success = false;
			
			try {
				if (overlaps(request.getParameter("date"), request.getParameter("time"), newProjection.getHall(), newProjection.getMovie().getDuration())) success = false;
				else newProjection.setPeriod(createPeriod(request.getParameter("date"), request.getParameter("time"), newProjection.getMovie().getDuration()));
			} catch (Exception e) {	success = false;}
				
			try {
				double ticket = Double.valueOf(request.getParameter("ticket"));
				if (ticket > 0) newProjection.setTicketPrice(ticket); 
				else success = false;
			} catch (Exception e) {success = false;}
			
			if (success) {
				//upisuj u bazu; period pa projekciju, 
				newProjection.setAdministrator(loggedInUser);
				try {
					if (ProjectionDAO.add(newProjection)) {
						request.getSession().removeAttribute(String.valueOf(loggedInUser.getId()));
						response.sendRedirect("./MainPageAppServlet");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			} else {
				//nesto je pogresio...
				try {
					
					if (newProjection.getPeriod() != null && newProjection.getPeriod().getStart().isAfter(LocalDateTime.now())) {
						LocalDateTime start = newProjection.getPeriod().getStart();
						String dateString = start.getYear() + "-" + start.getMonth() + "-" + start.getDayOfMonth();
						String timeString = start.getHour() +":" + start.getMinute();
						
						request.setAttribute("time", timeString);
						request.setAttribute("date", dateString);
					}
										
					
					request.setAttribute("movies", MovieDAO.getAll());
					request.setAttribute("halls", HallDAO.getAll());
					request.setAttribute("key", String.valueOf(loggedInUser.getId()));
					
					request.getRequestDispatcher("./AddNewProjection.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
		} //od elsa
		
		
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean overlaps(String date, String time, Hall hall, int movieDuration) throws SQLException, ParseException {
		
		Period periodForProjection = createPeriod(date, time, movieDuration);
		if (periodForProjection == null) return true;
		
		//ArrayList<Projection> projectionsForHall = ProjectionDAO.getProjectionsForHall(hall.getId()); 
		
		return  ProjectionDAO.getProjectionsForHall(hall.getId())
				.stream()
				.filter(p -> p.getPeriod().getStart().isAfter(LocalDateTime.now()))
				.anyMatch(p -> p.getPeriod().overlaps(periodForProjection));
		
		
	}
	
	private Period createPeriod(String date, String time, int movieDuration) {
		Period period = null;
		try {
			period = new Period(-1, true, LocalDateTime.parse(date + " " + time, formatter), movieDuration);
			if (period.getStart().isBefore(LocalDateTime.now())) return null;
			
		} catch (Exception e) {return null;}
		
		return period;
	}

}
