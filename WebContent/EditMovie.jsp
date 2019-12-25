<%@page import="model.Movie"%>
<%@page import="model.Genre"%>
<%@page import="model.Actor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Director"%>
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
<title>Edit movie page</title>
<script type = "text/javascript">
         
function removeDirector(){
    var directorId = document.getElementById('directorsCombobox').value;
    window.location = "http://localhost:8080/Cinema/EditDirectorServlet?director=" + encodeURIComponent(directorId);   
}
        
</script>

</head>
<body>
	
	
	<form action="EditMovieServlet" method="post">
		<table>
			<tr><td align="left">Name:</td><td><input  type="text" name="movieName" value="<%=movie.getName()%>"></td></tr>
			<tr><td align="left">Duration:</td><td><input type="text" name="movieDuration" value="<%= movie.getDuration() < 0 ?  ""  : movie.getDuration()%>"/></td></tr>
			<tr><td align="left">Production year:</td><td><input type="text" name="movieProductionYear" value="<%= movie.getProductionYear() < 1950 ?  "" : movie.getProductionYear() %>"/></td></tr>
 			<tr><td align="left">Description:</td><td><input type="text" name="movieDescription" value="<%= movie.getDescription()%>"/></td></tr> 
		    <tr><td align="left">Country of origin:</td><td><input type="text" name="countryOfOrigin" value="<%= movie.getCountryOfOrigin()%>"/></td></tr>
			<tr><td align="left">Distributor:</td><td><input type="text" name="distributor" value="<%= movie.getDistributor()%>"/></td></tr>
	 		<tr><td align="left">Directors:<select id="directorsCombobox"> <% for(Director d : movie.getDirectors()){ %> <option value="<%=d.getId() %>"> <%=d.getName() %> </option> <%} %> </select> <button onclick="removeDirector()">Remove director</button></td></tr>
			<tr><td align="left">Actors:<select> <% for(Actor a : movie.getActors()){ %> <option value="<%=a.getId() %>"> <%=a.getName() %> </option> <%} %> </select> </td></tr>
			<tr><td align="left">Genres:<select> <% for(Genre g : movie.getGenres()){ %> <option value="<%=g.getId() %>"> <%=g.getName() %> </option> <%} %> </select> </td></tr>
			
			<tr>
				<td>
					<input type="submit" value="Edit">
				</td>
			</tr>
			
		</table>		
	</form>
	
	<form action="RemoveDirectorServlet" method="post">
						<select name="director">
							<% for(Director d : movie.getDirectors()){ %>
				
						
								<option value="<%=d.getId() %>"> <%=d.getName() %> </option>
			
				
							<%} %>
						</select>
					<input type="submit" value="Remove director">
	</form></br>
	<form action="AddDirectorServlet" method="post">
						<select name="director">
							<% for(Director d : allDirectors){ %>
				
						
								<option value="<%=d.getId() %>"> <%=d.getName() %> </option>
			
				
							<%} %>
						</select>
					<input type="hidden" name="origin" value="EditMovie">
					<input type="submit" value="Add director">
	</form></br>
	
	
	
	
	
	<form action="RemoveActorServlet" method="post">
						<select name="actor">
							<% for(Actor a : movie.getActors()){ %>
				
						
								<option value="<%=a.getId() %>"> <%=a.getName() %> </option>
			
				
							<%} %>
						</select>
					<input type="submit" value="Remove actor">
	</form></br>
	<form action="AddActorServlet" method="post">
						<select name="actor">
							<% for(Actor a : allActors){ %>
				
						
								<option value="<%=a.getId() %>"> <%=a.getName() %> </option>
			
				
							<%} %>
						</select>
					<input type="hidden" name="origin" value="EditMovie">
					<input type="submit" value="Add actor">
	</form></br>
	
	
	
	
	
	
	
	<form action="RemoveGenreServlet" method="post">
						<select name="genre">
							<% for(Genre g : movie.getGenres()){ %>
				
						
								<option value="<%=g.getId() %>"> <%=g.getName() %> </option>
			
				
							<%} %>
						</select>
					<input type="submit" value="Remove genre">
	</form></br>
	<form action="AddGenreServlet" method="post">
						<select name="genre">
							<% for(Genre g : allGenres){ %>
				
						
								<option value="<%=g.getId() %>"> <%=g.getName() %> </option>
			
				
							<%} %>
						</select>
					<input type="hidden" name="origin" value="EditMovie">
					<input type="submit" value="Add genre">
	</form></br>
	
	
	
	
	
	
	
	
	
	
	<!-- <a href="./MovieServlet">Back to all movies</a> -->
	
</body>
</html>