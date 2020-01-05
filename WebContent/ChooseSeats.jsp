<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@page import="model.Role"%>
<%@page import="model.Projection"%>
<%@page import="utility.Utility"%>
<%@page import="model.Seat" %>

<%ArrayList<Seat> availableSeatsForProjection = (ArrayList<Seat>) request.getAttribute("availableSeatsForProjection"); %>
<%User loggedInUser = (User) request.getSession().getAttribute("loggedInUser"); %>
<%Projection p = (Projection) request.getAttribute("projection"); %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Choose seats page</title>
</head>
<body>

		<form action="ProceedToConfirmPurchaseServlet" method="get" id="form1">
		<input type="hidden" name="projection" value="<%=p.getId()%>">
		<table border="1" style=width:100%>
		<tr>
			<th>Movie</th>
			<th>Period</th>
			<th>Projection type</th>
			<th>Hall</th>
			<th>Ticket price</th>
			<th>Seats available</th>
			<th>Buy ticket(s)</th>
		
		<tr>
		
		
			
			<tr>
			
				<td><a href="SingleMovieServlet?id=<%= p.getMovie().getId() %>"> <%= p.getMovie().getName() %></a></td>
				<td><a href="SingleProjectionServlet?id=<%= p.getId() %>"> <%= Utility.convertDateWithTimeToString(p.getPeriod().getStart()) + " " + Utility.convertDateWithTimeToString(p.getPeriod().getEnd())%></a></td>
				<td><%=p.getProjectionType().getName() %></td>
				<td><%=p.getHall().getName() %></td>
				<td><%=p.getTicketPrice() %></td>
				<td>
					<select multiple form="form1" name="seats">
			 			<option value="" selected disabled>Choose seat(s) here</option>
							<% for(Seat s : availableSeatsForProjection){ %>
								<option value="<%=s.getId() %>"> <%=s.getNumber()%> </option>
							<%} %>				
					</select>
				
				</td>
<%-- 				<td><button name="tickets" type="submit" style=width:100% value="<%= p.getId()%>">Buy ticket</button></td>
 --%>					
 					<td><input type="submit" value="Proceed to confirmation"></td>
			
			</tr>
		
	
		
		</table>
		</form>

	

</body>
</html>