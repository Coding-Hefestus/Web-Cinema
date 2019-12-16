package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MovieDAO;
import model.Movie;


public class MovieServlet extends HttpServlet {
	
  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String nameF = request.getParameter("nameFilter"); 
		nameF = (nameF != null ? nameF : "");		
		final String titleFilter = new String(nameF);

		String fromDF = request.getParameter("fromDurationFilter"); //fromDF - fromDurationFilter
		final int fromDurationFilter = getFilter(fromDF);

		
		String toDF = request.getParameter("toDurationFilter"); //toDF - toDurationFilter
		final int toDurationFilter = getToFilter(toDF);
		

		String fromPF = request.getParameter("fromProductionFilter"); //fromDF - fromPublishingFilter
		final int fromProductionFilter = getFilter(fromPF);
		
		String toPF = request.getParameter("toProductionFilter"); //toPF - toPublishingFilter
		final int toProductionFilter = getToFilter(toPF);
		
		String descriptionF = request.getParameter("descriptionFilter"); 
		descriptionF = (descriptionF != null ? descriptionF : "");		
		final String descriptionFilter = new String(descriptionF);
		
		try {
			
			ArrayList<Movie> fm = (ArrayList<Movie>) MovieDAO.getAll();
			
			
			ArrayList<Movie> filteredMovies = (ArrayList<Movie>) MovieDAO.getAll().stream()
					.filter(Movie.nameFilter(titleFilter)						
							.and(Movie.durationFilter(fromDurationFilter, toDurationFilter))
							.and(Movie.productionFilter(fromProductionFilter, toProductionFilter))
							.and(Movie.descriptionFilter(descriptionFilter)))						
							.collect(Collectors.toList());
			
			request.setAttribute("nameFilter", titleFilter);
			
			request.setAttribute("toDurationFilter", toDurationFilter);
			request.setAttribute("fromDurationFilter", fromDurationFilter);
			
			request.setAttribute("fromProductionFilter", fromProductionFilter);
			request.setAttribute("toProductionFilter", toProductionFilter);
			
			request.setAttribute("descriptionFilter", descriptionFilter);

			request.setAttribute("filteredMovies", filteredMovies);
			request.getRequestDispatcher("./AllMovies.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			//e.printStackTrace();
			
		}
		return  tempFilter;
	}

}
