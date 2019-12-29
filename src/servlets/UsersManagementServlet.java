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

import DAO.UserDAO;
import model.Movie;
import model.User;


public class UsersManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Movie m = (Movie)request.getSession().getAttribute(String.valueOf(loggedInUser.getId()));
		if (m != null) request.getSession().removeAttribute(String.valueOf(loggedInUser.getId()));
		
		String usernameF = request.getParameter("usernameFilter"); 
		usernameF = (usernameF != null ? usernameF : "");		
		final String usernameFilter = new String(usernameF);
		
		String roleF =  request.getParameter("roleFilter");
		roleF = (roleF != null ? roleF : "");		
		final String roleFilter = new String(roleF);
		
		//from 
		String dayStringFrom = request.getParameter("fromDay");
		String monthStringFrom = request.getParameter("fromMonth");
		String yearStringFrom = request.getParameter("fromYear");
		
		
		//to
		String dayStringTo = request.getParameter("toDay");
		String monthStringTo = request.getParameter("toMonth");
		String yearStringTo = request.getParameter("toYear");
		
		LocalDateTime from = LocalDateTime.MIN;
		LocalDateTime to = LocalDateTime.MAX;
		
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
		
		
		
		//to
		List<Comparator<User>> comparators = new ArrayList<>();
		
		String usernameSort = request.getParameter("byUsername");
		String roleSort = request.getParameter("byRole");
		String registrationSort = request.getParameter("byDate");
		
		
			
		
		if (usernameSort != null) comparators.add(User.comparatorByUsername(usernameSort));
		if (roleSort != null) comparators.add(User.comparatorByRole(roleSort));
		if (registrationSort != null) comparators.add(User.comparatorByRegistrationDate(registrationSort));
		
		try {
		
			ArrayList<User> filteredUsers = (ArrayList<User>)  UserDAO.getAll().stream()
							.filter(User.usernameFilter(usernameFilter)
									.and(User.roleFilter(roleFilter))
									.and(User.registrationDateFilter(from, to)))
									.collect(Collectors.toList());
			
			
			if (comparators.size() != 0) Collections.sort(filteredUsers, ComparatorUtils.chainedComparator(comparators));
			
			
			request.setAttribute("filteredUsers", filteredUsers);
			request.setAttribute("usernameFilter", usernameFilter);
			request.setAttribute("roleFilter", roleFilter);
			
			request.getRequestDispatcher("./AllUsers.jsp").forward(request, response);
			
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		
		//prvi put stizemo na servlet
		//String username = request.getParameter("username");
		//String registrationDate = request.getParameter("registrationDate");
		//String role = request.getParameter("role");
		
		
		
	}

}
