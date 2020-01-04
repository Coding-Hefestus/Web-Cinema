<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@page import="model.Role"%>
<%@page import="model.Projection"%>
<%@page import="utility.Utility"%>

<%User loggedInUser = (User) request.getSession().getAttribute("loggedInUser"); %>
<%ArrayList<Projection> projectionsForMovie = (ArrayList<Projection>) request.getAttribute("projections"); %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Choose projection for ticket</title>
</head>
<body>
	<form action="FindAvailableSeatsServlet" method="get" id="form1">
	<table border="1" style=width:100%>
		<tr>
			<th>Movie</th>
			<th>Period</th>
			<th>Projection type</th>
			<th>Hall</th>
			<th>Ticket price</th>
			<th>Seats available</th>
			<th>Buy ticket</th>
		
		<tr>

	
	
	
		
		<%for(Projection p : projectionsForMovie){ %>
			
			<tr>
			
				<td><a href="SingleMovieServlet?id=<%= p.getMovie().getId() %>"> <%= p.getMovie().getName() %></a></td>
				<td><a href="SingleProjectionServlet?id=<%= p.getId() %>"> <%= Utility.convertDateWithTimeToString(p.getPeriod().getStart()) + " " + Utility.convertDateWithTimeToString(p.getPeriod().getEnd())%></a></td>
				<td><%=p.getProjectionType().getName() %></td>
				<td><%=p.getHall().getName() %></td>
				<td><%=p.getTicketPrice() %></td>
				<td><%=  p.getHall().getCapacity() - p.getTicketsSold() %>  </td>
				<td><button name="projection" type="submit" style=width:100% value="<%= p.getId()%>">Buy ticket</button></td>

			
			</tr>
		
		<%} %>
	
	
	
	</table>
	</form>


</body>
</html>