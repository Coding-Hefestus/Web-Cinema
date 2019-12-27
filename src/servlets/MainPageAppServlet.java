package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
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
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		
		//all filtering parameters
		String movieF = request.getParameter("movieFilter"); 
		movieF = (movieF != null ? movieF : "");		
		final String movieFilter = new String(movieF);
		
		
		String dayStringFrom = request.getParameter("fromDay");
		String monthStringFrom = request.getParameter("fromMonth");
		String yearStringFrom = request.getParameter("fromYear");
		

		String dayStringTo = request.getParameter("toDay");
		String monthStringTo = request.getParameter("toMonth");
		String yearStringTo = request.getParameter("toYear");
		
		LocalDateTime from = LocalDateTime.MIN;
		LocalDateTime to = LocalDateTime.MAX;
		
		
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
		
		if (movieSort != null) comparators.add(Projection.sortByMovie(movieSort));
		if (dateSort != null) comparators.add(Projection.sortByDate(dateSort));
		if (dimensionSort != null) comparators.add(Projection.sortByDimension(dimensionSort));
		if (hallSort != null) comparators.add(Projection.sortByHall(hallSort));
		if (priceSort != null) comparators.add(Projection.sortByPrice(priceSort));
		
		if (dayStringFrom != null && monthStringFrom != null  && yearStringFrom  != null && dayStringTo != null &&  monthStringTo != null && yearStringTo != null ) {
			
			int fromDay = Integer.valueOf(dayStringFrom);
			int fromMonth = Integer.valueOf(monthStringFrom);
			int fromYear = Integer.valueOf(yearStringFrom);
			
			int toDay = Integer.valueOf(dayStringTo);
			int toMonth = Integer.valueOf(monthStringTo);
			int toYear = Integer.valueOf(yearStringTo);
			
			from = LocalDateTime.of(fromYear, fromMonth, fromDay, 0, 0);
			to = LocalDateTime.of(toYear, toMonth, toDay, 0, 0);
		}
		
		
		
		
		
		try {
			ArrayList<Projection> filteredProjections = (ArrayList<Projection>) ProjectionDAO.getAll().stream()
							.filter(Projection.movieFilter(movieFilter)						
							.and(Projection.dateFilter(from, to))
							.and(Projection.dimensionFilter(dimensionFilter))
							.and(Projection.hallFilter(hallFilter))
							.and(Projection.ticketFilter(fromPriceFilter, toPriceFilter)))
							.collect(Collectors.toList());
			
			System.out.println(filteredProjections.size());
			if (comparators.size() != 0) Collections.sort(filteredProjections, ComparatorUtils.chainedComparator(comparators));

			request.setAttribute("movieFilter", movieFilter);
			
			if (from == LocalDateTime.MIN) {
				request.setAttribute("fromDay", 1);
				request.setAttribute("fromMonth", 1);
				request.setAttribute("fromYear", 1950);
			} else {
				request.setAttribute("fromDay", from.getDayOfMonth());
				request.setAttribute("fromMonth", from.getMonthValue());
				request.setAttribute("fromYear", from.getYear());
			}
			
			if (to == LocalDateTime.MAX) {
				request.setAttribute("toDay", 1);
				request.setAttribute("toMonth", 1);
				request.setAttribute("toYear", 2025);
			} else {
				request.setAttribute("toDay", to.getDayOfMonth());
				request.setAttribute("toMonth", to.getMonthValue());
				request.setAttribute("toYear", to.getYear());
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private  int getFilter(String filter) {
		if (filter != null) System.out.println("from: " + filter);
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
			//e.printStackTrace();
			
		}
		return  tempFilter;
	}

}
