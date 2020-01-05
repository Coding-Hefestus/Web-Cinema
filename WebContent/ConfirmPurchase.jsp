<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@page import="model.Role"%>
<%@page import="model.Projection"%>
<%@page import="utility.Utility"%>
<%@page import="model.Seat" %>
<%double total = (Double) request.getAttribute("totalPrice"); %>
<%String seatsIds = (String) request.getAttribute("seatsIds"); %>
<%User loggedInUser = (User) request.getSession().getAttribute("loggedInUser"); %>
<%Projection p = (Projection) request.getAttribute("projection"); %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirmation of purchase page</title>
</head>
<body>

	<form action="ConfirmPurchaseServlet" method="get">
	<input type="hidden" name="projection" value="<%=p.getId()%>">
	<input type="hidden" name="seatsIds" value="<%=seatsIds%>">
	<table border="1" style=width:100%>
		<tr>
			<th>Movie</th>
			<th>Period</th>
			<th>Projection type</th>
			<th>Hall</th>
			<th>Total price</th>
			<th>Buy ticket(s)</th>
		
		<tr>
		
		
			
			<tr>
			
				<td><a href="SingleMovieServlet?id=<%= p.getMovie().getId() %>"> <%= p.getMovie().getName() %></a></td>
				<td><a href="SingleProjectionServlet?id=<%= p.getId() %>"> <%= Utility.convertDateWithTimeToString(p.getPeriod().getStart()) + " " + Utility.convertDateWithTimeToString(p.getPeriod().getEnd())%></a></td>
				<td><%=p.getProjectionType().getName() %></td>
				<td><%=p.getHall().getName() %></td>
				<td><%=total %></td>
				
			
 				<td><input type="submit" value="Confirm purchase"></td>
			
			</tr>
		
	
		
		</table>
		</form>

</body>
</html>