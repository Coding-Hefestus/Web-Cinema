<%@page import="model.Movie"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
 <%ArrayList<Movie> filteredMovies = (ArrayList<Movie>) request.getAttribute("filteredMovies"); %>
 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Movies</title>
</head>
<body>
	<h3>Available movies</h3>
	<table border="1">
		<tr>
			<th>Name</th>
			<th>Duration</th>
			<th>Production year</th>
			<th>Description</th>		
		</tr>
		
		<!-- filteri u formi koja je u tabeli -->
		<form action="MovieServlet" method="get">
			<tr>
				<td align="center"><input type="text" name="nameFilter" value="<% request.getAttribute("nameFilter"); %>"></td>
				<td align="center">
					from:&nbsp;<input type="text" name="fromDurationFilter" value="<%= request.getAttribute("fromDurationFilter")%>"><br/>
					to:&nbsp;<input type="text" name="toDurationFilter" value="<%= request.getAttribute("toDurationFilter")%>">
				</td>
				
				<td align="center">
					from:&nbsp;<input type="text" name="fromProductionFilter" value="<%= request.getAttribute("fromProductionFilter")%>"></br>
					to:&nbsp;<input type="text" name="toProductionFilter" value="<%= request.getAttribute("toProductionFilter")%>">
				</td>
				
				<td><input type="text" name="descriptionFilter" value="<%= request.getAttribute("descriptionFilter")%>"></td>
				
				
				<td align="center"><input type="submit" value="Filter"></td>	
			</tr>
			
			
		</form>
		
		<%for (Movie m : filteredMovies){ %>
			
			<tr>
				<td><a href="#?id=<%= m.getId() %>"> <%= m.getName() %></a></td>
				<td><%=m.getDuration() %></td>
				<td><%=m.getProductionYear() %></td>
				<td><%=m.getDescription() %></td>
			</tr>
			
		
		<%} %>
	
	</table>
</body>
</html>