package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.ComparatorUtils;

import DAO.ReportDAO;
import model.MapCollector;
import model.Report;
import model.User;



public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		else {
			
			try {
				String from = request.getParameter("fromDate");
				String to = request.getParameter("toDate");
				
				String fromTime = request.getParameter("fromTime");
				String toTime = request.getParameter("toTime");
				
				LocalDateTime fromDate = getLocalDateTime(from + " " + fromTime);
				LocalDateTime toDate =  getLocalDateTime(to + " " + toTime);
				
				if (fromDate.isBefore(toDate)) {
					
					ArrayList<Report> reports = ReportDAO.getReports(fromDate, toDate);
					request.setAttribute("from", from);
					request.setAttribute("to", to);
					request.setAttribute("fromTime", fromTime);
					request.setAttribute("toTime", toTime);
									
					setStatistics(reports, request);
	
					request.setAttribute("sortedReports", reports);
					//request.setAttribute("totalProjections", getTotalProjections);
					
					request.getRequestDispatcher("./Report.jsp").forward(request, response);
				} else response.sendRedirect("./Report.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("./Report.jsp");
				
			}
			//u formi mm-dd-yyy ukucao sam 31 dec dobio ovo dolee
			//izgled: 2018-12-31 yyyy-mm-dd
			
		} //od elsa
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		else {
			try {
				
				String from = request.getParameter("fromDate");
				String to = request.getParameter("toDate");
			
				
				String fromTime = request.getParameter("fromTime");
				String toTime = request.getParameter("toTime");
				
				LocalDateTime fromDate = getLocalDateTime(from + " " + fromTime);
				LocalDateTime toDate =  getLocalDateTime(to + " " + toTime);
				
				String movieSort = request.getParameter("byMovie");
				String projectionsSort = request.getParameter("byProjections");		
				String ticketsSort = request.getParameter("byTickets");	
				String incomeSort = request.getParameter("byIncome");	
				
				List<Comparator<Report>> comparators = new ArrayList<>();
				
				if (movieSort != null)  comparators.add(Report.comparatorByMovie(movieSort));
				if (projectionsSort != null) comparators.add(Report.comparatorByProjections(projectionsSort));
				if (ticketsSort != null) comparators.add(Report.comparatorByTickets(ticketsSort));
				if (incomeSort != null) comparators.add(Report.comparatorByIncome(incomeSort));
				
				ArrayList<Report> reports = ReportDAO.getReports(fromDate, toDate);
				
				if (comparators.size() != 0) Collections.sort(reports, ComparatorUtils.chainedComparator(comparators));
				
				setStatistics(reports, request);
				request.setAttribute("from", from);
				request.setAttribute("to", to);
				request.setAttribute("fromTime", fromTime);
				request.setAttribute("toTime", toTime);

				request.setAttribute("sortedReports", reports);
				
				request.getRequestDispatcher("./Report.jsp").forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private LocalDateTime getLocalDateTime(String dateString) {

		return  LocalDateTime.parse(dateString, formatter);
	}
	
	
	//@SuppressWarnings("serial")
	private  HashMap<String, Double> getTotals(ArrayList<Report> reports){
		
		return (HashMap<String, Double>) reports.stream().collect(new MapCollector());


	}
	
	
	private void setStatistics(ArrayList<Report> reports, HttpServletRequest request) {
		HashMap<String, Double> map = getTotals(reports);
		request.setAttribute("projections", map.get("projections"));
		request.setAttribute("tickets", map.get("tickets"));
		request.setAttribute("income", map.get("income"));
		
	}
	
}
