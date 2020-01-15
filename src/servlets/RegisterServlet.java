package servlets;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;
import model.Role;
import model.User;


public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) response.sendRedirect("./Login.html");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
	
		
		try {
			if (!isAlphanumeric(username) || !isAlphanumeric(password)) { response.sendRedirect("./Register.html");
			}else if (UserDAO.alreadyExists(username, password)) { response.sendRedirect("./Register.html");	
			}else {
				
				User newUser = new User();
				newUser.setActive(true);
				newUser.setUsername(username);
				newUser.setPassword(password);
				newUser.setRegistrationDate(LocalDateTime.now());
				newUser.setRole(Role.USER);
				
				if (UserDAO.add(newUser)) {
					response.sendRedirect("./Login.html");
				} else response.sendRedirect("./Register.html");

			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
	
	private boolean isAlphanumeric(String str) {
//		char c;
//		for (int i=0; i<str.length(); i++) {
//            c = str.charAt(i);
//            if ((!Character.isDigit(c) && !Character.isLetter(c)) || c.equals(" ")) return false;
//        }
//        return true;
		if (str.equals("")) return false;
		else return !str.chars()
                    .mapToObj(c -> (char) c)
                    .anyMatch(c -> ((!Character.isDigit(c) && !Character.isLetter(c)) || Character.isWhitespace(c) ));
	}

}
