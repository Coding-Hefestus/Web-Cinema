
<%@page import="java.time.LocalDateTime"%>
<%@page import="utility.Utility"%>
<%@page import="model.User"%>
<%@page import="model.Ticket"%>
<%@page import="model.Role"%>
<%User loggedInUser = (User) request.getSession().getAttribute("loggedInUser"); %>
<%Ticket ticket = (Ticket) request.getAttribute("ticket"); %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Single Ticket page</title>
</head>
<body>

	<h3>Ticket</h3>
		<table border="1" style=width:100%>
		<tr>
			<th>Movie</th>
			<th>Period</th>
			<th>Projection type</th>
			<th>Hall</th>
			<th>Seat number</th>
			<th>Ticket price</th>
			<%if (loggedInUser.getRole() == Role.ADMIN){ %>
			<th>User</th>
			<%} %>
		
		<tr>
		
		<tr>
			<td> <a href="SingleMovieServlet?id=<%=ticket.getProjection().getMovie().getId()%>"> <%=ticket.getProjection().getMovie().getName() %></a></td>
		
			<td> <a href="SingleProjectionServlet?id=<%=ticket.getProjection().getId() %>"><%=Utility.convertDateWithTimeToString(ticket.getProjection().getPeriod().getStart()).concat(" ").concat(Utility.convertDateWithTimeToString(ticket.getProjection().getPeriod().getEnd())) %> </a></td>
			<td><%=ticket.getProjection().getProjectionType().getName() %></td>
			<td><%=ticket.getProjection().getHall().getName() %></td>
			<td><%=ticket.getSeat().getNumber()%></td>		 
			<td><%=ticket.getProjection().getTicketPrice() %></td>
			<%if (loggedInUser.getRole() == Role.ADMIN){ %>
			<td><a href="SingleUserServlet?id=<%=ticket.getUser().getId()%>"><%=ticket.getUser().getUsername() %></a></td>
			<%} %>
		</tr>
		</table>
		
		<%if (loggedInUser.getRole() == Role.ADMIN && ticket.getProjection().getPeriod().getStart().isAfter(LocalDateTime.now())){ %>
			<form action="DeleteTicketServlet" method="get">
				<button name="delete" style=width:100% type="submit" value="<%= ticket.getId()%>">Delete ticket</button>			
			</form>
		<%} %>
		
		
		<form action="MyProfileServlet" method="get">
			<input type=submit value="My profile" style=width:100%>
		</form>
	



</body>
</html>