<%@page import="model.Movie"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%Movie editMovie = (Movie) request.getAttribute("editMovie"); %>
<%request.setAttribute("editMovie", editMovie); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit movie page</title>
</head>
<body>
	
	
	<form action="EditMovieServlet" method="get">
		<table>
			<tr><td align="left">Name:</td><td><input  type="text" name="movieName" value="<%= editMovie.getName() %>"/></td></tr>
			<tr><td align="left">Duration:</td><td><input type="text" name="movieDuration" value="<%= editMovie.getDuration() %>"/></td></tr>
			<tr><td align="left">Production year:</td><td><input type="text" name="movieProductionYear" value="<%= editMovie.getProductionYear() %>"/></td></tr>
			<tr><td align="left">Description:</td><td><input type="text" name="movieDescription" value="<%= editMovie.getDescription() %>"/></td></tr>

			<tr>
			
				<td>
					
					<input type="submit" value="Edit">
				
				</td>
			
			</tr>
		</table>
		
		
	</form>
	
	
	
	<a href="./MovieServlet">Back to all movies</a>
	
</body>
</html>