<%@page import="model.User"%>
<%@page import="model.Movie"%>
<%@page import="model.Role"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%Movie movie = (Movie) request.getAttribute("movie"); %>
 <%User loggedInUser = (User) request.getSession().getAttribute("loggedInUser"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Single Movie Preview</title>
</head>
<body>



	<table border="1" style=width:100%>
		<tr>
			<th>Name</th>
			<th>Duration</th>
			<th>Production year</th>
			<th>Genres</th>
			<th>Distributor</th>
			<th>Country of origin</th>
			<th>Directors</th>
			<th>Actors</th>
			<th>Description</th>
					
		</tr>
		
		<tr>
		 <td><%= movie.getName() %></td>
		 <td><%= movie.getDuration() %></td>
		 <td><%= movie.getProductionYear() %></td>
		 <td><%= movie.getGenresDisplay() %></td>
		 <td><%= movie.getDistributor() %></td>
		 <td><%= movie.getCountryOfOrigin() %></td>
		 <td><%= movie.getDirectorsDisplay() %></td>
		 <td><%= movie.getActorsDisplay() %></td>
		 <td><%= movie.getDescription() %></td>
		 
		</tr>
		
		
	</table>
	<%if (loggedInUser.getRole() == Role.ADMIN){ %>
		<form action="UsersManagementServlet" method="get">
			<input type=submit value="Users management" style=width:100%>
		</form>
		
		
	<%} %>
	<form action="LogoutServlet" method="get">
		<input type=submit value="Logout" style=width:100%>
	</form>
	
	
</body>
</html>