<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@page import="model.Role"%>
<%@page import="model.Projection"%>
<%@page import="utility.Utility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%User loggedInUser = (User) request.getSession().getAttribute("loggedInUser"); %>
<%ArrayList<Projection> filteredProjections = (ArrayList<Projection>) request.getAttribute("filteredProjections"); %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Main application page</title>
</head>
<body>

	<a href="./MovieServlet">Show all movies</a>
	
	
	<a href="./LogoutServlet">Logout</a>
	
	<a href="./MyProfileServlet">My profile</a>
	
	<%if(loggedInUser.getRole() == Role.ADMIN){ %>
		
		<a href="./UsersManagementServlet">Users management</a>
		
	<%} %>
	
	
	<table border="1" style=width:100%>
		<tr>
			<th>Movie</th>
			<th>Projections</th>
			<th>Projection type</th>
			<th>Hall</th>
			<th>Ticket price</th>
			
					
		</tr>
		
		<form action="MainPageAppServlet" method="get"> 
		<tr>
			<td align="center">
					
				 		
				 		<fieldset> 
							<legend>Filter</legend>
								<input type="text" name="movieFilter" value="<%=request.getAttribute("movieFilter") %>">
				 		</fieldset> 
				 		
    					 <fieldset>
        						<legend>Sorting order</legend>
        						
        						
        						<input type="radio" id="movieAsc" name="byMovie" value="asc">
    							<label for="movieAsc">Ascending</label><br>

    							<input type="radio" id="movieDsc" name="byMovie" value="dsc">
    							<label for="movieDsc">Descending</label>

        					
    					</fieldset> 
					
				
			</td>
			
			<td align="center">
					<fieldset>
						<legend>Filter</legend>
						<%--  --%>
							<!--  -->
							from:&nbsp;<input name="fromDate" type="date" value="<%=request.getAttribute("fromDate")%>" > 
							<input name="fromTime" type="time" value="<%=request.getAttribute("fromTime")%>" ></br>
							
							
							to:&nbsp;<input name="toDate" type="date" value="<%=request.getAttribute("toDate")%>" > 
							<input name="toTime" type="time" value="<%=request.getAttribute("toTime")%>" ></br>
										
					</fieldset>
					
					<fieldset>
						<legend>Sorting</legend>
						
        					<input type="radio" id="dateAsc" name="byDate" value="asc">
    						<label for="dateAsc">Ascending</label><br>

    						<input type="radio" id="dateDsc" name="byDate" value="dsc">
    						<label for="dateDsc">Descending</label>
					
					</fieldset>
				</td>
				
				<!-- Projection Type -->
				<td align="center">
				 	
    					 <fieldset>
        						<legend>Filter</legend>
        						
        						<input type="radio" id="dimension2D" <%if(request.getAttribute("dimensionFilter").equals("IID")){ %> checked <%} %> name="dimensionFilter" value="2D">
    							<label for="dimension2D">2D</label><br>

    							<input type="radio" id="dimension3D" <%if(request.getAttribute("dimensionFilter").equals("IIID")){ %> checked <%} %> name="dimensionFilter" value="3D">
    							<label for="dimension3D">3D</label>
    							
    							<input type="radio" id="dimension4D" <%if(request.getAttribute("dimensionFilter").equals("IVD")){ %> checked <%} %> name="dimensionFilter" value="4D">
    							<label for="dimension4D">4D</label>

    					</fieldset> 
    					
    					<fieldset>
        						<legend>Sorting</legend>
        						
        						<input type="radio" id="dimensionAsc" name="byDimension" value="asc">
    							<label for="dimensionAsc">Ascending</label><br>

    							<input type="radio" id="dimensionAsc" name="byDimension" value="dsc">
    							<label for="dimensionAsc">Descending</label>

    					</fieldset> 
				</td>
				
				<!-- Halls -->
				<td align="center">
				 	
    					 <fieldset>
        						<legend>Filter</legend>
        						
        						<input type="radio" id="hallWhite" <%if (request.getAttribute("hallFilter").equals("White Hall")){ %> checked <%} %> name="hallFilter" value="White Hall">
    							<label for="hallWhite">White Hall</label><br>

    							<input type="radio" id="hallBlack" <%if (request.getAttribute("hallFilter").equals("Black Hall")){ %> checked <%} %> name="hallFilter" value="Black Hall">
    							<label for="hallBlack">Black Hall</label><br>
    							
    							<input type="radio" id="hallOrange" <%if (request.getAttribute("hallFilter").equals("Orange Hall")){ %> checked <%} %> name="hallFilter" value="Orange Hall">
    							<label for="hallOrange">Orange Hall</label><br>
    							
    							<input type="radio" id="hallBlue" <%if (request.getAttribute("hallFilter").equals("Blue Hall")){ %> checked <%} %> name="hallFilter" value="Blue Hall">
    							<label for="hallBlue">Blue Hall</label><br>

    					</fieldset> 
    					
    					<fieldset>
        						<legend>Sorting</legend>
        						
        						<input type="radio" id="hallAsc" name="byHall" value="asc">
    							<label for="hallAsc">Ascending</label><br>

    							<input type="radio" id="hallAsc" name="byHall" value="dsc">
    							<label for="hallAsc">Descending</label>

    					</fieldset> 
				</td>
		
				<!-- Ticket price -->
				<td align="center">
				<fieldset>
        					<legend>Filtering</legend>
								from:&nbsp;<input type="text" name="fromPriceFilter" value="<%= request.getAttribute("fromPriceFilter")%>"></br>
								to:&nbsp;<input type="text" name="toPriceFilter" value="<%= request.getAttribute("toPriceFilter")%>">
				</fieldset> 
					
				<fieldset>
        			
        			<legend>Sorting order</legend>
        						
        						
        			<input type="radio" id="priceAsc" name="byPrice" value="asc">
    				<label for="priceAsc">Ascending</label><br>

    				<input type="radio" id="priceDsc" name="byPrice" value="dsc">
    				<label for="priceDsc">Descending</label>

        					
    			</fieldset> 
				
				
				</td>
		
				<td align="center"><input type="submit" value="Filter"></td>
				
				<td align="center"><input type="submit" value="Sort"></td>
		
		</tr>
		
		
	  	</form>
		
		<%for (Projection p : filteredProjections){ %>
			<tr>
					<td><a href="SingleMovieServlet?id=<%= p.getMovie().getId() %>"> <%= p.getMovie().getName() %></a></td>
					<td><a href="SingleProjectionServlet?id=<%= p.getId() %>"> <%= Utility.convertDateWithTimeToString(p.getPeriod().getStart()) %></a></td>
					<td><%=p.getProjectionType().getName() %></td>
					<td><%=p.getHall().getName() %></td>
					<td><%=p.getTicketPrice() %></td>
					
				 <%-- <%if (loggedInUser.getRole() == Role.ADMIN){ %>
						<td>
						<form action="" method="post">
  					
  							<button name="delete" type="submit" value="<%= p.getId()%>">Delete projection</button>
  							
						</form>
					</td>  
					<%} %>  --%>
					
					 
				</tr>
		
		<%} %>
		
		</table>
		<%if (loggedInUser.getRole() == Role.ADMIN){ %>
			<form action="" method="get">
				<input type=button value="Add new projection" style=width:100%>
			</form>
			
			<form action="AddNewMovieServlet" method="get">
				<input type=button value="Add new movie" style=width:100%>
			</form>
		<%}%>
	
		<form action="MainPageAppServlet" method="get">
			<input type=submit value="Refresh all projections" style=width:100%>
		</form>
		
		
	
</body>
</html>