package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.ComparatorUtils;

import DAO.ProjectionDAO;
import model.Projection;
import model.User;


public class MainPageAppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		else {
		
		//all filtering parameters
		String movieF = request.getParameter("movieFilter"); 
		movieF = (movieF != null ? movieF : "");		
		final String movieFilter = new String(movieF);
		
		String fromDate = request.getParameter("fromDate");
		String fromTime = request.getParameter("fromTime");
		
		
		
		String toDate = request.getParameter("toDate");
		String toTime = request.getParameter("toTime");
		
		LocalDateTime from = LocalDateTime.MIN;
		LocalDateTime to = LocalDateTime.MAX;
		
		if (fromDate != null && fromTime != null && toDate != null && toTime != null) {			
			from = LocalDateTime.parse(fromDate + " " + fromTime, formatter);
			to = LocalDateTime.parse(toDate + " " + toTime, formatter);
		}

		String dimensionFilter = request.getParameter("dimensionFilter");
		dimensionFilter = (dimensionFilter != null ? dimensionFilter : "");	

		
		String hallFilter = request.getParameter("hallFilter");
		hallFilter = (hallFilter != null ? hallFilter : "");	
		
		
		String fromPF = request.getParameter("fromPriceFilter"); //fromPF - fromPriceFilter
		final int fromPriceFilter = getFilter(fromPF);
		
		String toPF = request.getParameter("toPriceFilter"); //toPF - toPriceFilter
		final int toPriceFilter = getToFilter(toPF);
		
		
		
		List<Comparator<Projection>> comparators = new ArrayList<>();
		
		//sorting parameters
		String movieSort = request.getParameter("byMovie");
		String dateSort = request.getParameter("byDate");		
		String dimensionSort = request.getParameter("byDimension");		
		String hallSort = request.getParameter("byHall");
		String priceSort = request.getParameter("byPrice");
		
		
		
		if (movieSort != null)  comparators.add(Projection.comparatorByMovie(movieSort));
		if (dateSort != null) comparators.add(Projection.sortByDate(dateSort));
		if (dimensionSort != null) comparators.add(Projection.sortByDimension(dimensionSort));
		if (hallSort != null) comparators.add(Projection.sortByHall(hallSort));
		if (priceSort != null) comparators.add(Projection.sortByPrice(priceSort));
		

		
		try {

			ArrayList<Projection> filteredProjections = (ArrayList<Projection>) ProjectionDAO.getAll().stream()
							.filter(Projection.movieFilter(movieFilter)						
							.and(Projection.dateFilter(from, to))
							.and(Projection.dimensionFilter(dimensionFilter))
							.and(Projection.hallFilter(hallFilter))
							.and(Projection.ticketFilter(fromPriceFilter, toPriceFilter)))
							.collect(Collectors.toList());
			
			
			

			if (comparators.size() != 0) Collections.sort(filteredProjections, ComparatorUtils.chainedComparator(comparators));

			request.setAttribute("movieFilter", movieFilter);
			
			if (from == LocalDateTime.MIN) {
				
				request.setAttribute("fromDate", "1950-01-01" );
				request.setAttribute("fromTime", "12:00" );
			} else {
				request.setAttribute("fromDate", fromDate);
				request.setAttribute("fromTime", fromTime);
			}
			
			if (to == LocalDateTime.MAX) {
				request.setAttribute("toDate", "2025-01-01" );
				request.setAttribute("toTime", "12:00" );
			} else {
				request.setAttribute("toDate", toDate);
				request.setAttribute("toTime", toTime);
			}
			
			request.setAttribute("dimensionFilter", dimensionFilter);
			
			request.setAttribute("hallFilter", hallFilter);
			
			request.setAttribute("fromPriceFilter", fromPriceFilter);
			
			request.setAttribute("toPriceFilter", toPriceFilter);

			request.setAttribute("filteredProjections", filteredProjections);
		
			request.getRequestDispatcher("./MainPageApp.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private  int getFilter(String filter) {
		
		int tempFilter = 0;
		try {
			int temp = Integer.valueOf(filter);
			temp = (temp > 0 ? temp : 0);
			tempFilter = temp;
		} catch(Exception e) {
			return 0;
			//e.printStackTrace();
			
		}
		return  tempFilter;
	}
	
	
	private  int getToFilter(String filter) {
		
		int tempFilter = 0;
		try {
			//int temp = Integer.valueOf(filter);
			int temp = Integer.parseInt(filter);
			temp = (temp > 0 ? temp : Integer.MAX_VALUE);
			tempFilter = temp;
		} catch(Exception e) {
			
			return Integer.MAX_VALUE;
		
			
		}
		return  tempFilter;
	}

}
