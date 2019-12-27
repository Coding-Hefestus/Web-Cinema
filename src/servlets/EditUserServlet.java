package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;
import model.Role;
import model.User;



public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		
		
		String id = request.getParameter("id");
		String newRole = request.getParameter("role");
		String newPassword = request.getParameter("newPassword");
		
		
		
		int userId = Integer.valueOf(id);
		
		try {
			User u = UserDAO.getById(userId);
			
			
			if (!newRole.equals(u.getRole().toString())) {
				u.setRole(Role.valueOf(newRole));
				UserDAO.update(u);
			}
			
			if (!newPassword.equals("")) {
				if (!UserDAO.alreadyExists(u.getUsername(), newPassword)) {
					u.setPassword(newPassword);
					UserDAO.update(u);
					response.sendRedirect("./UsersManagementServlet");
				}  else {
					request.setAttribute("user", u); 
					request.getRequestDispatcher("./SingleUser.jsp").forward(request, response);
				}
			} else {
				response.sendRedirect("./UsersManagementServlet");
			}
			
		
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
	}

}