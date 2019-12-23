package servlets;

import java.io.IOException;

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

import DAO.MovieDAO;
import model.Movie;
import model.User;


public class MovieServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		
		//filter params
		String nameF = request.getParameter("nameFilter"); 
		nameF = (nameF != null ? nameF : "");		
		final String titleFilter = new String(nameF);

		String fromDF = request.getParameter("fromDurationFilter"); //fromDF - fromDurationFilter
		final int fromDurationFilter = getFilter(fromDF);
		
		String toDF = request.getParameter("toDurationFilter"); //toDF - toDurationFilter
		final int toDurationFilter = getToFilter(toDF);
		

		String fromPF = request.getParameter("fromProductionFilter"); //fromDF - fromPublishingFilter
		final int fromProductionFilter = getFilter(fromPF);
		
		String toPF = request.getParameter("toProductionFilter"); //toPF - toProductionFilter
		final int toProductionFilter = getToFilter(toPF);
		
//		String descriptionF = request.getParameter("descriptionFilter"); 
//		descriptionF = (descriptionF != null ? descriptionF : "");		
//		final String descriptionFilter = new String(descriptionF);
		
//		String actorsF = request.getParameter("actorsFilter"); 
//		actorsF = (actorsF != null ? actorsF : "");		
//		final String actorsFilter = new String(actorsF);
		
		String genresF = request.getParameter("genresFilter"); 
		genresF = (genresF != null ? genresF : "");		
		final String genresFilter = new String(genresF);
		
		String distributorF = request.getParameter("distributorFilter"); 
		distributorF = (distributorF != null ? distributorF : "");		
		final String distributorFilter = new String(distributorF);
		
		String countryOfOriginF = request.getParameter("countryOfOriginFilter"); 
		countryOfOriginF = (countryOfOriginF != null ? countryOfOriginF : "");		
		final String countryOfOriginFilter = new String(countryOfOriginF);
		
		
		//sorter params
		
		List<Comparator<Movie>> comparators = new ArrayList<>();
		
		String nameSort = request.getParameter("byName");
		String durationSort = request.getParameter("byDuration");		
		String productionYearSort = request.getParameter("byProductionYear");		
		String distributorSort = request.getParameter("byDistributor");
		String countryOfOriginSort = request.getParameter("byCountryOfOrigin");
		String genresSort = request.getParameter("byGenres");
		
		if (nameSort != null) comparators.add(Movie.comparatorByName(nameSort));
		if (durationSort != null) comparators.add(Movie.comparatorByDuration(durationSort));
		if (productionYearSort != null) comparators.add(Movie.comparatorByProductionYear(productionYearSort));
		if (countryOfOriginSort != null) comparators.add(Movie.comparatorByCountryOfOrigin(countryOfOriginSort));
		if (distributorSort != null) comparators.add(Movie.comparatorByDistributor(distributorSort));
		if (genresSort != null) comparators.add(Movie.compartorByGenres(genresSort));
		
		try {

			ArrayList<Movie> filteredMovies = (ArrayList<Movie>) MovieDAO.getAll().stream()
					.filter(Movie.nameFilter(titleFilter)						
							.and(Movie.durationFilter(fromDurationFilter, toDurationFilter))
							.and(Movie.productionFilter(fromProductionFilter, toProductionFilter))
							.and(Movie.genresFilter(genresFilter))
							.and(Movie.distributorFilter(distributorFilter))
							.and(Movie.countryOfOriginFilter(countryOfOriginFilter)))
							.collect(Collectors.toList());
			//Sorting here
			if (comparators.size() != 0) Collections.sort(filteredMovies, ComparatorUtils.chainedComparator(comparators));
			
			request.setAttribute("nameFilter", titleFilter);
			
			request.setAttribute("toDurationFilter", toDurationFilter);
			request.setAttribute("fromDurationFilter", fromDurationFilter);
			
			request.setAttribute("fromProductionFilter", fromProductionFilter);
			request.setAttribute("toProductionFilter", toProductionFilter);
			
			
			//request.setAttribute("descriptionFilter", descriptionFilter);
			
			request.setAttribute("genresFilter", genresFilter);
			
			request.setAttribute("distributorFilter", distributorFilter);
			request.setAttribute("countryOfOriginFilter", countryOfOriginFilter);

			request.setAttribute("filteredMovies", filteredMovies);
			request.getRequestDispatcher("./AllMovies.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		
		
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
			//e.printStackTrace();
			
		}
		return  tempFilter;
	}

}
