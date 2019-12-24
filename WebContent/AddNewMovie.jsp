<%@page import="model.Genre"%>
<%@page import="model.Actor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Director"%>
<%@page import="model.User"%>
<%@page import="model.Movie"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%String key = (String) request.getAttribute("key"); %>
<%Movie movie = (Movie) request.getSession().getAttribute(key); %>
<%ArrayList<Director> allDirectors = (ArrayList<Director>) request.getAttribute("directors"); %>
<%ArrayList<Actor> allActors = (ArrayList<Actor>) request.getAttribute("actors"); %>
<%ArrayList<Genre> allGenres = (ArrayList<Genre>) request.getAttribute("genres"); %>





<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add new Movie</title>
</head>
<body>
	
	<form action="AddNewMovieServlet" method="post">
		<table>
		
			<tr><td align="left">Name:</td><td><input  type="text" name="movieName"/></td></tr>
			<tr><td align="left">Duration:</td><td><input type="text" name="movieDuration" value="<%= movie.getDuration() < 0 ?  ""  : movie.getDuration()%>"/></td></tr>
			<tr><td align="left">Production year:</td><td><input type="text" name="movieProductionYear" value="<%= movie.getProductionYear() < 1950 ?  "" : movie.getDuration() %>"/></td></tr>
 			<tr><td align="left">Description:</td><td><input type="text" name="movieDescription"/></td></tr> 
		    <tr><td align="left">Country of origin:</td><td><input type="text" name="countryOfOrigin"/></td></tr>
			<tr><td align="left">Distributor:</td><td><input type="text" name="distributor"/></td></tr>
			
		
			<tr>
				<td align="left">Directors:<select> <% for(Director d : movie.getDirectors()){ %> <option value="<%=d.getId() %>"> <%=d.getName() %> </option> <%} %> </select> </td>
				<td>

				</td>
			</tr>
			
				
			<tr>
			
				<td>
					<!-- <input type="submit" value="Add new movie"> -->
				</td>
			
			</tr>
		</table>

	<!-- </form> -->
	
	<form action="AddDirectorServlet" method="post">
						<select name="director">
							<% for(Director d : allDirectors){ %>
				
						
								<option value="<%=d.getId() %>"> <%=d.getName() %> </option>
			
				
							<%} %>
						</select>
					<input type="submit" value="Add director">
	</form></br>
	<form action="AddActorServlet" method="post">
						<select name="actor">
							<% for(Actor a : allActors){ %>
				
						
								<option value="<%=a.getId() %>"> <%=a.getName() %> </option>
			
				
							<%} %>
						</select>
					<input type="submit" value="Add actor">
	</form></br>
	<form action="AddGenreServlet" method="post">
						<select name="actor">
							<% for(Genre g : allGenres){ %>
				
						
								<option value="<%=g.getId() %>"> <%=g.getName() %> </option>
			
				
							<%} %>
						</select>
					<input type="submit" value="Add genre">
	</form></br>
	
	<input type="submit" value="Add new movie">
	</form>

</body>
</html>