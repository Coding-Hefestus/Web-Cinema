<%@page import="model.ProjectionType"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@page import="model.Hall"%>
<%@page import="model.Movie"%>
<%@page import="model.Projection"%>
<%ArrayList<Movie> allMovies = (ArrayList<Movie>) request.getAttribute("movies");%>
<%ArrayList<Hall> allHalls = (ArrayList<Hall>) request.getAttribute("halls");%>
<%String key = (String) request.getAttribute("key"); %>
<%Projection projection = (Projection) request.getSession().getAttribute(key); %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add new projection page</title>
</head>
<body>


	
	<form action="AddMovieServlet" method="get">
		<select name="movie">
			 <option value="" selected disabled>Choose movie here</option>
			<% for(Movie m : allMovies){ %>
					<option value="<%=m.getId() %>"> <%=m.getName()%> </option>
			<%} %>				
		</select>
			<input type="submit" value="Set movie">		
	</form>
	
	<form action="AddHallServlet" method="get">
		<select name="hall">
			 <option value="" selected disabled>Choose hall here</option>
			<% for(Hall h : allHalls){ %>
					<option value="<%=h.getId() %>"> <%=h.getName()%> </option>
			<%} %>				
		</select>
		<input type="submit" value="Set hall">
	</form>
	
	
	<form action="AddProjectionTypeServlet" method="get">
		<%if(projection.getHall() != null){ %>
			<select name="projectionTypeId">
				 <option value="" selected disabled>Choose dimension here</option>
			<% for(ProjectionType pt : projection.getHall().getDimensions()){ %>
					<option value="<%=pt.getId() %>"> <%=pt.getName()%> </option>
			<%} %>				
		</select>
		<input type="submit" value="Set dimension">	
		<%} %>
	
	</form>
	
	

	</br>	
	</br>	
	</br>	
	</br>	
	</br>	







	<form action="AddNewProjectionServlet" method="get">
			Movie:<input readonly type="text"  value="<%=projection.getMovie() == null ? "" : projection.getMovie().getName()%>"></br>	
			Hall:<input readonly type="text"  value="<%=projection.getHall() == null ? "" : projection.getHall().getName()%>"></br>
			Projection type:<input readonly type="text"  value="<%=projection.getProjectionType() == null ? "" : projection.getProjectionType().getName()%>"></br>	
			Start date:<input name="date" type="date" <%if (request.getParameter("date") != null){%> value="<%= request.getParameter("date")%>"   <%}%>> 
			<input name="time" type="time" <%if (request.getParameter("time") != null){%> value="<%= request.getParameter("time")%>"   <%}%>></br>
			Ticket:<input type="text" name="ticket" <%if (projection.getTicketPrice() > 0){%> value="<%=projection.getTicketPrice() %>" <%} %>></br>
		<input type="submit" value="Add new projection">	
	</form>
	
	
	<form action="UsersManagementServlet" method="get">
		<input type=submit value="Users management" style=width:100%>
	</form>
	
	<form action="MyProfileServlet" method="get">
			<input type=submit value="My profile" style=width:100%>
	</form>
	
	<form action="LogoutServlet" method="get">
					 	<input type=submit value="Logout" style=width:100%>
	</form>
	

</body>
</html>